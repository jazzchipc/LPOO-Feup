package com.color.ninja.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.color.ninja.MyColorNinja;

/**
 * Class that'll represent the shapes being used in the game, such as Circle, Square and Triangle.
 */
public class Shape {

    private Vector2 position;
    private Vector2 velocity;

    private Sprite shapeSprite;

    public Shape(int x, int y)
    {
        position = new Vector2 (x, y);
        velocity = new Vector2 (1, 300);

        shapeSprite = new Sprite(new Texture("squareblue.png"));
    }

    public void update(float dt)
    {
        velocity.add(0, MyColorNinja.GRAVITY);
        velocity.scl(dt);   // scales the changes to match time interval

        position.add(velocity.x, velocity.y);

        velocity.scl(1 / dt); // reverse the scale
    }

    public void render(SpriteBatch sb)
    {
        shapeSprite.setPosition(position.x, position.y);
        shapeSprite.setSize(100, 100);
        shapeSprite.draw(sb);

        /*"I had to use sprite.draw(batch); instead of using Batch.draw(Sprite sp, float x, float y);
        since the Batch.draw(...) method takes the texture from the passed sprite and uses the texture in
        the drawing process which has a fixed width and a fixed height.

        Another way to solve this problem is to use the
        batch.draw(Sprite, float x, float y, float width, float height); method in the SpriteBatch class."*/
    }

    public void dispose()
    {
        shapeSprite.getTexture().dispose();
    }

    public Vector2 getPosition() {
        return position;
    }

    public Sprite getShapeSprite() {
        return shapeSprite;
    }
}
