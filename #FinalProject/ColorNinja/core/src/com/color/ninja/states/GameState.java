package com.color.ninja.states;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private Vector2 gravity;
    private World world;
    private ArrayList<Body> bodies;

    // Shapes sprites
    private ShapeFactory factory;
    private ArrayList<Shape> shapes;


    public GameState(com.color.ninja.states.GameStateManager gsm) {

        super(gsm);

        gravity = new Vector2(0, -98f); // f stands for float
        world = new World(gravity, true);
        bodies = new ArrayList<Body>();
        factory = new ShapeFactory();
        shapes = new ArrayList<Shape>();

        Shape s = factory.getRandomShape();
        shapes.add(s);
    }

    /**
     * Fills the bodies array with bodies corresponding to the shapes.
     */
    public void generateBodies()
    {
        for(int i = 0; i < shapes.size(); i++)
        {
            /*http://www.gamefromscratch.com/post/2014/08/27/LibGDX-Tutorial-13-Physics-with-Box2D-Part-1-A-Basic-Physics-Simulations.aspx*/

            Shape currentShape = shapes.get(i);

            // Creating the body
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set(currentShape.sprite.getX(), currentShape.sprite.getY());

            Body body = world.createBody(bodyDef);

            // Defining the body's shape
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(currentShape.sprite.getWidth()/2, currentShape.sprite.getHeight()/2);

            // Defining body's fixture which includes some physical properties
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = 1f;

            Fixture fixture = body.createFixture(fixtureDef);

            // Shape is the only one disposable
            shape.dispose();

            bodies.add(body);
        }
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        shapes.get(0).sprite.draw(sb);

        sb.end();
    }

    @Override
    public void dispose() {

        shapes.get(0).dispose();
    }
}
