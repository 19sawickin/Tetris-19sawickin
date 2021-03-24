package tetris;

import javafx.scene.layout.*;
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
    }

    public void setupBorder() {
        for(int i=0; i<Constants.ROWS; i++) {
            if(i==0 || i==Constants.ROWS-1){
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

}
