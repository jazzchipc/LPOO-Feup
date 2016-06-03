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

    private float soundsVol = SettingsMenuState.soundsVol;

    public ScoresMenuState(GameStateManager gsm) {
        super(gsm);

        background = new Texture("background.png");

        title = new Sprite(new Texture("scorestitle.png"));

        backBtnSprite = new Sprite(new Texture ("backbtn.png"));

        backBtn = new Button(new SpriteDrawable(backBtnSprite));

        kungfu = Gdx.audio.newSound(Gdx.files.internal("sound/effects/kungfu.mp3"));

        stage = new Stage();

        // Title's position and size
        title.setSize(8 * MyColorNinja.WIDTH / 10, MyColorNinja.HEIGHT / 5);
        title.setCenterX(MyColorNinja.WIDTH / 2);
        title.setY(MyColorNinja.HEIGHT - MyColorNinja.HEIGHT / 9 - title.getHeight());

        backBtn.setSize(MyColorNinja.WIDTH / 6, MyColorNinja.HEIGHT / 12);
        backBtn.setX(MyColorNinja.WIDTH / 5 - backBtn.getWidth());
        backBtn.setY(MyColorNinja.HEIGHT - MyColorNinja.HEIGHT / 10);

        // Setting up stage and actors
        Gdx.input.setInputProcessor(stage);

        stage.addActor(backBtn);

        setListeners();

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

        // draws title sprite
        title.draw(sb);

        // draws game options buttons
        backBtn.draw(sb, 1);

        sb.end();   // Close batch.

    }

    @Override
    public void dispose() {
        background.dispose();
        title.getTexture().dispose();
        backBtnSprite.getTexture().dispose();
        kungfu.dispose();
    }
}

