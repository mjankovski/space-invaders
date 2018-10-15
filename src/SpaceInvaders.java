import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.CardLayout;


/**
 * Klasa gry, przechowujaca dane na temat wszystkich ekranow w grze.
 *
 */
public class SpaceInvaders extends JFrame implements Config {

	/**
	 * CardLayout potrzebny do zmiany ekranow gry.
	 */
	public static CardLayout cl = new CardLayout();

	/**
	 * Panel glowny odpowiadajacy za zmiane ekranow.
	 */
	public static JPanel cards = new JPanel(cl);

	/**
	 * Panel menu glownego.
	 */
	private JPanel start = new Start();

	/**
	 * Panel menu koncowego.
	 */
	private JPanel end = new End();

	/**
	 * Panel okna gry.
	 */
	private JPanel game;

	/**
	 * Przycisk umozliwiajacy rozpoczecie gry.
	 */

	private JButton buttonStart = new JButton("ROZPOCZNIJ GRE");

    /**
     * Konstruktor odpowiadajacy za wywolanie metody odpowiedzialnej za GUI.
     */
    public SpaceInvaders() {

        initGUI();
    }

    /**
     * Ustawienie parametrow okna gry - wymiarow, tytulu, wlasciwosci.
     * Dodanie przycisku umozliwiajacego rozpoczecie gry oraz konfiguracja CardLayoutu.
     */
    private void initGUI() {
    	setTitle("Space Invaders - Milosz Jankowski");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        start.add(buttonStart);
        buttonStart.setBounds(115, 160, 203, 30);
        SpaceInvaders.cards.add(start, "Start");
        SpaceInvaders.cards.add(end, "End");
        add(SpaceInvaders.cards);
        SpaceInvaders.cl.show(SpaceInvaders.cards, "Start");

        buttonStart.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game = new Game();
				SpaceInvaders.cards.add(game, "Game");
				SpaceInvaders.cl.show(SpaceInvaders.cards, "Game");
		        game.requestFocusInWindow();
			}
		});
    }

    /**
     * Metoda main gry. Odpowiada za uruchomienie gry.
     * @param args tablica parametrow wywolania.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SpaceInvaders ex = new SpaceInvaders();
            ex.setVisible(true);
        });
    }
}