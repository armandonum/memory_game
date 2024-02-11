package arm.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class Game {
    // Instancias
    Carts cartas = new Carts();  // Instanciamos la clase cartas

    // Atributos
    JFrame window;
    JPanel presentation_panel; // Paneles que podemos ir cambiando
    JLabel Presentation_background;
    JLabel button_start; // Botón para inicio rápido
    JLabel button_bod_esponja; // Para ir al nivel de bod esponja
    JPanel game_panel;
    JLabel FondoJuego;
    JLabel matriz[][];

    Timer tiempo;
    Timer tiempo_de_espera;
    Timer tiempoEsp;
    JLabel contador_tiempo;
    int min, seg;
    int contador;
    int contSegEspera;
    boolean bandera;
    boolean bandera2;

    String player; // Para colocar el nombre del jugador
    JLabel name_player;
    int numCarta;
    int posicioAntX;
    int posicioAntY;
    int numCartaNew;
    int posicioXNew;
    int posicioYNew;

    public Game() {
        window = new JFrame("memory game");
        window.setSize(670, 1000);
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
        Presentation_background.setIcon(new ImageIcon("src/main/java/arm/images/Fondo.jpg"));
        Presentation_background.setSize(window.getWidth(), window.getHeight());
        Presentation_background.setVisible(true);
        presentation_panel.add(Presentation_background, 0);
        window.add(presentation_panel);

        // Botón de inicio
        button_start = new JLabel();
        button_start.setIcon(new ImageIcon("src/main/java/arm/images/start.png"));
        button_start.setSize(200, 61);
        button_start.setLocation(25, 0);
        button_start.setVisible(true);
        presentation_panel.add(button_start, 0);

        // Botón de Bod esponja
        button_bod_esponja = new JLabel();
        button_bod_esponja.setIcon(new ImageIcon("src/main/java/arm/images3/button.png"));
        button_bod_esponja.setSize(200, 61);
        button_bod_esponja.setLocation(300, 450);
        button_bod_esponja.setVisible(true);
        presentation_panel.add(button_bod_esponja, 0);

        // Panel de juego
        game_panel = new JPanel();
        game_panel.setSize(window.getWidth(), window.getHeight());
        game_panel.setLocation(0, 0);
        game_panel.setLayout(null);
        game_panel.setVisible(false);
        window.add(game_panel);

        // Fondo de juego
        FondoJuego = new JLabel();
        FondoJuego.setIcon(new ImageIcon("src/main/java/arm/images/Fondo.jpg"));
        FondoJuego.setSize(window.getWidth(), window.getHeight());
        FondoJuego.setLocation(0, 0);
        FondoJuego.setVisible(true);
        game_panel.add(FondoJuego, 0);

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

        // Matriz de imágenes
        matriz = new JLabel[4][5];
        for (int i = 0; i < 4; i++) {
            LinkedList<JLabel> fila = new LinkedList<>();
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = new JLabel();
                matriz[i][j].setBounds(140 + (j * 80) + (j * 10), 100 + (i * 100) + (i * 10), 80, 100);
                ImageIcon originalIcon = new ImageIcon("src/main/java/arm/images2/" + cartas.matAux[i][j] + ".png");
                Image image = originalIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
                matriz[i][j].setIcon(new ImageIcon(image));
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
        button_start.addMouseListener(new MouseAdapter() {
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
                addMouseListenerToCards();
            }
        });

        button_bod_esponja.addMouseListener(new MouseAdapter() {
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
                addMouseListenerToCards();
            }
        });

        window.setVisible(true);
    }

    private void addMouseListenerToCards() {
        contSegEspera = 0;
        contador = 0;
        bandera = true;
        bandera2 = true;

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
                                    ImageIcon originalIcon = new ImageIcon("src/main/java/arm/images2/" + cartas.mat[k][l] + ".png");
                                    Image image = originalIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
                                    matriz[k][l].setIcon(new ImageIcon(image));
                                    contador++;
                                    if (contador == 1) {
                                        System.out.println("contador: " + contador);
                                        numCartaNew = cartas.mat[k][l];
                                        posicioXNew = k;
                                        posicioYNew = l;
                                    }
                                    if (contador == 2) {
                                        System.out.println("contador: " + contador);
                                        numCarta = cartas.mat[k][l];
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
                                                    ImageIcon originalIcon2 = new ImageIcon("src/main/java/arm/images2/" + cartas.matAux[posicioXNew][posicioYNew] + ".png");
                                                    Image image2 = originalIcon2.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
                                                    matriz[posicioXNew][posicioYNew].setIcon(new ImageIcon(image2));
                                                    ImageIcon originalIcon3 = new ImageIcon("src/main/java/arm/images2/" + cartas.matAux[posicioAntX][posicioAntY] + ".png");
                                                    Image image3 = originalIcon3.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
                                                    matriz[posicioAntX][posicioAntY].setIcon(new ImageIcon(image3));
                                                    contador = 0;
                                                }

                                                for (int m = 0; m < 4; m++) {
                                                    for (int n = 0; n < 5; n++) {
                                                        if (cartas.matAux[m][n] != 0 && cartas.matAux[m][n] != -1) {
                                                            cartas.matAux[m][n] = 0;
                                                            ImageIcon originalIcon2 = new ImageIcon("src/main/java/arm/images2/" + cartas.matAux[m][n] + ".png");
                                                            Image image2 = originalIcon2.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
                                                            matriz[m][n].setIcon(new ImageIcon(image2));
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

}
