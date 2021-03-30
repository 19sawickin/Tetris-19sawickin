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
    public Square[][] _board;

    double _newXLoc;
    double _newYLoc;

    double _logicXLoc;
    double _logicYLoc;

    public Piece(int[][] coords, Color color, BorderPane root, Square[][] board) {
        _coords = coords;
        _board = board;
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

    public void addToBoard() {
        for(int i=0; i<4; i++) {
            _logicXLoc = _piece[i].getX()/Constants.SQUARE_WIDTH;
            _logicYLoc = _piece[i].getY()/Constants.SQUARE_WIDTH;
            _board[(int) _logicYLoc][(int) _logicXLoc] = _square; //Logically adds square to board
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
            if(this.checkMove((int)_newYLoc, (int)_newXLoc)) {
                _piece[i].setX(_newXLoc);
                _piece[i].setY(_newYLoc);
            }
        }
    }

    public boolean checkMove(int potentialY, int potentialX) {
        potentialY = potentialY/Constants.SQUARE_WIDTH;
        potentialX = potentialX/Constants.SQUARE_WIDTH;
        for(int i=0; i<4; i++) {
            double futureY = _piece[i].getY()/Constants.SQUARE_WIDTH + potentialY;
            double futureX = _piece[i].getX()/Constants.SQUARE_WIDTH + potentialX;
            //System.out.println("futureY= " + futureY + "future=" + futureX);
            if(_board[(int)futureY][(int)futureX]!=null) {
                return false;
            }
        }
        return true;
    }
}
