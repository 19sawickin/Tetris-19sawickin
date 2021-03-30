package tetris;

import javafx.scene.paint.Color;

public class Constants {
     // TODO: fill this class in with any more constants you might need!

    public static final double DURATION = 0.25;
     // width of each square
    public static final int SQUARE_WIDTH = 30;

    public static final int ROWS = 24;
    public static final int COLUMNS = 12;
    public static final int SCENE_WIDTH = COLUMNS*SQUARE_WIDTH;
    public static final int SCENE_HEIGHT = (ROWS+1)*SQUARE_WIDTH;

    public static final int X_OFFSET = SQUARE_WIDTH*COLUMNS/2;
    public static final int Y_OFFSET = SQUARE_WIDTH;

    public static final int RIGHT = SQUARE_WIDTH;
    public static final int LEFT = -1*SQUARE_WIDTH;
    public static final int DOWN = SQUARE_WIDTH;

    // coordinates for squares in each tetris piece
    public static final int[][] I_PIECE_COORDS = { {0, 0}, {0, SQUARE_WIDTH}, {0, 2*SQUARE_WIDTH}, {0, 3*SQUARE_WIDTH} };
    public static final int[][] T_PIECE_COORDS = { {-1*SQUARE_WIDTH, 0}, {-1*SQUARE_WIDTH, SQUARE_WIDTH}, {-1*SQUARE_WIDTH, 2*SQUARE_WIDTH}, {0, SQUARE_WIDTH}};
    public static final int[][] SQUARE_PIECE_COORDS = { {0, 0}, {SQUARE_WIDTH, 0}, {0, SQUARE_WIDTH}, {SQUARE_WIDTH, SQUARE_WIDTH} };
    public static final int[][] R_PIECE_COORDS = { {0, 0}, {0, SQUARE_WIDTH}, {0, 2*SQUARE_WIDTH}, {SQUARE_WIDTH, 0} };
    public static final int[][] SEVEN_PIECE_COORDS = { {0, 0}, {0, SQUARE_WIDTH}, {0, 2*SQUARE_WIDTH}, {-1*SQUARE_WIDTH, 0} };
    public static final int[][] BACK_Z_COORDS = { {0, 0}, {-1*SQUARE_WIDTH, 0}, {-1*SQUARE_WIDTH, -1*SQUARE_WIDTH}, {-2*SQUARE_WIDTH, -1*SQUARE_WIDTH} };
    public static final int[][] Z_PIECE_COORDS = { {0, 0}, {1*SQUARE_WIDTH, 0}, {1*SQUARE_WIDTH, 1*SQUARE_WIDTH}, {2*SQUARE_WIDTH, 1*SQUARE_WIDTH} };
    public static final int[][] INITIAL_COORDS = I_PIECE_COORDS;

}
