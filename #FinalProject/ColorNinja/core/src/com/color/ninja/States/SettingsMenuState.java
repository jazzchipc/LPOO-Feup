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

    private Sound kungfu;


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
        title.setSize(8 * MyColorNinja.WIDTH / 10, MyColorNinja.HEIGHT / 5);
        title.setCenterX(MyColorNinja.WIDTH / 2);
        title.setY(MyColorNinja.HEIGHT - MyColorNinja.HEIGHT / 9 - title.getHeight());

        backBtn.setSize(MyColorNinja.WIDTH / 6, MyColorNinja.HEIGHT / 12);
        backBtn.setX(MyColorNinja.WIDTH / 5 - backBtn.getWidth());
        backBtn.setY(MyColorNinja.HEIGHT - MyColorNinja.HEIGHT / 10);

        soundsVolSprite.setSize(MyColorNinja.WIDTH /2, MyColorNinja.HEIGHT / 15);
        soundsVolSprite.setX(MyColorNinja.WIDTH / 2 - soundsVolSprite.getWidth()/2);
        soundsVolSprite.setY(6* MyColorNinja.HEIGHT / 10 - soundsVolSprite.getHeight());

        soundsVolSld.setSize(5*MyColorNinja.WIDTH /6 - soundsVolSld.getWidth()/2, MyColorNinja.HEIGHT /5);
        soundsVolSld.setX(MyColorNinja.WIDTH / 2 - soundsVolSld.getWidth() / 2);
        soundsVolSld.setY(2 * MyColorNinja.HEIGHT /5);

        musicVolSprite.setSize(MyColorNinja.WIDTH /2, MyColorNinja.HEIGHT / 15);
        musicVolSprite.setX(MyColorNinja.WIDTH / 2 - soundsVolSprite.getWidth()/2);
        musicVolSprite.setY(9* MyColorNinja.HEIGHT / 20 - soundsVolSprite.getHeight());

        musicVolSld.setSize(5*MyColorNinja.WIDTH /6 - musicVolSld.getWidth()/2, MyColorNinja.HEIGHT /5);
        musicVolSld.setX(MyColorNinja.WIDTH / 2 - musicVolSld.getWidth() / 2);
        musicVolSld.setY(2 * MyColorNinja.HEIGHT /8);

        difficultySprite.setSize(MyColorNinja.WIDTH /2, MyColorNinja.HEIGHT / 15);
        difficultySprite.setX(MyColorNinja.WIDTH / 2 - soundsVolSprite.getWidth()/2);
        difficultySprite.setY(3* MyColorNinja.HEIGHT / 10 - soundsVolSprite.getHeight());

        //easyCheckBox.setSize(MyColorNinja.WIDTH/10,MyColorNinja.HEIGHT/10);
       // easyCheckBox.setScale(2);
        easyCheckBox.setX(MyColorNinja.WIDTH / 4 );
        easyCheckBox.setY(3*MyColorNinja.HEIGHT /18);

       // mediumCheckBox.setSize(MyColorNinja.WIDTH/5,MyColorNinja.HEIGHT/5);
        mediumCheckBox.setX(MyColorNinja.WIDTH / 4 );
        mediumCheckBox.setY(2*MyColorNinja.HEIGHT /18);

        //hardCheckBox.setSize(MyColorNinja.WIDTH/10,MyColorNinja.HEIGHT/10);
        hardCheckBox.setX(MyColorNinja.WIDTH / 4 );
        hardCheckBox.setY(MyColorNinja.HEIGHT /18);

        Gdx.input.setInputProcessor(stage);

        stage.addActor(backBtn);
        stage.addActor(soundsVolSld);
        stage.addActor(musicVolSld);
        stage.addActor(easyCheckBox);
        stage.addActor(mediumCheckBox);
        stage.addActor(hardCheckBox);


        setListeners();

    }

    public float getSoundsVol() {
        return soundsVol;
    }

    public float getMusicVol() {
        return musicVol;
    }

    private void setListeners()
            {
                backBtn.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        kungfu.play(soundsVol);
                        gsm.set(new MainMenuState(gsm));
                    }
                });

                soundsVolSld.addListener(new InputListener(){
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        super.touchUp(event, x, y, pointer, button);
                        soundsVol = soundsVolSld.getValue();
                    }

                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return super.touchDown(event, x, y, pointer, button);
                    }

                });

                musicVolSld.addListener(new InputListener() {
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        super.touchUp(event, x, y, pointer, button);
                        musicVol = musicVolSld.getValue();
                    }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
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

        sb.draw(background,0,0,MyColorNinja.WIDTH,MyColorNinja.HEIGHT);

        title.draw(sb);

        backBtn.draw(sb,1);

        soundsVolSprite.draw(sb,1);
        soundsVolSld.draw(sb,1);

        musicVolSprite.draw(sb,1);
        musicVolSld.draw(sb,1);

        difficultySprite.draw(sb,1);
        easyCheckBox.draw(sb,1);
        mediumCheckBox.draw(sb,1);
        hardCheckBox.draw(sb,1);

        sb.end();   // Close batch.

    }

    @Override
    public void dispose() {
        background.dispose();
        title.getTexture().dispose();
    }
}
