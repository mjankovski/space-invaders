import java.awt.Image;

/**
 * Klasa bedaca szkieletem dla obiektow poruszajacych sie w oknie - Alienow, statku, bomb i strzalow.
 */
public class Sprite {

    /**
     * Flaga okreslajaca czy dany obiekt jest wyswietlany w oknie gry.
     */
    private boolean visible;

    /**
     * Przechowywana grafika obiektu.
     */
    private Image image;

    /**
     * Flaga okreslajaca czy dany obiekt jest martwy.
     */
    protected boolean dead;

    /**
     * Wspolrzedna x polozenia obiektu.
     */
    protected int x;
    /**
     * Wspolrzedna y polozenia obiektu.
     */
    protected int y;
    /**
     * Przemieszczenie obiektu w poziomie.
     */
    protected int dx;

    /**
     * Konstruktor obiektu sprawia, ze obiekt staje sie jednoczesnie widoczny.
     */
    public Sprite() {
        this.visible = true;
    }

    /**
     * W momencie smierci obiektu przestaje on byl widoczny.
     */
    public void die() {
    	this.visible = false;
    }

    /**
     * Zwraca flage widocznosci obiektu.
     * @return true jesli obiekt jest widoczny. W przeciwnym razie false.
     */
    public boolean isVisible() {

        return this.visible;
    }

    /**
     * Ustawienie widocznosci obiektu.
     * @param visible widocznosc obiektu.
     */
    protected void setVisible(boolean visible) {

        this.visible = visible;
    }

    /**
     * Ustawienie stanu zycia obiektu.
     * @param dead stan zycia obiektu.
     */
    public void setDead(boolean dead) {

        this.dead = dead;
    }

    /**
     * Sprawdzenie stanu zycia obiektu.
     * @return true jesli obiekt jest zniszczony. W przeciwnym razie false.
     */
    public boolean isDead() {

        return this.dead;
    }

    /**
     * Ustawienie wspolrzednej x polozenia obiektu.
     * @param x wspolrzedna x polozenia obiektu.
     */
    public void setX(int x) {

        this.x = x;
    }

    /**
     * Ustawienie wspolrzednej y polozenia obiektu.
     * @param y wspolrzedna y polozenia obiektu.
     */
    public void setY(int y) {

        this.y = y;
    }

    /**
     * Zwraca wartosc wspolrzednej x polozenia obiektu.
     * @return wspolrzedna x polozenia obiektu.
     */
    public int getX() {

        return this.x;
    }

    /**
     * Zwraca wartosc wspolrzednej y polozenia obiektu.
     * @return wspolrzedna y polozenia obiektu.
     */
    public int getY() {

        return this.y;
    }

    /**
     * Ustawienie grafiki obiektu.
     * @param image grafika danego obiektu.
     */
    public void setImage(Image image) {

        this.image = image;
    }

    /**
     * Zwraca referencje do grafiki obiektu.
     * @return referencja do grafiki obiektu.
     */
    public Image getImage() {

        return this.image;
    }
}