package com.nuclearthinking.myheroagency.model.entity.components;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

/**
 * Created by mkuksin on 02.09.2016.
 */
public class LightComponent implements Component {
    private @Getter static RayHandler rayHandler;
    private @Getter static PointLight playerLight;
    private @Getter @Setter Entity target;

    private @Getter final static Color lightOff = new Color(1f,1f,1f,0f);
    private @Getter final static Color lightOn = new Color(1f, 1f, 1f, 1f);

    public LightComponent(){
        val lightWorld = new World(new Vector2(), true);

        rayHandler = new RayHandler(lightWorld);
        rayHandler.setCulling(true);

        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(true);

        playerLight = new PointLight(rayHandler, 50);
        playerLight.setDistance(250);
        playerLight.setColor(lightOn);
    }
}
