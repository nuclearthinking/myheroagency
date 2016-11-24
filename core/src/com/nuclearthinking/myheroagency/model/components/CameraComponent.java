package com.nuclearthinking.myheroagency.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 01.09.2016.
 */
public final class CameraComponent implements Component {

    private @Getter @Setter Entity target;
    private @Getter @Setter OrthographicCamera camera;
}
