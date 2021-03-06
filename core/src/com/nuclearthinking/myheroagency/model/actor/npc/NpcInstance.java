package com.nuclearthinking.myheroagency.model.actor.npc;

import box2dLight.PointLight;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.nuclearthinking.myheroagency.controller.loader.file.JsonToObject;
import com.nuclearthinking.myheroagency.controller.manager.AssetsManager;
import com.nuclearthinking.myheroagency.controller.manager.GameWorldManager;
import com.nuclearthinking.myheroagency.model.actor.base.*;
import com.nuclearthinking.myheroagency.model.actor.base.DialogComponent;
import com.nuclearthinking.myheroagency.model.actor.base.NameComponent;
import com.nuclearthinking.myheroagency.model.effect.LightComponent;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;
import lombok.val;

import java.util.ArrayList;

/**
 * Created by mkuksin on 21.11.2016.
 */
public class NpcInstance {

    private static final NpcInstance instance = new NpcInstance();

    private final ArrayList<Npc> npc = AssetsManager.getInstance().get(Constants.NPC_JSON, JsonToObject.class).getNpcParser().getBaseNpc();

    private @Getter final ArrayList<Entity> npsList = new ArrayList<Entity>();

    private PooledEngine getEngine(){
        return GameWorldManager.getEngine();
    }

    private World getWorld(){
        return GameWorldManager.getWorld();
    }

    private void createNpc() {
        for(val stat : npc){
            val entity = getEngine().createEntity();

            val animation = getEngine().createComponent(AnimationComponent.class);
            val state = getEngine().createComponent(StateComponent.class);
            val light = getEngine().createComponent(LightComponent.class);
            val bodyCom = getEngine().createComponent(BodyComponent.class);
            val npcCom = getEngine().createComponent(NpcComponent.class);
            val dialog = getEngine().createComponent(DialogComponent.class);
            val infoNpc = getEngine().createComponent(NameComponent.class);

            npcCom.initialize(stat);

            infoNpc.setName(new Label(stat.getName(), UiFactory.getSkin()));
            infoNpc.setTitle(new Label("", UiFactory.getSkin()));
            infoNpc.show();

            dialog.getDialog().getTitleLabel().setText(stat.getName());
            dialog.getDialog().getTitleLabel().setScale(.01f);
            dialog.getDialog().setMovable(true);

            animation.getAnimations().put(AnimationState.IDLE.getValue(), GameWorldManager.IDLE);

            bodyCom.getBodyDef().type = BodyDef.BodyType.StaticBody;
            bodyCom.setBody(getWorld().createBody(bodyCom.getBodyDef()));
            val bodyPolygon = new PolygonShape();
            bodyPolygon.setAsBox(10,10);
            bodyCom.getFixtureDef().shape = bodyPolygon;
            bodyCom.getFixtureDef().isSensor = true;
            bodyCom.getFixtureDef().filter.categoryBits = Constants.BIT_NPC;
            bodyCom.getFixtureDef().filter.maskBits = Constants.BIT_PLAYER;
            bodyCom.getBody().createFixture(bodyCom.getFixtureDef()).setUserData("NPC");
            bodyPolygon.dispose();

            bodyCom.getBody().setFixedRotation(true);

            light.setPlayerLight(new PointLight(LightComponent.getRayHandler(), 50));
            light.getPlayerLight().setDistance(100);
            light.getPlayerLight().setColor(LightComponent.getLightOn());
            light.setTarget(entity);

            state.set(AnimationState.IDLE.getValue());

            entity.add(animation);
            entity.add(state);
            entity.add(light);
            entity.add(npcCom);
            entity.add(bodyCom);
            entity.add(new TextureComponent());
            entity.add(dialog);
            entity.add(infoNpc);

            getEngine().addEntity(entity);

            npsList.add(entity);
        }
    }

    private NpcInstance() {
        createNpc();
    }

    public static NpcInstance getInstance() {
        return instance;
    }
}
