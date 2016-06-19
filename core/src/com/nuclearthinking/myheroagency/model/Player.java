package com.nuclearthinking.myheroagency.model;

import java.io.Serializable;

public class Player implements Serializable {

    private byte level;

    private int exp;

    public int getLevel() {
        return level;
    }

    public void setLevel(final byte level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(final int exp) {
        this.exp = exp;
    }
}
