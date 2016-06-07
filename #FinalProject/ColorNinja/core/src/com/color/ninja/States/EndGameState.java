package com.color.ninja.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.color.ninja.MyColorNinja;
import com.color.ninja.ui.TextLabel;

/**
 * State of game end.
 */
public class EndGameState extends State {
    private Stage stage;

    private Texture background;

    private Sprite title;
    private Sprite restartBtnSprite;
    private Sprite exitBtnSprite;

    private Button restartBtn;
    private Button exitBtn;

    private Sound kungfu;

    private boolean newHighscore;

    protected EndGameState(GameStateManager gsm) {
        super(gsm);

        stage = new Stage();

        background = new Texture("background.png");

        title = new Sprite(new Texture("game over1.png"));

        restartBtnSprite = new Sprite(new Texture("restart game1.png"));
        exitBtnSprite = new Sprite (new Texture("exit1.png"));

        restartBtn = new Button(new SpriteDrawable(restartBtnSprite));
        exitBtn = new Button(new SpriteDrawable(exitBtnSprite));

        kungfu = Gdx.audio.newSound(Gdx.files.internal("sound/effects/kungfu.mp3"));

        title.setSize(8 * Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 3);
        title.setCenterX(Gdx.graphics.getWidth() / 2);
        title.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 25 - title.getHeight());

        restartBtn.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 8);
        restartBtn.setX(Gdx.graphics.getWidth() / 2 - restartBtn.getWidth() / 2);
        restartBtn.setY(23*Gdx.graphics.getHeight() / 100 );

        exitBtn.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 8);
        exitBtn.setX(Gdx.graphics.getWidth() / 2 - exitBtn.getWidth() / 2 );
        exitBtn.setY( Gdx.graphics.getHeight() / 15);

        Gdx.input.setInputProcessor(stage);

        stage.addActor(restartBtn);
        stage.addActor(exitBtn);

        newHighscore = MyColorNinja.getOurInstance().highscores.newScore(MyColorNinja.getOurInstance().score);

        setListeners();
    }

    private void setListeners()
    {
        restartBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kungfu.play(SettingsMenuState.soundsVol);
                gsm.set(new GameState(gsm));
            }
        });

        exitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kungfu.play(SettingsMenuState.soundsVol);
                gsm.set(new MainMenuState(gsm));
            }
        });
    }

    @Override
    public void handleInput() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); // Open batch to add things to it. A batch works like a box which is filled with images.

        //draws background texture
        sb.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        title.draw(sb);
        // draws game options buttons
        restartBtn.draw(sb,1);
        exitBtn.draw(sb, 1);

        if(newHighscore)
        {
            TextLabel highscore = new TextLabel("NEW\nHIGHSCORE!");
            highscore.setPosition(Gdx.graphics.getWidth() / 2 - highscore.getWidth() / 3, 6 * Gdx.graphics.getHeight()/10);
            highscore.draw(sb);
        }

        sb.end();


    }

    @Override
    public void dispose() {
        background.dispose();
        restartBtnSprite.getTexture().dispose();
        exitBtnSprite.getTexture().dispose();
        kungfu.dispose();
    }
}
