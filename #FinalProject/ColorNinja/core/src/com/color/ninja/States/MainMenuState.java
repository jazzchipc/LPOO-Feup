package com.color.ninja.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by JoséAleixo on 11/05/2016.
 */
public class MainMenuState extends State {

    private Texture shape;

    public MainMenuState(GameStateManager gsm) {
        super(gsm);

        shape = new Texture("bola.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }
}
