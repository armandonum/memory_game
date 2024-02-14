package arm.Game;

import java.util.LinkedList;
import java.util.Random;




public class Carts {
    LinkedList<Integer> numberList=new LinkedList<>();
    Random aleatorio=new Random();
    int mat[][]=new int[4][5];
    int matAux[][]=new int[4][5];

    public void numbre_randon() {

        while (numberList.size() < 20) {
            int randomNumber = aleatorio.nextInt(10) + 1;
            if (!numberList.contains(randomNumber)) {//verifica si el numero existe en la lista
                numberList.add(randomNumber);
                numberList.add(randomNumber);//se agrega el mismo numero dos veces
            }
        }


        LinkedList<Integer> mezclar = new LinkedList<>();
        while (!numberList.isEmpty()) {         //This mixes up the numbers on the list
            int index = aleatorio.nextInt(numberList.size());
            mezclar.add(numberList.remove(index));
        }


        int counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {

                mat[i][j] = mezclar.get(counter++);
                matAux[i][j] = 0;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(mat[i][j] + "   ");
            }
            System.out.println(" - ");
        }
    }
}
