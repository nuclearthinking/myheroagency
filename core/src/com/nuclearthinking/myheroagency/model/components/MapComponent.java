package com.nuclearthinking.myheroagency.model.components;

import com.badlogic.ashley.core.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 01.09.2016.
 */
public final class MapComponent implements Component {
    private @Getter @Setter static int levelPixelWidth;
    private @Getter @Setter static int levelPixelHeight;
    // Пиксели на тайл. Если тайл 32х32, установить в значение 32f
    private @Getter static final float ppt = 32f;
}
