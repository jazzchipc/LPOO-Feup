package com.color.ninja.sprites;

import com.badlogic.gdx.physics.box2d.World;

/**
 * Interface for the shape factory.
 */
public interface AbstractFactory {
    /**
     * Creates a shape with given characteristics and adds it to the world.
     * @param world Box2D world to add to.
     * @param shapeType Whether it's a "circle", "square", or "triangle".
     * @param color Whether it's "red", "green" or "blue".
     * @return The resulting Shape.
     */
    Shape getShape(World world, String shapeType, String color);

    /**
     * Creates a shape with random characteristics and adds it to the world.
     * @param world Box2D world to add to.
     * @return The resulting Shape.
     */
    Shape getRandomShape(World world);
}
