package com.color.ninja.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.color.ninja.ColorNinja;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = ColorNinja.WIDTH;
		config.height = ColorNinja.HEIGHT;
		config.title = ColorNinja.TITLE;

		new LwjglApplication(new ColorNinja(), config);
	}
}
