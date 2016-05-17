package com.color.ninja;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.color.ninja.states.GameStateManager;
import com.color.ninja.states.MainMenuState;

public class MyColorNinja extends ApplicationAdapter {

	// Window size in pixels
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	// Window title
	public static final String TITLE = "Color Ninja";

	private GameStateManager gsm;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		Gdx.gl.glClearColor(1, 1, 1, 1);	// the starting background color (R,G,B, alpha)

		gsm = new GameStateManager();

		gsm.push(new MainMenuState(gsm));	// starts the game on the MainMenuState
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
