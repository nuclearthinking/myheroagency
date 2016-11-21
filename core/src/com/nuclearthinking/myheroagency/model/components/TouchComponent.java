package com.nuclearthinking.myheroagency.model.components;

import com.badlogic.ashley.core.Component;
import com.nuclearthinking.myheroagency.controller.systems.Speaker;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 08.11.2016.
 */
public class TouchComponent implements Component {

    private @Getter @Setter boolean isTouch;
    private @Getter @Setter Speaker actor;
}
