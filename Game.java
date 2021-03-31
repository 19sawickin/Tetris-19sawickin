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

public class Game {
    private Pane _gamePane;
    private Square[][] _board;
    private Square _square;
    private Piece _piece;
    private Label _pauseLabel;
    private Label _gameOverLabel;

    private double xVal;
    private double yVal;


//make game pane
    private Timeline _timeline;

    public Game(Pane gamePane, Label pauseLabel, Label gameOverLabel) {
        _gamePane = gamePane;
        _board = new Square[Constants.ROWS][Constants.COLUMNS];
        this.setupBorder();
        _piece = this.generatePiece();
        this.setupTimeline(gameOverLabel);
        gamePane.addEventHandler(KeyEvent.KEY_PRESSED,
                new KeyHandler(pauseLabel));
    }

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

    public void setupTimeline(Label gameOverLabel) {
        _timeline = new Timeline
                (new KeyFrame(Duration.seconds(Constants.DURATION),
                        new TimeHandler(gameOverLabel)));
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    public void setupBorder() {
        for(int i=0; i<Constants.ROWS; i++) {
            for(int j=0; j<Constants.COLUMNS; j++) {
                xVal = j*Constants.SQUARE_WIDTH;
                yVal = i*Constants.SQUARE_WIDTH;
                if(i==0 || i==Constants.ROWS-1) { //Top and bottom
                    _square = new Square(_gamePane, Color.GRAY);
                    _board[i][j] = _square;
                    _square.setX(xVal);
                    _square.setY(yVal);
                }
                if(j==0 || j==Constants.COLUMNS-1) { //side bars
                    _square = new Square(_gamePane, Color.GRAY);
                    _board[i][j] = _square;
                    _square.setX(xVal);
                    _square.setY(yVal);
                }
            }
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> {

        private int _pause;

        public KeyHandler(Label pauseLabel) {
            _pauseLabel = pauseLabel;
            _pause = 0;
        }

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

    private class TimeHandler implements EventHandler<ActionEvent> {

        public TimeHandler(Label gameOverLabel) {
            _gameOverLabel = gameOverLabel;
        }

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

        public boolean rowIsFull(int row) {
            for(int j=0; j<Constants.COLUMNS; j++) {
                if(_board[row][j]==null) {
                    return false;
                }
            }
            return true;
        }

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
