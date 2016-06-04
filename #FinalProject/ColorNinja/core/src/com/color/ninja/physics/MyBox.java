package com.color.ninja.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.color.ninja.MyColorNinja;
import com.color.ninja.sprites.Shape;

/**
 * Box that'll be surrounding game's screen.
 */
public class MyBox {
    private Body topBound;
    private Body leftBound;
    private Body rightBound;

    private World world;

    private float width;
    private float height;


    public MyBox(World world, float width, float height)
    {
        this.width = width;
        this.height = height;
        this.world = world;

        setBounds();
    }

    public MyBox(World world){
        new MyBox(world, MyColorNinja.WIDTH / MyColorNinja.PIXELS_PER_METER, MyColorNinja.HEIGHT / MyColorNinja.PIXELS_PER_METER);
    }

    private Body createEdgeBody(World world, float p1x, float p1y, float p2x, float p2y)
    {
        Body bound;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        FixtureDef fixtureDef = new FixtureDef();
        EdgeShape edge = new EdgeShape();
        edge.set(p1x, p1y, p2x, p2y);
        fixtureDef.shape = edge;
        fixtureDef.restitution = 1f;

        bound = world.createBody(bodyDef);
        bound.createFixture(fixtureDef);
        edge.dispose();

        return bound;
    }

    private void setBounds()
    {
        this.leftBound = createEdgeBody(world, 0, 0, 0, height);
        this.rightBound = createEdgeBody(world, width, 0, width, height);
        this.topBound = createEdgeBody(world, 0, height, width, height);
    }

    private void createLeftBound()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

    }

    /**
     * Checks if a body has collided with any of the 3 edges. If it has, it'll return a letter identifying which edge.
      * @param contact The contact event given by a contactListener.
     * @param testBody The body to test the collision with.
     * @return 'T' if it collided with Top Bound, 'L' with Left Bound and 'R' with Right Bound. '0' if it did not collide.
     */
    public char checkCollisionWithBody(Contact contact, Body testBody)
    {
        if((contact.getFixtureA().getBody() == topBound && contact.getFixtureB().getBody() == testBody) ||
        (contact.getFixtureB().getBody() == topBound && contact.getFixtureA().getBody() == testBody))
        {
            return 'T';
        }

        if((contact.getFixtureA().getBody() == leftBound && contact.getFixtureB().getBody() == testBody) ||
                (contact.getFixtureB().getBody() == leftBound && contact.getFixtureA().getBody() == testBody))
        {
            return 'L';
        }

        if((contact.getFixtureA().getBody() == rightBound && contact.getFixtureB().getBody() == testBody) ||
                (contact.getFixtureB().getBody() == rightBound && contact.getFixtureA().getBody() == testBody))
        {
            return 'R';
        }

        return '0';
    }

    public char checkCollisionWithShape(Contact contact, Shape shape)
    {
        return checkCollisionWithBody(contact, shape.getBody());
    }
}
