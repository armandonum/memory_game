package test;

import org.junit.Assert;
import org.junit.Test;
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

        // Verificar que el puntaje se incrementa correctamente con valores grandes
        score.addToScore(100);
        Assert.assertEquals(105, score.getScore());
    }

    @Test
    public void testResetScore() {
        Score score = new Score();

        // Establecer un puntaje y luego restablecerlo
        score.addToScore(20);
        score.resetScore();
        Assert.assertEquals(0, score.getScore());
    }

    @Test
    public void testPrintScores() {
        Score score = new Score();

        // Agregar puntajes y verificar la salida del método print
        score.addToScore(10);
        score.addToScore(20);
        score.addToScore(15);

        // Utilizar el método print y verificar la salida en la consola (observa manualmente)
        score.print();
    }

    @Test
    public void testEmptyScore() {
        Score score = new Score();

        // Verificar que el puntaje es cero inicialmente
        Assert.assertEquals(0, score.getScore());

        // Verificar que el puntaje sigue siendo cero después de un reset
        score.resetScore();
        Assert.assertEquals(0, score.getScore());
    }
}
