package com.color.ninja.logic;

/**
 * Score structure.
 */
public class Score implements Comparable<Score> {
    private int score;
    private String difficulty;

    public Score(){}

    public Score(int score, String difficulty)
    {
        this.score = score;
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        String ret = score + ";" + difficulty;
        return ret;
    }

    @Override
    public int compareTo(Score o) {
        if(o.score == this.score)
            return 0;

        if(o.score > this.score)
            return -1;

        return 1;
    }
}
