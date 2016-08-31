package com.nuclearthinking.myheroagency;

import com.badlogic.gdx.Game;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public final class Main extends Game {

    private final static String LOG_FILE_NAME = "myheroagency.log";

    @Override
    public void create() {
        Thread.currentThread().setName("My Hero Agency");
        //initLogging(Constants.DEBUG);
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showScreen(ScreenEnum.LOADING_SCREEN);
    }

    private void initLogging(final boolean debug) {
        if (debug) {
            System.setProperty("org.slf4j.simpleLogger.logFile", "System.out");
            System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");
        } else {
            if(createLogFile()){
                System.setProperty("org.slf4j.simpleLogger.logFile", LOG_FILE_NAME);
                System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");
            }
            else{
                log.debug(String.format("Can't create logfile %s", LOG_FILE_NAME));
                System.setProperty("org.slf4j.simpleLogger.logFile", LOG_FILE_NAME);
            }
        }
        System.setProperty("org.slf4j.simpleLogger.showDateTime", "true");
        System.setProperty("org.slf4j.simpleLogger.dateTimeFormat", "HH:mm:ss.SSS");
        System.setProperty("org.slf4j.simpleLogger.showThreadName", "true");
    }

    //Возвращает true, если файл существует или создался
    private boolean createLogFile() {
        File file;
        try {
            file = new File(LOG_FILE_NAME);
            if (file.exists()) {
                return true;
            } else {
                return file.createNewFile();
            }
        } catch (IOException ex) {
            log.debug("Can't create logfile in log directory");
            return false;
        }
    }

}
