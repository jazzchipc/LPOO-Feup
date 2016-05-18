package com.color.ninja.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.color.ninja.MyColorNinja;

/**
 * CLass representing the MainMenu.
 */
public class MainMenuState extends State {

    private Sprite title;
    private Texture newGameBtn;
    private Texture settingsBtn;
    private Texture scoresBtn;



    public MainMenuState(com.color.ninja.states.GameStateManager gsm) {
        super(gsm);

        title = new Sprite(new Texture("title1.png"));
        newGameBtn = new Texture ("new game1.png");
        settingsBtn = new Texture ("settings1.png");
        scoresBtn = new Texture("scores1.png");
    }

    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched())
        {
            gsm.set(new GameState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); // Open batch to add things to it. A batch works like a box which is filled with images.

        title.scale(1/5);

        // draws title sprite centered in x, with 20 px interval from top boundary
        sb.draw(title, MyColorNinja.WIDTH/2 - title.getWidth() / 2, MyColorNinja.HEIGHT - title.getHeight() - 20);

        sb.draw(newGameBtn, MyColorNinja.WIDTH/2 - newGameBtn.getWidth()/2,
                MyColorNinja.HEIGHT/2 - newGameBtn.getHeight());

        sb.end();   // Close batch.

    }

    @Override
    public void dispose() {
        title.getTexture().dispose();
        newGameBtn.dispose();
        settingsBtn.dispose();
        scoresBtn.dispose();
    }
}
