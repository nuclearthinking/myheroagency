package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.*;

/**
 * Created by Izonami on 12.05.2016.
 */
public class Asset implements Disposable, AssetErrorListener {

    private static final String TAG = "Asset";
    private Logger logger;
    private AssetManager manager;
    private ObjectMap<String, Array<Assetes>> groups;
    private static Asset instance;

    public static Asset getInstance() {
        if (instance == null) {
            instance = new Asset();
        }
        return instance;
    }

    public void init(String assetFile) {
        logger = new Logger(TAG, Logger.INFO);

        manager = new AssetManager();
        manager.setErrorListener(this);
        manager.setLoader(TextureAtlas.class, new TextureAtlasLoader(new InternalFileHandleResolver()));
        manager.setLoader(Texture.class, new TextureLoader(new InternalFileHandleResolver()));

        loadGroups(assetFile);
    }

    public void loadGroup(String groupName) {
        logger.info("loading group " + groupName);

        Array<Assetes> assets = groups.get(groupName, null);

        if (assets != null) {
            for (Assetes asset : assets) {
                manager.load(asset.path, asset.type);
            }
        }
        else {
            logger.error("error loading group " + groupName + ", not found");
        }
    }

    public void unloadGroup(String groupName) {
        logger.info("unloading group " + groupName);

        Array<Assetes> assets = groups.get(groupName, null);

        if (assets != null) {
            for (Assetes asset : assets) {
                if (manager.isLoaded(asset.path, asset.type)) {
                    manager.unload(asset.path);
                }
            }
        }
        else {
            logger.error("error unloading group " + groupName + ", not found");
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
        logger.info("shutting down");
        manager.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        logger.error("error loading " + asset);
    }

    private void loadGroups(String assetFile) {
        groups = new ObjectMap<String, Array<Assetes>>();

        logger.info("loading file " + assetFile);

        try {
            XmlReader reader = new XmlReader();
            XmlReader.Element root = reader.parse(Gdx.files.internal(assetFile));

            for (XmlReader.Element groupElement : root.getChildrenByName("group")) {
                String groupName = groupElement.getAttribute("name", "base");

                if (groups.containsKey(groupName)) {
                    logger.error("group " + groupName + " already exists, skipping");
                    continue;
                }

                logger.info("registering group " + groupName);

                Array<Assetes> assets = new Array<Assetes>();

                for (XmlReader.Element assetElement : groupElement.getChildrenByName("asset")) {
                    assets.add(new Assetes(assetElement.getAttribute("type", ""),
                            assetElement.getAttribute("path", "")));
                }

                groups.put(groupName, assets);
            }
        }
        catch (Exception e) {
            logger.error("error loading file " + assetFile + " " + e.getMessage());
        }
    }

    private class Assetes {
        public Class<?> type;
        public String path;

        public Assetes(String type, String path) {
            try {
                this.type = Class.forName(type);
                this.path = path;
            } catch (ClassNotFoundException e) {
                logger.error("asset type " + type + " not found");
            }
        }
    }

}
