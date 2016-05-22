package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nuclearthinking.myheroagency.controller.Asset;
import com.nuclearthinking.myheroagency.model.GameData;
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
    Sprite sprite, castle;
    OrthographicCamera camera;

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
//        table.add(attack).left().bottom().width(100).height(40).pad(10).fill(false);
//        table.add(defend).left().bottom().width(100).height(40).pad(10).fill(false);
//        table.add(done).right().bottom().width(100).height(40).pad(10).expandX();

        stage.addActor(backgroundImage);
//        stage.addActor(manImage);
        stage.addActor(table);
//        manImage.setPosition(50, 100);
//        manImage.addListener(new ManListner());
        castle = new Sprite(castleTexture);
        sprite = new Sprite(manTexture);
        castle.setPosition(200, 200);
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
        castle.draw(stage.getBatch());
        sprite.draw(stage.getBatch());
        if (Gdx.input.justTouched()) {
//            System.out.println("Toched");
            Vector3 tochPoint = new Vector3();
            camera.unproject(tochPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));


            if (castle.getBoundingRectangle().contains(tochPoint.x, tochPoint.y)) {
                System.out.println("toch on sprite");
                castle.getTexture().getTextureData().prepare();
                Pixmap pixmap = castle.getTexture().getTextureData().consumePixmap();
                System.out.println("touch point " + tochPoint.toString());
                System.out.println(pixmap.getPixel(20,20));
                System.out.println(pixmap.getPixel((int) tochPoint.x, (int) tochPoint.y));
            }
        }
        stage.getBatch().end();

    }

    @Override
    public void dispose() {
        super.dispose();
    }


    class ManListner extends ClickListener {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            logger.debug("Man pressed");
            logger.debug("is visual pressed {}", isVisualPressed());
            return true;
        }
    }
}
