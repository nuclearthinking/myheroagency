package com.nuclearthinking.myheroagency.model.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.nuclearthinking.myheroagency.controller.manager.AssetsManager;
import com.nuclearthinking.myheroagency.i18n.Localization;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

import static com.nuclearthinking.myheroagency.utils.Constants.*;

/**
 * Created by Izonami on 20.05.2016.
 */
public final class UiFactory {

    private static final Map<String, TextButton> textButtonHashMap = new HashMap<String, TextButton>();
    private static final Map<String, TextField> textFieldHashMap = new HashMap<String, TextField>();
    private static final Map<String, Label> labelHashMap = new HashMap<String, Label>();
    //TODO: Пока побудет затычкой для SelectBox, так как для его создание не нужно имя
    private @Getter static Skin skin;

    public UiFactory(){
        if(skin == null){
            skin = AssetsManager.getInstance().get(UI_JSON, Skin.class);
            skin.addRegions(AssetsManager
                    .getInstance()
                    .get(UI_ATLAS, TextureAtlas.class));
        }
    }

    @Deprecated
    public final TextButton getTextButton(final String name) {
        TextButton textButton = textButtonHashMap.get(name);
        if (textButton == null) {
            textButton = new TextButton(name, skin, UI_SKIN_TYPE);
            textButtonHashMap.put(name, textButton);
        }
        return textButton;
    }

    public final TextButton getTextButton(final String name, final String screenName, @NonNull final Localization locale) {
        final String key = locale.get(name);

        TextButton textButton = textButtonHashMap.get(key+screenName);
        if (textButton == null) {
            textButton = new TextButton(key, skin, UI_SKIN_TYPE);
            textButtonHashMap.put(key+screenName, textButton);
        }
        return textButton;
    }

    @Deprecated
    public final Label getLabel(final String name) {
        Label label = labelHashMap.get(name);
        if (label == null) {
            label = new Label(name, skin, UI_SKIN_TYPE);
            labelHashMap.put(name, label);
        }
        return label;
    }

    public final Label getLabel(final String name, final String screenName, @NonNull final Localization locale) {
        final String key = locale.get(name);

        Label label = labelHashMap.get(key+screenName);
        if (label == null) {
            label = new Label(key, skin, UI_SKIN_TYPE);
            labelHashMap.put(key+screenName, label);
        }
        return label;
    }

    //TODO: Для размера экрана вылазит баг, когда высота и ширина совпадают, по хорошему в ползунок разрешение переделать или в селект
    @Deprecated
    public final TextField getTextField(final String name) {
        TextField textField = textFieldHashMap.get(name);
        if (textField == null) {
            textField = new TextField(name, skin, UI_SKIN_TYPE);
            textFieldHashMap.put(name, textField);
        }
        return textField;
    }

    public final TextField getTextField(final String name, final String screenName, @NonNull final Localization locale) {
        final String key = locale.get(name);

        TextField textField = textFieldHashMap.get(key+screenName);
        if (textField == null) {
            textField = new TextField(key, skin, UI_SKIN_TYPE);
            textFieldHashMap.put(key+screenName, textField);
        }
        return textField;
    }
}
