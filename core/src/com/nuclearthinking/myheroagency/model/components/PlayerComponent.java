package com.nuclearthinking.myheroagency.model.components;

import com.nuclearthinking.myheroagency.model.quest.Quest;
import com.nuclearthinking.myheroagency.model.skills.Stats;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mkuksin on 01.09.2016.
 */
@Slf4j(topic = "PlayerComponent")
@ToString
public final class PlayerComponent extends GameActor {

    public static final int BASE_HP_MAX = 100;
    public static final int BASE_RUN_SPD = 100;

    private Map<String, Quest> quests = new HashMap<String, Quest>();

    private @Getter int lvl = 1;
    private @Getter int exp = 0;

    private static final int maxLvl = 10;

    private static final int needToLvlUp[] = {0, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000}; //TODO: Статичный костыль

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

    public int getSpeed(){
        return (int) calcStat(Stats.RUN_SPEED, PlayerComponent.BASE_RUN_SPD);
    }

    public int getMaxHp(){
        return (int) calcStat(Stats.MAX_HP, PlayerComponent.BASE_HP_MAX);
    }

    @Override
    public boolean isPlayer(){
        return true;
    }
}
