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
        titleLabel = uiFactory.getLabel(locale.get("mainTitle"));
        languageLabel = uiFactory.getLabel(locale.get("languageLabel"));
        widthLabel = uiFactory.getLabel(locale.get("widthLabel"));
        heightLabel = uiFactory.getLabel(locale.get("heightLabel"));

        back = uiFactory.getTextButton(locale.get("buttonBack"));
        back.getLabel().setFontScale(.9f);
        back.getLabel().setColor(Color.FOREST);
        back.addListener(new BackListener(back)); //Добавляет листнер кнопке

        save = uiFactory.getTextButton(locale.get("buttonSave"));
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
        locale.loadBundle(this.getClass());

        titleLabel.setText(locale.get("mainTitle"));
        languageLabel.setText(locale.get("languageLabel"));
        widthLabel.setText(locale.get("widthLabel"));
        heightLabel.setText(locale.get("heightLabel"));
        back.setText(locale.get("buttonBack"));
        save.setText(locale.get("buttonSave"));
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
