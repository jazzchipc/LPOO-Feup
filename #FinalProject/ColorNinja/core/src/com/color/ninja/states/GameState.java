package com.color.ninja.states;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import com.color.ninja.sprites.*;
import com.color.ninja.sprites.Shape;

import java.util.ArrayList;

/**
 * Class representing the game.
 */
public class GameState extends com.color.ninja.states.State {

    // World/physics settings
    public static final float METERS_PER_PIXEL = 100f;  // scaling physics world
    private Vector2 gravity;
    private World world;

    // Physics debugger
    protected Box2DDebugRenderer debugRenderer;
    protected Matrix4 debugMatrix;

    // Shapes
    private ShapeFactory factory;
    private ArrayList<Shape> shapes;


    public GameState(com.color.ninja.states.GameStateManager gsm) {

        super(gsm);

        gravity = new Vector2(0, -9.8f); // f stands for float
        world = new World(gravity, true);
        factory = new ShapeFactory();
        shapes = new ArrayList<Shape>();

        // Debug renderer
        debugRenderer=new Box2DDebugRenderer();

        Shape s = factory.getRandomShape(world);
        shapes.add(s);
        shapes.get(0).sling();

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);

        shapes.get(0).applyTorque();

        shapes.get(0).update();
    }

    @Override
    public void render(SpriteBatch sb) {
        debugMatrix = sb.getProjectionMatrix();

        sb.begin();

        shapes.get(0).sprite.draw(sb);

        sb.end();

        debugRenderer.render(world, debugMatrix);
    }

    @Override
    public void dispose() {

        shapes.get(0).dispose();
    }
}
