package com.color.ninja.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.color.ninja.MyColorNinja;

/**
 * Class representing the Settings Menu
 */
public class SettingsMenuState extends com.color.ninja.states.State {

    private Stage stage;
    private Skin skin;

    private Texture background;

    private Sprite title;
    private Sprite backBtnSprite;
    private Sprite soundsVolSprite;
    private Sprite musicVolSprite;
    private Sprite difficultySprite;

    private Button backBtn;

    private Slider soundsVolSld;
    private Slider musicVolSld;

    private CheckBox easyCheckBox;
    private CheckBox mediumCheckBox;
    private CheckBox hardCheckBox;

    public static float soundsVol = 0.5f;
    public static float musicVol = 0.5f;
    public static int difficulty = 1;

    private Sound kungfu;


    /**
     * Default Constructor
     * @param gsm
     */
    public SettingsMenuState(com.color.ninja.states.GameStateManager gsm) {

        super(gsm);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        background = new Texture("background.png");
        title = new Sprite(new Texture("title2.png"));
        backBtnSprite = new Sprite(new Texture ("backbtn.png"));
        soundsVolSprite = new Sprite(new Texture("soundsvolume.png"));
        musicVolSprite = new Sprite(new Texture("musicvolume.png"));
        difficultySprite = new Sprite(new Texture("difficulty.png"));

        backBtn = new Button(new SpriteDrawable(backBtnSprite));

        soundsVolSld = new Slider(0,1,0.05f,false,skin);
        soundsVolSld.setValue(0.5f);
        musicVolSld = new Slider(0,1,0.05f,false,skin);
        musicVolSld.setValue(0.5f);

        easyCheckBox = new CheckBox("easy",skin);
        mediumCheckBox = new CheckBox("medium",skin);
        mediumCheckBox.setChecked(true);
        hardCheckBox = new CheckBox("hard",skin);

        kungfu = Gdx.audio.newSound(Gdx.files.internal("sound/effects/kungfu.mp3"));


        stage = new Stage();

        //title's position and size
        title.setSize(8 * Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 5);
        title.setCenterX(Gdx.graphics.getWidth() / 2);
        title.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 9 - title.getHeight());

        backBtn.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 12);
        backBtn.setX(Gdx.graphics.getWidth() / 5 - backBtn.getWidth());
        backBtn.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 10);

        soundsVolSprite.setSize(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() / 15);
        soundsVolSprite.setX(Gdx.graphics.getWidth() / 2 - soundsVolSprite.getWidth()/2);
        soundsVolSprite.setY(6* Gdx.graphics.getHeight() / 10 - soundsVolSprite.getHeight());

        soundsVolSld.setSize(5*Gdx.graphics.getWidth() /6 - soundsVolSld.getWidth()/2, Gdx.graphics.getHeight() /5);
        soundsVolSld.setX(Gdx.graphics.getWidth() / 2 - soundsVolSld.getWidth() / 2);
        soundsVolSld.setY(2 * Gdx.graphics.getHeight() /5);

        musicVolSprite.setSize(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() / 15);
        musicVolSprite.setX(Gdx.graphics.getWidth() / 2 - soundsVolSprite.getWidth()/2);
        musicVolSprite.setY(9* Gdx.graphics.getHeight() / 20 - soundsVolSprite.getHeight());

        musicVolSld.setSize(5*Gdx.graphics.getWidth() /6 - musicVolSld.getWidth()/2, Gdx.graphics.getHeight() /5);
        musicVolSld.setX(Gdx.graphics.getWidth() / 2 - musicVolSld.getWidth() / 2);
        musicVolSld.setY(2 * Gdx.graphics.getHeight() /8);

        difficultySprite.setSize(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() / 15);
        difficultySprite.setX(Gdx.graphics.getWidth() / 2 - soundsVolSprite.getWidth()/2);
        difficultySprite.setY(3* Gdx.graphics.getHeight() / 10 - soundsVolSprite.getHeight());

        easyCheckBox.setX(Gdx.graphics.getWidth() / 4 );
        easyCheckBox.setY(3*Gdx.graphics.getHeight() /18);

        mediumCheckBox.setX(Gdx.graphics.getWidth() / 4 );
        mediumCheckBox.setY(2*Gdx.graphics.getHeight() /18);

        hardCheckBox.setX(Gdx.graphics.getWidth() / 4 );
        hardCheckBox.setY(Gdx.graphics.getHeight() /18);

        Gdx.input.setInputProcessor(stage);

        stage.addActor(backBtn);
        stage.addActor(soundsVolSld);
        stage.addActor(musicVolSld);
        stage.addActor(easyCheckBox);
        stage.addActor(mediumCheckBox);
        stage.addActor(hardCheckBox);

        setListeners();

        if(MyColorNinja.DEBUG)
            System.out.println(soundsVol);

    }

    public float getSoundsVol() {
        return soundsVol;
    }

    public float getMusicVol() {
        return musicVol;
    }

    /**
     * Sets the listeners for the options in the Settings menu.
     */
    private void setListeners()
    {
        backBtn.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        kungfu.play(soundsVol);
                        gsm.set(new MainMenuState(gsm));
                    }
        });

        soundsVolSld.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        soundsVol = soundsVolSld.getValue();
                    }
        });

        musicVolSld.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 musicVol = musicVolSld.getValue();
            }
        });

        easyCheckBox.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(mediumCheckBox.isChecked() )
                    mediumCheckBox.setChecked(false);
                if(hardCheckBox.isChecked())
                    hardCheckBox.setChecked(false);
                if(!mediumCheckBox.isChecked() && !hardCheckBox.isChecked())
                    easyCheckBox.setChecked(true);

                difficulty = 0;
            }
        });

        mediumCheckBox.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(easyCheckBox.isChecked() )
                    easyCheckBox.setChecked(false);
                if(hardCheckBox.isChecked())
                    hardCheckBox.setChecked(false);
                if(!easyCheckBox.isChecked() && !hardCheckBox.isChecked())
                    mediumCheckBox.setChecked(true);

                difficulty = 1;
            }
        });

        hardCheckBox.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(easyCheckBox.isChecked() )
                    easyCheckBox.setChecked(false);
                if(mediumCheckBox.isChecked())
                    mediumCheckBox.setChecked(false);
                if(!mediumCheckBox.isChecked() && !easyCheckBox.isChecked())
                    hardCheckBox.setChecked(true);

                difficulty = 2;
            }
        });
    }


    @Override
    protected void handleInput() {


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

        sb.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        title.draw(sb);

        backBtn.draw(sb,1);

        soundsVolSprite.draw(sb,1);
        soundsVolSld.draw(sb,1);
        soundsVolSld.setValue(soundsVol);

        musicVolSprite.draw(sb,1);
        musicVolSld.draw(sb,1);
        musicVolSld.setValue(musicVol);

        difficultySprite.draw(sb,1);
        easyCheckBox.draw(sb,1);
        mediumCheckBox.draw(sb,1);
        hardCheckBox.draw(sb,1);

        sb.end();   // Close batch.

    }

    /**
     * Disposes the resources after the execution
     */
    @Override
    public void dispose() {
        background.dispose();
        title.getTexture().dispose();
    }
}
