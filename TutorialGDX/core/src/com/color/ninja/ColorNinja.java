package com.color.ninja;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.color.ninja.States.GameStateManager;
import com.color.ninja.States.MenuState;

public class ColorNinja extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Color Ninja";

	private GameStateManager gsm;
	private SpriteBatch sb;			// should be only one for the game, since it's a very heavy file. It should be passed between states.

	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		sb = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);

		gsm = new GameStateManager();

		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	// wipes the screen

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
}
