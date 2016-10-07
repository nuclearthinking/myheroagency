package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.nuclearthinking.myheroagency.model.entity.AnimationState;
import com.nuclearthinking.myheroagency.model.entity.Components;
import com.nuclearthinking.myheroagency.model.entity.components.BodyComponent;
import com.nuclearthinking.myheroagency.model.entity.components.NpcComponent;
import com.nuclearthinking.myheroagency.model.entity.components.StateComponent;
import lombok.NonNull;
import lombok.val;

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
        @NonNull val state = Components.STATE.get(entity);

        state.set(AnimationState.IDLE.getValue());
    }

    public void dialog(){
        System.out.println("F**k you");
    }

    @Override
    public boolean isNpc(){
        return true;
    }

}
