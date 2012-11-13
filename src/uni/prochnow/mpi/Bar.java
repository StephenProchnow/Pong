/*
 * Author: Stephen Prochnow
 * Matrikelnummer: 310322
 */
package uni.prochnow.mpi;

/**
 * The Class Bar.
 */
public class Bar {

	/** The pos y. */
	int posY;

	// height is length in x-Axis
	/** The height. */
	int height;

	// width is length in y-Axis
	/** The width. */
	int width;

	/** The score. */
	public int score;

	/** The Constant MOVE_SPEED. */
	public final static int MOVE_SPEED = 40;

	/**
	 * Instantiates a new bar.
	 *
	 * @param width the width
	 * @param height the height
	 * @param posY the pos y
	 */
	public Bar(int width, int height, int posY) {
		this.height = height;
		this.width = width;
		this.posY = posY;
		this.score = 0;
	}

	/**
	 * Bar up.
	 */
	public void barUp() {
		if (this.posY-MOVE_SPEED > 0) {
			this.posY = posY - MOVE_SPEED;
		}else{
			this.posY = 0;
		}
	}
	
	/**
	 * Bar up.
	 *
	 * @param distance the distance
	 */
	public void barUp(int distance) {
		if (this.posY-distance > 0) {
			this.posY = posY - distance;
		}else{
			this.posY = 0;
		}
	}

	/**
	 * Bar down.
	 */
	public void barDown() {
		if (posY+MOVE_SPEED < Playfield.WINDOW_HEIGHT - width) {
			this.posY = posY + MOVE_SPEED;
		}else{
			posY =  Playfield.WINDOW_HEIGHT - width;
		}
	}
	
	/**
	 * Bar down.
	 *
	 * @param distance the distance
	 */
	public void barDown(int distance) {
		if (posY+distance < Playfield.WINDOW_HEIGHT - width) {
			this.posY = posY + distance;
		}else{
			posY = Playfield.WINDOW_HEIGHT - width;
		}
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public String getScore() {
		return Integer.toString(score);

	}

	/**
	 * Checks if is goal.
	 *
	 * @param ball the ball
	 * @return true, if is goal
	 */
	public boolean isGoal(Ball ball) {
		return (this.posY > ball.yPos + Ball.RADIUS || ball.yPos - Ball.RADIUS > (this.posY + this.width));
	}

}
