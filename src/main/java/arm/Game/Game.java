package arm.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

public class Game {
    // Instancias
    Cards cartas = new Cards();  // Instanciamos la clase cartas
    Score score = new Score(); // Instanciamos la clase score
    Lives lives = new Lives(3); // Instanciamos la clase lives con 3 vidas

    // Atributos
    JFrame window;
    JPanel presentation_panel; // Paneles que podemos ir cambiando
    JLabel Presentation_background;
    JLabel button_OnePice; // Botón para inicio rápido
    JLabel button_bod_esponja; // Para ir al nivel de bod esponja
    JLabel button_objetos;
    JPanel game_panel;
    JLabel FondoJuego;
    JLabel matriz[][];
    JLabel scoreLabel;
    JLabel highScoresLabel;
    JLabel button_inicio;
    JLabel buton_lives;

    Timer tiempo;
    Timer tiempo_de_espera;
    Timer tiempoEsp;
    JLabel contador_tiempo;
    int min, seg;
    int contador;
    int puntos;
    int contSegEspera;
    boolean bandera;
    boolean bandera2;

    String player; // Para colocar el nombre del jugador
    JLabel name_player;
    //int numCarta;
    int posicioAntX;
    int posicioAntY;
    //int numCartaNew;
    int posicioXNew;
    int posicioYNew;

    public Game() {
        window = new JFrame("memory game");
        window.setSize(670, 700);
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        // Propiedades del panel
        presentation_panel = new JPanel();
        presentation_panel.setSize(window.getWidth(), window.getHeight());
        presentation_panel.setLocation(0, 0);
        presentation_panel.setLayout(null);
        presentation_panel.setVisible(true);

        // Fondo de presentación
        Presentation_background = new JLabel();
        Presentation_background.setLocation(0, 0);
        Presentation_background.setIcon(new ImageIcon("src/main/java/arm/imagenes_inicio/Fondo.png"));
        Presentation_background.setSize(window.getWidth(), window.getHeight());
        Presentation_background.setVisible(true);
        presentation_panel.add(Presentation_background, 0);
        window.add(presentation_panel);



        // Panel de juego
        game_panel = new JPanel();
        game_panel.setSize(window.getWidth(), window.getHeight());
        game_panel.setLocation(0, 0);
        game_panel.setLayout(null);
        game_panel.setVisible(false);
        window.add(game_panel);

        // Fondo de juego
        FondoJuego = new JLabel();
        FondoJuego.setIcon(new ImageIcon("src/main/java/arm/imagenes_inicio/Fondo.jpg"));
        FondoJuego.setSize(window.getWidth(), window.getHeight());
        FondoJuego.setLocation(0, 0);
        FondoJuego.setVisible(true);

        game_panel.add(FondoJuego, 0);

        //***** botonoes de inicio*****
        botones_inicio();

        // boton de inicio;
        button_inicio = new JLabel();

        ImageIcon icon = new ImageIcon("src/main/java/arm/imagenes_inicio/inicio.png");
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        button_inicio.setIcon(icon);

        button_inicio.setSize(75, 75);
        button_inicio.setLocation(150, 560);
        button_inicio.setVisible(true);
        game_panel.add(button_inicio, 0);




        // Nombre de jugador
        name_player = new JLabel();
        name_player.setSize(160, 20);
        name_player.setLocation(20, 50);
        name_player.setForeground(Color.red);
        name_player.setVisible(true);
        game_panel.add(name_player, 0);

        // Cronómetro
        contador_tiempo = new JLabel();
        contador_tiempo.setSize(160, 20);
        contador_tiempo.setLocation(window.getWidth() - 200, 10);
        contador_tiempo.setForeground(Color.white);
        contador_tiempo.setVisible(true);
        game_panel.add(contador_tiempo, 0);

        // Lógica de la matriz
        cartas.numbre_randon();
        cartas.mezclarCads();
        cartas.print();


        // Matriz de imágenes
        matriz = new JLabel[4][5];
        for (int i = 0; i < 4; i++) {
            //LinkedList<JLabel> fila = new LinkedList<>();
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = new JLabel();
                matriz[i][j].setBounds(140 + (j * 80) + (j * 10), 100 + (i * 100) + (i * 10), 80, 100);
                ImageIcon originalIcon = new ImageIcon("src/main/java/arm/images/" + cartas.matAux[i][j] + ".png");
                mostrar_cartas(originalIcon, i, j);
               matriz[i][j].setVisible(true);
               game_panel.add(matriz[i][j], 0);
            }
        }

