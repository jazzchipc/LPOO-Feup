package com.color.ninja.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.color.ninja.MyColorNinja;
import com.color.ninja.states.SettingsMenuState;

import java.util.ArrayList;
import java.util.Random;

/**
 * General shape class
 */
public class Shape {
    Camera cam;

    private static float ANIMATION_TIME = 0.5f;
    private static int NUM_OF_SPRITES = 7;

    //Sprite
    protected String color;
    protected String shapeType;

    protected String textureUrl;
    protected Texture texture;

    protected Sprite sprite;

    protected Button btn; // used to detect input

    protected String animationUrl;
    protected Animation shapeExplosion;
    protected float tExplosion;

    protected boolean exploded;
    protected boolean destroyed;

    //Physics
    protected Body body;
    protected World world;
    protected Vector2 linearVelocity;
    protected double angularVelocity;

    private Sound whoosh;
    private Sound blop;

    float difMult;

    /**
     * Default constructor with no arguments
     */
    public Shape(){}

    /**
     * Shape constructor.
     * @param world World in which the shape is in.
     * @param color Color of the Shape
     * @param shapeType Type of the Shape
     */
    public Shape(World world, String color, String shapeType) {
        // Setting parameters
        this.cam = new OrthographicCamera();

        this.world = world;
        this.color = color;
        this.shapeType = shapeType;

        this.exploded = false;
        this.destroyed = false;

        whoosh = Gdx.audio.newSound(Gdx.files.internal("sound/effects/whoosh.mp3"));
        blop = Gdx.audio.newSound(Gdx.files.internal("sound/effects/blop.mp3"));

        defineDifMult();
        createSprite();
        createButton();
        setListener();
        createBody();
        createAnimation();
    }

    /**
     * Defines a float which will influence sprite's velocity and size, according to the game's difficulty.
     */
    private void defineDifMult()
    {
        switch(MyColorNinja.getOurInstance().difficulty)
        {
            case 0: difMult = 0.75f; break;
            case 1: difMult = 1; break;
            case 2: difMult = 1.25f; break;
            default: difMult = 1;
        }
    }

    /**
     * Creates and sets the Shape's sprite, according to its parameters.
     */
    private void createSprite()
    {
        StringBuilder strBld = new StringBuilder();

        strBld.append(shapeType);
        strBld.append(color);
        strBld.append(".png");

        textureUrl = strBld.toString();

        texture = new Texture(textureUrl);
        sprite = new Sprite(texture);
        sprite.setSize(Gdx.graphics.getWidth() / 5 / difMult, Gdx.graphics.getHeight() / 8 / difMult);
        sprite.setOriginCenter();

        randomPlacing();
    }

    /**
     * Creates the button to make the shape clickable
     */
    private void createButton()
    {
        btn = new Button(new SpriteDrawable(sprite));
        sprite.setOrigin(sprite.getOriginX(), sprite.getOriginY());
    }

    /**
     * Sets the listener for the shape's button
     */
    private void setListener()
    {
        if(Gdx.input.isTouched())
        {

            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            Vector3 input = new Vector3(x, y, 0);
            cam.unproject(input);

            if(MyColorNinja.DEBUG) {
                System.out.println("Clicked on screen.");

                System.out.println(x);
                System.out.println(y);

                System.out.println(input.x);
                System.out.println(input.y);
            }

            if(sprite.getBoundingRectangle().contains(x, Gdx.graphics.getHeight() - y))
            {
                if(MyColorNinja.DEBUG)
                    System.out.println("Clicked.");

                explode();
                blop.play(SettingsMenuState.soundsVol);
            }

        }
        /*sprite.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MyColorNinja.DEBUG)
                    System.out.println("Clicked.");
                explode();
                blop.play(SettingsMenuState.soundsVol);
            }
        });*/
    }

    /**
     * Creates the animation for when the shape is destroyed
     */
    private void createAnimation()
    {
        StringBuilder strBld = new StringBuilder();

        strBld.append("color ninja animacoes/");
        strBld.append(shapeType);
        strBld.append(color);
        strBld.append("/");
        strBld.append(shapeType);
        strBld.append(color);
        strBld.append("animation.png");

        this.animationUrl = strBld.toString();

        Texture spriteSheet = new Texture(animationUrl);

        shapeExplosion = new Animation(new TextureRegion(spriteSheet), NUM_OF_SPRITES, ANIMATION_TIME);

        tExplosion = 0;
    }

