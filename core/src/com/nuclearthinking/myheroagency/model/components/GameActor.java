package com.nuclearthinking.myheroagency.model.components;

import com.badlogic.ashley.core.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 03.10.2016.
 */
public abstract class GameActor implements Component {

    private @Getter @Setter int baseCON;
    private @Getter @Setter int baseDEX;
    private @Getter @Setter int baseINT;
    private @Getter @Setter int baseMEN;
    private @Getter @Setter int baseSTR;
    private @Getter @Setter int baseWIT;
    private @Getter int level;
    private @Getter String name = "Василий";

    private @Getter int curHp;

    public GameActor(){
        baseCON = 10;
        baseDEX = 10;
        baseINT = 10;
        baseMEN = 10;
        baseSTR = 10;
        baseWIT = 10;
        level = 1;
    }
}
