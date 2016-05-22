package com.nuclearthinking.myheroagency.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.nuclearthinking.myheroagency.utils.Constants;
import org.slf4j.impl.SimpleLoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;

public class GameData implements Serializable {

    private Player player;
    private BattleData battleData;

    public static void save(GameData gameData) {
        Kryo kryo = new Kryo();
        try (Output output = new Output(new FileOutputStream(Constants.SAVE_NAME))) {
            kryo.writeObject(output, gameData);
        } catch (FileNotFoundException ex) {
            new SimpleLoggerFactory().getLogger("GameData").info("Can't save game to file {}", Constants.SAVE_NAME);
        }
    }

    public static GameData load() {
        Kryo kryo = new Kryo();
        GameData gameData = null;
        try (Input input = new Input(new FileInputStream(Constants.SAVE_NAME))) {
            gameData = kryo.readObject(input, GameData.class);
        } catch (FileNotFoundException ex) {
            new SimpleLoggerFactory().getLogger("GameData").info("Can't load game from file {}", Constants.SAVE_NAME);
        }
        if (gameData == null) {
            new SimpleLoggerFactory().getLogger("GameData").info("Loading game is failed, create new one");
            return new GameData();
        } else {
            new SimpleLoggerFactory().getLogger("GameData").info("Loading success");
            return gameData;
        }
    }

    public BattleData getBattleData() {
        return battleData;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
