package com.nuclearthinking.myheroagency.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.nuclearthinking.myheroagency.model.entity.components.AnimationComponent;
import com.nuclearthinking.myheroagency.model.entity.components.StateComponent;
import com.nuclearthinking.myheroagency.model.entity.components.TextureComponent;
import lombok.val;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class AnimationSystem extends IteratingSystem {
    private ComponentMapper<TextureComponent> tm;
    private ComponentMapper<AnimationComponent> am;
    private ComponentMapper<StateComponent> sm;

    public AnimationSystem() {
        super(Family.all(TextureComponent.class,
                AnimationComponent.class,
                StateComponent.class).get());

        tm = ComponentMapper.getFor(TextureComponent.class);
        am = ComponentMapper.getFor(AnimationComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        val tex = tm.get(entity);
        val anim = am.get(entity);
        val state = sm.get(entity);

        val animation = anim.getAnimations().get(state.getState());

        if (animation != null) {
            tex.setRegion(animation.getKeyFrame(state.getTime()));
        }

        state.setTime(state.getTime() + deltaTime);
    }
}
