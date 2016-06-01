package com.color.ninja.sprites;

/**
 * Interface for the shape factory.
 */
public interface AbstractFactory {
    Shape getShape(String shapeType, String color);
    Shape getRandomShape();
}
