package com.nuclearthinking.myheroagency.model.components;

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
public final class LightComponent implements Component {
    private @Getter @Setter static RayHandler rayHandler;
    private @Getter @Setter PointLight playerLight;
    private @Getter @Setter Entity target;

    private @Getter static final Color lightOff = new Color(1f,1f,1f,0f);
    private @Getter static final Color lightOn = new Color(1f,1f,1f,1.f);

    public LightComponent(){
        RayHandler.setGammaCorrection(true);     // enable or disable gamma correction
        RayHandler.useDiffuseLight(true);       // enable or disable diffused lighting
    }

}
