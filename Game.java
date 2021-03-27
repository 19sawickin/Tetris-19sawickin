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
        this.setupShape();
        new Piece(root);
    }

    public void setupBorder() {
        for(int i=0; i<Constants.ROWS; i++) {
            if(i==0 || i==Constants.ROWS-1) {
                for(int j=0; j<Constants.COLUMNS; j++) {
                    _board[i][j] = new Square(_root, Color.GRAY);
                    xVal = j*Constants.SQUARE_WIDTH;
                    yVal = i*Constants.SQUARE_WIDTH;
                    _square.setX(xVal);
                    _square.setY(yVal);
                }
            }
        }
        for(int j=0; j<Constants.COLUMNS; j++) {
            if(j==0 || j==Constants.COLUMNS-1) {
                for(int i=0; i<Constants.ROWS; i++) {
                    xVal = j*Constants.SQUARE_WIDTH;
                    yVal = i*Constants.SQUARE_WIDTH;
                    _board[i][j] = new Square(_root, Color.GRAY);
                    _square.getSquare().setX(xVal);
                    _square.getSquare().setY(yVal);
                }
            }
        }
    }

    public void setupShape() {
        Rectangle rect1 = new Rectangle(Constants.SQUARE_WIDTH,Constants.SQUARE_WIDTH);
        Rectangle rect2 = new Rectangle(Constants.SQUARE_WIDTH,Constants.SQUARE_WIDTH);
        Rectangle rect3 = new Rectangle(Constants.SQUARE_WIDTH,Constants.SQUARE_WIDTH);
        Rectangle rect4 = new Rectangle(Constants.SQUARE_WIDTH,Constants.SQUARE_WIDTH);

        rect1.setX(6*Constants.SQUARE_WIDTH);
        rect2.setX(6*Constants.SQUARE_WIDTH);
        rect3.setX(6*Constants.SQUARE_WIDTH);
        rect4.setX(6*Constants.SQUARE_WIDTH);

        rect1.setY(1*Constants.SQUARE_WIDTH);
        rect2.setY(2*Constants.SQUARE_WIDTH);
        rect3.setY(3*Constants.SQUARE_WIDTH);
        rect4.setY(4*Constants.SQUARE_WIDTH);

        rect1.setStroke(Color.BLACK);
        rect2.setStroke(Color.BLACK);
        rect3.setStroke(Color.BLACK);
        rect4.setStroke(Color.BLACK);

        rect1.setFill(Color.ORANGE);
        rect2.setFill(Color.ORANGE);
        rect3.setFill(Color.ORANGE);
        rect4.setFill(Color.ORANGE);

        _root.getChildren().addAll(rect1,rect2,rect3,rect4);
    }
}
