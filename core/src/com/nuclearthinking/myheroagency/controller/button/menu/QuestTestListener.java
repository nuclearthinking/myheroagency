package com.nuclearthinking.myheroagency.controller.button.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.button.AbstractButtonListener;
import com.nuclearthinking.myheroagency.controller.manager.QuestManager;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mkuksin on 31.08.2016.
 */
@Slf4j(topic = "QuestTestListener")
public final class QuestTestListener extends AbstractButtonListener {

    public QuestTestListener(@NonNull TextButton button) {
        super(button);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        log.info(QuestManager.getQuestById(0).getQuest().getDescription());
    }
}
