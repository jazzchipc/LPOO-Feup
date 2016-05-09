package com.color.ninja.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by josea on 29/04/2016.
 */
public class GameStateManager {

    private Stack<State> states;

    public GameStateManager()
    {
        states = new Stack<State>();
    }

    public void push(State state)
    {
        states.push(state);
    }

    public void pop()
    {
        states.pop();
    }

    public void set (State state)
    {
        states.pop();
        states.push(state);
    }

    public void update(float dt)    // dt = time between frames
    {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb)
    {
        states.peek().render(sb);
    }

}
