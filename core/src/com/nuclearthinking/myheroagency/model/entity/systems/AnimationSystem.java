package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nuclearthinking.myheroagency.model.entity.components.AnimationComponent;
import com.nuclearthinking.myheroagency.model.entity.components.Components;
import com.nuclearthinking.myheroagency.model.entity.components.StateComponent;
import com.nuclearthinking.myheroagency.model.entity.components.TextureComponent;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class AnimationSystem extends IteratingSystem {
    private static final Family family = Family.all(TextureComponent.class,
            AnimationComponent.class,
            StateComponent.class).get();

    public AnimationSystem() {
        super(family);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        @NonNull val tex = Components.TEXTURE.get(entity);
        @NonNull val anim = Components.ANIMATION.get(entity);
        @NonNull val state = Components.STATE.get(entity);

        val animation = anim.getAnimations().get(state.getState());

        if (animation != null) {
            tex.setRegion(animation.getKeyFrame(state.getTime()));
        }

        state.setTime(state.getTime() + deltaTime);
    }
}
