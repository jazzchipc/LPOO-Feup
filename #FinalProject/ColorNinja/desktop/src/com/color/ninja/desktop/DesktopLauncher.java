package com.color.ninja.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.color.ninja.MyColorNinja;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = MyColorNinja.WIDTH;
		config.height = MyColorNinja.HEIGHT;
		config.title = MyColorNinja.TITLE;

		new LwjglApplication(MyColorNinja.getOurInstance(), config);
	}
}
