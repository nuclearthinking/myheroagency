package com.nuclearthinking.myheroagency.model;

import java.io.Serializable;

class Player implements Serializable {

    private byte level;

    private int exp;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
