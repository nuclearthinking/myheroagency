package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.Gdx;

/**
 * Created by Izonami on 19.06.2016.
 */
public final class ScreenController {

    public void splashUpdate(){
        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(66) || Gdx.input.isKeyPressed(131)) {
            com.nuclearthinking.myheroagency.controller.manager.ScreenManager.getInstance().showScreen( ScreenEnum.MAIN_MENU_SCREEN );
        }
    }
}
