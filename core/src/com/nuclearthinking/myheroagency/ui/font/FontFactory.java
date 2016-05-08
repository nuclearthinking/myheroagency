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

    private static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Kramola.TTF"));
    private static BitmapFont font9;

    public static BitmapFont getFont9() {
        if (font9 == null) {
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.characters = Constants.ALL_CHARACTERS;
            parameter.size = 9;
            generateFont9(parameter);
        }
        return font9;
    }


    private static void generateFont9(FreeTypeFontGenerator.FreeTypeFontParameter param) {
        font9 = generator.generateFont(param);
    }

}
