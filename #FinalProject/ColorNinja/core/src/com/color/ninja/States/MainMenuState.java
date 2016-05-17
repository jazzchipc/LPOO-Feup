package com.color.ninja.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.color.ninja.MyColorNinja;

/**
 * CLass representing the MainMenu.
 */
public class MainMenuState extends State {

    private Texture shape;

    public MainMenuState(com.color.ninja.states.GameStateManager gsm) {
        super(gsm);

        shape = new Texture("bolaazul.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); // Open batch to add things to it. A batch works like a box which is filled with images.

        sb.draw(shape, 0, 0, MyColorNinja.WIDTH, MyColorNinja.HEIGHT);

        sb.end();   // Close batch.

    }
}
