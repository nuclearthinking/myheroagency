package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.controller.battle.BattleState;
import com.nuclearthinking.myheroagency.controller.battle.StartBattleState;
import com.nuclearthinking.myheroagency.model.GameData;
import com.nuclearthinking.myheroagency.utils.AccurateListener;
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
    Image backgroundImage, manImage;
    Texture backgroundTexture, manTexture, castleTexture;
    OrthographicCamera camera;
    BattleState state;

    public BattleScreen(GameData gameData) {
        this.gameData = gameData;

        state = new StartBattleState(gameData.getBattleData());
        asset = Asset.getInstance();
        skin = asset.get("ui/ui.json", Skin.class);
        atlas = asset.get("ui/ui.atlas", TextureAtlas.class);
        table = new Table();
        skin.addRegions(atlas);
        defend = createButton("defendButtonLabel", .9f, skin, "kramola");
        attack = createButton("attackButtonLabel", .7f, skin, "kramola");
        done = createButton("doneButtonLabel", .7f, skin, "kramola");
        manTexture = asset.get("img/man.png");
        backgroundTexture = asset.get("img/battleBackground.png");
        castleTexture = asset.get("img/splash.png");
        camera = new OrthographicCamera(Constants.GAME_W, Constants.GAME_H);
    }

    @Override
    public void buildStage() {
        backgroundImage = new Image(backgroundTexture);
        manImage = new Image(manTexture);
        table.setFillParent(true);
        table.setDebug(Constants.DEBUG);
        table.left().bottom();
        table.row();
        table.add(attack).left().bottom().width(100).height(40).pad(10).fill(false);
        table.add(defend).left().bottom().width(100).height(40).pad(10).fill(false);
        table.add(done).right().bottom().width(100).height(40).pad(10).expandX();

        stage.addActor(backgroundImage);
        stage.addActor(manImage);
        stage.addActor(table);
        manImage.setPosition(50, 100);
        manImage.addListener(new ManListener(manTexture));
        attack.addListener(new AttackListener());
        defend.addListener(new DefenseListener());
        done.addListener(new EndTurnListener());
        camera.setToOrtho(false);
    }

    private TextButton createButton(String localKey, float fontScale, Skin skin, String font) {
        TextButton button = new TextButton(locale.get(localKey), skin, font);
        button.getLabel().setFontScale(fontScale);
        return button;
    }

    @Override
    public void render(float delta) {

        camera.update();
        stage.getBatch().setProjectionMatrix(camera.combined);
        super.render(delta);
        stage.getBatch().begin();
        stage.getBatch().end();

    }

    @Override
    public void dispose() {
        super.dispose();
    }


    class ManListener extends AccurateListener {

        public ManListener(Texture texture) {
            super(texture);
        }

        @Override
        public boolean accurateClicked(InputEvent event, float x, float y) {
            System.out.println("SPRITE TOUCHED");
            return true;
        }
    }

    class AttackListener extends ClickListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            state.attack();
            return true;
        }
    }

    class DefenseListener extends ClickListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            state.defense();

            return true;
        }
    }

    class EndTurnListener extends ClickListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            state.endTurn();
            return true;
        }
    }
}
