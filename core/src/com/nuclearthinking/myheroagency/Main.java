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
        //Загружаем файл с конфигурацией логера
        PropertyConfigurator.configure("log4j.properties");
        //Инициализируем менеджер экранов
        ScreenManager.getInstance().initialize(this);
        //Устанавливаем экран
        ScreenManager.getInstance().showScreen(ScreenEnum.LOADING_SCREEN);
    }
}
