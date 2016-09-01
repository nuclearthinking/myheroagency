package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class CameraComponent implements Component {
    public Entity target;
    public OrthographicCamera camera;
}
