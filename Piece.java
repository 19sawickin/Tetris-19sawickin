package tetris;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Piece {
    private Square _square;
    private Square[] _piece;
    private BorderPane _root;
    private double xVal;
    private double yVal;
    private Color _color;
    private int[][] _coords;
    private int direction;
    private int _right;
    private int _left;
    private int _down;

    public Piece(int[][] coords, Color color, BorderPane root) { //add color as well!
        _coords = coords;
        _piece = new Square[4];
        _color = color;
        _root = root;
        this.formPiece();

        _right = Constants.RIGHT;
        _left = Constants.LEFT;
        _down = Constants.DOWN;
    }



    public void formPiece() {
        for(int i=0; i<4; i++) {
            _square = new Square(_root, _color);
            xVal = _coords[i][0];
            _square.setX(Constants.X_OFFSET + xVal);
            yVal = _coords[i][1];
            _square.setY(Constants.Y_OFFSET + yVal);
            _piece[i] = _square;
        }
    }

    public void move(int xTranslate, int yTranslate) {//remove instance variable
        for(int i=0; i<4; i++) {
            _piece[i].setY(_piece[i].getY() + yTranslate);
            _piece[i].setX(_piece[i].getX() + xTranslate);
        }
    }
}
