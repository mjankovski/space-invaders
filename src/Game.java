import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, Config {

    /**
     * Obiekt klasy Dimension do przypisania wymiarow okna gry.
     */
    private Dimension d;

    /**
     * Watek potrzebny do animacji obiektow.
     */
    private Thread animator;

    /**
     * Flaga okreslajaca czy gra nadal trwa.
     */
    private boolean ingame = true;

    /**
     * Flaga okreslajaca pauze.
     */
    private boolean pause = false;

    /**
     * Kierunek poruszania sie Alienow.
     */
    private int aliensDirection = -1;

    /**
     * Liczba zestrzelonych Alienow w trakcie gry.
     */
    private int alienDeaths = 0;

    /**
     * Sciezka do grafiki eksplozji
     */
    private final String explosionImage = "src/images/explosion.png";

    /**
     * Obiekt gracza.
     */
    private Player player;

    /**
     * Obiekt pocisku.
     */
    private PlayerShot shot;

    /**
     * Lista Alienow.
     */
    private ArrayList<Alien> aliens;

    /**
     * Wyznaczona liczba alienow wymagana do wygrania gry.
     */
    private int destroysToWin = Start.aliensX * Start.aliensY;

    /**
     * Startowa wspolrzedna x polozenia Alienow.
     */
    private final int alienStartX = 150;

    /**
     * Startowa wspolrzedna y polozenia Alienow.
     */
    private final int alienStartY = 5;


    /**
     * Dodanie sluchacza klawiatury. Przypisanie wymiarow okna gry.
     * Ustawienie koloru tla. Wywolanie inicjalizacji gry.
     * Wlaczenie Double Bufferingu.
     */
    public Game() {
    	addKeyListener(new TAdapter());

        d = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
        setBackground(Color.black);

        initGame();
        setDoubleBuffered(true);
    }

    /**
     * Inicjalizacja obiektow potrzebnych w grze.
     * Stworzenie gracza(oraz wyzerowanie jego punktow),
     * pocisku, watku potrzebnego do animacji oraz listy Alienow.
     */
    public void initGame() {

        aliens = new ArrayList<>();

        for (int i = 0; i < Start.aliensY; i++) {
            for (int j = 0; j < Start.aliensX; j++) {

                Alien alien = new Alien(alienStartX + 18 * j, alienStartY + 18 * i);
                aliens.add(alien);
            }
        }

        player = new Player();
        Player.setPoints(0);

        shot = new PlayerShot();

        if (animator == null) {
            animator = new Thread(this);
            animator.start();
        }
    }

    /**
     * Rysowanie modelu statku w oknie gry.
     * @param g obiekt klasy Graphics.
     */
    public void drawPlayer(Graphics g) {

    	g.setFont(new Font("Consolas", 12, 12));
    	g.drawString("Punkty: " + Player.getPoints(), 5, 15);

        if (player.isVisible()) {

            g.drawImage(player.getImage(), player.getX(), player.getY(), null);
        }

        if (player.isDead()) {

            player.die();
            Start.gameWon = 0;
            ingame = false;
        }
    }

    /**
     * Rysowanie modelu pocisku w oknie gry.
     * @param g obiekt klasy Graphics.
     */
    public void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), null);
        }
    }

    /**
     * Rysowanie modelu Alienow w oknie gry.
     * @param g obiekt klasy Graphics.
     */
    public void drawAliens(Graphics g) {

        for (Alien a: aliens) {

            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), null);
            }

            if (a.isDead()) {
                a.die();
            }
        }
    }

    /**
     * Rysowanie modelu bomb w oknie gry.
     * @param g obiekt klasy Graphics.
     */
    public void drawBombing(Graphics g) {

        for (Alien a : aliens) {

            Alien.BombAlien b = a.getBombAlien();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), null);
            }
        }
    }

    /**
     * Krok animacji pocisku. Aktualizacja polozenia.
     * Krok animacji pocisku. Aktualizacja polozenia.
     * Sprawdzenie i obsluga kolizji z Alienem lub opuszczenia powierzchni mapy.
     */
    private void shotStep(){
    	if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();

            for (Alien a: aliens) {

                int alienX = a.getX();
                int alienY = a.getY();

                if (a.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX) && shotX <= (alienX + ALIEN_WIDTH)
                            && shotY >= (alienY) && shotY <= (alienY + ALIEN_HEIGHT)) {
                        a.setImage(new ImageIcon(explosionImage).getImage());
                        a.setDead(true);
                        player.addPoints();
                        alienDeaths++;
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }
    }

    /**
     * Krok animacji Alienow. Aktualizacja polozenia.
     * Ewentualne zejscie w dol i zmiana kierunku poruszania sie.
     * Sprawdzenie i obsluga przekroczenia linii konca gry.
     */
    private void aliensStep(){
    	for (Alien alien: aliens) {

            int x = alien.getX();
            Iterator<Alien> it = aliens.iterator();


            if (x >= PANEL_WIDTH - BORDER_RIGHT && aliensDirection != -1) {

                aliensDirection = -1;

                while (it.hasNext()) {

                    Alien a = (Alien) it.next();
                    a.setY(a.getY() + GO_DOWN);
                }
            }

            if (x <= BORDER_LEFT && aliensDirection != 1) {

                aliensDirection = 1;

                while (it.hasNext()) {

                    Alien a = (Alien) it.next();
                    a.setY(a.getY() + GO_DOWN);
                }
            }
        }

        Iterator<Alien> it = aliens.iterator();

        while (it.hasNext()) {

            Alien alien = (Alien) it.next();

            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > GROUND - 2*ALIEN_HEIGHT) {
                    ingame = false;
                    Start.gameWon = 0;
                }

                alien.move(aliensDirection);
            }
        }
    }

    /**
     * Krok animacji dla bomb. Losowanie szansy na strzao.
     * Aktualizacja polozenia. Sprawdzenie kolizji z graczem lub podlozem.
     */
    private void bombsStep(){
    	Random generator = new Random();

        for (Alien alien: aliens) {

            int bombRand = generator.nextInt(250);
            Alien.BombAlien b = alien.getBombAlien();

            if (bombRand == BOMB_VALUE && alien.isVisible() && b.isDestroyed()) {
                b.setDestroyed(false);
                b.setX(alien.getX());
                b.setY(alien.getY());
            }

            int bombX = b.getX();
            int bombY = b.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !b.isDestroyed()) {

                if (bombX >= (playerX) && bombX <= (playerX + PLAYER_WIDTH)
                        && bombY >= (playerY) && bombY <= (playerY + PLAYER_HEIGHT)) {
                    player.setImage(new ImageIcon(explosionImage).getImage());
                    player.setDead(true);
                    b.setDestroyed(true);
                }
            }

            if (!b.isDestroyed()) {

                b.setY(b.getY() + 1);

                if (b.getY() >= GROUND - BOMB_HEIGHT) {
                    b.setDestroyed(true);
                }
            }
        }
    }

    /**
     * Sprawdzenie warunku wygranej poprzez zabicie wymaganej ilosci Alienow.
     */
    private void checkWin(){
    	if (alienDeaths == destroysToWin) {
        	Start.gameWon = 1;
            ingame = false;
        }
    }

    /**
     * Wykonanie kolejnego kroku animacji przez gracza, pocisk, Alienow i bomby.
     */
    public void animationStep() {

        checkWin();

        player.move();

        shotStep();

        aliensStep();

        bombsStep();
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, d.width, d.height);

        if (ingame) {

        	g.setColor(Color.WHITE);
            g.drawLine(0, GROUND, PANEL_WIDTH, GROUND);
            g.setColor(Color.GREEN);
            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        long timeBeforeStep, timeDifference, sleep;

        timeBeforeStep = System.currentTimeMillis();

        while (ingame) {
        	if(!pause){
            repaint();
            animationStep();
        	}
            timeDifference = System.currentTimeMillis() - timeBeforeStep;
            sleep = DELAY - timeDifference;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
            	String msg = String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
            timeBeforeStep = System.currentTimeMillis();
        }
        	SpaceInvaders.cl.show(SpaceInvaders.cards, "End");
    }

    /**
     * Obsluga klawiatury w grze.
     */
    private class TAdapter extends KeyAdapter {

        /* (non-Javadoc)
         * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
         */
        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        /* (non-Javadoc)
         * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
         */
        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX() + (int)(0.5*Player.width);
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (ingame) {
                    if (!shot.isVisible()) {
                        shot = new PlayerShot(x, y);
                    }
                }
            } else if (key == KeyEvent.VK_P){
            	pause = !pause;
            }
        }
    }
}