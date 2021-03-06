package com.nuclearthinking.myheroagency.model.actor.base;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;
import lombok.Getter;

/**
 * Created by mkuksin on 01.09.2016.
 */
public final class AnimationComponent implements Component {

    private @Getter final IntMap<Animation> animations = new IntMap<Animation>();
}
