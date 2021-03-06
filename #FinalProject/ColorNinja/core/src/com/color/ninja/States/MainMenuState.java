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
 * CLass representing the MainMenu.
 */
public class MainMenuState extends State {
    private Stage stage;

    private Texture background;

    private Sprite title;
    private Sprite newGameBtnSprite;
    private Sprite settingsBtnSprite;
    private Sprite scoresBtnSprite;

    private Button newGameBtn;
    private Button settingsBtn;
    private Button scoresBtn;

    // Pixel values for the main menu
    private float buttonsWidth;
    private float buttonsHeight;
    private float buttonsSpace; // space in the y-axis between 2 buttons
    private float buttonsBegin; // y-coordinate of the first button

    private Sound kungfu;


    /**
     * Default Constructor
     * @param gsm
     */
    public MainMenuState(GameStateManager gsm) {
        super(gsm);


        background = new Texture("background.png");

        title = new Sprite(new Texture("title1.png"));
        newGameBtnSprite = new Sprite(new Texture ("new game1.png"));
        settingsBtnSprite = new Sprite (new Texture ("settings1.png"));
        scoresBtnSprite = new Sprite (new Texture("scores1.png"));

        newGameBtn = new Button(new SpriteDrawable(newGameBtnSprite));
        settingsBtn = new Button(new SpriteDrawable(settingsBtnSprite));
        scoresBtn = new Button(new SpriteDrawable(scoresBtnSprite));

        kungfu = Gdx.audio.newSound(Gdx.files.internal("sound/effects/kungfu.mp3"));

        stage = new Stage();

        // Menu button properties
        buttonsWidth = Gdx.graphics.getWidth() / 2;
        buttonsHeight = Gdx.graphics.getHeight() / 8;
        buttonsSpace = Gdx.graphics.getHeight() / 15;
        buttonsBegin = 2 * Gdx.graphics.getHeight() / 4;

        // Title's position and size
        title.setSize(8 * Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 3);
        title.setCenterX(Gdx.graphics.getWidth() / 2);
        title.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 35 - title.getHeight());

        // Buttons' position and size
        newGameBtn.setSize(buttonsWidth, buttonsHeight);
        settingsBtn.setSize(buttonsWidth, buttonsHeight);
        scoresBtn.setSize(buttonsWidth, buttonsHeight);

        newGameBtn.setX(Gdx.graphics.getWidth() / 2 - newGameBtn.getWidth() / 2);
        settingsBtn.setX(Gdx.graphics.getWidth() / 2 - settingsBtn.getWidth() / 2);
        scoresBtn.setX(Gdx.graphics.getWidth() / 2 - scoresBtn.getWidth() / 2 );

        newGameBtn.setY(buttonsBegin);
        settingsBtn.setY(newGameBtn.getY() - buttonsHeight - buttonsSpace);
        scoresBtn.setY(settingsBtn.getY() - buttonsHeight - buttonsSpace);

        // Setting up stage and actors
        Gdx.input.setInputProcessor(stage);

        stage.addActor(newGameBtn);
        stage.addActor(settingsBtn);
        stage.addActor(scoresBtn);

        setListeners();

    }


    /**
     * Sets the Click listeners for the options in the Main Menu
     */
    private void setListeners()
    {
        newGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kungfu.play(SettingsMenuState.soundsVol);
                gsm.set(new GameState(gsm));
            }
        });

        settingsBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kungfu.play(SettingsMenuState.soundsVol);
                gsm.push(new SettingsMenuState(gsm));
             }
        });

        scoresBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kungfu.play(SettingsMenuState.soundsVol);
                gsm.set(new ScoresMenuState(gsm));
            }
        });
    }

    @Override
    public void handleInput() {
        Gdx.input.setInputProcessor(stage);
    }

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
        newGameBtn.draw(sb, 1);
        settingsBtn.draw(sb, 1);
        scoresBtn.draw(sb, 1);


        sb.end();   // Close batch.

    }

    /**
     * Disposes the content after the execution
     */
    @Override
    public void dispose() {
        background.dispose();
        title.getTexture().dispose();
        newGameBtnSprite.getTexture().dispose();
        settingsBtnSprite.getTexture().dispose();
        scoresBtnSprite.getTexture().dispose();
        kungfu.dispose();
    }
}
