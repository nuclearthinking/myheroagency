package com.nuclearthinking.myheroagency.model.components;

import com.nuclearthinking.myheroagency.model.quest.Quest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mkuksin on 01.09.2016.
 */
@Slf4j(topic = "PlayerComponent")
public final class PlayerComponent extends GameActor {

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

    private Map<String, Quest> quests = new HashMap<String, Quest>();

    private @Getter int lvl = 1;
    private @Getter int exp = 0;

    private static final int maxLvl = 10;

    private static final int needToLvlUp[] = {0, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000}; //TODO: Статичный костыль

    @Override
    protected void init() {

    }

    public PlayerComponent(){
        super();
    }

    public void setLevel(final int lvl){
        if(this.lvl + lvl > maxLvl){
            this.lvl = maxLvl;
            log.info("You have maximum level");
            return;
        }else
            this.lvl = lvl;
    }

    public void setExp(final int exp){
        if(lvl < maxLvl){
            int tmp = this.exp + exp;
            for (int i = 0; i < needToLvlUp.length; i++) {
                if(tmp >= needToLvlUp[i] && lvl < i+1){
                   setLevel(lvl+1);
                }
                else {
                    this.exp = exp;
                }
            }
        }
        else
            log.info("You have maximum level");
    }

    public Quest getQuest(final String questId){
        return quests.get(questId);
    }
}
