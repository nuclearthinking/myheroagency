package com.nuclearthinking.myheroagency.ui.font;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.nuclearthinking.myheroagency.controller.Asset;

import static com.nuclearthinking.myheroagency.utils.Constants.ALL_CHARACTERS;

/**
 * Date: 08.05.2016
 * Time: 11:22
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */
public class FontFactory {

    private FreeTypeFontGenerator robotoLightGenerator;
    private FreeTypeFontGenerator robotoBoldGenerator;
    private BitmapFont robotoLight14;
    private BitmapFont robotoLight18;
    private BitmapFont robotoLight26;
    private BitmapFont robotoBold14;
    private BitmapFont robotoBold18;
    private BitmapFont robotoBold26;

    public BitmapFont getRobotoLight14() {
        if (robotoLight14 == null) {
            robotoLight14 = generateFont(getRobotoLightGenerator(), 14);
        }
        return robotoLight14;
    }

    public BitmapFont getRobotoLight18() {
        if (robotoLight18 == null) {
            robotoLight18 = generateFont(getRobotoLightGenerator(), 18);
        }
        return robotoLight18;
    }

    public BitmapFont getRobotoLight26() {
        if (robotoLight26 == null) {
            robotoLight26 = generateFont(getRobotoLightGenerator(), 26);
        }
        return robotoLight26;
    }

    public BitmapFont getRobotoBold14() {
        if (robotoBold14 == null) {
            robotoBold14 = generateFont(getRobotoBoldGenerator(), 14);
        }
        return robotoBold14;
    }

    public BitmapFont getRobotoBold18() {
        if (robotoBold18 == null) {
            robotoBold18 = generateFont(getRobotoBoldGenerator(), 18);
        }
        return robotoBold18;
    }

    public BitmapFont getRobotoBold26() {
        if (robotoBold26 == null) {
            robotoBold26 = generateFont(getRobotoBoldGenerator(), 26);
        }
        return robotoBold26;
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


    private BitmapFont generateFont(FreeTypeFontGenerator generator, int size) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.characters = ALL_CHARACTERS;
        parameter.size = size;
        return generator.generateFont(parameter);
    }

}
