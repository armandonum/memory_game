package arm.Game;

import java.util.LinkedList;
import java.util.Queue;

public class Lives {
    private Queue<Integer> livesQueue;
    public Lives(int numLives) {
       livesQueue = new LinkedList<>();
       for(int i = 0; i < numLives; i++) {
           livesQueue.add(i);// agrega vidas a la cola

       }
    }
    //para ver si hay vidas
    public boolean hasLives() {
        return !livesQueue.isEmpty();
    }
    // para quitar vidas
    public void removeLife() {
        if(!livesQueue.isEmpty()) {
            livesQueue.poll();
        }
    }

    // para ver cuantas vidas quedan
    public int getLives() {
        return livesQueue.size();
    }


}
