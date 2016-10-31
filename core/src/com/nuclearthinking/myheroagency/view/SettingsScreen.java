package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.nuclearthinking.myheroagency.controller.button.menu.BackListener;
import com.nuclearthinking.myheroagency.controller.button.menu.SaveListener;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Created by Izonami on 13.05.2016.
 */
public final class SettingsScreen extends AbstractScreen {

    private static Table table;
    private static TextButton back, save;
    private static SelectBox<String> selectLanguage;
    private static TextField height, width;
    private static Label titleLabel, languageLabel, widthLabel, heightLabel;
    private static UiFactory uiFactory;

    @Override
    public void buildStage() {
        uiFactory = new UiFactory();
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

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);

        table.setClip(true);
        table.setSize(width, height);
    }

    private void initButton(){
        titleLabel = uiFactory.getLabel("ss.mainTitle", locale);
        languageLabel = uiFactory.getLabel("ss.languageLabel", locale);
        widthLabel = uiFactory.getLabel("ss.widthLabel", locale);
        heightLabel = uiFactory.getLabel("ss.heightLabel",locale);

        back = uiFactory.getTextButton("ss.buttonBack", locale);
        back.getLabel().setFontScale(.9f);
        back.getLabel().setColor(Color.FOREST);
        back.addListener(new BackListener(back)); //Добавляет листнер кнопке

        save = uiFactory.getTextButton("ss.buttonSave", locale);
        save.getLabel().setFontScale(.8f);
        save.getLabel().setColor(Color.FOREST);
        save.addListener(new SaveListener(save, this)); //Добавляет листнер кнопке

        selectLanguage = new SelectBox<String>(uiFactory.getSkin(), "kramola");
        selectLanguage.setItems("ru", "en");
        selectLanguage.setSelected(Settings.getLanguage());

        //TODO: Это костыль для фабрики, так как если выставить в настройках одинаковые значения, начинает браться один и тот же объект
        // по хорошему это надо бы переделать в слайдер или лучше в селектор
        width = uiFactory.getTextField("800");
        height = uiFactory.getTextField("600");
    }

    public void reloadLabel(){
        locale.loadBundle();

        titleLabel.setText(locale.get("ss.mainTitle"));
        languageLabel.setText(locale.get("ss.languageLabel"));
        widthLabel.setText(locale.get("ss.widthLabel"));
        heightLabel.setText(locale.get("ss.heightLabel"));
        back.setText(locale.get("ss.buttonBack"));
        save.setText(locale.get("ss.buttonSave"));
    }

    public TextField getHeight(){
        return height;
    }

    public TextField getWidth(){
        return width;
    }

    public SelectBox<String> getSelectLanguage(){
        return selectLanguage;
    }

    @Override
    public void dispose() {
        super.dispose();

        back.clearListeners();
        save.clearListeners();
    }

}
