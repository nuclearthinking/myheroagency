package com.nuclearthinking.myheroagency.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.nuclearthinking.myheroagency.controller.ScreenEnum;
import com.nuclearthinking.myheroagency.controller.ScreenManager;
import com.nuclearthinking.myheroagency.model.LoadingBar;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Created by Izonami on 10.05.2016.
 */
public class LoadingScreen extends AbstractScreen {

    private Image logo, loadingFrame, loadingBarHidden, screenBg, loadingBg;

    private float startX, endX;
    private float percent;
    private boolean isFirst = true; // Два варианта загрузки, меняй true\false

    private Actor loadingBar;

    @Override
    public void buildStage() {
        preLoadAsset(); // Загружает ассет для экрана загузки
        ScreenManager.getInstance().getAssetManager().finishLoading(); // Проверяет загруженность ассета

        TextureAtlas atlas = ScreenManager.getInstance().getAssetManager().get("img/loading.pack", TextureAtlas.class); //Получает ассет

        if(isFirst){
            //Разбивает полученный ассет на регионы, описанные в loading.pack
            logo = new Image(atlas.findRegion("libgdx-logo"));
            loadingFrame = new Image(atlas.findRegion("loading-frame"));
            loadingBarHidden = new Image(atlas.findRegion("loading-bar-hidden"));
            screenBg = new Image(atlas.findRegion("screen-bg"));
            loadingBg = new Image(atlas.findRegion("loading-frame-bg"));

            //Создает анимацию из загруженного ассета
            Animation anim = new Animation(0.05f, atlas.findRegions("loading-bar-anim") );
            anim.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
            loadingBar = new LoadingBar(anim); //Передает анимацию в бар

            //Добавляем актеров на Stage
            addActor(screenBg);
            addActor(loadingBar);
            addActor(loadingBg);
            addActor(loadingBarHidden);
            addActor(loadingFrame);
            addActor(logo);
        }

        postLoadingAssets(); //Загрузка остальных ассетов
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        if(isFirst){
            // Растягивает БГ по размерам экрана
            screenBg.setSize(width, height);

            // Центрует логотип
            logo.setX((getWidth() - logo.getWidth()) / 2);
            logo.setY((getHeight() - logo.getHeight()) / 2 + 100);

            // Центрует фрейм
            loadingFrame.setX((getWidth() - loadingFrame.getWidth()) / 2);
            loadingFrame.setY((getHeight() - loadingFrame.getHeight()) / 2);

            // Центрует бар
            loadingBar.setX(loadingFrame.getX() + 15);
            loadingBar.setY(loadingFrame.getY() + 5);

            // Центрует картинку которая скрывает бар
            loadingBarHidden.setX(loadingBar.getX() + 35);
            loadingBarHidden.setY(loadingBar.getY() - 3);
            // Стартовая позиция для скрития бара
            startX = loadingBarHidden.getX();
            endX = 440;

            // Сброс скрытия бара
            loadingBg.setSize(450, 50);
            loadingBg.setX(loadingBarHidden.getX() + 30);
            loadingBg.setY(loadingBarHidden.getY() + 3);
        }
    }

    @Override
    public void render(float delta) {
       super.render(delta);

        //Получает процент загрузки
        percent = Interpolation.linear.apply(percent, ScreenManager.getInstance().getAssetManager().getProgress(), 0.1f);

        if(isFirst){
            if (ScreenManager.getInstance().getAssetManager().update()) { // Каждый фрейм проверяет загруженны ли ассеты
                if(Gdx.input.isTouched())
                    ScreenManager.getInstance().showScreen( ScreenEnum.START_SCREEN );
            }

            //Расчеты для отрисовки бара из показателей процента загрузки
            loadingBarHidden.setX(startX + endX * percent);
            loadingBg.setX(loadingBarHidden.getX() + 30);
            loadingBg.setWidth(450 - 450 * percent);
            loadingBg.invalidate();
        }
        else{
            if (ScreenManager.getInstance().getAssetManager().update() && percent >= ScreenManager.getInstance().getAssetManager().getProgress() - .001f) {
                ScreenManager.getInstance().showScreen( ScreenEnum.START_SCREEN );
            }

            getBatch().begin();
            ScreenManager.getInstance().getFont().draw(getBatch(), "Loading: " + Float.toString(percent * 100) + "%", Constants.GAME_W - 150, 35);
            getBatch().end();
        }

    }

    @Override
    public void hide() {
        unloadingAssets(); //Выгрузка ассетов при сворочивании апп
    }

    //Сюда нужно пихать, то что нужно для первоначального рендеренга экрана загрузки, полосочка, лого, текстовка и прочее
    private void preLoadAsset(){
        ScreenManager.getInstance().getAssetManager().load("img/loading.pack", TextureAtlas.class);
    }

    //Сюда пихать, то что нужно для рендеринга сцены
    private void postLoadingAssets(){
        ScreenManager.getInstance().getAssetManager().load("img/splash.png", Texture.class);
    }

    private void unloadingAssets(){
        ScreenManager.getInstance().getAssetManager().unload("img/loading.pack");
    }
}
