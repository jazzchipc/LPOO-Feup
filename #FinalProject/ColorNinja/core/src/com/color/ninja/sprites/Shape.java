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
        sprite.setOriginCenter();
    }

    private void createBody()
    {
        // Creating the body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        // Because Box2D considers (0.0) the CENTER of a referential, and NOT lower left corner, sprite's position is
        //different than body's
        bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2)/ MyColorNinja.PIXELS_PER_METER,
                (sprite.getY() + sprite.getHeight() / 2) / MyColorNinja.PIXELS_PER_METER);

        body = world.createBody(bodyDef);

        // Defining the body's shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2 / MyColorNinja.PIXELS_PER_METER,
                sprite.getHeight() / 2 / MyColorNinja.PIXELS_PER_METER);

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

        //h = vy^2 / 2g
        float maxVelY = 3 * (float)Math.sqrt(MyColorNinja.HEIGHT / MyColorNinja.PIXELS_PER_METER / 2 * MyColorNinja.GRAVITY); // 3 times velocity needed to reach maximum height
        float minVelY = maxVelY / 5;

        //int randomNum = rand.nextInt((max - min) + 1) + min;
        //float randomFloat = rand.nextFloat() * (max - min) + min;
        float vy = rand.nextFloat() * (maxVelY-minVelY) + (minVelY);

        // reach = h * (vy/vx) / 4 <=> vx = h* vy / (4*reach)
        float maxVelX = 2 * (MyColorNinja.HEIGHT / MyColorNinja.PIXELS_PER_METER) * vy / (4*MyColorNinja.WIDTH/ MyColorNinja.PIXELS_PER_METER);    // 2 times the velocity needed to reach the screens width
        float minVelX = maxVelX / 5;

        float vx = rand.nextFloat() * (maxVelX-minVelX) + (minVelX);

        linearVelocity = new Vector2(vx, vy);
        angularVelocity = rand.nextFloat() * 4*Math.PI;

        body.setLinearVelocity(vx, vy);
        body.setAngularVelocity((float) angularVelocity);
    }

    public void applyTorque()
    {
        body.applyTorque(0.0f, true);
    }

    public void update()
    {
        // Compensating for position differences
        sprite.setPosition((body.getPosition().x * MyColorNinja.PIXELS_PER_METER) - sprite.getWidth() / 2,
                (body.getPosition().y * MyColorNinja.PIXELS_PER_METER) - sprite.getHeight() / 2);
        sprite.setRotation((float)Math.toDegrees(body.getAngle()));
    }

    public void dispose()
    {
        texture.dispose();
    }

    public Body getBody() {
        return body;
    }

}
