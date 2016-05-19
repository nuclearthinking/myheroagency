package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
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
    private TextButton back, save;
    private SelectBox<String> selectLanguage;
    private TextField height, width;
    private Label titleLabel, languageLabel, widthLabel, heightLabel;

    @Override
    public void buildStage() {
        table = new Table(); // Создаем таблицу
        table.setDebug(Constants.DEBUG); // Включаем дебаг режим (Разные прямоугольнико вокруг кнопок это оно самое)
        table.setFillParent(true);
        table.align(Align.center | Align.top);

        initButton();

        // Таблица рулит размером кнопок, отступами и прочей хренотой
        table.add(titleLabel).spaceBottom(50).colspan(3).expandX().row();
        table.row();
        table.add(languageLabel).height(80).right();
        table.add(selectLanguage).top().expandX().center().left();
        table.add().row();
        table.add(widthLabel).height(80).right();
        table.add(width).top().center().left();
        table.add().row();
        table.add(heightLabel).height(80).right();
        table.add(height).top().center().left();
        table.row();
        table.add(save).right().expandX().width(100).height(40);
        table.add(back).left().expandX().width(100).height(40);

        stage.addActor(table);
    }

    private void initButton(){
        titleLabel = new Label(locale.get("mainTitle"), Asset.getInstance().getSkin(), "kramola");
        languageLabel = new Label(locale.get("languageLabel"), Asset.getInstance().getSkin(), "kramola");
        widthLabel = new Label(locale.get("widthLabel"), Asset.getInstance().getSkin(), "kramola");
        heightLabel = new Label(locale.get("heightLabel"), Asset.getInstance().getSkin(), "kramola");

        back = new TextButton(locale.get("buttonBack"), Asset.getInstance().getSkin(), "kramola");

        back.getLabel().setFontScale(.9f);
        back.getLabel().setColor(Color.FOREST);
        back.addListener(new BackListener()); //Добавляет листнер кнопке

        save = new TextButton(locale.get("buttonSave"), Asset.getInstance().getSkin(), "kramola");
        save.getLabel().setFontScale(.8f);
        save.getLabel().setColor(Color.FOREST);
        save.addListener(new SaveListener()); //Добавляет листнер кнопке

        selectLanguage = new SelectBox<String>(Asset.getInstance().getSkin(), "kramola");
        selectLanguage.setItems("ru", "en");
        selectLanguage.setSelected(Settings.getLanguage());

        width = new TextField(Integer.toString(Gdx.graphics.getWidth()), Asset.getInstance().getSkin());
        height = new TextField(Integer.toString(Gdx.graphics.getHeight()), Asset.getInstance().getSkin());
    }

    private void reloadLabel(){
        locale.loadBundle(this.getClass());
        titleLabel.setText(locale.get("mainTitle"));
        languageLabel.setText(locale.get("languageLabel"));
        widthLabel.setText(locale.get("widthLabel"));
        heightLabel.setText(locale.get("heightLabel"));
        back.setText(locale.get("buttonBack"));
        save.setText(locale.get("buttonSave"));
    }


    private class BackListener extends ClickListener{
        @Override
        public void clicked (InputEvent event, float x, float y) {
            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU_SCREEN );
        }

        @Override
        public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
            back.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
        }
    }

    private class SaveListener extends ClickListener{
        @Override
        public void clicked (InputEvent event, float x, float y) {
            Settings settings = new Settings();
            settings.setLanguage(selectLanguage.getSelected());
            logger.debug("Language {}", Settings.getLanguage());
            settings.setHeight(Integer.parseInt(height.getText()));
            settings.setWidth(Integer.parseInt(width.getText()));
            logger.debug("height {}", height.getText());
            logger.debug("height {}", width.getText());
            settings.save();
            Asset.getInstance().reloadLocale();
            Gdx.graphics.setWindowedMode(Settings.getWidth(), Settings.getHeight());
            reloadLabel();
        }

        @Override
        public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
            save.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
        }
    }

}
