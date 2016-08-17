package com.nuclearthinking.myheroagency.ui.hud.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.nuclearthinking.myheroagency.controller.button.menu.ExitListener;
import com.nuclearthinking.myheroagency.controller.button.menu.SaveLayerListener;
import com.nuclearthinking.myheroagency.ui.UiFactory;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Izonami on 24.05.2016.
 */
public class SettingsLayer extends AbstractLayer implements ILayer{

    private Label titleLabel, widthLabel, heightLabel;
    private TextField height, width;
    private TextButton exit, save;
    private boolean isShowTable = false;

    public SettingsLayer(final UiFactory factory){
        super(factory);
    }

    @Override
    public void buildLayer() {
        table.setSkin(factory.getSkin());
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.setPosition(-Gdx.graphics.getWidth(), 0);
        table.setBackground("default-window");

        initButton();

        table.add(titleLabel).spaceBottom(50).colspan(3).expandX().row();
        table.row();
        table.add(widthLabel).height(80).right();
        table.add(width).top().center().left();
        table.add().row();
        table.add(heightLabel).height(80).right();
        table.add(height).top().center().left();
        table.row();
        table.add(save).right().expandX().width(100).height(40);
        table.add(exit).left().expandX().width(100).height(40);
    }

    private void initButton(){
        titleLabel = factory.getLabel(locale.get("mainTitle"));
        widthLabel = factory.getLabel(locale.get("widthLabel"));
        heightLabel = factory.getLabel(locale.get("heightLabel"));

        //TODO: Это костыль для фабрики, так как если выставить в настройках одинаковые значения, начинает браться один и тот же объект
        // по хорошему это надо бы переделать в слайдер или лучше в селектор
        width = factory.getTextField("800");
        height = factory.getTextField("600");

        exit = factory.getTextButton(locale.get("buttonExit"));
        exit.getLabel().setFontScale(.9f);
        exit.getLabel().setColor(Color.FOREST);
        exit.addListener(new ExitListener(exit)); //Добавляет листнер кнопке

        save = factory.getTextButton(locale.get("buttonSave"));
        save.getLabel().setFontScale(.8f);
        save.getLabel().setColor(Color.FOREST);
        save.addListener(new SaveLayerListener(save, this)); //Добавляет листнер кнопке
    }

    public void setTableVisible(final boolean isShowTable){
        this.isShowTable = isShowTable;

        if (isShowTable)
            table.addAction(sequence(moveTo(-Gdx.graphics.getWidth(), 0), moveTo(0, 0, .5f)));
        else
            table.addAction(sequence(moveTo(0, 0), moveTo(-Gdx.graphics.getWidth(), 0, .5f)));
    }

    public boolean isShowTable(){
        return isShowTable;
    }

    @Override
    public void resize(final int w, final int h){
        super.resize(w,h);

        table.setPosition(-Gdx.graphics.getWidth(), 0);
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public TextField getHeight(){
        return height;
    }

    public TextField getWidth(){
        return width;
    }

}
