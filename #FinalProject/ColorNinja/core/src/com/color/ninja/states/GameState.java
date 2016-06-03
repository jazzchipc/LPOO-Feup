package com.color.ninja.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
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

    private Texture background;

    private int paused = 0;

    private Sprite pauseBtnSprite;
    private Button pauseBtn;

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

        background = new Texture("background.png");

        pauseBtnSprite = new Sprite(new Texture("pause.png"));
        pauseBtn = new Button(new SpriteDrawable(pauseBtnSprite));
        pauseBtn.setSize(MyColorNinja.WIDTH / 10, MyColorNinja.HEIGHT / 14);
        pauseBtn.setX(MyColorNinja.WIDTH - MyColorNinja.WIDTH/20 - pauseBtn.getWidth());
        pauseBtn.setY(MyColorNinja.HEIGHT - MyColorNinja.HEIGHT / 10);

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

    private void setListeners()
    {
        pauseBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                paused = 1;
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

        shapes.get(0).applyTorque();

        shapes.get(0).update();
    }

    @Override
    public void render(SpriteBatch sb) {
        debugMatrix = sb.getProjectionMatrix();

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
    }
}
