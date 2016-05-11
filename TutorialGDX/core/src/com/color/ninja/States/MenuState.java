package com.color.ninja.States;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.color.ninja.ColorNinja;

/**
 * Created by josea on 29/04/2016.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playBtn;


    public MenuState(GameStateManager gsm) {
        super(gsm);

        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); //Open batch to add things

        sb.draw(background, 0, 0, ColorNinja.WIDTH, ColorNinja.HEIGHT);
        sb.draw(playBtn,(ColorNinja.WIDTH / 2) - (playBtn.getWidth() / 2), (ColorNinja.HEIGHT / 2));

        sb.end();   //Close batch

    }
}
