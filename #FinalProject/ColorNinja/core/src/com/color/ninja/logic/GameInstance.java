package com.color.ninja.logic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Logical game instance singleton.
 */
public class GameInstance{
    private static GameInstance ourInstance = new GameInstance();

    /**
     *
     * @return The Previously Created GameInstance
     */
    public static GameInstance getInstance() {
        return ourInstance;
    }

    // Game counters
    private IntegerCounter scoreCounter;
    private IntegerCounter timerCounter;

    /**
     * Default constructor
     */
    private GameInstance() {

    }

    public void render(SpriteBatch sb)
    {

    }

    public void update(float dt)
    {

    }

    public void dispose()
    {

    }
}
