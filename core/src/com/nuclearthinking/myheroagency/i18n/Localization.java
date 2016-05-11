package com.nuclearthinking.myheroagency.i18n;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.I18NBundle;
import com.nuclearthinking.myheroagency.controller.Assets;

import java.util.MissingResourceException;


/**
 * Date: 07.05.2016
 * Time: 23:48
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Localization {

    I18NBundle localisationBundle;

    public Localization(Class initiatorClass) {
        String bundleName = "i18n/" + initiatorClass.getSimpleName();
        localisationBundle = Assets.getInstance().getAssetManager().get(bundleName, I18NBundle.class);
        Gdx.app.log(this.getClass().getName(), "Loaded I18NBundle with name " + bundleName);
    }


    public String get(String key) {
        if (localisationBundle != null) {
            try {
                return localisationBundle.get(key);
            } catch (MissingResourceException ex) {
                Gdx.app.error(this.getClass().getName(), "Can't load key with name " + key, ex);
                return null;
            }
        } else {
            return null;
        }
    }

    public String format(String key, Object... args) {
        if (localisationBundle != null) {
            return localisationBundle.format(localisationBundle.get(key), args);
        } else {
            return null;
        }
    }

}
