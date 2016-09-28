package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 26.09.2016.
 */
public class NpcComponent implements Component {
    public static final float BASE_HP_REG = 10;
    public static final float BASE_MP_REG = 10;
    public static final int BASE_HP_MAX = 100;
    public static final int BASE_MP_MAX = 100;
    public static final int BASE_PATK = 10;
    public static final int BASE_MATK = 10;
    public static final int BASE_PDEF = 10;
    public static final int BASE_MDEF = 10;
    public static final int BASE_PATK_SPD = 10;
    public static final int BASE_MATK_SPD = 10;
    public static final int BASE_CRIT_RATE = 10;
    public static final int BASE_MCRIT_RATE = 10;
    public static final int BASE_RUN_SPD = 100;

    private @Getter @Setter static int baseSTR = 10;
    private @Getter @Setter static int baseCON = 10;
    private @Getter @Setter static int baseDEX = 10;
    private @Getter @Setter static int baseINT = 10;
    private @Getter @Setter static int baseWIT = 10;
    private @Getter @Setter static int baseMEN = 10;

    private @Getter static int level = 1;
}
