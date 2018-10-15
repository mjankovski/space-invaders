import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

/**
 * Klasa obiektu gracza.
 */
public class Player extends Sprite implements Config {

    /**
     * Startowa wspolrzedna y gracza.
     */
    private final int startY = GROUND - 15;
    /**
     * Startowa wspolrzedna x gracza.
     */
    private final int startX = 215;

    /**
     * Sciezka do grafiki modelu gracza.
     */
    private final String playerImage = "src/images/player.png";

    /**
     * Punkty gracza.
     */
    private static int points = 0;
    /**
     * Szerokosc modelu gracza.
     */
    public static int width;


    /**
     * Ustawienie grafiki modelu gracza, jego wspolrzednych startowych oraz przypisanie jego szerokosci.
     */
    public Player() {
    	ImageIcon imageIcon = new ImageIcon(playerImage);

        width = imageIcon.getImage().getWidth(null);
        setImage(imageIcon.getImage());
        setX(startX);
        setY(startY);
    }

    /**
     * Zmiana polozenia gracza o dx biorac pod uwage ograniczenia okna gry.
     */
    public void move() {

        x += dx;

        if (x <= 1) {
            x = 1;
        }

        if (x >= PANEL_WIDTH - 1.5 * width) {
            x = (int) (PANEL_WIDTH - 1.5 * width);
        }
    }

    /**
     * Ustawienie punktow gracza.
     * @param points punkty gracza.
     */
    public static void setPoints(int points){
    	Player.points = points;
    }

    /**
     * Dodanie punktow graczowi.
     */
    public void addPoints(){
    	Player.points += 100;
    }

    /**
     * Zwraca liczba punktow zdobytych przez gracza.
     * @return liczba punktow gracza.
     */
    public static int getPoints(){
    	return points;
    }

    /**
     * Obsluga nacisniecia klawisza w kontekscie gracza - poruszanie sie w prawo lub w lewo.
     * @param e Informacje na temat wcisnietego klawisza.
     */
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            this.dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {

            this.dx = 2;
        }
    }

    /**
     * Obsluga puszczenia klawisza w kontekscie gracza - zatrzymanie sie gracza.
     * @param e Informacje na temat wcisnietego klawisza.
     */
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            this.dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            this.dx = 0;
        }
    }
}