package com.nuclearthinking.myheroagency.model.entity.systems;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.nuclearthinking.myheroagency.model.entity.Components;
import com.nuclearthinking.myheroagency.model.entity.components.LightComponent;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

/**
 * Created by mkuksin on 02.09.2016.
 */
public class LightSystem extends IteratingSystem{
    private static final Family family = Family.all(LightComponent.class).get();

    private final Color color;
    private @Setter OrthographicCamera camera;

    public LightSystem(@NonNull World world){
        super(family);

        color = new Color();
        LightComponent.setRayHandler(new RayHandler(world));

        //LightComponent.getRayHandler().setBlur(true);           // enabled or disable blur
        //LightComponent.getRayHandler().setBlurNum(1);           // set number of gaussian blur passes
        LightComponent.getRayHandler().setShadows(true);        // enable or disable shadow
        LightComponent.getRayHandler().setCulling(true);        // enable or disable culling
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        @NonNull val light = Components.LIGHT.get(entity);
        @NonNull val target = Components.BODY.get(light.getTarget());

        setAmbient(light.getRayHandler(), getLight());
        light.getPlayerLight().setPosition(target.getBody().getPosition().x, target.getBody().getPosition().y);
        //light.getDirectionalLight().setPosition(target.getBody().getPosition().x, target.getBody().getPosition().y);

        light.getRayHandler().setCombinedMatrix(camera);
        light.getRayHandler().updateAndRender();
    }

    private Color getLight(){
        int hour = getHour();

        if(hour > 8 && hour <= 9) {
            color.set(0.15f,0.15f,0.15f,1f);
        }
        else if (hour > 9 && hour <= 10) {
            color.set(0.35f,0.35f,0.35f,1f);
        }
        else if (hour > 10 && hour <= 11) {
            color.set(0.95f,0.95f,0.95f,1f);
        }

        return color;
    }

    private int getHour(){
        return 9;
    }

    public void setAmbient(@NonNull final RayHandler rayHandler, @NonNull final Color color) {
        rayHandler.setAmbientLight(color);
    }

    public void lightOff(@NonNull final PointLight pointLight){
        if(checkOffLight(pointLight)){
            pointLight.setColor(LightComponent.getLightOff());
        }
    }

    public boolean checkOffLight(@NonNull final PointLight pointLight){
        if(pointLight.getColor().equals(LightComponent.getLightOff())){
            return false;
        }
        return true;
    }

}
