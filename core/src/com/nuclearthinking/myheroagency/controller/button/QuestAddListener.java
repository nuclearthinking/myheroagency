package com.nuclearthinking.myheroagency.controller.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.ui.hud.layer.QuestLayer;

/**
 * Created by Izonami on 19.06.2016.
 */
public class QuestAddListener extends AbstractButtonListener {

    private final QuestLayer questLayer;

    public QuestAddListener(final TextButton button, final QuestLayer questLayer) {
        super(button);
        this.questLayer = questLayer;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        for (int i = 0; i < 5; i++) {
            questLayer.addQuestToList("New Quest" + i);
        }
    }

}
