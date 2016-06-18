package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.*;
import com.nuclearthinking.myheroagency.model.Settings;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

import java.util.Locale;

public class Asset implements Disposable, AssetErrorListener {

    private static Asset instance;
    private Logger logger = new SimpleLoggerFactory().getLogger(getClass().getSimpleName());
    private AssetManager manager;
    private ObjectMap<String, Array<Assets>> groups;
    private Locale locale;

    public static Asset getInstance() {
        if (instance == null) {
            instance = new Asset();
        }
        return instance;
    }

    public void init(String assetFile) {
        manager = new AssetManager(); // Инициализируем менеджер ассетов
        manager.setErrorListener(this); // Ставим листнера ошибок
        Settings.loadSettings(); // Загружаем настройки

        logger.info("Loading assets");
        locale = new Locale(Settings.getLanguage()); // Получаем локаль из пропертей
        Gdx.graphics.setWindowedMode(Settings.getWidth(), Settings.getHeight()); // Получаем разрешение из пропертей

        //Задаем загрузчики для ресурсов
        manager.setLoader(I18NBundle.class, new CustomI18NBundleLoader(new InternalFileHandleResolver(), new CustomI18NBundleLoader.I18NBundleParameter(locale)));
        manager.setLoader(TextureAtlas.class, new TextureAtlasLoader(new InternalFileHandleResolver()));
        manager.setLoader(Texture.class, new TextureLoader(new InternalFileHandleResolver()));
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(new InternalFileHandleResolver()));
        manager.setLoader(Skin.class, new SkinLoader(new InternalFileHandleResolver()));
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

        // Получаем список групп с ресурсами
        loadGroups(assetFile);
    }

    /**
     * Перезагрузка локализаций
     */
    public void reloadLocale(){
        logger.debug("Start reloading localization");
        unloadGroup("localization");
        locale = new Locale(Settings.getLanguage());
        manager.setLoader(I18NBundle.class, new CustomI18NBundleLoader(new InternalFileHandleResolver(), new CustomI18NBundleLoader.I18NBundleParameter(locale)));
        loadGroup("localization");
        finishLoading();
        logger.debug("Finish reloading localization");
    }

    /**
     *
     * @param fileName
     * @return - Возвращает результат проверки наличия файла в ассет менеджере
     */
    public boolean isLoaded(String fileName) {
        return manager != null && manager.isLoaded(fileName);
    }

    public void loadGroup(String groupName) {
        logger.info("Loading group of assets {}", groupName);

        Array<Assets> assets = groups.get(groupName, null);

        if (assets != null) {
            for (Assets asset : assets) {
                manager.load(asset.path, asset.type);
                logger.debug("Asset {} added to loading queue", asset.path);
            }
        } else {
            logger.error("Error loading group {}, not found ", groupName);
        }
    }

    public void unloadGroup(String groupName) {
        logger.info("Unloading group of assets {}", groupName);

        Array<Assets> assets = groups.get(groupName, null);

        if (assets != null) {
            for (Assets asset : assets) {
                if (manager.isLoaded(asset.path, asset.type)) {
                    manager.unload(asset.path);
                    logger.debug("Asset {} added to unload queue", asset.path);
                }
            }
        } else {
            logger.error("Error unloading group {}, not found", groupName);
        }
    }

    public synchronized <T> T get(String fileName) {
        return manager.get(fileName);
    }

    public synchronized <T> T get(String fileName, Class<T> type) {
        return manager.get(fileName, type);
    }

    public boolean update() {
        return manager.update();
    }

    public void finishLoading() {
        manager.finishLoading();
    }

    public float getProgress() {
        return manager.getProgress();
    }

    @Override
    public void dispose() {
        logger.info("Dispose");
        manager.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        logger.error("Error loading {}", asset);
    }

    private void loadGroups(String assetFile) {
        groups = new ObjectMap<String, Array<Assets>>();

        logger.info("Loading file {}", assetFile);

        try {
            XmlReader reader = new XmlReader();
            XmlReader.Element root = reader.parse(Gdx.files.internal(assetFile));

            for (XmlReader.Element groupElement : root.getChildrenByName("group")) {
                String groupName = groupElement.getAttribute("name", "");

                if (groups.containsKey(groupName)) {
                    logger.error("Group {} already exists, skipping", groupName);
                    continue;
                }

                Array<Assets> assets = new Array<Assets>();

                for (XmlReader.Element assetElement : groupElement.getChildrenByName("asset")) {
                    assets.add(new Assets(assetElement.getAttribute("type", ""),
                            assetElement.getAttribute("path", "")));
                }

                groups.put(groupName, assets);
                logger.info("Registering group {}", groupName);
            }
        } catch (Exception e) {
            logger.error("Error loading file {} {}", assetFile, e.getMessage());
        }
    }

    private class Assets {
        public Class<?> type;
        public String path;

        public Assets(String type, String path) {
            try {
                this.type = Class.forName(type);
                this.path = path;
            } catch (ClassNotFoundException e) {
                logger.error("Asset type {} not found", type);
            }
        }
    }

}
