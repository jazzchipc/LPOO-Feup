package com.color.ninja.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 *  State class that will be the super-class for all other states.
 */
public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State (GameStateManager gsm)
    {
        this.gsm = gsm;
        this.cam = new OrthographicCamera();
        this.mouse = new Vector3();
    }

    /**
     * 
     */
    protected abstract void handleInput();

    /**
     * Makes all the changes necessary to the state, according a time period.
     * @param dt Update rate (in miliseconds).
     */
    public abstract void update(float dt);  // dt is the time between frames rendered

    /**
     * <P>Renders the current state SpriteBatch.</P>
     * <P>A SpriteBatch is a type of Batch, that holds all the graphic elements of the application.</P>
     * <P>There should be only one SpriteBatch for the entire application, since it is a pretty heavy object.</P>
     * @param sb State's SpriteBatch.
     */
    public abstract void render(SpriteBatch sb);

}