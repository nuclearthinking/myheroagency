package com.nuclearthinking.myheroagency.model.entity.components.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 03.10.2016.
 */
public class UtilsHudComponent implements Component {
    private @Getter static final Table table = new Table();
    private @Getter @Setter static Label fps;
}
