package com.nuclearthinking.myheroagency.ui.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.nuclearthinking.myheroagency.utils.Constants;

/**
 * Date: 08.05.2016
 * Time: 11:22
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class FontFactory {

    private static final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Kramola.ttf"));
    private static BitmapFont font;

    public static BitmapFont getFont() {
        if (font == null) {
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.characters = Constants.ALL_CHARACTERS;
            parameter.size = 21;
            generateFont(parameter);
        }
        return font;
    }


    private static void generateFont(FreeTypeFontGenerator.FreeTypeFontParameter param) {
        font = generator.generateFont(param);
        generator.dispose();
    }

}
