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
 * Class representing the Pause Menu
 */
public class PauseMenuState extends State {
    private Stage stage;

    private Texture background;

    private Sprite resumeBtnSprite;
    private Sprite restartBtnSprite;
    private Sprite settingsBtnSprite;
    private Sprite exitBtnSprite;

    private Button resumeBtn;
    private Button restartBtn;
    private Button settingsBtn;
    private Button exitBtn;

    // Pixel values for the main menu
    private float buttonsWidth;
    private float buttonsHeight;
    private float buttonsSpace; // space in the y-axis between 2 buttons
    private float buttonsBegin; // y-coordinate of the first button

    private Sound kungfu;

    private float soundsVol = SettingsMenuState.soundsVol;

    public PauseMenuState(GameStateManager gsm) {
        super(gsm);

        background = new Texture("background.png");

        resumeBtnSprite = new Sprite(new Texture ("resume game1.png"));
        restartBtnSprite = new Sprite(new Texture("restart game1.png"));
        settingsBtnSprite = new Sprite (new Texture ("settings1.png"));
        exitBtnSprite = new Sprite (new Texture("exit1.png"));

        resumeBtn = new Button(new SpriteDrawable(resumeBtnSprite));
        restartBtn = new Button(new SpriteDrawable(restartBtnSprite));
        settingsBtn = new Button(new SpriteDrawable(settingsBtnSprite));
        exitBtn = new Button(new SpriteDrawable(exitBtnSprite));

        kungfu = Gdx.audio.newSound(Gdx.files.internal("sound/effects/kungfu.mp3"));

        stage = new Stage();

        // Menu button properties
        buttonsWidth = MyColorNinja.WIDTH / 2;
        buttonsHeight = MyColorNinja.HEIGHT / 8;
        buttonsSpace = MyColorNinja.HEIGHT / 15;
        buttonsBegin = 3 * MyColorNinja.HEIGHT / 4;

        // Buttons' position and size
        resumeBtn.setSize(buttonsWidth, buttonsHeight);
        restartBtn.setSize(buttonsWidth,buttonsHeight);
        settingsBtn.setSize(buttonsWidth, buttonsHeight);
        exitBtn.setSize(buttonsWidth, buttonsHeight);

        resumeBtn.setX(MyColorNinja.WIDTH / 2 - resumeBtn.getWidth() / 2);
        restartBtn.setX(MyColorNinja.WIDTH / 2 - restartBtn.getWidth() / 2);
        settingsBtn.setX(MyColorNinja.WIDTH / 2 - settingsBtn.getWidth() / 2);
        exitBtn.setX(MyColorNinja.WIDTH / 2 - exitBtn.getWidth() / 2 );

        resumeBtn.setY(buttonsBegin);
        restartBtn.setY(resumeBtn.getY()- buttonsHeight - buttonsSpace);
        settingsBtn.setY(restartBtn.getY() - buttonsHeight - buttonsSpace);
        exitBtn.setY(settingsBtn.getY() - buttonsHeight - buttonsSpace);

        // Setting up stage and actors
        Gdx.input.setInputProcessor(stage);

        stage.addActor(resumeBtn);
        stage.addActor(restartBtn);
        stage.addActor(settingsBtn);
        stage.addActor(exitBtn);

        setListeners();

    }

    private void setListeners()
    {
        resumeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kungfu.play(soundsVol);
                gsm.set(new GameState(gsm));
            }
        });

        restartBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kungfu.play(soundsVol);
                gsm.set(new GameState(gsm));
            }
        });

        settingsBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kungfu.play(soundsVol);
                gsm.set(new SettingsMenuState(gsm));
            }
        });

        exitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kungfu.play(soundsVol);
                gsm.set(new MainMenuState(gsm));
            }
        });
    }

    @Override
    protected void handleInput() {


    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); // Open batch to add things to it. A batch works like a box which is filled with images.

        //draws background texture
        sb.draw(background,0,0,MyColorNinja.WIDTH,MyColorNinja.HEIGHT);

        // draws game options buttons
        resumeBtn.draw(sb, 1);
        restartBtn.draw(sb,1);
        settingsBtn.draw(sb, 1);
        exitBtn.draw(sb, 1);

        sb.end();   // Close batch.

    }

    @Override
    public void dispose() {
        background.dispose();
        resumeBtnSprite.getTexture().dispose();
        restartBtnSprite.getTexture().dispose();
        settingsBtnSprite.getTexture().dispose();
        exitBtnSprite.getTexture().dispose();
        kungfu.dispose();
    }
}


