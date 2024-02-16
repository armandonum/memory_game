package test;

import arm.Game.Cards;
import org.junit.Assert;
import org.junit.Test;

public class TestCards {
    @Test
    public void testnumbre_random() {
        Cards generador = new Cards();

        int expected = 20;
        generador.numbre_randon();
        Assert.assertEquals(expected, generador.numberList.size());
    }
    @Test
    public void testmezclar() {
        Cards generador = new Cards();
        generador.numbre_randon();
        generador.mezclarCads();
        int expected = 20;
        Assert.assertEquals(expected, generador.mezclar.size());
    }
    @Test
    public void testprint() {
        Cards generador = new Cards();
        generador.numbre_randon();
        generador.mezclarCads();
        generador.print();
        Assert.assertNotNull(generador.mat);
    }

}
