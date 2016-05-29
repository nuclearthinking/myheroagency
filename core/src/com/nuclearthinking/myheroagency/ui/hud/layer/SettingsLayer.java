package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.model.Settings;
import com.nuclearthinking.myheroagency.ui.UiFactory;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by Izonami on 24.05.2016.
 */
public class SettingsLayer {

    private final UiFactory factory;
    private final Table settingsTable;
    private Image i;
    private Label titleLabel, widthLabel, heightLabel;
    private TextField height, width;
    private TextButton back, save;
    private boolean isShowSettings = false;

    public SettingsLayer(final UiFactory factory){
        this.factory = factory;

        settingsTable = new Table();
        settingsTable.setSize(Settings.getWidth(), Settings.getHeight());
        settingsTable.setDebug(true);
        settingsTable.setPosition(-Settings.getWidth(), 0);

        Texture t = Asset.getInstance().get("img/testQuestLayer.jpg", Texture.class);
        i = new Image(t);
        i.setColor(Color.BLACK);
        i.setSize(settingsTable.getWidth(), settingsTable.getHeight());

        initButton();

        settingsTable.addActor(i);

        settingsTable.add(titleLabel).spaceBottom(50).colspan(3).expandX().row();
        settingsTable.row();
        settingsTable.add(widthLabel).height(80).right();
        settingsTable.add(width).top().center().left();
        settingsTable.add().row();
        settingsTable.add(heightLabel).height(80).right();
        settingsTable.add(height).top().center().left();
        settingsTable.row();
        settingsTable.add(save).right().expandX().width(100).height(40);
        settingsTable.add(back).left().expandX().width(100).height(40);
    }

    private void initButton(){
        titleLabel = factory.getLabel("mainTitle");
        widthLabel = factory.getLabel("widthLabel");
        heightLabel = factory.getLabel("heightLabel");

        back = factory.getTextButton("buttonBack");

        back.getLabel().setFontScale(.9f);
        back.getLabel().setColor(Color.FOREST);
        back.addListener(new BackListener()); //Добавляет листнер кнопке

        save = factory.getTextButton("buttonSave");
        save.getLabel().setFontScale(.8f);
        save.getLabel().setColor(Color.FOREST);
        save.addListener(new SaveListener()); //Добавляет листнер кнопке

        width = factory.getTextField(Integer.toString(Gdx.graphics.getWidth()));
        height = factory.getTextField(Integer.toString(Gdx.graphics.getHeight()));
    }

    public Table getSettingsTable(){
        return settingsTable;
    }

    public void tableVisible(boolean isShowSettings){
        this.isShowSettings = isShowSettings;

        if (isShowSettings)
            settingsTable.addAction(sequence(moveTo(-Settings.getWidth(), 0), moveTo(0, 0, .5f)));
        else
            settingsTable.addAction(sequence(moveTo(0, 0), moveTo(-Settings.getWidth(), 0, .5f)));
    }

    public boolean isShowSettings(){
        return isShowSettings;
    }

    private class BackListener extends ClickListener {
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
            settings.setHeight(Integer.parseInt(height.getText()));
            settings.setWidth(Integer.parseInt(width.getText()));
            settings.save();
            Gdx.graphics.setWindowedMode(settings.getWidth(), settings.getHeight());
        }

        @Override
        public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
            save.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
        }
    }
}
