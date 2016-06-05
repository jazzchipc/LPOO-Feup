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
import com.color.ninja.physics.MyBox;
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

    // World/physics elements
    private Vector2 gravity;
    private World world;
    private MyBox box;

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
        pauseBtn.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 14);
        pauseBtn.setX(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/20 - pauseBtn.getWidth());
        pauseBtn.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 10);

        // Setting up physics and box
        gravity = new Vector2(0, -MyColorNinja.GRAVITY); // f stands for float
        world = new World(gravity, true);
        factory = new ShapeFactory();
        shapes = new ArrayList<Shape>();
        box = new MyBox(world);

        // Debug renderer
        if(MyColorNinja.DEBUG)
            debugRenderer=new Box2DDebugRenderer();

        Shape s = factory.getRandomShape(world);
        s.addToGame(shapes, stage);

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


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);

        shapes.get(0).update();
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

        shapes.get(0).draw(sb);

        sb.end();

        if(MyColorNinja.DEBUG) {
            debugRenderer.render(world, debugMatrix);
        }
    }

    @Override
    public void dispose() {

        shapes.get(0).dispose();
        world.dispose();
    }
}
