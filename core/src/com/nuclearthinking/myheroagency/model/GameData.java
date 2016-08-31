package com.nuclearthinking.myheroagency.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.nuclearthinking.myheroagency.model.actor.Player;
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
public class GameData implements Serializable {

    private @Getter @Setter Player player;

    public static void save(@NonNull final GameData gameData) {
        val kryo = new Kryo();
        try (val output = new Output(new FileOutputStream(Constants.SAVE_NAME))) {
            kryo.writeObject(output, gameData);
        } catch (FileNotFoundException ex) {
            log.debug("Can't save game to file {}", Constants.SAVE_NAME);
        }
    }

    public static GameData load() {
        val kryo = new Kryo();
        GameData gameData = null;
        try (val input = new Input(new FileInputStream(Constants.SAVE_NAME))) {
            gameData = kryo.readObject(input, GameData.class);
        } catch (FileNotFoundException ex) {
            log.debug("Can't load game from file {}", Constants.SAVE_NAME);
        }
        if (gameData == null) {
            log.debug("Loading game is failed, create new one");
            return new GameData();
        } else {
            log.debug("Loading success");
            return gameData;
        }
    }

}
