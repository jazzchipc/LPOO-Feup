package com.color.ninja.sprites;

import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class responsible for generating Shapes according to a shapeType and color.
 */
public class ShapeFactory implements AbstractFactory {

    private ArrayList<String> shapes;
    private ArrayList<String> colors;

    /**
     * Default Constructor for the ShapeFactory
     */
    public ShapeFactory() {
        shapes = new ArrayList<String>();
        shapes.add("square");
        shapes.add("triangle");
        shapes.add("circle");

        colors = new ArrayList<String>();
        colors.add("red");
        colors.add("green");
        colors.add("blue");
    }

    public boolean arrayListContainsCaseInsensitive(ArrayList<String> a, String s)
    {
        for (String string : a){
            if (string.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }

    public Shape getShape(World world, String shapeType, String color) {
        if(shapeType == null)
            return null;

        if(!(arrayListContainsCaseInsensitive(shapes, shapeType)))
            return null;

        if(!(arrayListContainsCaseInsensitive(colors, color)))
            return null;

        return new Shape(world, color, shapeType);
    }

    @Override
    public Shape getRandomShape(World world) {

        Random rand = new Random();

        int i = rand.nextInt((shapes.size()-1)+1); // generates a random number between 0 and shapes.size()-1

        String shapeType = shapes.get(i);

        i = rand.nextInt(colors.size());    // random other number

        String color = colors.get(i);

        return new Shape(world, color, shapeType);
    }
}
