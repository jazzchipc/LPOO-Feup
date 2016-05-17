package com.color.ninja.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 *  This class is responsible for the management of game states. It holds each game state in a stack, as it is added
 *  or removed.
 */
public class GameStateManager {

    private Stack<State> states;

    public GameStateManager()
    {
        states = new Stack<State>();
    }

    /**
     * Pushes a new state to GSM.
     * @param state State to add.
     */
    public void push(State state)
    {
        states.push(state);
    }

    /**
     * Pops the last state in stack.
     */
    public void pop()
    {
        states.pop();
    }

    /**
     * Changes current top state to a new state.
     * @param state New state.
     */
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
