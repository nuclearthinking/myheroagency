package com.nuclearthinking.myheroagency.model.components;

import com.badlogic.ashley.core.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 03.10.2016.
 */
public abstract class GameActor implements Component {

    protected @Getter int baseHpReg;
    protected @Getter int baseMpReg;
    protected @Getter int baseHpMax;
    protected @Getter int baseMpMax;
    protected @Getter int basePAtk;
    protected @Getter int baseMAtk;
    protected @Getter int basePDef;
    protected @Getter int baseMDef;
    protected @Getter int basePAtkSpd;
    protected @Getter int baseMAtkSpd;
    protected @Getter int basePCritRate;
    protected @Getter int baseMCritRate;
    protected @Getter int basePCritChance;
    protected @Getter int baseMCritChance;
    protected @Getter int baseRunSpd;
    protected @Getter @Setter int baseCON;
    protected @Getter @Setter int baseDEX;
    protected @Getter @Setter int baseINT;
    protected @Getter @Setter int baseMEN;
    protected @Getter @Setter int baseSTR;
    protected @Getter @Setter int baseWIT;

    protected @Getter int curHp;
    protected @Getter int level;

    protected abstract void init();

    public GameActor(){
    }
}
