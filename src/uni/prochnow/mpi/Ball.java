/*
 * Author: Stephen Prochnow
 * Matrikelnummer: 310322
 */
package uni.prochnow.mpi;

/**
 * The Class Ball.
 */
public class Ball {

	/** The initial pos y. */
	float yPos, initialPosY;
	
	/** The initial pos x. */
	float xPos, initialPosX;
	
	/** The initial vel x. */
	float velocityX, initialVelX;
	
	/** The initial vel y. */
	float velocityY, initialVelY;

	/** The Constant RADIUS. */
	public static final int RADIUS = 10;
	
	/** The Constant DIAMETER. */
	public static final int DIAMETER = RADIUS*2;
	
	
	/**
	 * Instantiates a new ball.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 * @param velocityX the velocity x
	 * @param velocityY the velocity y
	 */
	public Ball(float xPos, float yPos, int velocityX, int velocityY){
		this.xPos = xPos;
		this.initialPosX = xPos;
		this.yPos = yPos;
		this.initialPosY = yPos;
		this.velocityX = velocityX;
		this.initialVelX = velocityX;
		this.velocityY = velocityY;
		this.initialVelY = velocityY;

	}

	/**
	 * Reset ball.
	 */
	public void resetBall(){
		this.xPos = initialPosX;
		this.yPos = initialPosY;
		this.velocityX = initialVelX;
		this.velocityY = initialVelY;
	}



	
	
	
	
	
	
}
