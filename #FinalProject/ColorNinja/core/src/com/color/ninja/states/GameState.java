package com.color.ninja.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.color.ninja.MyColorNinja;
import com.color.ninja.sprites.Shape;

/**
 * Class representing the game.
 */
public class GameState extends com.color.ninja.states.State {

    private Shape testSquare;

    public GameState(com.color.ninja.states.GameStateManager gsm) {

        super(gsm);

        testSquare = new Shape(0, 0);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

        testSquare.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        sb.draw(testSquare.getShapeSprite(), testSquare.getPosition().x, testSquare.getPosition().y, 100, 100);

        sb.end();
    }

    @Override
    public void dispose() {

        testSquare.dispose();

    }
}
