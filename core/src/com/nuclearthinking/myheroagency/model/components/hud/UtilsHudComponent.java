package com.nuclearthinking.myheroagency.model.components.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 03.10.2016.
 */
public final class UtilsHudComponent implements Component {
    public static final Table table = new Table();
    private @Getter @Setter Label fps;
}
