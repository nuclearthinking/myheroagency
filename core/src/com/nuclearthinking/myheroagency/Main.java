package com.nuclearthinking.myheroagency;

import com.badlogic.gdx.Game;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.manager.ScreenManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.PropertyConfigurator;

@Slf4j(topic = "Main")
public final class Main extends Game {
    @Override
    public void create() {
        Thread.currentThread().setName("My Hero Agency");
        PropertyConfigurator.configure("log4j.properties");
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showScreen(ScreenEnum.LOADING_SCREEN);
    }

}
