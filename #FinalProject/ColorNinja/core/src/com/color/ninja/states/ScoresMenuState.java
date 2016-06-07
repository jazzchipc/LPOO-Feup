package com.color.ninja.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.color.ninja.MyColorNinja;
/**
 * Class representing the Scores Menu.
 */
public class ScoresMenuState extends State {
    private Stage stage;

    private Texture background;

    private Sprite title;
    private Sprite backBtnSprite;

    private Button backBtn;

    private Sound kungfu;

    /**
     * Default Constructor
     * @param gsm
     */
    public ScoresMenuState(GameStateManager gsm) {
        super(gsm);

        background = new Texture("background.png");

        title = new Sprite(new Texture("scorestitle.png"));

        backBtnSprite = new Sprite(new Texture ("backbtn.png"));

        backBtn = new Button(new SpriteDrawable(backBtnSprite));

        kungfu = Gdx.audio.newSound(Gdx.files.internal("sound/effects/kungfu.mp3"));

        stage = new Stage();

        // Title's position and size
        title.setSize(8 * Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 5);
        title.setCenterX(Gdx.graphics.getWidth() / 2);
        title.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 9 - title.getHeight());

        backBtn.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 12);
        backBtn.setX(Gdx.graphics.getWidth() / 5 - backBtn.getWidth());
        backBtn.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 10);

        // Setting up stage and actors
        Gdx.input.setInputProcessor(stage);

        stage.addActor(backBtn);

        setListeners();

    }

    /**
     * Sets the listener for the backbutton
     */
    private void setListeners()
    {
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kungfu.play(SettingsMenuState.soundsVol);
                gsm.set(new MainMenuState(gsm));
            }
        });

    }

    @Override
    public void handleInput() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Updates what the screen is outputting
     * @param dt Update rate (in miliseconds).
     */
    @Override
    public void update(float dt) {
        handleInput();
    }

    /**
     * Renders the SpriteBatch
     * @param sb State's SpriteBatch.
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); // Open batch to add things to it. A batch works like a box which is filled with images.

        //draws background texture
        sb.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        // draws title sprite
        title.draw(sb);

        // draws game options buttons
        backBtn.draw(sb, 1);

        sb.end();   // Close batch.

    }

    /**
     * Disposes the content after the execution
     */
    @Override
    public void dispose() {
        background.dispose();
        title.getTexture().dispose();
        backBtnSprite.getTexture().dispose();
        kungfu.dispose();
    }
}

