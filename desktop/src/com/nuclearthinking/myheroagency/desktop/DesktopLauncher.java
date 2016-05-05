package com.nuclearthinking.myheroagency.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nuclearthinking.myheroagency.Main;
import com.nuclearthinking.myheroagency.utils.Constants;

public class DesktopLauncher
{
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Constants.GAME_TITLE;
		config.width = Constants.GAME_W;
        config.height = Constants.GAME_H;
		new LwjglApplication(new Main(), config);
	}
}
