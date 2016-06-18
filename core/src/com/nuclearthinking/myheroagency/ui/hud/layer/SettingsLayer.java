package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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
public class SettingsLayer extends AbstractLayer{

    private Image i;
    private Label titleLabel, widthLabel, heightLabel;
    private TextField height, width;
    private TextButton exit, save;
    private boolean isShowTable = false;

    public SettingsLayer(final UiFactory factory){
        super(factory);

        getTable().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Texture t = Asset.getInstance().get("img/testQuestLayer.jpg", Texture.class);
        i = new Image(t);
        i.setColor(Color.BLACK);
        i.setSize(getTable().getWidth(), getTable().getHeight());

        initButton();

        getTable().addActor(i);

        getTable().add(titleLabel).spaceBottom(50).colspan(3).expandX().row();
        getTable().row();
        getTable().add(widthLabel).height(80).right();
        getTable().add(width).top().center().left();
        getTable().add().row();
        getTable().add(heightLabel).height(80).right();
        getTable().add(height).top().center().left();
        getTable().row();
        getTable().add(save).right().expandX().width(100).height(40);
        getTable().add(exit).left().expandX().width(100).height(40);
    }

    private void initButton(){
        titleLabel = factory.getLabel(locale.get("mainTitle"));
        widthLabel = factory.getLabel(locale.get("widthLabel"));
        heightLabel = factory.getLabel(locale.get("heightLabel"));

        width = factory.getTextField("800");
        height = factory.getTextField("600");

        exit = factory.getTextButton(locale.get("buttonExit"));
        exit.getLabel().setFontScale(.9f);
        exit.getLabel().setColor(Color.FOREST);
        exit.addListener(new ExitListener()); //Добавляет листнер кнопке

        save = factory.getTextButton(locale.get("buttonSave"));
        save.getLabel().setFontScale(.8f);
        save.getLabel().setColor(Color.FOREST);
        save.addListener(new SaveListener()); //Добавляет листнер кнопке
    }

    public void setTableVisible(boolean isShowTable){
        this.isShowTable = isShowTable;

        if (isShowTable)
            getTable().addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));
        else
            getTable().addAction(sequence(moveTo(0, 0), moveTo(-Gdx.graphics.getWidth(), 0, .5f)));
    }

    public boolean isShowTable(){
        return isShowTable;
    }

    @Override
    public void resize(int w, int h){
        super.resize(w,h);

        getTable().setPosition(-Gdx.graphics.getWidth(), 0);
        getTable().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        i.setSize(getTable().getWidth(), getTable().getHeight());
    }

    private class ExitListener extends ClickListener {
        @Override
        public void clicked (InputEvent event, float x, float y) {
            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU_SCREEN );
        }

        @Override
        public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
            exit.addAction(sequence(alpha(0), parallel(fadeIn(.4f))));
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
