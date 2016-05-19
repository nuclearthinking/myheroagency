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
        skin = asset.get("ui/ui.json", Skin.class);
        atlas = asset.get("ui/ui.atlas", TextureAtlas.class);
        table = new Table();
        skin.addRegions(atlas);
        defend = createButton("defendButtonLabel", .9f, skin, "kramola");
        attack = createButton("attackButtonLabel", .7f, skin, "kramola");
        done = createButton("doneButtonLabel", .7f, skin, "kramola");
    }

    @Override
    public void buildStage() {
        table.setFillParent(true);
        table.setDebug(Constants.DEBUG);
        table.left().bottom();
        table.row();
        table.add(attack).left().bottom().width(100).height(40).pad(10).fill(false);
        table.add(defend).left().bottom().width(100).height(40).pad(10).fill(false);
        table.add(done).right().bottom().width(100).height(40).pad(10).expandX();


        stage.addActor(table);
    }

    private TextButton.TextButtonStyle getButtonStyle() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new FontFactory().getRobotoBold(20);
        return style;
    }

    private TextButton createButton(String localKey, float fontScale, Skin skin, String font) {
        TextButton button = new TextButton(locale.get(localKey), skin, font);
        button.getLabel().setFontScale(fontScale);
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
