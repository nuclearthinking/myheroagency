package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class MapComponent implements Component {
    private @Getter @Setter static int levelPixelWidth;
    private @Getter @Setter static int levelPixelHeight;
}
