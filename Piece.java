package tetris;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Piece {

    private Square[] _piece;
    private double xVal;
    private double yVal;
    private Color _color;
    private int[][] _coords;
    public Square[][] _board;

    public Piece(int[][] coords, Color color, Pane gamePane, Square[][] board) {
        _coords = coords;
        _board = board;
        _piece = new Square[4];
        _color = color;
        this.formPiece(gamePane);
    }

    public void formPiece(Pane gamePane) {
        for(int i=0; i<4; i++) {
            Square square = new Square(gamePane, _color);
            xVal = _coords[i][0];
            square.setX(Constants.X_OFFSET + xVal);
            yVal = _coords[i][1];
            square.setY(Constants.Y_OFFSET + yVal);
            _piece[i] = square;
        }
    }

    public void addToBoard() {
        for(int i=0; i<4; i++) {
            double logicXLoc = _piece[i].getX()/Constants.SQUARE_WIDTH;
            double logicYLoc = _piece[i].getY()/Constants.SQUARE_WIDTH;
            _board[(int) logicYLoc][(int) logicXLoc] = _piece[i]; //Logically adds square to board
        }
    }

    public void move(int xTranslate, int yTranslate) {//remove instance variable
        for(int i=0; i<4; i++) {
            _piece[i].setY(_piece[i].getY() + yTranslate);
            _piece[i].setX(_piece[i].getX() + xTranslate);
        }
    }

    /**
     * The problem is that individual squares aren't next to another piece or
     * the border, so they change positions. I need the entire shape to not
     * change position if one square can't move.
     */
    public void rotate(Boolean notSquarePiece) {
        if(notSquarePiece) {
            double centerX = _piece[1].getX();
            double centerY = _piece[1].getY();
            for(int i=0; i<4; i++) {
                double newXLoc = centerX - centerY + _piece[i].getY();
                double newYLoc = centerY + centerX - _piece[i].getX();
                if(this.checkRotate((int) newYLoc, (int) newXLoc)) {
                    _piece[i].setX(newXLoc);
                    _piece[i].setY(newYLoc);
                }
            }
        }
    }

    public boolean checkMove(int potentialY, int potentialX) {
        potentialY = potentialY/Constants.SQUARE_WIDTH;
        potentialX = potentialX/Constants.SQUARE_WIDTH;
        for(int i=0; i<4; i++) {
            double futureY = _piece[i].getY()/Constants.SQUARE_WIDTH + potentialY;
            double futureX = _piece[i].getX()/Constants.SQUARE_WIDTH + potentialX;
            if(_board[(int)futureY][(int)futureX]!=null) {
                return false;
            }
        }
        return true;
    }

    public boolean checkRotate(int newYLoc, int newXLoc) {
        newYLoc = newYLoc/Constants.SQUARE_WIDTH;
        newXLoc = newXLoc/Constants.SQUARE_WIDTH;
        for(int i=0; i<4; i++) {
            if(newXLoc<1 || newXLoc>Constants.COLUMNS-1 || newYLoc>Constants.ROWS-1) {
                return false;
            } else if(_board[newYLoc][newXLoc]!=null) {
                return false;
            }
        }
        return true;
    }
}
