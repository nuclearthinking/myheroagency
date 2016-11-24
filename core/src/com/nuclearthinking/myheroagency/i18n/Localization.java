package com.nuclearthinking.myheroagency.i18n;

import com.badlogic.gdx.utils.I18NBundle;
import com.nuclearthinking.myheroagency.controller.manager.AssetsManager;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

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
    private final String bundleName;

    public Localization(@NonNull final Class initiatorClass) {
        this.bundleName = "i18n/" + initiatorClass.getSimpleName();
        loadBundle();
    }

    public void loadBundle() {
        if (AssetsManager.getInstance().isLoaded(bundleName)) {
            localisationBundle = AssetsManager.getInstance().get(bundleName, I18NBundle.class);
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
        if (localisationBundle != null || key != null || key.trim().length() != 0 || args.length != 0) {
            return localisationBundle.format(key, args);
        } else {
            return null;
        }
    }

}
