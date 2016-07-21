package com.nuclearthinking.myheroagency.controller.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.model.quest.Quest;

/**
 * Created by Izonami on 20.06.2016.
 */
public class QuestCheck extends AbstractButtonListener {

    private final Quest quest;

    public QuestCheck(final TextButton button, final Quest quest) {
        super(button);

        this.quest = quest;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if(quest.isQuestItem(1)){
            logger.info("IS correct");
        }
        else
            logger.info("IS incorrect");
    }
}
