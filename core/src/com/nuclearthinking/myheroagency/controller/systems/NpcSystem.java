package com.nuclearthinking.myheroagency.controller.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.nuclearthinking.myheroagency.model.components.BodyComponent;
import com.nuclearthinking.myheroagency.model.components.NpcComponent;
import com.nuclearthinking.myheroagency.model.components.StateComponent;

/**
 * Created by mkuksin on 26.09.2016.
 */
public class NpcSystem extends ActorSystem {
    private static final Family family = Family.all(StateComponent.class,
                                                    NpcComponent.class,
                                                    BodyComponent.class).get();

    public NpcSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

    public void dialog(){
        System.out.println("F**k you");
    }

    @Override
    public boolean isNpc(){
        return true;
    }

}
