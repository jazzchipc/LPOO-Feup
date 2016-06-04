package com.color.ninja.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.color.ninja.MyColorNinja;
import com.color.ninja.sprites.*;
import com.color.ninja.sprites.Shape;

import java.util.ArrayList;

/**
 * Class representing the game.
 */
public class GameState extends com.color.ninja.states.State {

    private Stage stage;

    private Texture background;

    private Sprite pauseBtnSprite;
    private Button pauseBtn;

    // World/physics settings
    public static final float PIXELS_PER_METER = 100f;  // scaling physics world
    public static final float GRAVITY = 9.8f;

    private Vector2 gravity;
    private World world;
    private Body box;

    // Physics debugger
    protected Box2DDebugRenderer debugRenderer;
    protected Matrix4 debugMatrix;

    // Shapes
    private ShapeFactory factory;
    private ArrayList<Shape> shapes;


    public GameState(com.color.ninja.states.GameStateManager gsm) {

        super(gsm);

        // Setting up scene
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);


        // Setting up sprites
        background = new Texture("background.png");

        pauseBtnSprite = new Sprite(new Texture("pause.png"));
        pauseBtn = new Button(new SpriteDrawable(pauseBtnSprite));
        pauseBtn.setSize(MyColorNinja.WIDTH / 10, MyColorNinja.HEIGHT / 14);
        pauseBtn.setX(MyColorNinja.WIDTH - MyColorNinja.WIDTH/20 - pauseBtn.getWidth());
        pauseBtn.setY(MyColorNinja.HEIGHT - MyColorNinja.HEIGHT / 10);


        // Setting up physics
        gravity = new Vector2(0, -GRAVITY); // f stands for float
        world = new World(gravity, true);
        factory = new ShapeFactory();
        shapes = new ArrayList<Shape>();

        createBox();
        setWorldListeners();

        // Debug renderer
        debugRenderer=new Box2DDebugRenderer();

        Shape s = factory.getRandomShape(world);
        shapes.add(s);

        //Adding actors to scene
        setStageListeners();
        stage.addActor(pauseBtn);
    }

    private void setStageListeners()
    {
        pauseBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new PauseMenuState(gsm));
            }
        });

    }

    private void setWorldListeners()
    {
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if((contact.getFixtureA().getBody() == box && contact.getFixtureB().getBody() == shapes.get(0).getBody()) ||
                        (contact.getFixtureB().getBody() == box && contact.getFixtureA().getBody() == shapes.get(0).getBody()))
                {
                   System.out.println("Collision");
                }

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }


    private void createBox()
    {
        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;
        float width = MyColorNinja.WIDTH/PIXELS_PER_METER;

        // Set the height to just 50 pixels above the bottom of the screen so we can see the edge in the
        // debug renderer
        float height = MyColorNinja.HEIGHT/PIXELS_PER_METER- 50/PIXELS_PER_METER;
        bodyDef2.position.set(0,0);
        FixtureDef fixtureDef2 = new FixtureDef();

        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(-width/2,-height/2,width/2,-height/2);
        fixtureDef2.shape = edgeShape;

        box = world.createBody(bodyDef2);
        box.createFixture(fixtureDef2);
        edgeShape.dispose();
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
        debugMatrix = new Matrix4(sb.getProjectionMatrix());
        debugMatrix.scale(PIXELS_PER_METER, PIXELS_PER_METER, 1);

        sb.begin();

        sb.draw(background,0,0, MyColorNinja.WIDTH,MyColorNinja.HEIGHT);

        pauseBtn.draw(sb,1);

        shapes.get(0).sprite.draw(sb);

        sb.end();

        debugRenderer.render(world, debugMatrix);
    }

    @Override
    public void dispose() {

        shapes.get(0).dispose();
        world.dispose();
    }
}
