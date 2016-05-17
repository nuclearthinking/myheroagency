package com.nuclearthinking.myheroagency.model;

import java.io.Serializable;

public class Player implements Serializable {

    private byte level;

    private int exp;

    private int attack;

    private int health;

    public Player() {
    }

    public Player(int attack, int health) {
        this.attack = attack;
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
