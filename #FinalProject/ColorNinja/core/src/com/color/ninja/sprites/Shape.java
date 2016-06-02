package com.color.ninja.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.color.ninja.MyColorNinja;
import com.color.ninja.states.GameState;

import java.util.Random;

/**
 * General shape class
 */
public class Shape {
    //Sprite
    protected String color;
    protected String shapeType;

    protected String textureUrl;
    protected Texture texture;

    public Sprite sprite;

    //Physics
    protected Body body;
    protected World world;
    protected Vector2 linearVelocity;
    protected double angularVelocity;


    public Shape(){}

    public Shape(World world, String color, String shapeType) {
        // Setting parameters
        this.world = world;
        this.color = color;
        this.shapeType = shapeType;

        createSprite();
        createBody();
        setVelocities();
    }

    private void createSprite()
    {
        StringBuilder strBld = new StringBuilder();

        strBld.append(color);
        strBld.append(shapeType);
        strBld.append(".png");

        textureUrl = strBld.toString();

        texture = new Texture(textureUrl);
        sprite = new Sprite(texture);
        sprite.setSize(MyColorNinja.WIDTH / 5, MyColorNinja.HEIGHT / 8);
    }

    private void createBody()
    {
        // Creating the body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        // Because Box2D considers (0.0) the CENTER of a referential, and NOT lower left corner, sprite's position is
        //different than body's
        bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2)/ GameState.METERS_PER_PIXEL,
                (sprite.getY() + sprite.getHeight() / 2) / GameState.METERS_PER_PIXEL);

        body = world.createBody(bodyDef);

        // Defining the body's shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2 / GameState.METERS_PER_PIXEL,
                sprite.getHeight() / 2 / GameState.METERS_PER_PIXEL);

        // Defining body's fixture which includes some physical properties
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        Fixture fixture = body.createFixture(fixtureDef);

        // Shape is the only one disposable
        shape.dispose();
    }

    private void setVelocities()
    {
        Random rand = new Random();

        int minVelY = MyColorNinja.HEIGHT * 50;
        int maxVelY = MyColorNinja.HEIGHT * 100;

        int minVelX = MyColorNinja.WIDTH * 50;
        int maxVelX = MyColorNinja.WIDTH * 100;


        //int randomNum = rand.nextInt((max - min) + 1) + min;
        int vy = rand.nextInt((maxVelY-minVelY)+1) + (minVelY);

        int vx = rand.nextInt((maxVelX - minVelX)+1) + (minVelX);

        float angularMult = rand.nextFloat();

        linearVelocity = new Vector2(vx, vy);
        angularVelocity = angularMult * 4*Math.PI;

        //body.setAngularVelocity((float) angularVelocity);
        //body.setAwake(true);

    }

    public void sling()
    {
        body.setLinearVelocity(0f, 10f);
    }

    public void applyTorque()
    {
        body.applyTorque(0.0f, true);
    }

    public void update()
    {
        // Compensating for position differences
        sprite.setPosition((body.getPosition().x * GameState.METERS_PER_PIXEL) - sprite.getWidth() / 2,
                (body.getPosition().y * GameState.METERS_PER_PIXEL) - sprite.getHeight() / 2);
        sprite.setRotation((float)Math.toDegrees(body.getAngle()));
    }

    public void dispose()
    {
        texture.dispose();
    }
}
