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

    private float fontWidth = Gdx.graphics.getWidth() / 400;
    private float fontHeight = Gdx.graphics.getHeight() / 600;

    public TextLabel(String s)
    {
        font = new BitmapFont(Gdx.files.internal("fonts/DomoAregato.fnt"));
        text = s;

        this.posX = 0;
        this.posY = 0;

        font.getData().setScale(fontWidth, fontHeight);
    }

    public TextLabel(String s, int posX, int posY)
    {
        font = new BitmapFont(Gdx.files.internal("fonts/DomoAregato.fnt"));
        text = s;
        this.posX = posX;
        this.posY = posY;

        font.getData().setScale(fontWidth, fontHeight);
    }

    public int getWidth()
    {
        System.out.println(font.getSpaceWidth());
        System.out.println(this.text.length() * fontWidth * font.getSpaceWidth());
        System.out.println(fontHeight);

        return (int)(this.text.length() * fontWidth * 8 * font.getSpaceWidth());

    }

    public int getHeight()
    {
        return (int)(fontHeight * (int)font.getCapHeight());
    }

    public void setPosition(int x, int y)
    {
        this.posX = x;
        this.posY = y;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
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
