package com.nuclearthinking.myheroagency.model.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.nuclearthinking.myheroagency.controller.manager.AssetsManager;
import com.nuclearthinking.myheroagency.utils.Constants;
import lombok.val;

import java.util.HashMap;

import static com.nuclearthinking.myheroagency.utils.Constants.ALL_CHARACTERS;

public final class FontFactory {

    private static final HashMap<Integer, BitmapFont> robotoLightMap = new HashMap<Integer, BitmapFont>();
    private static final HashMap<Integer, BitmapFont> robotoBoldMap = new HashMap<Integer, BitmapFont>();

    private FreeTypeFontGenerator robotoLightGenerator;
    private FreeTypeFontGenerator robotoBoldGenerator;

    public final BitmapFont getRobotoLight(final int size) {
        BitmapFont font = robotoLightMap.get(size);
        if (font == null) {
            val parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.characters = ALL_CHARACTERS;
            parameter.size = size;
            font = getRobotoLightGenerator().generateFont(parameter);
            robotoLightMap.put(size, font);
        }
        return font;
    }

    public final BitmapFont getRobotoBold(final int size) {
        BitmapFont font = robotoBoldMap.get(size);
        if (font == null) {
            val parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.characters = ALL_CHARACTERS;
            parameter.size = size;
            font = getRobotoBoldGenerator().generateFont(parameter);
            robotoBoldMap.put(size, font);
        }
        return font;
    }

    private FreeTypeFontGenerator getRobotoLightGenerator() {
        if (robotoLightGenerator == null) {
            robotoLightGenerator = AssetsManager.getInstance().get(Constants.FONT_ROBO_LIGHT);
        }
        return robotoLightGenerator;
    }

    private FreeTypeFontGenerator getRobotoBoldGenerator() {
        if (robotoBoldGenerator == null) {
            robotoBoldGenerator = AssetsManager.getInstance().get(Constants.FONT_ROBO_BOLD);
        }
        return robotoBoldGenerator;
    }

}
