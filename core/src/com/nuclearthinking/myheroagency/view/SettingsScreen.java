package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.utils.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by Izonami on 13.05.2016.
 */
public class SettingsScreen extends AbstractScreen {

    private Table table;
    private TextButton back;
    private SelectBox selectLanguage;
    private Label selectLanguageLabel;
    private TextField height, width;

    @Override
    public void buildStage() {
        table = new Table(); // Создаем таблицу
        table.setDebug(Constants.DEBUG); // Включаем дебаг режим (Разные прямоугольнико вокруг кнопок это оно самое)
        table.setFillParent(true);

        initButton();

        // Таблица рулит размером кнопок, отступами и прочей хренотой
        table.add(selectLanguageLabel).top().expandY().pad(10);
        table.add(selectLanguage).top().expandY().pad(10);
        table.add().row();
        table.add(width).top().expandY();
        table.add(height).top().expandY();
        table.add().row();
        table.add(back).bottom().right().width(100).height(40);

        addActor(table);
    }

    private void initButton(){
        back = new TextButton("Назад", Asset.getInstance().getSkin(), "kramola");
        back.getLabel().setFontScale(.9f);
        back.getLabel().setColor(Color.FOREST);
        back.addListener(new BackListener()); //Добавляет листнер кнопке

        selectLanguageLabel = new Label("Язык", Asset.getInstance().getSkin(), "kramola");
        selectLanguage = new SelectBox(Asset.getInstance().getSkin(), "kramola");
        selectLanguage.setItems("ru","en");

        width = new TextField(Integer.toString(Gdx.graphics.getWidth()), Asset.getInstance().getSkin());
        height = new TextField(Integer.toString(Gdx.graphics.getHeight()), Asset.getInstance().getSkin());
    }


    private class BackListener extends ClickListener{
        @Override
        public void clicked (InputEvent event, float x, float y) {
            Settings settings = new Settings();
            settings.setLanguage(selectLanguage.getSelected().toString());
            logger.info("Language {}", settings.getLanguage().toString());
            settings.setHeight(height.getText());
            settings.setWidth(width.getText());
            logger.info("height {}", height.getText());
            logger.info("height {}", width.getText());
            settings.save();
            Gdx.graphics.setWindowedMode(settings.getWidth(), settings.getHeight());

            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU_SCREEN );
        }

        @Override
        public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
            back.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
        }
    }

}
