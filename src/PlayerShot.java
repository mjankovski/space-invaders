import javax.swing.ImageIcon;

/**
 * Klasa pocisku wystrzeliwanego przez gracza.
 */
public class PlayerShot extends Sprite {

    /**
     * Sciezka do grafiki modelu pocisku.
     */
    private final String shotImage = "src/images/playerShot.png";

    /**
     * Wysokosc pocisku.
     */
    private final int playerShotHeight = 5;
    /**
     * Szerokosc pocisku.
     */
    private final int playerShotWidth = 1;

    /**
     * Konstruktor sluzacy do poczatkowego stworzenia obiektu pocisku.
     */
    public PlayerShot() {
    }

    /**
     * Konstruktor sluzacy do stworzenia pocisku w momencie strza�u gracza
     * oraz ustawienie jego grafiki i polozenia.
     * @param x startowa wspolrzedna x pocisku.
     * @param y startowa wspolrzedna y pocisku.
     */
    public PlayerShot(int x, int y) {

    	setImage(new ImageIcon(shotImage).getImage());

        setX(x);
        setY(y - playerShotHeight);
    }
}