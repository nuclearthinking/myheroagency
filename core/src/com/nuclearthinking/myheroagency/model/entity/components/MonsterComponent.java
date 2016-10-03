package com.nuclearthinking.myheroagency.model.entity.components;

import com.badlogic.ashley.core.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 03.10.2016.
 */
public class MonsterComponent extends GameActor {

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
    public static final int BASE_RUN_SPD = 30;

    private @Getter @Setter Entity target;

    public MonsterComponent(){
        super();
    }

}
