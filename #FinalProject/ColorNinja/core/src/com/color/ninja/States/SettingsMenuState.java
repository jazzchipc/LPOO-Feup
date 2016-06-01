package com.color.ninja.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.color.ninja.states.GameState;
import com.color.ninja.states.GameStateManager;
import com.color.ninja.MyColorNinja;

/**
 * Class representing the Settings Menu
 */
public class SettingsMenuState extends state {
    private Stage stage;

    private Texture background;

    public SettingsMenuState(GameStateManager gsm) {

        background = new Texture("background.png");
    }

    private void setListeners()
    {

            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
    }

    @Override
    protected void handleInput() {


    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); // Open batch to add things to it. A batch works like a box which is filled with images.

        sb.draw(background,0,0,MyColorNinja.WIDTH,MyColorNinja.HEIGHT);


        sb.end();   // Close batch.

    }

    @Override
    public void dispose() {
        background.dispose();
        title.getTexture().dispose();
        newGameBtnSprite.getTexture().dispose();
        settingsBtnSprite.getTexture().dispose();
        scoresBtnSprite.getTexture().dispose();
    }
}
