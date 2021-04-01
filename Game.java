package tetris;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;

/**
 * The Game class contains most of the logic for the actual game. It is
 * responsible for creating the game's board and setting up the border, as
 * well as for generating pieces. The Game class also contains the timeline
 * that is responsible for moving and animating everything.
 */
public class Game {
    private Pane _gamePane;
    private Square[][] _board;
    private Piece _piece;
    private Label _pauseLabel;
    private Label _gameOverLabel;
    private Timeline _timeline;

    /**
     * The Game constructor is responsible for creating a new time handler
     * and key handler and generating the border and first piece.
     */
    public Game(Pane gamePane, Label pauseLabel, Label gameOverLabel) {
        _gamePane = gamePane;
        _board = new Square[Constants.ROWS][Constants.COLUMNS];
        this.setupBorder();
        _piece = this.generatePiece();
        this.setupTimeline(gameOverLabel);
        gamePane.addEventHandler(KeyEvent.KEY_PRESSED,
                new KeyHandler(pauseLabel));
    }

    /**
     * This method is responsible for generating a random piece using
     * coordinates from a static array in the constants class.
     */
    public Piece generatePiece() {
        int randInt = (int)(Math.random()*7);
        Piece piece = null;
        switch(randInt) {
            case 0:
                piece = new Piece(Constants.I_PIECE_COORDS, Color.RED, _gamePane, _board);
                break;
            case 1:
                piece = new Piece(Constants.T_PIECE_COORDS, Color.ORANGE, _gamePane, _board);
                break;
            case 2:
                piece = new Piece(Constants.SQUARE_PIECE_COORDS, Color.PINK, _gamePane, _board);
                break;
            case 3:
                piece = new Piece(Constants.R_PIECE_COORDS, Color.YELLOW, _gamePane, _board);
                break;
            case 4:
                piece = new Piece(Constants.SEVEN_PIECE_COORDS, Color.GREEN, _gamePane, _board);
                break;
            case 5:
                piece = new Piece(Constants.BACK_Z_COORDS, Color.LIGHTBLUE, _gamePane, _board);
                break;
            default:
                piece = new Piece(Constants.Z_PIECE_COORDS, Color.BLUE, _gamePane, _board);
                break;
        }
        return piece;
    }

