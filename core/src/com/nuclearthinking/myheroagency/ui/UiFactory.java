package com.nuclearthinking.myheroagency.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.nuclearthinking.myheroagency.controller.Asset;

import java.util.HashMap;

/**
 * Created by Izonami on 20.05.2016.
 */
public final class UiFactory {

    //TODO: Можно сделать единую карту для всех элементов, но существует риск словить одинаковые названия
    private static final HashMap<String, TextButton> textButtonHashMap = new HashMap<String, TextButton>();
    private static final HashMap<String, TextField> textFieldHashMap = new HashMap<String, TextField>();
    private static final HashMap<String, Label> labelHashMap = new HashMap<String, Label>();
    private static Skin skin;

    public UiFactory(){
        if(skin == null){
            skin = Asset.getInstance().get("ui/ui.json", Skin.class);
            skin.addRegions(Asset
                    .getInstance()
                    .get("ui/ui.atlas", TextureAtlas.class));
        }
    }

    public final TextButton getTextButton(final String name) {
        TextButton textButton = textButtonHashMap.get(name);
        if (textButton == null) {
            textButton = new TextButton(name, skin, "kramola");
            textButtonHashMap.put(name, textButton);
        }
        return textButton;
    }

    public final Label getLabel(final String name) {
        Label label = labelHashMap.get(name);
        if (label == null) {
            label = new Label(name, skin, "kramola");
            labelHashMap.put(name, label);
        }
        return label;
    }

    //TODO: Для размера экрана вылазит баг, когда высота и ширина совпадают, по хорошему в ползунок разрешение переделать или в селект
    public final TextField getTextField(final String name) {
        TextField textField = textFieldHashMap.get(name);
        if (textField == null) {
            textField = new TextField(name, skin, "kramola");
            textFieldHashMap.put(name, textField);
        }
        return textField;
    }

    //TODO: Пока побудет затычкой для SelectBox, так как для его создание не нужно имя
    public Skin getSkin(){
        return skin;
    }

}
