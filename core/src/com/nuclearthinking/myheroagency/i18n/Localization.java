package com.nuclearthinking.myheroagency.i18n;

import com.badlogic.gdx.utils.I18NBundle;
import com.nuclearthinking.myheroagency.controller.Asset;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

import java.util.MissingResourceException;


/**
 * Date: 07.05.2016
 * Time: 23:48
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class Localization {

    private final Logger logger;
    private I18NBundle localisationBundle;

    public Localization(final Class initiatorClass) {
        logger = new SimpleLoggerFactory().getLogger(getClass().getSimpleName());
        loadBundle(initiatorClass);
    }

    public void loadBundle(final Class bundleClass) {
        final String bundleName = "i18n/" + bundleClass.getSimpleName();
        if (Asset.getInstance().isLoaded(bundleName)) {
            localisationBundle = Asset.getInstance().get(bundleName, I18NBundle.class);
            logger.info("Loaded I18NBundle with name {}", bundleName);
        } else {
            logger.error("I18NBundle {} is not loaded yet", bundleName);
        }
    }

    public String get(final String key) {
        if (localisationBundle != null) {
            try {
                return localisationBundle.get(key);
            } catch (MissingResourceException ex) {
                logger.error("Can't load key with name {}", key);
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
