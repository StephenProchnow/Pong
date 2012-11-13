/*
 * Author: Stephen Prochnow
 * Matrikelnummer: 310322
 */
package uni.prochnow.mpi;

import processing.core.PApplet;
import processing.core.PFont;
import uni.prochnow.mpi.Opponent.DIFFICULTY;
import static uni.prochnow.mpi.Playfield.*;

/**
 * The Class Main.
 */
public class Main extends PApplet {

	/**
	 * The Enum GAMESTATE.
	 */
	public enum GAMESTATE {
		
		/** The menu. */
		MENU, 
 /** The cpu. */
 CPU, 
 /** The game. */
 GAME, 
 /** The end. */
 END
	};

	/** The speed step. */
	private final float SPEED_STEP = 0.5f;
	
	/** The frame step. */
	private final int FRAME_STEP = 5;

	/** The left bar. */
	public Bar leftBar;
	
	/** The right bar. */
	public Bar rightBar;
	
	/** The ball. */
	public Ball ball;
	
	/** The opponent. */
	public Opponent opponent = null;
	
	/** The invis ball. */
	private Ball invisBall = null;
	
	/** The state. */
	private GAMESTATE state;
	
	/** The screens. */
	private Screens screens;;
	
	/** The speed modifier. */
	private float speedModifier;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new main.
	 */
	public Main() {
		super();

	}

	/* (non-Javadoc)
	 * @see processing.core.PApplet#setup()
	 */
	@Override
	public void setup() {
		size(WINDOW_WIDTH, WINDOW_HEIGHT);
		background(0);
		createBars();
		this.screens = new Screens(this);
		this.ball = new Ball(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, 2, 2);
		this.invisBall = new Ball(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, 0, 0);
		this.state = GAMESTATE.MENU;

	}

	/* (non-Javadoc)
	 * @see processing.core.PApplet#draw()
	 */
	@Override
	public void draw() {
		background(0);

		switch (state) {
		case MENU:
			screens.showMenu();
			break;
		case CPU:
			screens.chooseCPU();
			break;
		case GAME:
			playGame();
			break;
		case END:
			screens.showEndScreen();
			break;
		}
	}

	/**
	 * Play game.
	 */
	private void playGame() {
		screens.paintScore();
		screens.paintInfo();
		float dT = Math.abs(ball.velocityX);
		while (dT > 0) {
			float simTime = simulationTime(dT);
			simulateBall(simTime); // object movements + collision responses
			checkCollisions();
			drawBars();
			dT = -simTime;

		}
		if (this.opponent != null) {
			opponent.react(this.invisBall);
		}

		applySpeedModifier();
	}

	/**
	 * Apply speed modifier.
	 */
	private void applySpeedModifier() {
		if (ball.velocityX >= 0) {
			ball.velocityX = max(1, (ball.velocityX + speedModifier));
		}
		if (ball.velocityX < 0) {
			ball.velocityX = min(-1, ball.velocityX - speedModifier);
		}
		if (ball.velocityY >= 0) {
			ball.velocityY = max(1, ball.velocityY + speedModifier);
		}
		if (ball.velocityY < 0) {
			ball.velocityY = min(-1, ball.velocityY - speedModifier);
		}
		speedModifier = 0;
	}

	/**
	 * Simulation time.
	 *
	 * @param dT the d t
	 * @return the float
	 */
	private float simulationTime(float dT) {
		dT = min(dT, collisionTimePaddleLeft(dT));
		dT = min(dT, collisionTimePaddleRight(dT));
		dT = min(dT, collisionTimeBorderTop(dT));
		dT = min(dT, collisionTimeBorderBottom(dT));
		return dT;
	}

	/**
	 * Collision time border bottom.
	 *
	 * @param dT the d t
	 * @return the float
	 */
	private float collisionTimeBorderBottom(float dT) {
		if (ball.velocityY < 0) {
			return dT;
		}
		float timeToBorder = Math
				.abs((WINDOW_HEIGHT - this.ball.yPos - Ball.RADIUS)
						/ this.ball.velocityY);
		return timeToBorder;

	}

	/**
	 * Collision time border top.
	 *
	 * @param dT the d t
	 * @return the float
	 */
	private float collisionTimeBorderTop(float dT) {
		if (ball.velocityY > 0) {
			return dT;
		}
		float timeToBorder = Math.abs((this.ball.yPos + Ball.RADIUS)
				/ this.ball.velocityY);
		return timeToBorder;
	}

	/**
	 * Collision time paddle right.
	 *
	 * @param dT the d t
	 * @return the float
	 */
	private float collisionTimePaddleRight(float dT) {
		if (ball.velocityX < 0) {
			return dT;
		}
		float timeToPaddle = Math
				.abs((PLAYFIELD_LIMIT_RIGHT - this.ball.xPos - Ball.RADIUS)
						/ this.ball.velocityX);
		return timeToPaddle;
	}

