package com.nuclearthinking.myheroagency.controller.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.view.SettingsScreen;

/**
 * Created by Izonami on 19.06.2016.
 */
public class SaveListener extends AbstractButtonListener {

    private final SettingsScreen screen;

    public SaveListener(final TextButton button, final SettingsScreen screen){
        super(button);
        this.screen = screen;
    }

    @Override
    public void clicked (InputEvent event, float x, float y) {
        final Settings settings = new Settings();
        settings.setLanguage(screen.getSelectLanguage().getSelected().toString());
        settings.setHeight(Integer.parseInt(screen.getHeight().getText()));
        settings.setWidth(Integer.parseInt(screen.getWidth().getText()));
        settings.save();
        Asset.getInstance().reloadLocale();
        Gdx.graphics.setWindowedMode(settings.getWidth(), settings.getHeight());
        screen.reloadLabel();
    }
}
