package com.color.ninja.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.color.ninja.MyColorNinja;

/**
 * Class representing the Settings Menu
 */
public class SettingsMenuState extends com.color.ninja.states.State {

    private Stage stage;

    private Texture background;

    private Sprite title;
    private Sprite BackBtnSprite;

    private Button backBtn;

    public SettingsMenuState(com.color.ninja.states.GameStateManager gsm) {

        super(gsm);

        background = new Texture("background.png");
        title = new Sprite(new Texture("title2.png"));
        BackBtnSprite = new Sprite(new Texture ("backbtn.png"));

        backBtn = new Button(new SpriteDrawable(BackBtnSprite));

        stage = new Stage();


        //title's position and size
        title.setSize(8 * MyColorNinja.WIDTH / 10, MyColorNinja.HEIGHT / 4);
        title.setCenterX(MyColorNinja.WIDTH / 2);
        title.setY(MyColorNinja.HEIGHT - MyColorNinja.HEIGHT / 9 - title.getHeight());

        backBtn.setSize(MyColorNinja.WIDTH / 6, MyColorNinja.HEIGHT / 12);
        backBtn.setX(MyColorNinja.WIDTH / 5 - backBtn.getWidth());
        backBtn.setY(MyColorNinja.HEIGHT - MyColorNinja.HEIGHT / 10);

        Gdx.input.setInputProcessor(stage);

        stage.addActor(backBtn);

        setListeners();

    }

    private void setListeners()
    {
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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

        sb.draw(background,0,0,MyColorNinja.WIDTH,MyColorNinja.HEIGHT);

        title.draw(sb);

        backBtn.draw(sb,1);

        sb.end();   // Close batch.

    }

    @Override
    public void dispose() {
        background.dispose();
        title.getTexture().dispose();
    }
}
