package com.nuclearthinking.myheroagency.model;

import java.io.Serializable;

public class GameData implements Serializable {


    private int qutestNumber;

    private String test;

    private Player player;

    public int getQutestNumber() {
        return qutestNumber;
    }

    public void setQutestNumber(int qutestNumber) {
        this.qutestNumber = qutestNumber;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
