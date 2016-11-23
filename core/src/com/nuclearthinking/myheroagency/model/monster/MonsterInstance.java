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
import com.nuclearthinking.myheroagency.model.AnimationState;
import com.nuclearthinking.myheroagency.model.components.*;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;
import lombok.val;

import java.util.ArrayList;

/**
 * Created by mkuksin on 21.11.2016.
 */
public class MonsterInstance {

    private static MonsterInstance instance = new MonsterInstance();

    private final ArrayList<Monster> monster = Asset.getInstance().get(Constants.MONSTER_JSON, JsonToObject.class).getMonsterParser().getBaseMonster();

    private @Getter final ArrayList<Entity> monsterList = new ArrayList<Entity>();

    private PooledEngine getEngine(){
        return GameWorldManager.getEngine();
    }

    private World getWorld(){
        return GameWorldManager.getWorld();
    }

    private void createMonster(){
        for(val stat: monster){
            val entity = getEngine().createEntity();

            val animation = getEngine().createComponent(AnimationComponent.class);
            val state = getEngine().createComponent(StateComponent.class);
            val bodyCom = getEngine().createComponent(BodyComponent.class);
            val light = getEngine().createComponent(LightComponent.class);
            val monsterCom = getEngine().createComponent(MonsterComponent.class);

            monsterCom.setTemplate(stat);
            monsterCom.init();

            animation.getAnimations().put(AnimationState.IDLE.getValue(), GameWorldManager.IDLE);
            animation.getAnimations().put(AnimationState.RIGHT.getValue(), GameWorldManager.RIGHT);
            animation.getAnimations().put(AnimationState.LEFT.getValue(), GameWorldManager.LEFT);

            bodyCom.getBodyDef().type = BodyDef.BodyType.DynamicBody;
            bodyCom.setBody(getWorld().createBody(bodyCom.getBodyDef()));
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
            entity.add(new TouchComponent());
            entity.add(new TextureComponent());

            getEngine().addEntity(entity);

            monsterList.add(entity);
        }
    }

    private MonsterInstance() {
        createMonster();
    }

    public static MonsterInstance getInstance() {
        return instance;
    }
}
