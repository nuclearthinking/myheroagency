package com.nuclearthinking.myheroagency.model.ui.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.nuclearthinking.myheroagency.model.ui.UiFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 03.10.2016.
 */
public final class HudComponent implements Component {

    private @Getter final UiFactory uiFactory = new UiFactory();
    private @Getter @Setter static Stage stage;
    private @Getter @Setter Entity actor;
}
