package com.nuclearthinking.myheroagency.i18n;

import com.badlogic.gdx.utils.I18NBundle;
import com.nuclearthinking.myheroagency.controller.Asset;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.MissingResourceException;


/**
 * Date: 07.05.2016
 * Time: 23:48
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
@Slf4j(topic = "Localization")
public final class Localization {

    private I18NBundle localisationBundle;

    public Localization(final Class initiatorClass) {
        loadBundle(initiatorClass);
    }

    public void loadBundle(final Class bundleClass) {
        val bundleName = "i18n/" + bundleClass.getSimpleName();
        if (Asset.getInstance().isLoaded(bundleName)) {
            localisationBundle = Asset.getInstance().get(bundleName, I18NBundle.class);
            log.info("Loaded I18NBundle with name {}", bundleName);
        } else {
            log.error("I18NBundle {} is not loaded yet", bundleName);
        }
    }

    public String get(final String key) {
        if (localisationBundle != null) {
            try {
                return localisationBundle.get(key);
            } catch (MissingResourceException ex) {
                log.error("Can't load key with name {}", key);
                return null;
            }
        } else {
            return null;
        }
    }

    public String format(final String key, final Object... args) {
        if (localisationBundle != null) {
            return localisationBundle.format(key, args);
        } else {
            return null;
        }
    }

}
