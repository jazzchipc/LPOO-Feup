package com.color.ninja.sprites;

import com.badlogic.gdx.physics.box2d.World;

/**
 * Interface for the shape factory.
 */
public interface AbstractFactory {
    Shape getShape(World world, String shapeType, String color);
    Shape getRandomShape(World world);
}
