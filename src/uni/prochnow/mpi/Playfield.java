/*
 * Author: Stephen Prochnow
 * Matrikelnummer: 310322
 */
package uni.prochnow.mpi;


/**
 * The Class Playfield.
 */
public class Playfield {
	
	/** The Constant WINDOW_WIDTH. */
	public static final int WINDOW_WIDTH = 800;
	
	/** The Constant WINDOW_HEIGHT. */
	public static final int WINDOW_HEIGHT = 500;
	
	/** The Constant BAR_HEIGHT. */
	public static final int BAR_HEIGHT = 100;
	
	/** The Constant BAR_WIDTH. */
	public static final int BAR_WIDTH = 15;
	
	/** The Constant BAR_X_OFFSET. */
	public static final int BAR_X_OFFSET = 20;
	
	/** The Constant PLAYFIELD_LIMIT_LEFT. */
	public static final int PLAYFIELD_LIMIT_LEFT = BAR_X_OFFSET + BAR_WIDTH;
	
	/** The Constant PLAYFIELD_LIMIT_RIGHT. */
	public static final int PLAYFIELD_LIMIT_RIGHT = Playfield.WINDOW_WIDTH
			- BAR_X_OFFSET - BAR_WIDTH;
}
