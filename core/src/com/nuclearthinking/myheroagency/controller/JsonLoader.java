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
public class JsonLoader extends AsynchronousAssetLoader<JsonToObject, JsonLoader.ObjectParameter> {

    public JsonLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, ObjectParameter parameter) {
    }

    @Override
    public JsonToObject loadSync(AssetManager manager, String fileName, FileHandle file, ObjectParameter parameter) {
        val object = new JsonToObject(file);
        return object;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, ObjectParameter parameter) {
        return null;
    }

    static public class ObjectParameter extends AssetLoaderParameters<JsonToObject> {
        public final String jsonPath;
        public final ObjectMap<String, Object> resources;

        public ObjectParameter () {
            this(null, null);
        }

        public ObjectParameter(ObjectMap<String, Object> resources){
            this(null, resources);
        }

        public ObjectParameter (String jsonPath) {
            this(jsonPath, null);
        }

        public ObjectParameter (String jsonPath, ObjectMap<String, Object> resources) {
            this.jsonPath = jsonPath;
            this.resources = resources;
        }
    }
}
