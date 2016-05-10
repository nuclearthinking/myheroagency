package com.nuclearthinking.myheroagency;

import com.badlogic.gdx.Game;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;

public class Main extends Game {
    @Override
    public void create() {
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showScreen( ScreenEnum.START_SCREEN );
    }


}
