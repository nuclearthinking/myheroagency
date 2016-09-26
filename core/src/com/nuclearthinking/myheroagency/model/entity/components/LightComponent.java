package com.nuclearthinking.myheroagency.model.entity.components;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

/**
 * Created by mkuksin on 02.09.2016.
 */
public class LightComponent implements Component {
    private @Getter RayHandler rayHandler;
    private @Getter PointLight playerLight;
    private @Getter @Setter Entity target;

    private final Color lightOff = new Color(1f,1f,1f,0f);
    private final Color lightOn = new Color(1f,1f,1f,15f);

    public LightComponent() {
        val lightWorld = new World(new Vector2(), true);

        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(true);
        this.rayHandler = new RayHandler(lightWorld);

        playerLight = new PointLight(rayHandler, 128, lightOn, 256, 0, 0);
    }

    public void setAmbient(@NonNull final Color color) {
        this.rayHandler.setAmbientLight(color);
    }

    public void lightOff(){
        if(checkOffLight()){
            playerLight.setColor(lightOff);
        }
    }

    public boolean checkOffLight(){
        if(playerLight.getColor().equals(lightOff)){
            return false;
        }
        return true;
    }
}
