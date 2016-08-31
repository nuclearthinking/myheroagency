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
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

import java.util.Locale;

@Slf4j(topic = "Asset")
public class Asset implements Disposable, AssetErrorListener {

    private static Asset instance;
    private AssetManager manager;
    private ObjectMap<String, Array<Assets>> groups;
    private Locale locale;

    public static Asset getInstance() {
        if (instance == null) {
            instance = new Asset();
        }
        return instance;
    }

    public void init(@NonNull final String assetFile) {
        manager = new AssetManager(); // Инициализируем менеджер ассетов
        manager.setErrorListener(this); // Ставим листнера ошибок
        Settings.loadSettings(); // Загружаем настройки

        log.info("Loading assets");
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
        log.info("Start reloading localization");
        unloadGroup("localization");
        locale = new Locale(Settings.getLanguage());
        manager.setLoader(I18NBundle.class, new CustomI18NBundleLoader(new InternalFileHandleResolver(), new CustomI18NBundleLoader.I18NBundleParameter(locale)));
        loadGroup("localization");
        finishLoading();
        log.info("Finish reloading localization");
    }

    /**
     *
     * @param fileName
     * @return - Возвращает результат проверки наличия файла в ассет менеджере
     */
    public boolean isLoaded(@NonNull final String fileName) {
        return manager != null && manager.isLoaded(fileName);
    }

    public void loadGroup(@NonNull final String groupName) {
        log.info("Loading group of assets {}", groupName);

        val assets = groups.get(groupName, null);

        if (assets != null) {
            for (val asset : assets) {
                manager.load(asset.path, asset.type);
                log.debug("Asset {} added to loading queue", asset.path);
            }
        } else {
            log.error("Error loading group {}, not found ", groupName);
        }
    }

    public void unloadGroup(@NonNull final String groupName) {
        log.info("Unloading group of assets {}", groupName);

        val assets = groups.get(groupName, null);

        if (assets != null) {
            for (val asset : assets) {
                if (manager.isLoaded(asset.path, asset.type)) {
                    manager.unload(asset.path);
                    log.debug("Asset {} added to unload queue", asset.path);
                }
            }
        } else {
            log.error("Error unloading group {}, not found", groupName);
        }
    }

    public synchronized <T> T get(@NonNull final String fileName) {
        return manager.get(fileName);
    }

    public synchronized <T> T get(@NonNull final String fileName, @NonNull final Class<T> type) {
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
        log.info("Dispose");
        manager.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        log.error("Error loading {}", asset);
    }

    private void loadGroups(@NonNull final String assetFile) {
        groups = new ObjectMap<String, Array<Assets>>();

        log.info("Loading file {}", assetFile);

        try {
            val reader = new XmlReader();
            val root = reader.parse(Gdx.files.internal(assetFile));

            for (val groupElement : root.getChildrenByName("group")) {
                val groupName = groupElement.getAttribute("name", "");

                if (groups.containsKey(groupName)) {
                    log.error("Group {} already exists, skipping", groupName);
                    continue;
                }

                val assets = new Array<Assets>();

                for (val assetElement : groupElement.getChildrenByName("asset")) {
                    assets.add(new Assets(assetElement.getAttribute("type", ""),
                            assetElement.getAttribute("path", "")));
                }

                groups.put(groupName, assets);
                log.info("Registering group {}", groupName);
            }
        } catch (Exception e) {
            log.error("Error loading file {} {}", assetFile, e.getMessage());
        }
    }

    private class Assets {
        public Class<?> type;
        public String path;

        public Assets(@NonNull final String type, @NonNull final String path) {
            try {
                this.type = Class.forName(type);
                this.path = path;
            } catch (ClassNotFoundException e) {
                log.error("Asset type {} not found", type);
            }
        }
    }

}
