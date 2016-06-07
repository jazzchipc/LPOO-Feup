package com.color.ninja.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.color.ninja.MyColorNinja;

/**
 * Customized physics world.
 */
public class MyGameWorld {

    // World/physics elements
    private Vector2 gravity;
    private MyBox box;
    private World world;

    public MyGameWorld()
    {
        // Setting up physics and box
        gravity = new Vector2(0, -MyColorNinja.GRAVITY); // f stands for float
        world = new World(gravity, true);
        box = new MyBox(world);
    }


}
