package com.nuclearthinking.myheroagency.model.actor.base;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 01.09.2016.
 */
public final class TextureComponent implements Component {

    private @Getter @Setter TextureRegion region = null;
}
