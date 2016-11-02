package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.nuclearthinking.myheroagency.controller.manager.JsonToObject;
import lombok.val;

/**
 * Created by mkuksin on 01.11.2016.
 */
public class JsonLoader extends AsynchronousAssetLoader<JsonToObject, JsonLoader.QuestParameter> {

    public JsonLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, QuestParameter parameter) {
    }

    @Override
    public JsonToObject loadSync(AssetManager manager, String fileName, FileHandle file, QuestParameter parameter) {
        val quest = new JsonToObject(file);
        return quest;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, QuestParameter parameter) {
        return null;
    }

    static public class QuestParameter extends AssetLoaderParameters<JsonToObject> {
        public final String jsonPath;
        public final ObjectMap<String, Object> resources;

        public QuestParameter () {
            this(null, null);
        }

        public QuestParameter(ObjectMap<String, Object> resources){
            this(null, resources);
        }

        public QuestParameter (String jsonPath) {
            this(jsonPath, null);
        }

        public QuestParameter (String jsonPath, ObjectMap<String, Object> resources) {
            this.jsonPath = jsonPath;
            this.resources = resources;
        }
    }
}
