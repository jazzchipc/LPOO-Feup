package com.color.ninja.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.color.ninja.ui.TextLabel;

/**
 * Class responsible for counting game values while the game is running.
 */
public class IntegerCounter {

    private TextLabel label;
    private int value;

    /**
     * Creates a new IntegerCounter in given position. The origin is the lower left corner.
     * @param posX Position in X-Axis in pixels.
     * @param posY Position in Y-Axis in pixels.
     */
    public IntegerCounter(int posX, int posY)
    {
        value = 0;
        label = new TextLabel(Integer.toString(value),posX, posY);
    }

    /**
     * Gives value a new value.
     * @param value New Integer
     */
    public void setValue(int value) { this.value = value; }

    /**
     * Increases value by inc.
     * @param inc
     */
    public void increaseValue(int inc)
    {
        value = value + inc;
    }

    /**
     * Decreases value by dec.
     * @param dec
     */
    public void decreaseValue(int dec)
    {
        value = value - dec;
    }

    /**
     * Updates the label by converting value to a string.
     */
    public void update()
    {
        label.setText(Integer.toString(value));
    }

    /**
     * Draws the label in the SpriteBatch.
     * @param sb
     */
    public void draw(SpriteBatch sb)
    {
        label.draw(sb);
    }

    /**
     * Returns the value, for comparison purposes.
     * @return value
     */
    public int getValue(){
        return value;
    }

}
