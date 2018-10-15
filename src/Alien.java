import javax.swing.ImageIcon;

/**
 * Klasa obiektu Aliena..
 */
public class Alien extends Sprite {

    /**
     * Sciezka do grafiki modelu Aliena.
     */
    private final String imageAlien = "src/images/alien.png";

    /**
     * Referencja do bomby Aliena.
     */
    private BombAlien BombAlien;

    /**
     * Stworzenie nowego Aliena, przypisanie mu wspolrzednych polozenia, grafiki oraz stworzenie bomby.
     * @param x Wspolrzedna x polozenia Aliena.
     * @param y Wspolrzedna y polozeniaa Aliena.
     */
    public Alien(int x, int y) {
    	this.x = x;
        this.y = y;

        setImage(new ImageIcon(imageAlien).getImage());

        BombAlien = new BombAlien(x, y);
    }

    /**
     * Przemieszczenie Aliena w poziomie.
     * @param dx Wartosc, o ktora zostanie przemieszczony Alien.
     */
    public void move(int dx) {

        this.x += dx;
    }

    /**
     * Zwracanie referencji do bomby Aliena.
     * @return referencja do bomby danego Aliena.
     */
    public BombAlien getBombAlien() {

        return this.BombAlien;
    }

    /**
     * Klasa bomby zrzucanej przez Aliena.
     */
    public class BombAlien extends Sprite {

        /**
         * Sciezka do grafiki Aliena.
         */
        private final String imageBomb = "src/images/alienShot.png";
        /**
         * Flaga okreslajaca czy dana bomba ulegla zniszczeniu.
         */
        private boolean isDestroyed;

        /**
         * Konstruktor bomby nadaje wspolrzedne bombie.
         * Sprawia, ze na poczatku gry jest ona zniszczona,
         * aby po losowym czasie Alieni mogli zaczac je zrzucac.
         * Nastepnie nadanie sciezki do grafiki dla obiektu bomby.
         * @param x Wspolrzedna x polozenia bomby.
         * @param y Wspolrzedna y polozenia bomby.
         */
        public BombAlien(int x, int y) {
        	setDestroyed(true);
            this.x = x;
            this.y = y;
            setImage(new ImageIcon(imageBomb).getImage());
        }

        /**
         * Ustawienie stanu zycia bomby.
         * @param destroyed Flaga okreslajaca stan zycia bomby.
         */
        public void setDestroyed(boolean destroyed) {
            this.isDestroyed = destroyed;
        }

        /**
         * Zwraca stan zycia bomby.
         * @return true jesli bomba jest zniszczona. False w przeciwnym przypadku.
         */
        public boolean isDestroyed() {
            return this.isDestroyed;
        }
    }
}