package com.color.ninja.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Highscores class.
 */
public class Highscores {

    private Preferences prefs = Gdx.app.getPreferences("Scores");

    private Score topHard;
    private Score topMedium;
    private Score topEasy;

    /**
     * Default Constructor
     */
    public Highscores()
    {
        setScores();
    }

    /**
     * Sets the scores for each difficulty
     */
    private void setScores()
    {
        topHard = new Score(prefs.getInteger("hard", 0), "hard");
        topMedium = new Score(prefs.getInteger("medium", 0), "medium");
        topEasy = new Score(prefs.getInteger("easy", 0), "easy");
    }

    /**
     * Writes the top scores in each difficulty to Preferences object created
     */
    private void writeScoresToPrefs()
    {
        prefs.putInteger(topHard.getDifficulty(), topHard.getScore());
        prefs.putInteger(topMedium.getDifficulty(), topMedium.getScore());
        prefs.putInteger(topEasy.getDifficulty(), topEasy.getScore());

        prefs.flush();
    }

    /**
     * Checks if the obtained score is to be added to the Highscores.
     * @param score New Highscore.
     * @return If the given score is the highest in it's difficulty returns true, otherwise returns false.
     */
    public boolean newScore(Score score)
    {
        boolean ret = false;
        if(score.getDifficulty().equals("hard"))
        {
            if(score.compareTo(topHard) > 0) {
                ret = true;
                topHard = score;
            }
        }
        if(score.getDifficulty().equals("medium"))
        {
            if(score.compareTo(topMedium) > 0) {
                ret = true;
                topMedium = score;
            }
        }
        if(score.getDifficulty().equals("easy"))
        {
            if(score.compareTo(topEasy) > 0) {
                ret = true;
                topEasy = score;
            }
        }

        writeScoresToPrefs();

        setScores();

        return ret;
    }

    /**
     * Prints the Highest Score in each difficulty
     */
    public void showScores()
    {
        System.out.print(topHard.getDifficulty());
        System.out.print(": ");
        System.out.print(topHard.getScore());
        System.out.print("\n");

        System.out.print(topMedium.getDifficulty());
        System.out.print(": ");
        System.out.print(topMedium.getScore());
        System.out.print("\n");

        System.out.print(topEasy.getDifficulty());
        System.out.print(": ");
        System.out.print(topEasy.getScore());
        System.out.print("\n");
    }

    /**
     *
     * @return The highest score in hard difficulty.
     */
    public Score getTopHard() {
        return topHard;
    }

    /**
     *
     * @return The highest score in medium difficulty.
     */
    public Score getTopMedium() {
        return topMedium;
    }

    /**
     *
     * @return The highest score in easy difficulty.
     */
    public Score getTopEasy() {
        return topEasy;
    }




}
