/**
 * Interfejs przechowujacy stale,
 * ktore okreslaja parametry gry.
 */
public interface Config {
    /**
     * Szerokosc okna gry.
     */
    public static final int PANEL_WIDTH = 430;
    /**
     * Wysokosc okna gry.
     */
    public static final int PANEL_HEIGHT = 420;
    /**
     * Prawa granica obszaru gry.
     */
    public static final int BORDER_RIGHT = 23;
    /**
     * Lewa granica obszaru gry.
     */
    public static final int BORDER_LEFT = 5;
    /**
     * Wysokosc podloza.
     */
    public static final int GROUND = 360;


    /**
     * Wysokosc modelu Aliena.
     */
    public static final int ALIEN_HEIGHT = 12;
    /**
     * Szerokosc modelu Aliena.
     */
    public static final int ALIEN_WIDTH = 12;
    /**
     * Wysokosc modelu bomby.
     */
    public static final int BOMB_HEIGHT = 5;
    /**
     * Wartosc, po wylosowaniu ktorej nastepuje zrzucenie bomby przez Aliena.
     */
    public static final int BOMB_VALUE = 5;
    /**
     * Wartosc o jaka w pionie przesuwaja sie Alieni.
     */
    public static final int GO_DOWN = 20;

    /**
     * Szerokosc modelu gracza.
     */
    public static final int PLAYER_WIDTH = 15;
    /**
     * Wysokosc modelu gracza.
     */
    public static final int PLAYER_HEIGHT = 10;

    /**
     * Oponienie watka.
     */
    public static final int DELAY = 15;
}