package tetris;

/**
 * The constants class contains constants that are used in the game.
 */
public class Constants {

    public static final double DURATION = 0.25;
     // width of each square
    public static final int SQUARE_WIDTH = 30;

    // sets the number of rows and columns as well as the scene height and width
    public static final int ROWS = 24;
    public static final int COLUMNS = 12;
    public static final int SCENE_WIDTH = COLUMNS*SQUARE_WIDTH;
    public static final int SCENE_HEIGHT = (ROWS+1)*SQUARE_WIDTH;

    // offset values that determine the starting x and y positions for the initial piece
    public static final int X_OFFSET = SQUARE_WIDTH*COLUMNS/2;
    public static final int Y_OFFSET = SQUARE_WIDTH;

    // constants that determine how far the piece moves when moving left, right, or down
    public static final int RIGHT = SQUARE_WIDTH;
    public static final int LEFT = -1*SQUARE_WIDTH;
    public static final int DOWN = SQUARE_WIDTH;

    // coordinates for squares in each tetris piece
    public static final int[][] I_PIECE_COORDS = { {0, 0}, {0, SQUARE_WIDTH}, {0, 2*SQUARE_WIDTH}, {0, 3*SQUARE_WIDTH} };
    public static final int[][] T_PIECE_COORDS = { {-1*SQUARE_WIDTH, 0}, {-1*SQUARE_WIDTH, SQUARE_WIDTH}, {-1*SQUARE_WIDTH, 2*SQUARE_WIDTH}, {0, SQUARE_WIDTH}};
    public static final int[][] SQUARE_PIECE_COORDS = { {0, 0}, {SQUARE_WIDTH, 0}, {0, SQUARE_WIDTH}, {SQUARE_WIDTH, SQUARE_WIDTH} };
    public static final int[][] R_PIECE_COORDS = { {0, 0}, {0, SQUARE_WIDTH}, {0, 2*SQUARE_WIDTH}, {SQUARE_WIDTH, 0} };
    public static final int[][] SEVEN_PIECE_COORDS = { {0, 0}, {0, SQUARE_WIDTH}, {0, 2*SQUARE_WIDTH}, {-1*SQUARE_WIDTH, 0} };
    public static final int[][] BACK_Z_COORDS = { {0, 0}, {-1*SQUARE_WIDTH, 0}, {-1*SQUARE_WIDTH, 1*SQUARE_WIDTH}, {-2*SQUARE_WIDTH, 1*SQUARE_WIDTH} };
    public static final int[][] Z_PIECE_COORDS = { {0, 0}, {1*SQUARE_WIDTH, 0}, {1*SQUARE_WIDTH, 1*SQUARE_WIDTH}, {2*SQUARE_WIDTH, 1*SQUARE_WIDTH} };

}
