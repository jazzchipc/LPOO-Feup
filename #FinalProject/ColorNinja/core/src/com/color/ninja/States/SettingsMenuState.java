package com.color.ninja.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.color.ninja.MyColorNinja;

/**
 * Class representing the Settings Menu
 */
public class SettingsMenuState extends com.color.ninja.states.State {

    private Stage stage;

    private Texture background;

    private Sprite title;


    public SettingsMenuState(com.color.ninja.states.GameStateManager gsm) {

        super(gsm);

        background = new Texture("background.png");
        title = new Sprite(new Texture("title2.png"));

        stage = new Stage();

        //title's position and size
        title.setSize(8 * MyColorNinja.WIDTH / 10, MyColorNinja.HEIGHT / 4);
        title.setCenterX(MyColorNinja.WIDTH / 2);
        title.setY(MyColorNinja.HEIGHT - MyColorNinja.HEIGHT / 20 - title.getHeight());
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

        sb.end();   // Close batch.

    }

    @Override
    public void dispose() {
        background.dispose();
        title.getTexture().dispose();
    }
}
