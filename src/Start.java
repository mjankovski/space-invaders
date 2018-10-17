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
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

/**
 * Klasa menu glownego.
 */
public class Start extends JPanel {

	/**
	 * Przycisk do zapisu konfiguracji liczby Alienow.
	 */
	private JButton buttonConfig;

	/**
	 * Przycisk do wyjscia z gry.
	 */
	private JButton buttonExit;

	/**
	 * Sciezka do grafiki tla.
	 */
	private File imageFile;

	/**
	 * Pole do wprowadzenia liczby wierszy Alienow.
	 */
	private JTextField rowField = new JTextField();

	/**
	 * Pole do wprowadzenia liczby kolumn Alienow.
	 */
	private JTextField columnField = new JTextField();

	/**
	 * Podpis pol tekstowych.
	 */
	private JLabel configLabel = new JLabel();

	/**
	 * Odczytana grafika tla.
	 */
	private BufferedImage image;

	/**
	 * Liczba kolumn Alienow.
	 */
	public static int aliensX = 5;

	/**
	 * Liczba wierszy Alienow.
	 */
	public static int aliensY = 5;

	/**
	 * Flaga okresljaca wygranie badz przegranie gry.
	 */
	public static int gameWon = 0;

	/**
	 * Ustawienie layoutu na null i wprowadzenie napisow, przyciskow(wyjscia i zapisu konfiguracji) i pol tekstowych
	 * oraz konfiguracja ich wlasciwosci. Wyswietlenie grafiki tla. Dodanie obslugi przyciskow menu glownego.
	 */
	public Start() {

		super();
		setLayout(null);

		configLabel.setForeground(Color.GREEN);
		configLabel.setFont(new Font("Consolas", 12, 12));
		configLabel.setBounds(115, 190, 430, 30);
		configLabel.setText("ILOSC WIERSZY: ILOSC KOLUMN:");
		this.add(configLabel);

		rowField.setBounds(115, 215, 97, 20);
		this.add(rowField);

		columnField.setBounds(220, 215, 97, 20);
		this.add(columnField);

		buttonConfig = new JButton("ZAPISZ KONFIGURACJE");
		buttonConfig.setBounds(115, 245, 203, 30);
		this.add(buttonConfig);

		buttonExit = new JButton("WYJDZ");
		buttonExit.setBounds(115, 285, 203, 30);
		this.add(buttonExit);

		buttonConfig.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					getAliensX();
				} catch (IllegalArgumentException e) {
					columnField.setText(Integer.toString(Start.aliensX));
					showMsg("Zakres kolumn: 1-20");
				}
				try {
					getAliensY();
				} catch (IllegalArgumentException e) {
					rowField.setText(Integer.toString(Start.aliensY));
					showMsg("Zakres wierszy: 1-15");
				}
			}
		});

		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		imageFile = new File("src/images/start.jpg");
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.err.println("Blad odczytu grafiki");
			e.printStackTrace();
		}
	}

	public void showMsg(String txt){
		JOptionPane optionPane = new JOptionPane("Nieprawidlowa wartosc parametru!\n" + txt,JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog("Error!");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	/**
	 * Pobranie liczby kolumn Alienow i ich zapisanie.
	 * @throws IllegalArgumentException obsluga blednych wartosci liczby kolumn.
	 */
	public void getAliensX() throws IllegalArgumentException{
		if(Integer.parseInt(columnField.getText())<1 || Integer.parseInt(columnField.getText())>20) throw new IllegalArgumentException();
		Start.aliensX = Integer.parseInt(columnField.getText());
	}

	/**
	 * Pobranie liczby wierszy Alienow i ich zapisanie.
	 * @throws IllegalArgumentException obsluga blednych wartosci liczby wierszy.
	 */
	public void getAliensY() throws IllegalArgumentException{
		if(Integer.parseInt(rowField.getText())<1 || Integer.parseInt(rowField.getText())>15) throw new IllegalArgumentException();
		Start.aliensY = Integer.parseInt(rowField.getText());
	}


	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		rowField.setText(Integer.toString(Start.aliensY));
		columnField.setText(Integer.toString(Start.aliensX));
	}
}