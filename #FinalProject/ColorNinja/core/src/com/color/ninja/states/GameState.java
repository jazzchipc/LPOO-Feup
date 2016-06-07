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
import com.color.ninja.logic.IntegerCounter;
import com.color.ninja.physics.MyBox;
import com.color.ninja.sprites.*;
import com.color.ninja.sprites.Shape;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Class representing the game.
 */
public class GameState extends com.color.ninja.states.State {

    private Stage stage;

    private Texture background;

    private Sprite pauseBtnSprite;
    private Button pauseBtn;

    // World/physics elements
    private Vector2 gravity;
    private World world;
    private MyBox box;

    // Physics debugger
    protected Box2DDebugRenderer debugRenderer;
    protected Matrix4 debugMatrix;

    // Shapes
    private ShapeFactory factory;
    private ArrayList<Shape> shapesFlying;
    private PriorityQueue<Shape> shapesQueue;

    private boolean slinging;
    private float minThrowInterval;
    private float maxThrowInterval;

    // GameInstance timer and score
    private int score;  // in shapes popped
    private float timer;    // in seconds
    private int lives;

    private IntegerCounter scoreCounter;
    private IntegerCounter timeCounter;
    private IntegerCounter livesCounter;

    private static int GAME_TIME = 60;
    private static int NUM_LIVES = 3;




    public GameState(com.color.ninja.states.GameStateManager gsm) {

        super(gsm);

        // Setting up scene
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // Setting up sprites
        background = new Texture("background.png");

        pauseBtnSprite = new Sprite(new Texture("pause.png"));
        pauseBtn = new Button(new SpriteDrawable(pauseBtnSprite));
        pauseBtn.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 14);
        pauseBtn.setX(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/20 - pauseBtn.getWidth());
        pauseBtn.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 10);

        factory = new ShapeFactory();
        shapesFlying = new ArrayList<Shape>();
        shapesQueue = new PriorityQueue<Shape>();

        // Setting up physics and box
        gravity = new Vector2(0, -MyColorNinja.GRAVITY); // f stands for float
        world = new World(gravity, true);
        box = new MyBox(world);

        // Debug renderer
        if(MyColorNinja.DEBUG)
            debugRenderer=new Box2DDebugRenderer();

        // Score and timer
        this.score = 0;
        this.timer = 0;
        this.lives = NUM_LIVES;

        this.scoreCounter = new IntegerCounter(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/30);
        this.timeCounter  = new IntegerCounter(5 * Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/10);
        this.livesCounter = new IntegerCounter(5 * Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/30);
        this.livesCounter.setValue(lives);

        //Adding actors to scene
        setStageListeners();
        stage.addActor(pauseBtn);

        if(MyColorNinja.DEBUG)
            System.out.println("Hey!");


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

    private void updateShapes(float dt)
    {
        for (int i = 0; i < shapesFlying.size(); i++)
        {
            shapesFlying.get(i).update(dt);
        }
    }

    private void drawShapes(SpriteBatch sb)
    {
        for (int i = 0; i < shapesFlying.size(); i++)
        {
            shapesFlying.get(i).draw(sb);
        }
    }

    private void disposeShapes()
    {
        for (int i = 0; i < shapesFlying.size(); i++)
        {
            if(shapesFlying.get(i).isDestroyed()) {
                if(shapesFlying.get(i).isExploded())
                    scoreCounter.increaseValue(1);
                else
                    livesCounter.decreaseValue(1);

                slinging = false;
                shapesFlying.get(i).dispose();
                shapesFlying.get(i).removeFromGame(shapesFlying, stage);
            }
        }
    }

    private boolean mayThrow()
    {
        //Random rand = new Random();

        if((int)timer % 2 == 0) {
            return true;
        }
        else
            return false;
    }

    private void throwShape()
    {
        Shape s = factory.getRandomShape(world);
        s.addToGame(shapesFlying, stage);

        s.sling();

        slinging = true;

        if(MyColorNinja.DEBUG) {
            System.out.print("shapesFlying size: ");
            System.out.println(shapesFlying.size());
        }

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);

        timer = timer + dt;

        timeCounter.setValue(GAME_TIME - Math.round(timer));

        if(mayThrow() && !slinging)
            throwShape();

        updateShapes(dt);

        scoreCounter.update();
        timeCounter.update();
        livesCounter.update();

        disposeShapes();

        if(livesCounter.getValue() == 0 || timeCounter.getValue() == 0)
            gsm.set(new EndGameState(gsm));


    }

    @Override
    public void render(SpriteBatch sb) {
        if(MyColorNinja.DEBUG) {
            debugMatrix = new Matrix4(sb.getProjectionMatrix());
            debugMatrix.scale(MyColorNinja.PIXELS_PER_METER, MyColorNinja.PIXELS_PER_METER, 1);
        }

        sb.begin();

        sb.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        pauseBtn.draw(sb,1);

        drawShapes(sb);

        scoreCounter.draw(sb);
        timeCounter.draw(sb);
        livesCounter.draw(sb);

        sb.end();

        if(MyColorNinja.DEBUG) {
            debugRenderer.render(world, debugMatrix);
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        disposeShapes();
        world.dispose();
    }
}
