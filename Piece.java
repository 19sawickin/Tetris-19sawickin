package tetris;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Piece {

    private Square[] _piece;
    private int[][] _coords;
    public Square[][] _board;

    public Piece(int[][] coords, Color color, Pane gamePane, Square[][] board) {
        _coords = coords;
        _board = board;
        _piece = new Square[4];
        this.formPiece(gamePane, color);
    }

    public void formPiece(Pane gamePane, Color color) {
        for(int i=0; i<4; i++) {
            Square square = new Square(gamePane, color);
            double xVal = _coords[i][0];
            square.setX(Constants.X_OFFSET + xVal);
            double yVal = _coords[i][1];
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

    public void rotate() {
        int rotateCounter = 0;
        if(!this.checkIfSquare()) {
            double centerX = _piece[1].getX();
            double centerY = _piece[1].getY();
            for(int i=0; i<4; i++) {
                double newXLoc = centerX - centerY + _piece[i].getY();
                double newYLoc = centerY + centerX - _piece[i].getX();
                if(!this.checkRotate((int) newYLoc, (int) newXLoc)) {
                    break;
                }
                rotateCounter++;
                if(rotateCounter==4) {
                    this.rotatePiece();
                }
            }
        }
    }

    public void rotatePiece() {
        double centerX = _piece[1].getX();
        double centerY = _piece[1].getY();
        for(int i=0; i<4; i++) {
            double newXLoc = centerX - centerY + _piece[i].getY();
            double newYLoc = centerY + centerX - _piece[i].getX();
            _piece[i].setX(newXLoc);
            _piece[i].setY(newYLoc);
        }
    }

    public boolean checkIfSquare() {
        Boolean isSquare;
        if(_coords==Constants.SQUARE_PIECE_COORDS) {
            isSquare=true;
        } else {
            isSquare=false;
        }
        return isSquare;
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
        if(newXLoc<1 || newXLoc>Constants.COLUMNS-1 || newYLoc>Constants.ROWS-1) {
            return false;
        } else if(_board[newYLoc][newXLoc]!=null) {
            return false;
        }
        return true;
    }
}
