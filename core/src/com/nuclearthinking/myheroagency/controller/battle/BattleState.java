package com.nuclearthinking.myheroagency.controller.battle;

import com.nuclearthinking.myheroagency.model.BattleData;

/**
 * Date: 17.05.2016
 * Time: 21:32
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public abstract class BattleState {

    BattleData battleData;

    public BattleState(BattleData battleData) {
        this.battleData = battleData;
    }


    public abstract void act();


}
