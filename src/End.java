import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Klasa menu koncowego.
 */
public class End extends JPanel {

	/**
	 * Sciezka do grafiki tla.
	 */
	private File imageFile;

	/**
	 * Odczytana grafika tla.
	 */
	private BufferedImage image;

	/**
	 * Wypisany rezultat gry.
	 */
	private JLabel resultLabel = new JLabel();

	/**
	 * Wypisana liczba zdobytych punktow.
	 */
	private JLabel pointsLabel = new JLabel();

	/**
	 * Przycisk kontynuowania do menu glownego.
	 */
	private JButton buttonContinue;

	/**
	 * Ustawienie layoutu na null i wprowadzenie napisow, przycisku(kontynuacji) i pol tekstowych
	 * oraz konfiguracja ich wlasciwooci. Wyswietlenie grafiki tla. Dodanie obslugi przycisku menu koncowego.
	 */
	public End() {
		super();
		setLayout(null);

		resultLabel.setForeground(Color.GREEN);
		resultLabel.setFont(new Font("Consolas", 12, 12));
		resultLabel.setBounds(180, 160, 100, 30);
		this.add(resultLabel);

		pointsLabel.setForeground(Color.GREEN);
		pointsLabel.setFont(new Font("Consolas", 12, 12));
		pointsLabel.setBounds(145, 190, 430, 30);
		this.add(pointsLabel);

		buttonContinue = new JButton("Kontynuuj!");
		buttonContinue.setBounds(115, 240, 203, 30);
		this.add(buttonContinue);

		buttonContinue.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SpaceInvaders.cl.show(SpaceInvaders.cards, "Start");
			}
		});

		imageFile = new File("src/images/start.jpg");
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.err.println("B��d odczytu grafiki!");
			e.printStackTrace();
		}
	}

	/**
	 * Wyswietlenie komunikatu odnosnie wygranej badz przegranej oraz liczby zdobytych punktow.
	 */
	private void gameOverScreen(){
		if(Start.gameWon == 1){
			resultLabel.setText("WYGRALES!");
			pointsLabel.setText("ZDOBYLES " + Player.getPoints() + " PUNKTOW!");
		}else {
			resultLabel.setText("PRZEGRALES!");
			pointsLabel.setText("ZDOBYLES " + Player.getPoints() + " PUNKTOW!");
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		gameOverScreen();
	}
}