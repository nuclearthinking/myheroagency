package com.nuclearthinking.myheroagency.ui.font;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.nuclearthinking.myheroagency.controller.Asset;

import java.util.HashMap;

import static com.nuclearthinking.myheroagency.utils.Constants.ALL_CHARACTERS;

public class FontFactory {
    private static HashMap<Integer, BitmapFont> robotoLightMap = new HashMap<Integer, BitmapFont>();
    private static HashMap<Integer, BitmapFont> robotoBoldMap = new HashMap<Integer, BitmapFont>();

    private FreeTypeFontGenerator robotoLightGenerator;
    private FreeTypeFontGenerator robotoBoldGenerator;

    public BitmapFont getRobotoLight(int size) {
        BitmapFont font = robotoLightMap.get(size);
        if (font == null) {
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.characters = ALL_CHARACTERS;
            parameter.size = size;
            font = getRobotoLightGenerator().generateFont(parameter);
            robotoLightMap.put(size, font);
        }
        return font;
    }

    public BitmapFont getRobotoBold(int size) {
        BitmapFont font = robotoBoldMap.get(size);
        if (font == null) {
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.characters = ALL_CHARACTERS;
            parameter.size = size;
            font = getRobotoBoldGenerator().generateFont(parameter);
            robotoBoldMap.put(size, font);
        }
        return font;
    }

    private FreeTypeFontGenerator getRobotoLightGenerator() {
        if (robotoLightGenerator == null) {
            robotoLightGenerator = Asset.getInstance().get("font/RobotoSlab-Light.ttf");
        }
        return robotoLightGenerator;
    }

    private FreeTypeFontGenerator getRobotoBoldGenerator() {
        if (robotoBoldGenerator == null) {
            robotoBoldGenerator = Asset.getInstance().get("font/RobotoSlab-Bold.ttf");
        }
        return robotoBoldGenerator;
    }

}