    /**
     * The shape explodes. The animation is played and the shape disappears.
     */
    private void explode()
    {
        this.exploded = true;
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
        float maxVelY = 2.5f * (float)Math.sqrt(Gdx.graphics.getHeight() / MyColorNinja.PIXELS_PER_METER / 2 * MyColorNinja.GRAVITY); // 3 times velocity needed to reach maximum height
        float minVelY = maxVelY / 2;

        //int randomNum = rand.nextInt((max - min) + 1) + min;
        //float randomFloat = rand.nextFloat() * (max - min) + min;
        float vy = rand.nextFloat() * (maxVelY-minVelY) + (minVelY);

        // reach = h * (vy/vx) / 4 <=> vx = h* vy / (4*reach)
        float maxVelX = 2 * (Gdx.graphics.getHeight() / MyColorNinja.PIXELS_PER_METER) * vy / (4*Gdx.graphics.getWidth()/ MyColorNinja.PIXELS_PER_METER);    // 2 times the velocity needed to reach the screens width
        float minVelX = maxVelX / 5;

        float vx = rand.nextFloat() * (maxVelX-minVelX) + (minVelX);

        linearVelocity = new Vector2(vx * difMult, vy * difMult);
        angularVelocity = rand.nextFloat() * 4*Math.PI;

        body.setLinearVelocity(vx, vy);
        body.setAngularVelocity((float) angularVelocity);
    }

    /**
     * Puts the shape below the screen on a random x coordinate
     */
    private void randomPlacing()
    {
        // Put Shape below the screen on a random x coordinate
        Random rand = new Random();

        float randomX = rand.nextInt(Gdx.graphics.getWidth() - (int)sprite.getWidth());

        sprite.setPosition(randomX, -sprite.getHeight());

        if(MyColorNinja.DEBUG)
        {
            System.out.print("X: ");
            System.out.print(sprite.getX());
            System.out.print(", Y: ");
            System.out.println(sprite.getY());
        }
    }

    public void sling()
    {
        setVelocities();

        if(sprite.getX() > (Gdx.graphics.getWidth() / 2)) // if the object is thrown from the right half, it's thrown torwards the left wall
        {
            body.setLinearVelocity(-body.getLinearVelocity().x, body.getLinearVelocity().y);
        }

        whoosh.play(SettingsMenuState.soundsVol);
    }

    /**
     * Checks if a shape is destroyed.
     * @return true if yes, false otherwise.
     */
    private boolean checkIfDestroyed()
    {
        if(sprite.getY() + (sprite.getHeight() / MyColorNinja.PIXELS_PER_METER) < -sprite.getHeight() * 2)   // if it way down the screen
            return true;

        if(tExplosion >= ANIMATION_TIME)    // if it has already exploded
            return true;

        return false;
    }

    private void addToStage(Stage stage)
    {
        stage.addActor(btn);
    }

    private void addToArray(ArrayList<Shape> array)
    {
        array.add(this);
    }

    public void addToGame(ArrayList<Shape> array, Stage stage, Camera cam)
    {
        addToArray(array);
        addToStage(stage);
        this.cam = cam;
    }

    private void removeFromStage(Stage stage) { btn.remove(); }

    private void removeFromArray (ArrayList<Shape> shapes) {shapes.remove(this);}

    public void removeFromGame(ArrayList<Shape> shapes, Stage stage)
    {
        removeFromArray(shapes);
        removeFromStage(stage);
    }

    public void update(float dt)
    {
        // Instead of the sprite, the button is the one that is moved

        setListener();

        // Compensating for position differences
        if(!exploded)
        sprite.setPosition((body.getPosition().x * MyColorNinja.PIXELS_PER_METER) - sprite.getWidth() / 2,
                (body.getPosition().y * MyColorNinja.PIXELS_PER_METER) - sprite.getHeight() / 2);

        else {
            shapeExplosion.update(dt);
            tExplosion = tExplosion + dt;

            if(this.body.getFixtureList().size != 0)
                this.body.destroyFixture(this.body.getFixtureList().first());   // destroy fixture so it doesn't affect other throws
        }

        destroyed = checkIfDestroyed();

        if (MyColorNinja.DEBUG) {
            System.out.println(destroyed);
            System.out.println(sprite.getY());
        }
    }
    public void draw(SpriteBatch batch)
    {
        // This draws the sprite in current buttons position. Since Actors can't rotate, this was the easiest way to implement Shape as a Button.
        if(!exploded)
            batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(),(float)Math.toDegrees(body.getAngle()));

        else if(!destroyed)
            batch.draw(shapeExplosion.getFrame(), sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(),(float)Math.toDegrees(body.getAngle()));
    }

    public void dispose()
    {
        texture.dispose();
    }

    public Body getBody() {
        return body;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public boolean isExploded() {
        return exploded;
    }




}
