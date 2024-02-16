package arm.Game;

import java.util.LinkedList;

public class Score {
   private LinkedList<Integer> scoreList;
    public Score(){
        this.scoreList = new LinkedList<>();
    }
    public void addToScore(int score){
        scoreList.add(score);
    }

    public int getScore(){
        int total = 0;
        for(int i = 0; i < scoreList.size(); i++){
            total += scoreList.get(i);
            //System.out.println("Score totasl: " + scoreList.get(i));
        }
        return total;
    }

    public void resetScore(){
        scoreList.clear();
    }
    // moetodo que muetra todos los puntajes
    public void print(){
        for(int i = 0; i < scoreList.size(); i++){
            System.out.println("Score: " + scoreList.get(i));
        }

    }


}
