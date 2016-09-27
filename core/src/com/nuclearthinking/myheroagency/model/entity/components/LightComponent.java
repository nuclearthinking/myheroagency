package com.nuclearthinking.myheroagency.model.entity.components;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 02.09.2016.
 */
public class LightComponent implements Component {
    private @Getter @Setter static RayHandler rayHandler = null;
    private @Getter @Setter PointLight playerLight = null;
    private @Getter @Setter Entity target = null;

    private @Getter final static Color lightOff = new Color(1f,1f,1f,0f);
    private @Getter final static Color lightOn = new Color(1f,1f,1f,1.f);

    public LightComponent(){
        RayHandler.setGammaCorrection(true);     // enable or disable gamma correction
        RayHandler.useDiffuseLight(true);       // enable or disable diffused lighting
    }

}
