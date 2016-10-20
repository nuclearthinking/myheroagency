package com.nuclearthinking.myheroagency.model.components.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 05.10.2016.
 */
public class PlayerHudComponent implements Component {
    public  static final Table table = new Table();
    private @Getter @Setter Label playerLvl;
    private @Getter @Setter Label playerHp;
    private @Getter @Setter Label playerMp;
    private @Getter @Setter Label playerExp;
}
