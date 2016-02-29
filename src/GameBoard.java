import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GameBoard extends JPanel
		implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	public long startTime;
	public boolean isgameOver;
	BufferedImage space = null;
	private StarFighter game;
	private SpaceShip SpaceShip;
	private Asteroid asteroid;
	private ArrayList<Asteroid> asteroidArray;
	private Coin coin;
	private ArrayList<Coin> coinArray;
	private Timer timer;
	private int score;
	private int ticks;

	public GameBoard(StarFighter game) {
		try {
			space = ImageIO.read(getClass().getResourceAsStream("space1.png"));
		} catch (IOException ex) {
			System.out.println("IOException");
		}
		this.setGame(game);
		setPreferredSize(new Dimension(280, 350));

		SpaceShip = new SpaceShip();
		asteroidArray = new ArrayList<>(0);
		coinArray = new ArrayList<>(0);

		addKeyListener(this);
	}

	public void setScoreDisplay(SidePanel display) {
		game.sidePanel = display;
		game.sidePanel.update(score);
	}

	public void newGame() {
		ticks = 0;
		score = 0;
		game.sidePanel.update(score);
		startTime = System.currentTimeMillis();
		timer = new Timer(10, new ClockListener());
		timer.start();
		isgameOver = false;
		requestFocus();


	}

	public void stopGame() {
		timer.stop();
		game.sidePanel.requestFocus();
		//pauses the game
	}

	public void gameOver() {
		stopGame();
		isgameOver = true;
		game.sidePanel.gameOver();
		//ends the game
	}

	public void endGame() {
		asteroidArray.clear();
		coinArray.clear();
		game.sidePanel.startOver();
		repaint();
		newGame();
	}

	public void spawnObject() {
		int x = (int) (Math.random() * 2);
		if (x == 0) {
			coin = new Coin();
			int r = (int) (Math.random() * (getWidth() - coin.getEntityWidth()));
			coin.move(r, coin.getY());
			coinArray.add(coin);
		} else {
			asteroid = new Asteroid();
			int r = (int) (Math.random() * (getWidth() - asteroid.getEntityWidth()));
			asteroid.move(r, asteroid.getY());
			asteroidArray.add(asteroid);
		}

	}

	public boolean checkCollision() {
		for (int i = 0; i < asteroidArray.size(); i++) {
			int x = SpaceShip.getX();
			int y = SpaceShip.getY() + SpaceShip.getEntityHeight();
			if (asteroidArray.get(i).isInside(x, y)) {
				//collision.play();
				return true;
			}
			int x2 = SpaceShip.getX() + SpaceShip.getEntityHeight();
			int y2 = SpaceShip.getY() + SpaceShip.getEntityWidth();
			if (asteroidArray.get(i).isInside(x2, y2)) {
				//collision.play();
				return true;
			}
			int x3 = SpaceShip.getX() + 20;
			int y3 = SpaceShip.getY();
			if (asteroidArray.get(i).isInside(x3, y3)) {
				//collision.play();
				return true;
			}
			int x4 = SpaceShip.getX() + SpaceShip.getEntityWidth() - 20;
			int y4 = SpaceShip.getY();
			if (asteroidArray.get(i).isInside(x4, y4)) {
				//collision.play();
				return true;
			}

		}
		return false;
	}

	public boolean checkCoin() {
		for (int i = 0; i < coinArray.size(); i++) {
			int x = SpaceShip.getX();
			int y = SpaceShip.getY() + SpaceShip.getEntityHeight();
			if (coinArray.get(i).isInside(x, y)) {
				//collision.play();
				coinArray.remove(i);
				return true;
			}
			int x2 = SpaceShip.getX() + SpaceShip.getEntityHeight();
			int y2 = SpaceShip.getY() + SpaceShip.getEntityWidth();
			if (coinArray.get(i).isInside(x2, y2)) {
				//collision.play();
				coinArray.remove(i);
				return true;
			}
			int x3 = SpaceShip.getX() + 20;
			int y3 = SpaceShip.getY();
			if (coinArray.get(i).isInside(x3, y3)) {
				//collision.play();
				coinArray.remove(i);
				return true;
			}
			int x4 = SpaceShip.getX() + SpaceShip.getEntityWidth() - 20;
			int y4 = SpaceShip.getY();
			if (coinArray.get(i).isInside(x4, y4)) {
				//collision.play();
				coinArray.remove(i);
				return true;
			}

		}
		return false;
	}

	// Handles timer events
	public void actionPerformed(ActionEvent e) {
	}

	/***
	 * Graphics display methods
	 ***/

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		drawSpace(g);
		drawSpaceShip(g);
		drawAsteroids(g);
		drawCoins(g);
		if (isgameOver) {
			drawGameOver(g);
		}
	}

	public void drawSpace(Graphics g) {
		g.drawImage(space, 0, 0, null);
		g.drawImage(space, 0, 0, getWidth(), 0, getWidth(), getHeight(), 0, getHeight(), null);
		//g.drawImage(space, 0, getHeight(), getWidth(), getHeight(), getWidth(), 0, 0, 0, null);
	}

	public void drawGameOver(Graphics g) {
		g.setColor(Color.RED);
		if (game.sidePanel.getHighScore()) {
			g.drawString("You Lost, New High Score!", getWidth() / 2 - 50, getHeight() / 2);
		} else {
			g.drawString("You Lost", getWidth() / 2, getHeight() / 2);
		}
	}

	public void drawAsteroids(Graphics g) {
		for (Asteroid anAsteroidArray : asteroidArray) {
			anAsteroidArray.draw(g, 1, 1);

		}
	}

	public void drawCoins(Graphics g) {
		for (Coin aCoinArray : coinArray) {
			aCoinArray.draw(g, 1, 1);

		}
	}

	public void drawSpaceShip(Graphics g) {
		SpaceShip.draw(g, SpaceShip.getX(), SpaceShip.getY());
	}

	public StarFighter getGame() {
		return game;
	}

	public void setGame(StarFighter game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int dx = 0;
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_KP_LEFT:
				dx = -1;
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_KP_RIGHT:
				dx = 1;
				break;
			case KeyEvent.VK_SPACE:
				//shootBullet();
				break;
		}
		if (dx == -1) {
			for (int i = 0; i < 5; i++) {
				if (SpaceShip.getX() > 0) {
					SpaceShip.move(SpaceShip.getX() - 1, SpaceShip.getY());
					repaint();
				}
			}
		}
		if (dx == 1) {
			for (int i = 0; i < 5; i++) {
				if (SpaceShip.getX() < getWidth() - SpaceShip.getEntityWidth()) {
					SpaceShip.move(SpaceShip.getX() + 1, SpaceShip.getY());
					repaint();
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// Handles clock events.
	private class ClockListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (checkCollision()) {
				gameOver();
			}
			if (checkCoin()) {
				score += 50;
			}
			if (asteroidArray.size() != 0) {
				for (int i = 0; i < asteroidArray.size(); i++) {
					if (asteroid.getX() + asteroid.getEntityHeight() < getHeight()) {
						asteroidArray.get(i).dropAsteroid();
					} else {
						asteroidArray.remove(i);
					}
				}
			}
			if (coinArray.size() != 0) {
				for (int i = 0; i < coinArray.size(); i++) {
					if (coin.getX() + coin.getEntityHeight() < getHeight()) {
						coinArray.get(i).dropAsteroid();
					} else {
						coinArray.remove(i);
					}
				}
			}

			ticks++;
			if (ticks == 0 || ticks % 100 == 0) {
				spawnObject();
				score += 10;
				game.sidePanel.update(score);
				game.sidePanel.repaint();
			}

			game.repaint();
		}
	}
}


