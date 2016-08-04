package com.nuclearthinking.myheroagency.model.skills;

import java.util.NoSuchElementException;

/**
 * Created by Izonami on 03.08.2016.
 */
public enum Stats {

    MAX_HP("maxHp"),
    MAX_MP("maxMp"),
    REGENERATE_HP_RATE("regHp"),
    REGENERATE_MP_RATE("regMp"),

    RUN_SPEED("runSpd"),

    POWER_DEFENCE("pDef"),
    MAGIC_DEFENCE("mDef"),
    POWER_ATTACK("pAtk"),
    MAGIC_ATTACK("mAtk"),
    POWER_ATTACK_SPEED("pAtkSpd"),
    MAGIC_ATTACK_SPEED("mAtkSpd"),

    CRITICAL_DAMAGE("critDamage"),
    CRITICAL_BASE("baseCrit"),
    CRITICAL_RATE("critRate"),

    MCRITICAL_DAMAGE("mCritDamage"),
    MCRITICAL_BASE("mBaseCrit"),
    MCRITICAL_RATE("mCritRate"),


    STAT_STR("STR"),
    STAT_CON("CON"),
    STAT_DEX("DEX"),
    STAT_INT("INT"),
    STAT_WIT("WIT"),
    STAT_MEN("MEN");

    public static final int NUM_STATS = values().length;

    private String _value;

    public String getValue() {
        return _value;
    }

    private Stats(String s) {
        _value = s;
    }

    public static Stats valueOfXml(String name) {
        for(Stats s : values())
            if(s.getValue().equals(name))
                return s;

        throw new NoSuchElementException("Unknown name '" + name + "' for enum BaseStats");
    }

    @Override
    public String toString() {
        return _value;
    }

}
