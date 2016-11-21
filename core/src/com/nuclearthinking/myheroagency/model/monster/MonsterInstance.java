package com.nuclearthinking.myheroagency.model.monster;

import box2dLight.PointLight;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.manager.GameWorldManager;
import com.nuclearthinking.myheroagency.controller.manager.JsonToObject;
import com.nuclearthinking.myheroagency.controller.systems.MonsterSystem;
import com.nuclearthinking.myheroagency.model.AnimationState;
import com.nuclearthinking.myheroagency.model.components.*;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

import java.util.ArrayList;

/**
 * Created by mkuksin on 21.11.2016.
 */
public class MonsterInstance {

    private static final ArrayList<Monster> monster = Asset.getInstance().get(Constants.MONSTER_JSON, JsonToObject.class).getMonsterParser().getBaseMonster();
    private @Getter final ArrayList<Entity> monsterList = new ArrayList<Entity>();
    private PooledEngine engine;
    private World world;

    private static MonsterInstance instance = new MonsterInstance();

    public static MonsterInstance getInstance() {
        return instance;
    }

    private MonsterInstance() {
    }

    public void initialize(@NonNull final PooledEngine engine, @NonNull final World world){
        this.engine = engine;
        this.world = world;
        createMonster();
    }

    private void createMonster(){
        for(val stat: monster){
            val entity = engine.createEntity();

            val animation = engine.createComponent(AnimationComponent.class);
            val state = engine.createComponent(StateComponent.class);
            val bodyCom = engine.createComponent(BodyComponent.class);
            val light = engine.createComponent(LightComponent.class);
            val monsterCom = engine.createComponent(MonsterComponent.class);

            monsterCom.setTemplate(stat);
            monsterCom.init();

            engine.getSystem(MonsterSystem.class).setActor(monsterCom);

            animation.getAnimations().put(AnimationState.IDLE.getValue(), GameWorldManager.IDLE);
            animation.getAnimations().put(AnimationState.RIGHT.getValue(), GameWorldManager.RIGHT);
            animation.getAnimations().put(AnimationState.LEFT.getValue(), GameWorldManager.LEFT);

            bodyCom.getBodyDef().type = BodyDef.BodyType.DynamicBody;
            bodyCom.setBody(world.createBody(bodyCom.getBodyDef()));
            val bodyPolygon = new PolygonShape();
            bodyPolygon.setAsBox(10,10);
            bodyCom.getFixtureDef().shape = bodyPolygon;
            bodyCom.getFixtureDef().friction = 0.3f;
            bodyCom.getFixtureDef().filter.categoryBits = Constants.BIT_MONSTER;
            bodyCom.getFixtureDef().filter.maskBits = Constants.BIT_PLAYER;
            bodyCom.getBody().createFixture(bodyCom.getFixtureDef()).setUserData("MONSTER");
            bodyPolygon.dispose();

            bodyCom.getBody().setFixedRotation(true);

            state.set(AnimationState.IDLE.getValue());

            light.setPlayerLight(new PointLight(LightComponent.getRayHandler(), 50));
            light.getPlayerLight().setDistance(50);
            light.getPlayerLight().setColor(LightComponent.getLightOn());
            light.setTarget(entity);

            entity.add(animation);
            entity.add(state);
            entity.add(light);
            entity.add(monsterCom);
            entity.add(bodyCom);
            entity.add(new MovementComponent());
            entity.add(new TextureComponent());

            engine.addEntity(entity);

            monsterList.add(entity);
        }
    }
}
