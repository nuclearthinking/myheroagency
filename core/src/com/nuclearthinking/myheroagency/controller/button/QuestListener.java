package com.nuclearthinking.myheroagency.controller.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.ui.hud.layer.Quest;

/**
 * Created by Izonami on 19.06.2016.
 */
public class QuestListener extends AbstractButtonListener {

    private final Quest quest;

    public QuestListener(final TextButton button, final Quest quest) {
        super(button);
        this.quest = quest;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if(!quest.isShowTable())
            quest.setTableVisible(true);
        else
            quest.setTableVisible(false);
    }

}
