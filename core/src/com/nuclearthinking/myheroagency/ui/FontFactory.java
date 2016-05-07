package com.nuclearthinking.myheroagency.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Date: 02.04.2016
 * Time: 21:02
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class FontFactory {
    private static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.TTF"));
    private static BitmapFont font9;

    public static BitmapFont getFont9() {
        if (font9 == null) {
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 9;
            generateFont8(parameter);
        }
        return font9;
    }


    private static void generateFont8(FreeTypeFontGenerator.FreeTypeFontParameter param) {
        font9 = generator.generateFont(param);
    }

}
