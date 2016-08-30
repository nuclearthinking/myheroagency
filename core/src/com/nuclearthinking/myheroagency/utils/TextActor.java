package com.nuclearthinking.myheroagency.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Created by Izonami on 09.05.2016.
 */
public class TextActor extends Actor {
    private final TextButton[] listchar;
    private final TextButton.TextButtonStyle style;
    private final String text;
    private float x, y;

    public TextActor(final BitmapFont bitmapFont, final String text, final Stage stage) {
        this.text = text;

        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        listchar = new TextButton[text.length()];
        style = new TextButton.TextButtonStyle();
        style.font = bitmapFont;

        for (int i = 0; i < text.length(); i++) {
            listchar[i] = new TextButton(String.valueOf(text.charAt(i)), style);
            listchar[i].setTransform(true);
            listchar[i].setPosition(x, y);
            stage.addActor(listchar[i]);
        }
    }

    public void dropText(final float time, final float delay) {
        resetText();
        for (int i = 0; i < text.length(); i++) {
            listchar[i].setY(y + 200f);
            listchar[i].setColor(0, 0, 0, 0);
            listchar[i].addAction(
                    Actions.delay(delay * i,
                            Actions.parallel(Actions.alpha(1, time),
                                    Actions.moveTo(x, y, time, Interpolation.bounceOut))));
            setPositionX(getPositionX() + 10);
        }
    }

    public void spinText(final float time, final float delay) {
        resetText();
        for (int i = 0; i < text.length(); i++) {
            listchar[i].addAction(
                    Actions.delay(delay * i,
                            Actions.rotateBy(360f, time * 5, Interpolation.bounce)));
        }
    }

    public void appearText(final float time, final float delay) {
        resetText();
        for (int i = 0; i < text.length(); i++) {
            listchar[i].setScale(0f);
            listchar[i].setColor(0, 0, 0, 0);
            listchar[i].addAction(
                    Actions.delay(delay * i,
                            Actions.parallel(Actions.alpha(1, time),
                                    Actions.scaleTo(1, 1, time, Interpolation.swingOut))));
        }
    }

    public void fadeText() {
        resetText();
        for (int i = 0; i < text.length(); i++) {
            listchar[i].setY(y);
            listchar[i].setColor(0, 0, 0, 0);
            listchar[i].addAction(
                    //Секвенция задаёт порядок действий
                    sequence(
                            alpha(0), //Альфа канал 0
                            scaleTo(.1f, .1f), //Размер картинки
                            //Выполняется поралельно с сиквенцией
                            parallel(
                                    fadeIn(2f, Interpolation.pow2), //Появление
                                    scaleTo(1f, 1f, 2.5f, Interpolation.pow5), //Изменение размера
                                    moveTo(x - 32, y - 32, 2f, Interpolation.swing)), //Положение на экране
                            delay(1.5f), //Задержка
                            fadeOut(1.25f) //Исчезновение
                    )); //Запуск сцены
            setPositionX(getPositionX() + 10);
        }
    }

    public void setPosition(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    private void resetText() {
        for (int i = 0; i < text.length(); i++) {
            listchar[i].setPosition(x, y);
            listchar[i].setColor(0, 0, 0, 1);
            listchar[i].setScale(1f);
            setPositionX(getPositionX() + 10);
        }
    }

    private float getPositionX() {
        return this.x;
    }

    private void setPositionX(final float x) {
        this.x = x;
    }

    private float getPositionY() {
        return this.y;
    }

    public void setPositionY(final float y) {
        this.y = y;
    }

}
