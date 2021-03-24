package tetris;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class Game {
    private BorderPane _root;
    private HBox _bottomPane;
    private Square[][] _border;

    public Game(BorderPane root, HBox bottomPane) {
        _root = root;
        _bottomPane = bottomPane;
        _border = new Square[Constants.ROWS][Constants.COLUMNS];
        this.setupBorder();
        this.setupShape();
    }

    public void setupBorder() {
        for(int i=0; i<Constants.ROWS; i++) {
            if(i==0 || i==Constants.ROWS-1) {
                for(int j=0; j<Constants.COLUMNS; j++) {
                    _border[i][j] = new Square(i, j, _root);
                }
            }
        }
        for(int j=0; j<Constants.COLUMNS; j++) {
            if(j==0 || j==Constants.COLUMNS-1) {
                for(int i=0; i<Constants.ROWS; i++) {
                    _border[i][j] = new Square(i, j, _root);
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

        rect1.setY(6*Constants.SQUARE_WIDTH);
        rect2.setY(7*Constants.SQUARE_WIDTH);
        rect3.setY(8*Constants.SQUARE_WIDTH);
        rect4.setY(9*Constants.SQUARE_WIDTH);

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
