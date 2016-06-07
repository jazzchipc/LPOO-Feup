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

    public Highscores()
    {
        setScores();
    }

    private void setScores()
    {
        topHard = new Score(prefs.getInteger("hard", 0), "hard");
        topMedium = new Score(prefs.getInteger("medium", 0), "medium");
        topEasy = new Score(prefs.getInteger("easy", 0), "easy");
    }

    private void writeScoresToPrefs()
    {
        prefs.putInteger(topHard.getDifficulty(), topHard.getScore());
        prefs.putInteger(topMedium.getDifficulty(), topMedium.getScore());
        prefs.putInteger(topEasy.getDifficulty(), topEasy.getScore());

        prefs.flush();
    }

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

    public Score getTopHard() {
        return topHard;
    }

    public Score getTopMedium() {
        return topMedium;
    }

    public Score getTopEasy() {
        return topEasy;
    }




}
