/*
 * Author: Stephen Prochnow
 * Matrikelnummer: 310322
 */
package uni.prochnow.mpi;

import processing.core.PFont;

/**
 * The Class Screens.
 */
public class Screens {

	/** The main. */
	private Main main;

	/** The font. */
	PFont font;

	/**
	 * Instantiates a new screens.
	 *
	 * @param main the main
	 */
	public Screens(Main main) {
		this.main = main;
		font = main.loadFont("Univers45.vlw");
	}

	/**
	 * Show end screen.
	 */
	public void showEndScreen() {
		main.textFont(font, 130);
		main.text("Game Over!", 30, 150);
		main.textFont(font, 30);
		main.text("[R]etry", 680, 480);
		if (main.rightBar.score == 10 && main.opponent != null) {
			main.textFont(font, 50);
			main.text("Get some practice an come back.", 30, 350);
			return;
		}
		main.textFont(font, 100);
		main.text("Congratulations", 30, 300);

		main.textFont(font, 50);
		if (main.leftBar.score == 10 && main.opponent != null) {
			main.text("You defeated a machine...", 100, 400);
			return;
		}
		if (main.leftBar.score == 10) {
			main.text("Player 1 wins!", 250, 400);
			return;
		}
		if (main.rightBar.score == 10) {
			main.text("Player 2 wins!", 250, 400);
			return;
		}

	}

	/**
	 * Choose cpu.
	 */
	public void chooseCPU() {
		main.textFont(font, 80);
		main.text("Choose your enemy", 30, 150);
		main.textFont(font, 50);
		main.text("[1] Piece of Cake", 170, 250);
		main.text("[2] Let's Rock!", 170, 350);
		main.text("[3] Come get some!", 170, 450);
	}

	/**
	 * Show menu.
	 */
	public void showMenu() {
		main.textFont(font, 200);
		main.text("Pong!", 130, 200);
		main.textFont(font, 50);
		main.text("[1] Player vs. Player", 130, 300);
		main.text("[2] Player vs. Computer", 130, 400);
		main.textFont(font, 20);
		main.text("Insert Coin ... or Press the [1] or [2] key!", 220, 480);
	}

	/**
	 * Paint score.
	 */
	public void paintScore() {
		main.textFont(font, 60);
		main.text(main.leftBar.getScore(), 300, 60);
		main.text(main.rightBar.getScore(), 500, 60);
	}

	/**
	 * Paint info.
	 */
	public void paintInfo() {
		main.textFont(font, 15);
		main.text("Framerate: " + main.frameRate, 200, 490);
		main.text("BallSpeed: " + Math.abs(main.ball.velocityX), 500, 490);
	}

}