	/**
	 * Collision time paddle left.
	 *
	 * @param dT the d t
	 * @return the float
	 */
	private float collisionTimePaddleLeft(float dT) {
		if (ball.velocityX > 0) {
			return dT;
		}
		float timeToPaddle = Math.abs((this.ball.xPos - PLAYFIELD_LIMIT_LEFT)
				/ this.ball.velocityX);
		return timeToPaddle;
	}

	/**
	 * Check collisions.
	 */
	private void checkCollisions() {
		if (ball.yPos + Ball.RADIUS >= WINDOW_HEIGHT && ball.velocityY > 0) {
			this.ball.velocityY *= -1;
		}

		if (ball.yPos - Ball.RADIUS <= 0 && ball.velocityY < 0) {
			this.ball.velocityY *= -1;
		}
		if (ball.xPos + Ball.RADIUS >= (PLAYFIELD_LIMIT_RIGHT)
				&& ball.velocityX > 0) {
			if (rightBar.isGoal(ball)) {
				leftBar.score += 1;
				ball.resetBall();
			} else {
				this.ball.velocityX *= -1;
			}
		}
		if (ball.xPos - Ball.RADIUS / 2 <= PLAYFIELD_LIMIT_LEFT
				&& ball.velocityX < 0) {
			if (leftBar.isGoal(ball)) {
				rightBar.score += 1;
				ball.resetBall();
			} else {
				this.ball.velocityX *= -1;
			}
		}
		if (leftBar.score == 10 || rightBar.score == 10) {
			state = GAMESTATE.END;
		}

	}

	/**
	 * Simulate ball.
	 *
	 * @param simTime the sim time
	 */
	private void simulateBall(float simTime) {
		this.ball.xPos = (this.ball.xPos + ball.velocityX * simTime);
		this.ball.yPos = (this.ball.yPos + ball.velocityY * simTime);
		if (ball.xPos > WINDOW_WIDTH / 2) {
			this.invisBall.yPos = this.ball.yPos;
			this.invisBall.xPos = this.ball.xPos;
			this.invisBall.velocityX = this.ball.velocityX;
			this.invisBall.velocityY = this.ball.velocityY;
		}
		ellipse(this.ball.xPos, this.ball.yPos, Ball.DIAMETER, Ball.DIAMETER);
	}

	/**
	 * Creates the bars.
	 */
	private void createBars() {
		this.leftBar = new Bar(BAR_HEIGHT, BAR_WIDTH, 0);
		this.rightBar = new Bar(BAR_HEIGHT, BAR_WIDTH, 0);
	}

	/* (non-Javadoc)
	 * @see processing.core.PApplet#keyPressed()
	 */
	@Override
	public void keyPressed() {
		switch (key) {
		case 'q':
			this.leftBar.barUp();
			break;
		case 'a':
			this.leftBar.barDown();
			break;
		case 'o':
			this.rightBar.barUp();
			break;
		case 'l':
			this.rightBar.barDown();
			break;
		case '*':
			this.frameRate += FRAME_STEP;
			frameRate(this.frameRate);
			break;
		case '/':
			this.frameRate -= FRAME_STEP;
			frameRate(this.frameRate);
			break;
		case '+':
			this.speedModifier += SPEED_STEP;
			break;
		case '-':
			this.speedModifier -= SPEED_STEP;
			break;
		case '1':
			if (state == GAMESTATE.MENU) {
				state = GAMESTATE.GAME;
				break;
			}
			if (state == GAMESTATE.CPU) {
				this.opponent = new Opponent(DIFFICULTY.EASY, this.rightBar);
				state = GAMESTATE.GAME;
				break;
			}
			break;
		case '2':
			if (state == GAMESTATE.MENU) {
				state = GAMESTATE.CPU;
				break;
			}
			if (state == GAMESTATE.CPU) {
				this.opponent = new Opponent(DIFFICULTY.MEDIUM, this.rightBar);
				state = GAMESTATE.GAME;
				break;
			}
		case '3':
			if (state == GAMESTATE.CPU) {
				this.opponent = new Opponent(DIFFICULTY.HARD, this.rightBar);
				state = GAMESTATE.GAME;
				break;
			}
		case 'r':
			if (state == GAMESTATE.END) {
				this.opponent = null;
				setup();
				state = GAMESTATE.MENU;
				break;
			}

		}
	}



	/**
	 * Draw bars.
	 */
	private void drawBars() {
		rect(BAR_X_OFFSET, this.leftBar.posY, this.leftBar.height,
				this.leftBar.width);
		rect(WINDOW_WIDTH - BAR_X_OFFSET - BAR_WIDTH, this.rightBar.posY,
				this.rightBar.height, this.rightBar.width);
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */

	public static void main(String[] args) {
		PApplet.main(new String[] { "uni.prochnow.mpi.Main" });

	}

}
