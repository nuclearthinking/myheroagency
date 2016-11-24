package com.nuclearthinking.myheroagency.controller.listener.button.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.listener.button.AbstractButtonListener;
import com.nuclearthinking.myheroagency.controller.manager.AssetsManager;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.view.SettingsScreen;
import lombok.NonNull;
import lombok.val;

/**
 * Created by Izonami on 19.06.2016.
 */
public final class SaveListener extends AbstractButtonListener {

    private final SettingsScreen screen;

    public SaveListener(@NonNull final TextButton button, @NonNull final SettingsScreen screen){
        super(button);
        this.screen = screen;
    }

    @Override
    public void clicked (InputEvent event, float x, float y) {
        val settings = Settings.getInstance();

        settings.setLanguage(screen.getSelectLanguage().getSelected().toString());
        settings.setHeight(Integer.parseInt(screen.getHeight().getText()));
        settings.setWidth(Integer.parseInt(screen.getWidth().getText()));
        settings.save();
        AssetsManager.getInstance().reloadLocale();
        Gdx.graphics.setWindowedMode(settings.getWidth(), settings.getHeight());
        screen.reloadLabel();
    }

}
