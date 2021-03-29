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

    double _newXLoc;
    double _newYLoc;

    public Piece(int[][] coords, Color color, BorderPane root) { //add color as well!
        _coords = coords;
        _piece = new Square[4];
        _color = color;
        _root = root;
        this.formPiece();
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

    public void rotate() {
        double centerX = _piece[2].getX();
        double centerY = _piece[2].getY();
        for(int i=0; i<4; i++) {
            _newXLoc = centerX - centerY + _piece[i].getY();
            _newYLoc = centerY + centerX - _piece[i].getX();
            _piece[i].setX(_newXLoc);
            _piece[i].setY(_newYLoc);
        }
    }

    public boolean canMove(Square[][] board) {
        double futureYLoc

    }
}
