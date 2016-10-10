package com.nuclearthinking.myheroagency.model.entity.components.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.nuclearthinking.myheroagency.ui.UiFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 03.10.2016.
 */
public class HudComponent implements Component {
    public static final UiFactory uiFactory = new UiFactory();
    private @Getter @Setter static Stage stage;
}
