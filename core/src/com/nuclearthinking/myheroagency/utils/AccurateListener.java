package com.nuclearthinking.myheroagency.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Date: 22.05.2016
 * Time: 22:50
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class AccurateListener extends InputListener {

    Texture texture;

    public AccurateListener(Texture texture) {
        this.texture = texture;
    }

    public AccurateListener(Sprite sprite) {
        this(sprite.getTexture());
    }

    @Override
    public final boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        super.touchDown(event, x, y, pointer, button);
        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }
        Pixmap pixmap = texture.getTextureData().consumePixmap();
        Color color = new Color(pixmap.getPixel((int) x, (int) y));
        pixmap.dispose();
        if (color.a > 0f) {
            accurateClicked(event, x, y);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public final void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
    }

    @Override
    public final void touchDragged(InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);
    }

    @Override
    public final boolean mouseMoved(InputEvent event, float x, float y) {
        return super.mouseMoved(event, x, y);
    }

    @Override
    public final void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);
    }

    @Override
    public final void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);
    }

    @Override
    public final boolean scrolled(InputEvent event, float x, float y, int amount) {
        return super.scrolled(event, x, y, amount);
    }

    @Override
    public final boolean keyDown(InputEvent event, int keycode) {
        return super.keyDown(event, keycode);
    }

    @Override
    public final boolean keyUp(InputEvent event, int keycode) {
        return super.keyUp(event, keycode);
    }

    @Override
    public final boolean keyTyped(InputEvent event, char character) {
        return super.keyTyped(event, character);
    }

    public boolean accurateClicked(InputEvent event, float x, float y) {
        return true;
    }
}
