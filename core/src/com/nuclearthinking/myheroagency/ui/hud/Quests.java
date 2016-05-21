package com.nuclearthinking.myheroagency.ui.hud;

import com.badlogic.gdx.graphics.Color;
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
 * Created by Izonami on 21.05.2016.
 */
public class Quests{

    private final Group questGroup;
    private final TextButton test;

    public Quests(final UiFactory factory){
        questGroup = new Group();
        test = factory.getTextButton("Test");
        test.addListener(new Test());

        Texture t = Asset.getInstance().get("img/test.jpg", Texture.class);
        Image i = new Image(t);
        questGroup.setSize(100,100);
        questGroup.setColor(Color.YELLOW);
        questGroup.setPosition(-Constants.GAME_W, 0);

        questGroup.addActor(i);
        questGroup.addActor(test);
    }

    public Group getQuestGroup(){
        return questGroup;
    }

    private class Test extends ClickListener{
        @Override
        public void clicked(InputEvent event, float x, float y) {
            System.out.println("Это тестовая кнопка");
        }
    }

}
