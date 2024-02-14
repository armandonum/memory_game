package test;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import arm.Game.Score;

public class TestScore {
    @Test
    public void testAddToScore() {
        Score score = new Score();

        // Verificar que el puntaje se incremente correctamente
        score.addToScore(10);
        Assert.assertEquals(10, score.getScore());

        // Verificar que el puntaje se incrementa correctamente con números negativos
        score.addToScore(-5);
        Assert.assertEquals(5, score.getScore());

        // Verificar que el puntaje se incrementa correctamente con cero
        score.addToScore(0);
        Assert.assertEquals(5, score.getScore());
    }

    @Test
    public void testResetScore() {
        Score score = new Score();

        // Establecer un puntaje y luego restablecerlo
        score.addToScore(20);
        score.resetScore();
        Assert.assertEquals(0, score.getScore());
    }

}
