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
 * Class representing the Scores Menu.
 */
public class ScoresMenuState extends State {
    private Stage stage;

    private Texture background;

    private Sprite title;
    private Sprite backBtnSprite;

    private Button backBtn;

    private TextLabel hardScoreText;
    private TextLabel hardScore;

    private TextLabel easyScoreText;
    private TextLabel easyScore;

    private TextLabel mediumScoreText;
    private TextLabel mediumScore;



    private Sound kungfu;

    /**
     * Default Constructor
     * @param gsm
     */
    public ScoresMenuState(GameStateManager gsm) {
        super(gsm);

        background = new Texture("background.png");

        title = new Sprite(new Texture("scorestitle.png"));

        backBtnSprite = new Sprite(new Texture ("backbtn.png"));

        backBtn = new Button(new SpriteDrawable(backBtnSprite));

        kungfu = Gdx.audio.newSound(Gdx.files.internal("sound/effects/kungfu.mp3"));

        stage = new Stage();

        // Title's position and size
        title.setSize(8 * Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 5);
        title.setCenterX(Gdx.graphics.getWidth() / 2);
        title.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 9 - title.getHeight());

        backBtn.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() / 12);
        backBtn.setX(Gdx.graphics.getWidth() / 5 - backBtn.getWidth());
        backBtn.setY(Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 10);

        hardScoreText = new TextLabel("Hard");
        hardScore = new TextLabel(Integer.toString(MyColorNinja.getOurInstance().highscores.getTopHard().getScore()));

        hardScoreText.setPosition(Gdx.graphics.getWidth() / 2 - hardScoreText.getWidth() / 2, 4 * Gdx.graphics.getHeight() / 6);
        hardScore.setPosition(Gdx.graphics.getWidth() / 2 - hardScore.getWidth() / 2, hardScoreText.getPosY() - Gdx.graphics.getHeight()/10);

        mediumScoreText = new TextLabel("Medium");
        mediumScore = new TextLabel(Integer.toString(MyColorNinja.getOurInstance().highscores.getTopMedium().getScore()));

        mediumScoreText.setPosition(Gdx.graphics.getWidth() / 2 -  mediumScoreText.getWidth() / 2, hardScore.getPosY() - Gdx.graphics.getHeight()/8);
        mediumScore.setPosition(Gdx.graphics.getWidth() / 2 -  mediumScore.getWidth() / 2, mediumScoreText.getPosY() - Gdx.graphics.getHeight()/10);

        easyScoreText = new TextLabel("Easy");
        easyScore = new TextLabel(Integer.toString(MyColorNinja.getOurInstance().highscores.getTopEasy().getScore()));

        easyScoreText.setPosition(Gdx.graphics.getWidth() / 2 -  easyScoreText.getWidth() / 2,mediumScore.getPosY() - Gdx.graphics.getHeight()/8);
        easyScore.setPosition(Gdx.graphics.getWidth() / 2 -  easyScore.getWidth() / 2, easyScoreText.getPosY() - Gdx.graphics.getHeight()/10);


        // Setting up stage and actors
        Gdx.input.setInputProcessor(stage);

        stage.addActor(backBtn);

        setListeners();

    }

    /**
     * Sets the listener for the backbutton
     */
    private void setListeners()
    {
        backBtn.addListener(new ClickListener() {
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

    /**
     * Updates what the screen is outputting
     * @param dt Update rate (in miliseconds).
     */
    @Override
    public void update(float dt) {
        handleInput();
    }

    /**
     * Renders the SpriteBatch
     * @param sb State's SpriteBatch.
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); // Open batch to add things to it. A batch works like a box which is filled with images.

        //draws background texture
        sb.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        // draws title sprite
        title.draw(sb);

        // draws game options buttons
        backBtn.draw(sb, 1);

        hardScoreText.draw(sb);
        hardScore.draw(sb);

        mediumScoreText.draw(sb);
        mediumScore.draw(sb);

        easyScoreText.draw(sb);
        easyScore.draw(sb);

        sb.end();   // Close batch.

    }

    /**
     * Disposes the content after the execution
     */
    @Override
    public void dispose() {
        background.dispose();
        title.getTexture().dispose();
        backBtnSprite.getTexture().dispose();
        kungfu.dispose();
    }
}

