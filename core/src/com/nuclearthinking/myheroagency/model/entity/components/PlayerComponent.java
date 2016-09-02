package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 01.09.2016.
 */
public class PlayerComponent implements Component {

    private static PlayerComponent instance;

    private @Getter final float baseHpReg;
    private @Getter final float baseMpReg;
    private @Getter final int baseHpMax;
    private @Getter final int baseMpMax;
    private @Getter final int basePAtk;
    private @Getter final int baseMAtk;
    private @Getter final int basePDef;
    private @Getter final int baseMDef;
    private @Getter final int basePAtkSpd;
    private @Getter final int baseMAtkSpd;
    private @Getter final int baseCritRate;
    private @Getter final int baseMCritRate;
    private @Getter final int baseRunSpd;

    private @Getter @Setter int baseSTR;
    private @Getter @Setter int baseCON;
    private @Getter @Setter int baseDEX;
    private @Getter @Setter int baseINT;
    private @Getter @Setter int baseWIT;
    private @Getter @Setter int baseMEN;

    private PlayerComponent(){
        baseSTR = 10;
        baseCON = 10;
        baseDEX = 10;
        baseINT = 10;
        baseWIT = 10;
        baseMEN = 10;
        baseHpMax = 100;
        baseMpMax = 100;
        baseHpReg = 0.1f;
        baseMpReg = 0.1f;
        basePAtk = 10;
        baseMAtk = 10;
        basePDef = 10;
        baseMDef = 10;
        basePAtkSpd = 50;
        baseMAtkSpd = 50;
        baseCritRate = 5;
        baseMCritRate = 1;
        baseRunSpd = 100;
    }

    public static PlayerComponent getInstance() {
        if (instance == null) {
            instance = new PlayerComponent();
        }
        return instance;
    }

}