        // Tiempo
        min = 0;
        seg = 0;
        tiempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seg++;
                if (seg == 60) {
                    min++;
                    seg = 0;
                }
                contador_tiempo.setText("time " + min + ":" + seg);
            }
        });

        // Tiempo de espera
        tiempoEsp = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contSegEspera++;
            }
        });

        // Evento de clic en las cartas
        button_OnePice.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player = JOptionPane.showInputDialog(window, "Enter your name", "Write here");
                while (player == null || player.equals("")) {
                    player = JOptionPane.showInputDialog(window, "Enter your name", "Write here");
                }
                name_player.setText("Player: " + player);
                tiempo.start();
                presentation_panel.setVisible(false);
                window.add(game_panel);
                game_panel.setVisible(true);
                game_panel.add(button_inicio, 0);
                addMouseListenerToCards(1);
            }
        });
        button_objetos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player = JOptionPane.showInputDialog(window, "Enter your name", "Write here");
                while (player == null || player.equals("")) {
                    player = JOptionPane.showInputDialog(window, "Enter your name", "Write here");
                }
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        mostrar_cartas(new ImageIcon("src/main/java/arm/images2/" + cartas.matAux[i][j] + ".png"), i, j);
                    }
                }
                name_player.setText("Player: " + player);
                tiempo.start();
                presentation_panel.setVisible(false);
                window.add(game_panel);
                game_panel.setVisible(true);
                addMouseListenerToCards(2);
            }
        });
        button_bod_esponja.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player = JOptionPane.showInputDialog(window, "Enter your name", "Write here");
                while (player == null || player.equals("")) {
                    player = JOptionPane.showInputDialog(window, "Enter your name", "Write here");
                }
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        mostrar_cartas(new ImageIcon("src/main/java/arm/images3/" + cartas.matAux[i][j] + ".png"), i, j);

                    }
                }
                name_player.setText("Player: " + player);
                tiempo.start();
                presentation_panel.setVisible(false);
                window.add(game_panel);
                game_panel.setVisible(true);
                addMouseListenerToCards(3);
            }
        });

        button_inicio.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                window.dispose();
                new Game();
            }
        });

        scoreLabel = new JLabel();
        scoreLabel.setSize(160, 20);
        scoreLabel.setLocation(20, 80);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setText("Score: " + score.getScore());
        scoreLabel.setVisible(true);
        game_panel.add(scoreLabel, 0);

        //lives
        buton_lives = new JLabel();
       buton_lives.setIcon(new ImageIcon("src/main/java/arm/imagenes_inicio/vidas.png"));
        buton_lives.setSize(54, 24);
        buton_lives.setLocation(20+24+5, 100);
        buton_lives.setForeground(Color.white);
        buton_lives.setText(" : "+lives.getLives());
        buton_lives.setVisible(true);
        game_panel.add(buton_lives, 0);




        window.setVisible(true);
    }
    private void mostrar_cartas(ImageIcon originalIcon, int i, int j){
        Image image = originalIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        matriz[i][j].setIcon(new ImageIcon(image));
    }
    private void botones_inicio(){
        // Botón de inicio
        button_OnePice = new JLabel();
        button_OnePice.setIcon(new ImageIcon("src/main/java/arm/imagenes_inicio/one_pice.png"));
        button_OnePice.setSize(277, 62);
        button_OnePice.setLocation(200, 200);
        button_OnePice.setVisible(true);
        presentation_panel.add(button_OnePice, 0);

        // Botón de Bod esponja
        button_bod_esponja = new JLabel();
        button_bod_esponja.setIcon(new ImageIcon("src/main/java/arm/imagenes_inicio/bod_esponja.png"));
        button_bod_esponja.setSize(277, 62);
        button_bod_esponja.setLocation(200, 300);
        button_bod_esponja.setVisible(true);
        presentation_panel.add(button_bod_esponja, 0);
        // boton objetos
        button_objetos = new JLabel();
        button_objetos.setIcon(new ImageIcon("src/main/java/arm/imagenes_inicio/objetos.png"));
        button_objetos.setSize(277, 62);
        button_objetos.setLocation(200, 400);
        button_objetos.setVisible(true);
        presentation_panel.add(button_objetos, 0);

    }

    private void addMouseListenerToCards(int control) {
        contSegEspera = 0;
        contador = 0;
        bandera = true;
        bandera2 = true;
        puntos=10;

        List<JLabel> cardLabels = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                cardLabels.add(matriz[i][j]);
            }
        }

        for (JLabel cardLabel : cardLabels) {
            cardLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Mouse clicked on a card!");
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 5; l++) {
                            if (e.getSource() == matriz[k][l]) {
                                if (cartas.matAux[k][l] == 0 && contador != 2) {
                                    cartas.matAux[k][l] = cartas.mat[k][l];
                                    if (control==1){
                                        mostrar_cartas(new ImageIcon("src/main/java/arm/images/" + cartas.matAux[k][l] + ".png"), k, l);
                                    }else if (control==2){
                                        mostrar_cartas(new ImageIcon("src/main/java/arm/images2/" + cartas.matAux[k][l] + ".png"), k, l);
                                    }else if (control==3){
                                        mostrar_cartas(new ImageIcon("src/main/java/arm/images3/" + cartas.matAux[k][l] + ".png"), k, l);
                                    }
                                    contador++;
                                    if (contador == 1) {
                                        System.out.println("contador: " + contador);
                                        //numCartaNew = cartas.mat[k][l];
                                        posicioXNew = k;
                                        posicioYNew = l;
                                    }
                                    if (contador == 2) {
                                        System.out.println("contador: " + contador);
                                       // numCarta = cartas.mat[k][l];
                                        posicioAntX = k;
                                        posicioAntY = l;
                                    }

                                    tiempo_de_espera = new Timer(1000, new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (contador == 2 && bandera2) {
                                                tiempoEsp.restart();
                                                bandera2 = false;
                                            }
                                            if (contador == 2 && contSegEspera == 1) {
                                                tiempoEsp.stop();
                                                contSegEspera = 0;

                                                if (cartas.matAux[posicioXNew][posicioYNew] == cartas.matAux[posicioAntX][posicioAntY]) {
                                                    cartas.matAux[posicioXNew][posicioYNew] = -1;
                                                    cartas.matAux[posicioAntX][posicioAntY] = -1;
                                                    System.out.println("son iguales");
                                                    if (control==1) {
                                                        mostrar_cartas(new ImageIcon("src/main/java/arm/images/" + cartas.matAux[posicioXNew][posicioYNew] + ".png"), posicioXNew, posicioYNew);
                                                        mostrar_cartas(new ImageIcon("src/main/java/arm/images/" + cartas.matAux[posicioAntX][posicioAntY] + ".png"), posicioAntX, posicioAntY);
                                                    }else if (control==2){
                                                        mostrar_cartas(new ImageIcon("src/main/java/arm/images2/" + cartas.matAux[posicioXNew][posicioYNew] + ".png"), posicioXNew, posicioYNew);
                                                        mostrar_cartas(new ImageIcon("src/main/java/arm/images2/" + cartas.matAux[posicioAntX][posicioAntY] + ".png"), posicioAntX, posicioAntY);
                                                    }else if (control==3){
                                                        mostrar_cartas(new ImageIcon("src/main/java/arm/images3/" + cartas.matAux[posicioXNew][posicioYNew] + ".png"), posicioXNew, posicioYNew);
                                                        mostrar_cartas(new ImageIcon("src/main/java/arm/images3/" + cartas.matAux[posicioAntX][posicioAntY] + ".png"), posicioAntX, posicioAntY);
                                                    }
                                                    contador = 0;
                                                    score.addToScore(puntos); // Aumenta el puntaje si las cartas son iguales
                                                    scoreLabel.setText("Score: " + score.getScore()); // Actualiza el JLabel del puntaje
                                                }else {
                                                    puntos -=2;
                                                    lives.removeLife(); // Quita una vida si las cartas no son iguales
                                                    buton_lives.setText(" : "+lives.getLives());
                                                    if(!lives.hasLives()) {
                                                        System.out.println("lista de puntajes : " );
                                                        score.print();
                                                        JOptionPane.showMessageDialog(window, "Game over");
                                                        // reiniciar el juego
                                                        window.dispose();
                                                        new Game();


                                                    }
                                                }

                                                for (int m = 0; m < 4; m++) {
                                                    for (int n = 0; n < 5; n++) {
                                                        if (cartas.matAux[m][n] != 0 && cartas.matAux[m][n] != -1) {
                                                            cartas.matAux[m][n] = 0;
                                                            if(control==1){
                                                                mostrar_cartas(new ImageIcon("src/main/java/arm/images/" + cartas.matAux[m][n] + ".png"), m, n);
                                                            }else if(control==2){
                                                                mostrar_cartas(new ImageIcon("src/main/java/arm/images2/" + cartas.matAux[m][n] + ".png"), m, n);
                                                            }else if(control==3){
                                                                mostrar_cartas(new ImageIcon("src/main/java/arm/images3/" + cartas.matAux[m][n] + ".png"), m, n);
                                                            }

                                                            contador = 0;
                                                        }
                                                    }
                                                }

                                                tiempo_de_espera.stop();
                                                bandera2 = true;
                                            }
                                        }
                                    });
                                    if (bandera) {
                                        tiempo_de_espera.start();
                                        bandera = false;
                                    }
                                    if (contador == 2) {
                                        tiempo_de_espera.restart();
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }



    public static void main(String[] args) {
        new Game();
    }
}
