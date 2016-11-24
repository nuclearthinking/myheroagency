package com.nuclearthinking.myheroagency.model.components.hud;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.nuclearthinking.myheroagency.i18n.Localization;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mkuksin on 10.10.2016.
 */
public final class SettingHudComponent implements Component {

    public static final Table table = new Table();

    private  @Getter final Localization locale = new Localization(this.getClass());

    private @Getter @Setter Label titleLabel;
    private @Getter @Setter Label widthLabel;
    private @Getter @Setter Label heightLabel;
    private @Getter @Setter TextButton exit;
    private @Getter @Setter TextButton save;
    private @Getter @Setter TextField height;
    private @Getter @Setter TextField width;

    //На случай если нужно будет перезагрузить локаль в слое
    public void reloadLabel(){
        locale.loadBundle();

        titleLabel.setText(locale.get("mainTitle"));
        widthLabel.setText(locale.get("widthLabel"));
        heightLabel.setText(locale.get("heightLabel"));
        exit.setText(locale.get("buttonBack"));
        save.setText(locale.get("buttonSave"));
    }
}
