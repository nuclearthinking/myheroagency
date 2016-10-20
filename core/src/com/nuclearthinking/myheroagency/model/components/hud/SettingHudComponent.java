package com.nuclearthinking.myheroagency.model.components.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.nuclearthinking.myheroagency.controller.button.menu.ExitListener;
import com.nuclearthinking.myheroagency.controller.button.menu.SaveLayerListener;
import com.nuclearthinking.myheroagency.i18n.Localization;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 10.10.2016.
 */
public class SettingHudComponent implements Component {
    public static final Table table = new Table();
    public static boolean isShowTable = true;

    private @Getter final Localization locale = new Localization(this.getClass());

    private @Getter @Setter Label titleLabel;
    private @Getter @Setter Label widthLabel;
    private @Getter @Setter Label heightLabel;
    private @Getter @Setter TextButton exit;
    private @Getter @Setter TextButton save;
    private @Getter @Setter TextField height;
    private @Getter @Setter TextField width;
    private @Getter @Setter ExitListener exitListener;
    private @Getter @Setter SaveLayerListener saveLayerListener;
}
