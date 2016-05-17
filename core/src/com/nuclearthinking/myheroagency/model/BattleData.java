package com.nuclearthinking.myheroagency.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 17.05.2016
 * Time: 23:47
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class BattleData implements Serializable {

    List<Player> enemies;

    public BattleData() {
        enemies = new ArrayList<Player>();
    }

    public List<Player> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Player> enemies) {
        this.enemies = enemies;
    }

    public void addEnemy(Player player) {
        if (enemies != null) {
            enemies.add(player);
        }
    }
}
