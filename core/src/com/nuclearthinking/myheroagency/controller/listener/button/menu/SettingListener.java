package com.nuclearthinking.myheroagency.controller.listener.button.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.listener.button.AbstractButtonListener;
import com.nuclearthinking.myheroagency.controller.manager.ScreenManager;
import lombok.NonNull;

/**
 * Created by Izonami on 19.06.2016.
 */
public final class SettingListener extends AbstractButtonListener {

    public SettingListener(@NonNull final TextButton button) {
        super(button);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        ScreenManager.getInstance().showScreen(ScreenEnum.SETTINGS_SCREEN);
    }

}
