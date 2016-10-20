package com.nuclearthinking.myheroagency.model.entity;

import box2dLight.PointLight;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import com.nuclearthinking.myheroagency.model.entity.systems.MonsterSystem;
import com.nuclearthinking.myheroagency.model.entity.systems.NpcSystem;
import lombok.NonNull;
import lombok.val;

import java.util.ArrayList;

/**
 * Created by mkuksin on 20.10.2016.
 */
public final class BuildNpc {

    private final ArrayList<Entity> npsList = new ArrayList<Entity>();
    private final ArrayList<Entity> monsterList = new ArrayList<Entity>();

    private final PooledEngine engine;
    private final World world;

    public BuildNpc(@NonNull final PooledEngine engine, @NonNull final World world){
        this.engine = engine;
        this.world = world;
    }

    public void createNpc() {
        val entity = engine.createEntity();

        val animation = engine.createComponent(AnimationComponent.class);
        val state = engine.createComponent(StateComponent.class);
        val light = engine.createComponent(LightComponent.class);
        val bodyCom = engine.createComponent(BodyComponent.class);
        val npc = engine.createComponent(NpcComponent.class);
        engine.getSystem(NpcSystem.class).setActor(npc);

        animation.getAnimations().put(AnimationState.IDLE.getValue(), GameWorld.IDLE);

        bodyCom.getBodyDef().type = BodyDef.BodyType.StaticBody;
        bodyCom.setBody(world.createBody(bodyCom.getBodyDef()));
        val bodyPolygon = new PolygonShape();
        bodyPolygon.setAsBox(10,10);
        bodyCom.getFixtureDef().shape = bodyPolygon;
        bodyCom.getFixtureDef().isSensor = true; //TODO: Вместо этого настроить фильтры
        bodyCom.getBody().createFixture(bodyCom.getFixtureDef());
        bodyPolygon.dispose();

        bodyCom.getBody().setFixedRotation(true);

        light.setPlayerLight(new PointLight(light.getRayHandler(), 50));
        light.getPlayerLight().setDistance(100);
        light.getPlayerLight().setColor(light.getLightOn());
        light.setTarget(entity);

        state.set(AnimationState.IDLE.getValue());

        entity.add(animation);
        entity.add(state);
        entity.add(light);
        entity.add(npc);
        entity.add(bodyCom);
        entity.add(new TextureComponent());

        engine.addEntity(entity);

        npsList.add(entity);
    }

    public void createMonster(@NonNull final Entity target){
        val entity = engine.createEntity();

        val animation = engine.createComponent(AnimationComponent.class);
        val state = engine.createComponent(StateComponent.class);
        val bodyCom = engine.createComponent(BodyComponent.class);
        val light = engine.createComponent(LightComponent.class);
        val monster = engine.createComponent(MonsterComponent.class);
        engine.getSystem(MonsterSystem.class).setActor(monster);

        monster.setTarget(target);

        animation.getAnimations().put(AnimationState.IDLE.getValue(), GameWorld.IDLE);
        animation.getAnimations().put(AnimationState.RIGHT.getValue(), GameWorld.RIGHT);
        animation.getAnimations().put(AnimationState.LEFT.getValue(), GameWorld.LEFT);

        bodyCom.getBodyDef().type = BodyDef.BodyType.DynamicBody;
        bodyCom.setBody(world.createBody(bodyCom.getBodyDef()));
        val bodyPolygon = new PolygonShape();
        bodyPolygon.setAsBox(10,10);
        bodyCom.getFixtureDef().shape = bodyPolygon;
        bodyCom.getFixtureDef().friction = 0.3f;
        bodyCom.getBody().createFixture(bodyCom.getFixtureDef());
        bodyPolygon.dispose();

        bodyCom.getBody().setFixedRotation(true);

        state.set(AnimationState.IDLE.getValue());

        light.setPlayerLight(new PointLight(light.getRayHandler(), 50));
        light.getPlayerLight().setDistance(50);
        light.getPlayerLight().setColor(light.getLightOn());
        light.setTarget(entity);

        entity.add(animation);
        entity.add(state);
        entity.add(light);
        entity.add(monster);
        entity.add(bodyCom);
        entity.add(new MovementComponent());
        entity.add(new TextureComponent());

        engine.addEntity(entity);

        monsterList.add(entity);
    }

    public void spawnNpc(final float x, final float y){
        for(val npc : npsList){
            npc.getComponent(BodyComponent.class).getBody().setTransform(x, y, 0);
        }
    }

    public void spawnMonster(final float x, final float y){
        for(val monster : monsterList){
            monster.getComponent(BodyComponent.class).getBody().setTransform(x, y, 0);
        }
    }
}
