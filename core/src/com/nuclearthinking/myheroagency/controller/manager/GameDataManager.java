package com.nuclearthinking.myheroagency.controller.manager;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.nuclearthinking.myheroagency.controller.systems.PlayerSystem;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;

@Slf4j(topic = "GameData")
public final class GameDataManager implements Serializable {

    private @Getter @Setter PlayerSystem player;

    public static void save(@NonNull final GameDataManager gameDataManager) {
        val kryo = new Kryo();
        try (val output = new Output(new FileOutputStream(Constants.SAVE_NAME))) {
            kryo.writeObject(output, gameDataManager);
        } catch (FileNotFoundException ex) {
            log.debug("Can't save game to file {}", Constants.SAVE_NAME);
        }
    }

    public static GameDataManager load() {
        val kryo = new Kryo();
        GameDataManager gameDataManager = null;
        try (val input = new Input(new FileInputStream(Constants.SAVE_NAME))) {
            gameDataManager = kryo.readObject(input, GameDataManager.class);
        } catch (FileNotFoundException ex) {
            log.debug("Can't load game from file {}", Constants.SAVE_NAME);
        }
        if (gameDataManager == null) {
            log.debug("Loading game is failed, create new one");
            return new GameDataManager();
        } else {
            log.debug("Loading success");
            return gameDataManager;
        }
    }

}
