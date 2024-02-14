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

    public void updateHighScores() {

        for (int i = 0; i < highScores.size(); i++) {
            if (score > highScores.get(i)) {
                // Insertar el puntaje actual en la lista de puntajes altos en la posición i
                highScores.add(i, score);
                // Limitar la lista a los 5 puntajes más altos
                if (highScores.size() > 5) {
                    highScores.remove(5);
                }
                break; // Salir del bucle una vez que se ha actualizado la lista
            }
        }
    }

    public List<Integer> getHighScores() {
        return highScores;
    }
}