    /**
     * This method sets up the timeline
     */
    public void setupTimeline(Label gameOverLabel) {
        _timeline = new Timeline
                (new KeyFrame(Duration.seconds(Constants.DURATION),
                        new TimeHandler(gameOverLabel)));
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    /**
     * This method sets up the border which is comprised of the same
     * Tetris squares that the pieces are made of. Importantly, they
     * are also added to the board array.
     */
    public void setupBorder() {
        for(int i=0; i<Constants.ROWS; i++) {
            for(int j=0; j<Constants.COLUMNS; j++) {
                int xVal = j*Constants.SQUARE_WIDTH;
                int yVal = i*Constants.SQUARE_WIDTH;
                if(i==0 || i==Constants.ROWS-1) { //Top and bottom
                    Square square = new Square(_gamePane, Color.GRAY);
                    _board[i][j] = square;
                    square.setX(xVal);
                    square.setY(yVal);
                }
                if(j==0 || j==Constants.COLUMNS-1) { //side bars
                    Square square = new Square(_gamePane, Color.GRAY);
                    _board[i][j] = square;
                    square.setX(xVal);
                    square.setY(yVal);
                }
            }
        }
    }

    /**
     * The KeyHandler is responsible for executing the appropriate action
     * when a certain key is pressed.
     */
    private class KeyHandler implements EventHandler<KeyEvent> {

        private int _pause;

        /**
         * The KeyHandler constructor takes in the pauseLabel parameter and
         * also sets 'pause' equal to zero. Whenever 'P' is pressed, this variable
         * increases by 1. So the next time 'P' is pressed, the remainder when _pause
         * is divided by two is not 0, meaning the timeline resumes.
         */
        public KeyHandler(Label pauseLabel) {
            _pauseLabel = pauseLabel;
            _pause = 0;
        }

        /**
         * The handle method takes care of what happens when keys are
         * pressed. If left/right/down are pressed, the piece will move
         * in the respective direction. If space is pressed, the piece will
         * fall as far as it can. When 'P' is pressed, the timeline pauses.
         * When the up arrow is pressed, the piece will first check to see if
         * it can rotate, then will rotate if it can.
         */
        public void handle(KeyEvent e) {
            switch(e.getCode()) {
                case LEFT:
                    if(_piece.checkMove(0, Constants.LEFT)) {
                        _piece.move(Constants.LEFT,0);
                    }
                    break;
                case RIGHT:
                    if(_piece.checkMove(0, Constants.RIGHT)) {
                        _piece.move(Constants.RIGHT,0);
                    }
                    break;
                case UP:
                    _piece.rotate();
                case DOWN:
                    if(_piece.checkMove(Constants.DOWN, 0)) {
                        _piece.move(0,Constants.DOWN);
                    }
                    break;
                case SPACE:
                    while(_piece.checkMove(Constants.DOWN,0)) {
                        _piece.move(0,Constants.DOWN);
                    }
                    break;
                case P:
                    if(_pause%2==0) {
                        _timeline.pause();
                        _pauseLabel.setVisible(true);
                    } else {
                        _timeline.play();
                        _pauseLabel.setVisible(false);
                    }
                    _pause++;
                    break;
                default:
                    break;
            }
            e.consume();
        }
    }

    /**
     * The TimeHandler is responsible for setting up the timeline which updates
     * every 0.25 seconds. Every time the timeline updates, a check is run to see
     * if the game is over (meaning there's a square in the top row of the board)
     * then checks to see if a line needs to be cleared. It also automatically moves
     * a piece that's generated down by one square every update.
     */
    private class TimeHandler implements EventHandler<ActionEvent> {

        /**
         * The time handler constructor assigns the gameOverLabel.
         */
        public TimeHandler(Label gameOverLabel) {
            _gameOverLabel = gameOverLabel;
        }

        /**
         * The handle method checks to see if the game is over or if
         * any lines need to be cleared, then checks to see if the piece
         * that was generated can move any further. If it can't then it adds
         * the piece to the board and generates another piece.
         */
        public void handle(ActionEvent kf) {
            this.checkGameOver();
            this.clearLine();
            int potentialY = Constants.DOWN;
            int potentialX = 0;
            if(_piece.checkMove(potentialY, potentialX)) {
                _piece.move(0,Constants.DOWN);
            } else {
                _piece.addToBoard();
                _piece = Game.this.generatePiece();
            }
        }

        /**
         * This method is responsible for checking to see if any lines need
         * to be cleared, then removing the pieces from the board, and both
         * logically and graphically moving all of the pieces above the removed
         * row down by one row.
         */
        public void clearLine() {
            for(int i=1; i<Constants.ROWS-1; i++) {
                if(rowIsFull(i)) {
                    for(int j=1; j<Constants.COLUMNS-1;j++) {
                        _gamePane.getChildren().remove(_board[i][j].getSquare());
                    }
                    //moving squares down
                    for(int k=i; k>1;k--) {
                        for(int j=1; j<Constants.COLUMNS-1; j++) {
                            if(_board[k-1][j]!=null) {
                                //graphically
                                _board[k-1][j].getSquare().setY(_board[k-1][j].getSquare().getY() + Constants.SQUARE_WIDTH);
                                //logically
                                _board[k][j] = _board[k-1][j];
                            } else {
                                _board[k][j] = _board[k-1][j];
                            }
                        }
                    }
                }
            }
        }

        /**
         * This method returns false if a row on the board is unfilled
         * and true if a row on the board is full.
         */
        public boolean rowIsFull(int row) {
            for(int j=0; j<Constants.COLUMNS; j++) {
                if(_board[row][j]==null) {
                    return false;
                }
            }
            return true;
        }

        /**
         * This method checks to see if there are any squares in the top row
         * of the board, and if there is, stops the timeline and makes the
         * 'game over' label visible.
         */
        public void checkGameOver() {
            for(int j=1; j<Constants.COLUMNS-1; j++) {
                if(_board[1][j]!=null) {
                    _timeline.stop();
                    _gameOverLabel.setVisible(true);
                }
            }
        }
    }
}
