package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.ui.UiFactory;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Created by Izonami on 23.05.2016.
 */
public class Quest {

    private final Group questGroup;
    private final TextButton test;

    public Quest(final UiFactory factory){
        questGroup = new Group();
        test = factory.getTextButton("Test");
        test.addListener(new Test());

        Texture t = Asset.getInstance().get("img/testQuestLayer.jpg", Texture.class);
        Image i = new Image(t);
        questGroup.setPosition(-Constants.GAME_W, 0);

        questGroup.addActor(i);
        questGroup.addActor(test);
    }

    public Group getQuestGroup(){
        return questGroup;
    }

    private class Test extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            System.out.println("Это тестовая кнопка");
        }
    }
}
