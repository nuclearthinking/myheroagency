package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.GameData;
import com.nuclearthinking.myheroagency.ui.font.FontFactory;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Date: 05.05.2016
 * Time: 7:01
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class BattleScreen extends AbstractScreen {

    Asset asset;
    Skin skin;
    TextureAtlas atlas;
    Table table;
    TextButton defend, attack, done;

    public BattleScreen(GameData gameData) {
        this.gameData = gameData;
        asset = Asset.getInstance();
        skin = asset.get("ui/ui.json");
        atlas = asset.get("ui/ui.atlas");
        table = new Table();
        defend = createButton("defendButtonLabel", .9f, skin);
        attack = createButton("attackButtonLabel", .7f, skin);
        done = createButton("doneButtonLabel", .7f, skin);
    }

    @Override
    public void buildStage() {
        skin.addRegions(atlas);
        table.setFillParent(true);
        table.setDebug(Constants.DEBUG);
    }

    private TextButton.TextButtonStyle getButtonStyle() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new FontFactory().getRobotoBold(16);
        return style;
    }

    private TextButton createButton(String localKey, float fontScale, Skin skin) {
        TextButton button = new TextButton(locale.get(localKey), skin);
        button.getLabel().setFontScale(fontScale);
        button.setStyle(getButtonStyle());
        return button;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
