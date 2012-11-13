/*
 * Author: Stephen Prochnow
 * Matrikelnummer: 310322
 */
package uni.prochnow.mpi;

import java.util.Random;

/**
 * The Class Opponent.
 */
public class Opponent {
	
	/**
	 * The Enum DIFFICULTY.
	 */
	public enum DIFFICULTY {/** The easy. */
EASY,/** The medium. */
MEDIUM,/** The hard. */
HARD};
	
	/** The mode. */
	private DIFFICULTY mode;
	
	/** The bar. */
	private Bar bar;
	
	/** The random. */
	private Random random;
	
	/**
	 * Instantiates a new opponent.
	 *
	 * @param mode the mode
	 * @param bar the bar
	 */
	public Opponent(DIFFICULTY mode, Bar bar){
		this.mode = mode;
		this.random = new Random(System.currentTimeMillis());
		this.bar = bar;
	}
	
	/**
	 * React.
	 *
	 * @param ball the ball
	 */
	public void react(Ball ball){
		
		if(bar.posY+Playfield.BAR_HEIGHT > ball.yPos){
			moveBarUp();
			return;
		}
		if(bar.posY+Playfield.BAR_HEIGHT < ball.yPos){
			moveBarDown();
			return;
		}

	}

	/**
	 * Creates the move dist.
	 *
	 * @return the int
	 */
	private int createMoveDist() {	
		int moveDist = random.nextInt(3);
		switch(mode){
		case EASY: moveDist +=1;
		break;
		case MEDIUM: moveDist += 2;
		break;
		case HARD: moveDist +=5;
		break;
		}
		return moveDist;
	}
	
	/**
	 * Move bar down.
	 */
	private void moveBarDown() {
		this.bar.barDown(createMoveDist());
		
	}

	/**
	 * Move bar up.
	 */
	private void moveBarUp() {
		this.bar.barUp(createMoveDist());
	}

}
