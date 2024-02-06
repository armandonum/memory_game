package arm.Game;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.util.Random;


public class Game {
    // atributos
    JFrame window;
    JPanel presentation_panel; //caps que podemos ir cambiando
    JLabel Presentation_background;
    JLabel button_start;

    JPanel game_panel;
    JLabel matriz[][];
    int mat[][];
   LinkedList<Integer> numberList;
    String player;//para colocar el nombre del jugador

    Random aleatorio;

    //the constructor
    public Game() {
        window = new JFrame("memory game");
        window.setSize(760, 570);// the window size is of 800x600
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);//permite que la ventana aparezca en el centro de la compu
        window.setResizable(false);//no se puede cambiar el tamaño de la ventana

        // properties of panel
        presentation_panel = new JPanel();
        presentation_panel.setSize(window.getWidth(), window.getHeight());
        presentation_panel.setLocation(0, 0);
        presentation_panel.setLayout(null);
        presentation_panel.setVisible(true);


        //presentation background
        JLabel Presentation_background = new JLabel();

        Presentation_background.setLocation(0, 0);
        Presentation_background.setIcon(new ImageIcon("src/main/java/arm/images/backgroup.png"));
        Presentation_background.setSize(window.getWidth(), window.getHeight());
        Presentation_background.setVisible(true);
        presentation_panel.add(Presentation_background, 0);
        window.add(presentation_panel);

        //Button start
        button_start = new JLabel();
        button_start.setIcon(new ImageIcon("src/main/java/arm/images/start.png"));
        button_start.setSize(200, 61);
        button_start.setLocation(25, 0);
        button_start.setVisible(true);
        presentation_panel.add(button_start, 0);

// panel juego
        game_panel = new JPanel();
        game_panel.setSize(window.getWidth(), window.getHeight());
        game_panel.setLocation(0, 0);
        game_panel.setLayout(null);
        game_panel.setVisible(false);
        game_panel.setVisible(true);
        window.add(game_panel);


//logic matriz
        numberList= new LinkedList<>();
        aleatorio = new Random();
        mat= new int[4][5];
        this.numbre_randon();


        //event of click
        button_start.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("click");
            }
        });


        window.setVisible(true);
    }

    public void numbre_randon() {

        while (numberList.size() < 20) { // 4x5 = 20 cartas
            int randomNumber = aleatorio.nextInt(10) + 1;
            if (!numberList.contains(randomNumber)) {//verifica si el numero existe en la lista
                numberList.add(randomNumber);
                numberList.add(randomNumber);//se agrega el mismo numero dos veces
            }
        }


        LinkedList<Integer> mezclar = new LinkedList<>();
        while (!numberList.isEmpty()) {
            int index = aleatorio.nextInt(numberList.size());
            mezclar.add(numberList.remove(index));
        }


        int counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {

                mat[i][j] = mezclar.get(counter++);
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

}
