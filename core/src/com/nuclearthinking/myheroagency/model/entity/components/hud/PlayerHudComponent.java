package com.nuclearthinking.myheroagency.model.entity.components.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 05.10.2016.
 */
public class PlayerHudComponent implements Component {
    private @Getter static final Table table = new Table();
    private @Getter @Setter static Label playerLvl;
    private @Getter @Setter static Label playerHp;
    private @Getter @Setter static Label playerMp;
    private @Getter @Setter static Label playerExp;
}
