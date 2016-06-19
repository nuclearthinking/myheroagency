package com.nuclearthinking.myheroagency.controller.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.ui.hud.layer.Quest;

/**
 * Created by Izonami on 19.06.2016.
 */
public class QuestAddListener extends AbstractButtonListener {

    private final Quest quest;

    public QuestAddListener(final TextButton button, final Quest quest) {
        super(button);
        this.quest = quest;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        for (int i = 0; i < 5; i++) {
            quest.addQuestToList("New Quest" + i);
        }
    }

}
