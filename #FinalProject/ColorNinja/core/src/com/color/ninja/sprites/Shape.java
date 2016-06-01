package com.color.ninja.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.color.ninja.MyColorNinja;

/**
 * General shape class
 */
public class Shape {
    protected String color;
    protected String shapeType;

    protected String textureUrl;
    protected Texture texture;

    public Sprite sprite;

    public Shape(){}

    public Shape(String color, String shapeType) {
        this.color = color;
        this.shapeType = shapeType;

        StringBuilder strBld = new StringBuilder();

        strBld.append(color);
        strBld.append(shapeType);
        strBld.append(".png");

        textureUrl = strBld.toString();

        texture = new Texture(textureUrl);
        sprite = new Sprite(texture);
        sprite.setSize(MyColorNinja.WIDTH/5, MyColorNinja.HEIGHT/8);
    }

    public void dispose()
    {
        texture.dispose();
    }
}
