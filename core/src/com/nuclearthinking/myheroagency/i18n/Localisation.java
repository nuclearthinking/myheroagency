package com.nuclearthinking.myheroagency.i18n;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;


/**
 * Date: 07.05.2016
 * Time: 23:48
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Localisation {

    FileHandle baseFileHandle;
    I18NBundle localisationBundle;

    public Localisation(Class initiatorClass) {
        baseFileHandle = Gdx.files.internal("i18n/" + initiatorClass.getName());
    }

    public void setLocale(Locale locale) {
        localisationBundle = I18NBundle.createBundle(baseFileHandle, locale);
    }

    public String get(String key) throws LocalisationException {
        if (localisationBundle != null) {
            return localisationBundle.get(key);
        } else {
            throw new LocalisationException("Locale is not defined");
        }
    }

    public String format(String key, Object... args) throws LocalisationException {
        if (localisationBundle != null) {
            return localisationBundle.format(localisationBundle.get(key), args);
        } else {
            throw new LocalisationException("Locale is not defined");
        }
    }

}
