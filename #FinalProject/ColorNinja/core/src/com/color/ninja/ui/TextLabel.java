package com.color.ninja.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Represents a basic text label with customed font and BLACK color.
 */
public class TextLabel {
    private BitmapFont font;
    private String text;
    private int posX;
    private int posY;


    public TextLabel(String s, int posX, int posY)
    {
        font = new BitmapFont(Gdx.files.internal("fonts/DomoAregato.fnt"));
        text = s;
        this.posX = posX;
        this.posY = posY;

        font.getData().setScale(Gdx.graphics.getWidth() / 400, Gdx.graphics.getHeight() / 600);

    }

    public void draw(SpriteBatch sb)
    {
        font.draw(sb, text, posX, posY);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
