package com.nuclearthinking.myheroagency.ui.font.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Created by Izonami on 18.05.2016.
 */
public class HudGame {

    public Stage stage;
    private Viewport viewport;

    TextButton questButton;

    public HudGame(SpriteBatch sb){
        viewport = new StretchViewport(Settings.getWidth(), Settings.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.setDebug(Constants.DEBUG);

        questButton = new TextButton("!", Asset.getInstance().getSkin(), "kramola");
        questButton.getLabel().setColor(Color.YELLOW);
        questButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                System.out.println("Прости я пока не работаю, но я буду стараться исправиться");
            }
        });

        table.add(questButton).expandX().padTop(10).width(50).height(60);

        stage.addActor(table);
    }
}
