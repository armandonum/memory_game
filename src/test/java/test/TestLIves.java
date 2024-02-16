package test;
import org.junit.Assert;
import org.junit.Test;
import arm.Game.Lives;

public class TestLIves {
    @Test
    public void lives(){
        Lives lives=new Lives(5);
        Assert.assertEquals(5, lives.getLives());
    }
    @Test
    public void testHasLives(){
        Lives lives=new Lives(5);
        Assert.assertEquals(true, lives.hasLives());
    }

    @Test
    public void testLoseLife(){
        Lives lives=new Lives(5);
        lives.removeLife();
        Assert.assertEquals(4, lives.getLives());
    }
}
