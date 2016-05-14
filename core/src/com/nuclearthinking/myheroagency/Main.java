package com.nuclearthinking.myheroagency;

import com.badlogic.gdx.Game;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;

import java.io.File;
import java.io.IOException;

public class Main extends Game {
    private final static String LOG_FILE_NAME = "myheroagency.log";

    @Override
    public void create() {
        Thread.currentThread().setName("My Hero Agency");
        initLogging(false); // если false то пишем в консоль, если true то в файл myheroagency.log
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showScreen(ScreenEnum.LOADING_SCREEN);
    }

    private void initLogging(boolean fileLogging) {
        if (fileLogging) {
            if (new File(LOG_FILE_NAME).exists()) {
                System.setProperty("org.slf4j.simpleLogger.logFile", LOG_FILE_NAME);
            } else if (createLogFile()) {
                System.err.println(String.format("Can't create logfile %s", LOG_FILE_NAME));
                System.setProperty("org.slf4j.simpleLogger.logFile", LOG_FILE_NAME);
            }
        } else {
            System.setProperty("org.slf4j.simpleLogger.logFile", "System.out");
        }
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
        System.setProperty("org.slf4j.simpleLogger.showDateTime", "true");
        System.setProperty("org.slf4j.simpleLogger.dateTimeFormat", "yyyy-MM-dd HH:mm:ss.SSS");
        System.setProperty("org.slf4j.simpleLogger.showThreadName", "true");
    }

    private boolean createLogFile() {
        File file;
        try {
            file = new File("myheroagency.log");
            if (!file.exists()) {
                return file.createNewFile();
            } else {
                return true;
            }
        } catch (IOException ex) {
            System.err.println("Can't create logfile in log directory");
            return false;
        }
    }

}
