package tetris;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class Game {
    private BorderPane _root;
    private HBox _bottomPane;
    private Square[][] _board;
    private Square _square;

    private double xVal;
    private double yVal;

    public Game(BorderPane root, HBox bottomPane) {
        _root = root;
        _bottomPane = bottomPane;
        _board = new Square[Constants.ROWS][Constants.COLUMNS];
        this.setupBorder();
        new Piece(root);
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
}
