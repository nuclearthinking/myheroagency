package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.nuclearthinking.myheroagency.model.entity.AnimationState;
import com.nuclearthinking.myheroagency.model.entity.components.*;
import lombok.val;

/**
 * Created by mkuksin on 26.09.2016.
 */
public class NpcSystem extends ObjectSystem {
    private static final Family family = Family.all(StateComponent.class,
                                                    NpcComponent.class,
                                                    TransformComponent.class,
                                                    FunctionComponent.class).get();

    public NpcSystem() {
        super(family);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        val state = Components.STATE.get(entity);

        state.set(AnimationState.IDLE.getValue());
    }

    @Override
    public int getLevel(){
        return NpcComponent.getLevel();
    }

    @Override
    public int getBaseCON(){
        return NpcComponent.getBaseCON();
    }

    @Override
    public boolean isNpc(){
        return true;
    }

}
