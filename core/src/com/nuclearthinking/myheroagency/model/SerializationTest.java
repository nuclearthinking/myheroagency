package com.nuclearthinking.myheroagency.model;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Vlad on 15.05.2016.
 */
public class SerializationTest {


    public static void main(String[] args) throws FileNotFoundException {
        GameData gameData = new GameData();
        gameData.setTest("Test test tetetest");
        Player player = new Player();
        player.setExp(123);
        player.setLevel(11);
        gameData.setPlayer(player);


        Kryo kryo = new Kryo();

        Output output = new Output(new FileOutputStream("kryotest.sav"));
        kryo.writeObject(output, gameData);
        output.close();

        Input input = new Input(new FileInputStream("kryotest.sav"));
        GameData newGamedata = kryo.readObject(input, GameData.class);

        System.out.println(newGamedata.getQutestNumber());
        System.out.println(newGamedata.getTest());
        System.out.println(newGamedata.getPlayer().getExp());
        System.out.println(newGamedata.getPlayer().getLevel());
    }
}
