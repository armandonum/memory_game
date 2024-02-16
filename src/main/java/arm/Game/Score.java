package arm.Game;

import java.util.ArrayList;
import java.util.List;

public class Score {
    private int score;
    private List<Integer> highScores;

    public Score() {
        this.score = 0;
        this.highScores = new ArrayList<>();
    }

    public void addToScore(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
    }

    public List<Integer> getHighScores() {
        return highScores;
    }
}
