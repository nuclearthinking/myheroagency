package com.nuclearthinking.myheroagency.model.components;

import com.badlogic.ashley.core.Entity;
import com.nuclearthinking.myheroagency.controller.systems.PlayerSystem;
import com.nuclearthinking.myheroagency.controller.systems.Speaker;
import com.nuclearthinking.myheroagency.model.skills.Stats;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by mkuksin on 03.10.2016.
 */
@ToString
public final class MonsterComponent extends GameActor implements Speaker {

    private @Getter @Setter Entity target;

    public MonsterComponent(){
        super();
    }

    public int getSpeed(){
        return (int) calcStat(Stats.RUN_SPEED, baseRunSpd);
    }

    @Override
    public boolean isMonster(){
        return true;
    }

    @Override
    public void showDialog(@NonNull PlayerSystem player, String command) {
        System.out.println("MMMMMKOSNETE");
    }
}
