package com.color.ninja;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.color.ninja.states.GameStateManager;
import com.color.ninja.states.MainMenuState;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.color.ninja.states.SettingsMenuState;

public class MyColorNinja extends ApplicationAdapter {
	// Window size in pixels
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	// Window title
	public static final String TITLE = "Color Ninja";

	private GameStateManager gsm;
	private SpriteBatch batch;

	private Music music1;
	//private Music music2;
	//private Music music3;
	//private Music music4;
	//private Music music5;
	//private Music music6;

	private float musicVol;

	@Override
	public void create () {
		batch = new SpriteBatch();

		Gdx.gl.glClearColor(1, 1, 1, 1);	// the starting background color (R,G,B, alpha)

		gsm = new GameStateManager();

		music1 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/ninjamaster.mp3"));
		//music2 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/kanpai.mp3"));
		//music3 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/reverse.mp3"));
		//music4 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/spirit.mp3"));
		//music5 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/strong.mp3"));
		//music6 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/turn.mp3"));
		music1.setLooping(true);
		music1.setVolume(musicVol);
		music1.play();

		gsm.push(new MainMenuState(gsm));	// starts the game on the MainMenuState
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());	// makes all updates before putting out a frame
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
		music1.dispose();
	}
}
