package com.nuclearthinking.myheroagency.controller.button.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.button.AbstractButtonListener;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.model.components.hud.SettingHudComponent;
import lombok.NonNull;
import lombok.val;

/**
 * Created by Izonami on 19.06.2016.
 */
public final class SaveLayerListener extends AbstractButtonListener {

    private final SettingHudComponent layer;

    public SaveLayerListener(@NonNull final TextButton button, @NonNull final SettingHudComponent layer) {
        super(button);
        this.layer = layer;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        val settings = new Settings();
        settings.setHeight(Integer.parseInt(layer.getHeight().getText()));
        settings.setWidth(Integer.parseInt(layer.getWidth().getText()));
        settings.save();
        Asset.getInstance().reloadLocale();
        Gdx.graphics.setWindowedMode(settings.getWidth(), settings.getHeight());
        //layer.reloadLabel(); //TODO: Добавить переводы
    }

}
