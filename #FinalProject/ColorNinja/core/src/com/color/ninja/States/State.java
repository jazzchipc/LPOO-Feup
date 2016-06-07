package com.color.ninja.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.color.ninja.MyColorNinja;


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
     * Handles the input given by the user, utilizing LibGDX functions.
     */
    public abstract void handleInput();

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

    /**
     * Disposes all textures and media used in a state to prevent memory leaks.
     */
    public abstract void dispose();

}