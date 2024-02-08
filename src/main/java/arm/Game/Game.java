package arm.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.util.Random;
import java.awt.image.BufferedImage;


public class Game {
    //instances

    // atributos
    JFrame window;
    JPanel presentation_panel; //caps que podemos ir cambiando
    JLabel Presentation_background;
    JLabel button_start;
    JPanel game_panel;
    JLabel FondoJuego;
    JLabel matriz[][];

//    int mat[][];
//    int matAux[][];

    String player;//para colocar el nombre del jugador
    //Random aleatorio;
    JLabel name_player;


    public Game() {
        window = new JFrame("memory game");
        window.setSize(670, 1000);// the window size is of 670x1000
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
        Presentation_background.setIcon(new ImageIcon("src/main/java/arm/images/Fondo.jpg"));
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

        //fondo de juego
        FondoJuego = new JLabel();
        FondoJuego.setIcon(new ImageIcon("src/main/java/arm/images/Fondo.jpg"));
        FondoJuego.setSize(window.getWidth(), window.getHeight());
        FondoJuego.setLocation(0, 0);
        FondoJuego.setVisible(true);
        game_panel.add(FondoJuego, 0);



//nombre de jugador
        name_player = new JLabel();
        name_player.setSize(160, 20);
        name_player.setLocation(20, 50);
        name_player.setForeground(Color.red);
        name_player.setVisible(true);
        game_panel.add(name_player, 0);


//logic matriz
        Carts cartas = new Carts();  //instanciamos la clase cartas
        cartas.numbre_randon();

//matris de imagenes
       // matriz=new JLabel[4][5];
        LinkedList<LinkedList<JLabel>> matriz = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            LinkedList<JLabel> fila = new LinkedList<>();
            for (int j = 0; j < 5; j++) {
                JLabel label = new JLabel();
                label.setBounds(200 + (j * 63) + (j * 10), 70 + (i * 80) + (i * 10), 63, 80);
                ImageIcon originalIcon = new ImageIcon("src/main/java/arm/images/" + cartas.matAux[i][j] + ".png");
                Image image = originalIcon.getImage().getScaledInstance(63, 80, Image.SCALE_SMOOTH);
               label.setIcon(new ImageIcon(image));
                label.setVisible(true);
                game_panel.add(label, 0);
                fila.add(label);
            }
            matriz.add(fila);

        }


        //event of click
        button_start.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //System.out.println("click");
                player = JOptionPane.showInputDialog(window, "Enter your name", "whrite here");
                while (player == null || player.equals("")) {
                    player = JOptionPane.showInputDialog(window, "Enter your name", "whrite here");
                }
                name_player.setText("player: " + player);
                presentation_panel.setVisible(false);
                window.add(game_panel);
                game_panel.setVisible(true);

            }
        });

        window.setVisible(true);
    }









}
