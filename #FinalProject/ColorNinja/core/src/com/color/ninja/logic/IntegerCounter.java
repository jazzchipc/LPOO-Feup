package com.color.ninja.logic;

import com.badlogic.gdx.Gdx;
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


    public void setValue(int value) { this.value = value; }

    public void increaseValue(int inc)
    {
        value = value + inc;
    }

    public void decreaseValue(int dec)
    {
        value = value - dec;
    }

    public void update()
    {
        label.setText(Integer.toString(value));
    }

    public void draw(SpriteBatch sb)
    {
        label.draw(sb);
    }

    public int getValue(){
        return value;
    }

}
