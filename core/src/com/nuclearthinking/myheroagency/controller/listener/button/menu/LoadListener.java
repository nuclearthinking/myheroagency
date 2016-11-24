package com.nuclearthinking.myheroagency.controller.listener.button.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.listener.button.AbstractButtonListener;
import com.nuclearthinking.myheroagency.controller.manager.GameDataManager;
import com.nuclearthinking.myheroagency.controller.manager.ScreenManager;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Izonami on 19.06.2016.
 */
@Slf4j(topic = "LoadListener")
public final class LoadListener extends AbstractButtonListener {

    public LoadListener(@NonNull final TextButton button) {
        super(button);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        log.info("Loading game from save");

        ScreenManager.getInstance().showScreen(ScreenEnum.HOME_SCREEN, GameDataManager.load());
    }

}
