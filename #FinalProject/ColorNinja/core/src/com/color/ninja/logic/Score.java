package com.color.ninja.logic;

/**
 * Score structure.
 */
public class Score implements Comparable<Score> {
    private int score;
    private String difficulty;

    /**
     * Default constructor with no arguments.
     */
    public Score(){}

    /**
     * Constructor with two arguments.
     * @param score Score value.
     * @param difficulty Dfficulty in which the score was achieved.
     */
    public Score(int score, String difficulty)
    {
        this.score = score;
        this.difficulty = difficulty;
    }

    /**
     *
     * @return The difficulty of the score.
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     *
     * @return The value of the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Converts the Score into a string .
     * @return obtained string.
     */
    @Override
    public String toString() {
        String ret = score + ";" + difficulty;
        return ret;
    }

    /**
     * Compares two Scores.
     * @param o One of the Scores to compare.
     * @return 0 if both scores are equal, -1 if o is higher and 1 otherwise.
     */
    @Override
    public int compareTo(Score o) {
        if(o.score == this.score)
            return 0;

        if(o.score > this.score)
            return -1;

        return 1;
    }
}
