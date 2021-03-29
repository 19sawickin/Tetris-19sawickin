package tetris;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;
import tetris.Constants.*;


public class Game {
    private BorderPane _root;
    private Square[][] _board;
    private Square _square;
    private Piece _piece;

    private double xVal;
    private double yVal;
    private int _right;
    private int _left;
    private int _down;
//make game pane
    private Timeline _timeline;

    public Game(BorderPane root) {
        _root = root;
        _board = new Square[Constants.ROWS][Constants.COLUMNS];
        this.setupBorder();
        _piece = this.generatePiece();
        this.setupTimeline();
        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
    }

    public Piece generatePiece() {
        int randInt = (int)(Math.random()*7);
        Piece piece = null;
        switch(randInt) {
            case 0:
                piece = new Piece(Constants.I_PIECE_COORDS, Color.RED, _root, _board);
                break;
            case 1:
                piece = new Piece(Constants.T_PIECE_COORDS, Color.ORANGE, _root, _board);
                break;
            case 2:
                piece = new Piece(Constants.SQUARE_PIECE_COORDS, Color.PINK, _root, _board);
                break;
            case 3:
                piece = new Piece(Constants.R_PIECE_COORDS, Color.YELLOW, _root, _board);
                break;
            case 4:
                piece = new Piece(Constants.SEVEN_PIECE_COORDS, Color.GREEN, _root, _board);
                break;
            case 5:
                piece = new Piece(Constants.BACK_Z_COORDS, Color.LIGHTBLUE, _root, _board);
                break;
            default:
                piece = new Piece(Constants.Z_PIECE_COORDS, Color.BLUE, _root, _board);
                break;
        }
        return piece;
    }

    public void setupTimeline() {
        _timeline = new Timeline
                (new KeyFrame(Duration.seconds(Constants.DURATION), new TimeHandler()));
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    public void setupBorder() {
        for(int i=0; i<Constants.ROWS; i++) {
            for(int j=0; j<Constants.COLUMNS; j++) {
                xVal = j*Constants.SQUARE_WIDTH;
                yVal = i*Constants.SQUARE_WIDTH;
                if(i==0 || i==Constants.ROWS-1) { //Top and bottom
                    _square = new Square(_root, Color.GRAY);
                    _board[i][j] = _square;
                    _square.setX(xVal);
                    _square.setY(yVal);
                }
                if(j==0 || j==Constants.COLUMNS-1) { //side bars
                    _square = new Square(_root, Color.GRAY);
                    _board[i][j] = _square;
                    _square.setX(xVal);
                    _square.setY(yVal);
                }
            }
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> {

        public KeyHandler() {
            _right = Constants.RIGHT;
            _left = Constants.LEFT;
            _down = Constants.DOWN;

        }

        public void handle(KeyEvent e) {
            switch(e.getCode()) {
                case LEFT:
                    _piece.move(_left,0);
                    break;
                case RIGHT:
                    _piece.move(_right,0);
                    break;
                case UP:
                    _piece.rotate();
                default:
                    break;
            }
            e.consume();
        }
    }

    private class TimeHandler implements EventHandler<ActionEvent> {

        public TimeHandler() {

        }

        public void handle(ActionEvent kf) {
            int potentialY = Constants.DOWN/Constants.SQUARE_WIDTH;
            int potentialX = 0;
            if(_piece.checkMove(potentialY, potentialX)) {
                _piece.move(0,Constants.DOWN);
            } else {
                _piece.move(0, 0); //trying not to move

            }
             //Constants.DOWN
//            if(_piece.canMove(Constants.DOWN)) {
//                _piece.move(0,Constants.DOWN); //Constants.DOWN
//            } else {
//                _piece.move(0,0); //trying to make it not move
//            }
        }
    }
}
