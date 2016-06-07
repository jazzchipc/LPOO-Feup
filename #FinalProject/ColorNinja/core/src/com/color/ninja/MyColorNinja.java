package com.color.ninja;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.color.ninja.logic.Highscores;
import com.color.ninja.logic.Score;
import com.color.ninja.states.GameStateManager;
import com.color.ninja.states.MainMenuState;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.color.ninja.states.SettingsMenuState;

public class MyColorNinja extends ApplicationAdapter {

	private static MyColorNinja ourInstance = new MyColorNinja();

	private MyColorNinja()
	{}

	public static MyColorNinja getOurInstance() {
		return ourInstance;
	}

	// DEBUG
	public static final boolean DEBUG = true;

	// Window size in pixels
	public static final int WIDTH = 720;
	public static final int HEIGHT = 1190;

	// Window title
	public static final String TITLE = "Color Ninja";

	// Physics constants
    public static final float PIXELS_PER_METER = 100f;  // scaling physics world
	public static final float GRAVITY = 9.8f;

	// Game properties
	public static final int GAME_DURATION = 60; // in seconds

	private GameStateManager gsm;
	private SpriteBatch batch;

	private Music music1;
	private Music music2;
	private Music music3;
	private Music music4;
	private Music music5;
	private Music music6;

	private Array<Music> music;

	private Highscores highscores;

	private Score score;

	@Override
	public void create () {
		highscores = new Highscores();

		batch = new SpriteBatch();

		Gdx.gl.glClearColor(1, 1, 1, 1);	// the starting background color (R,G,B, alpha)

		gsm = new GameStateManager();

		music1 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/ninjamaster.mp3"));
		music2 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/kanpai.mp3"));
		music3 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/reverse.mp3"));
		music4 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/spirit.mp3"));
		music5 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/strong.mp3"));
		music6 = Gdx.audio.newMusic(Gdx.files.internal("sound/music/turn.mp3"));

		music = new Array<Music>();

		music.add(music1);
		music.add(music2);
		music.add(music3);
		music.add(music4);
		music.add(music5);
		music.add(music6);

		music2.setLooping(true);
		music2.setVolume(SettingsMenuState.musicVol);
		music2.play();

		gsm.push(new MainMenuState(gsm));	// starts the game on the MainMenuState
	}

	public Music getMusic(){
		return music2;
	}

	public int getOne(){
		return 1;
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		music2.setVolume(SettingsMenuState.musicVol);

		gsm.update(Gdx.graphics.getDeltaTime());	// makes all updates before putting out a frame
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
		music1.dispose();
		music2.dispose();
		music3.dispose();
		music4.dispose();
		music5.dispose();
		music6.dispose();
	}
}
