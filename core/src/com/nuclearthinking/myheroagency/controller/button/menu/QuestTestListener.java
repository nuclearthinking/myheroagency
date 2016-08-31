package com.nuclearthinking.myheroagency.controller.button.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.controller.button.AbstractButtonListener;
import lombok.NonNull;

/**
 * Created by mkuksin on 31.08.2016.
 */
public class QuestTestListener extends AbstractButtonListener {

    public QuestTestListener(@NonNull TextButton button) {
        super(button);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        ScreenManager.getInstance().showScreen(ScreenEnum.BATTLE_SCREEN);
    }
}
