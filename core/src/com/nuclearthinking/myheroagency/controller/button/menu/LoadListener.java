package com.nuclearthinking.myheroagency.controller.button.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.controller.button.AbstractButtonListener;
import com.nuclearthinking.myheroagency.model.GameData;

/**
 * Created by Izonami on 19.06.2016.
 */
public class LoadListener extends AbstractButtonListener {

    public LoadListener(final TextButton button) {
        super(button);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        logger.info("Loading game from save");

        ScreenManager.getInstance().showScreen(ScreenEnum.HOME_SCREEN, GameData.load());
    }

}
