package tetris;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * The Piece class is responsible for putting together a tetris piece that varies
 * its color and shape depending on the parameters passed into its constructor. Each
 * piece is comprised of four individual squares and added to a 1D array called _piece.
 */
public class Piece {

    private Square[] _piece;
    private int[][] _coords;
    public Square[][] _board;

    /**
     * The Piece constructor is responsible for constructing the 1D array that
     * will hold all of the squares in one piece.
     */
    public Piece(int[][] coords, Color color, Pane gamePane, Square[][] board) {
        _coords = coords;
        _board = board;
        _piece = new Square[4];
        this.formPiece(gamePane, color);
    }

    /**
     * This method forms four squares and stores them in a 1D array and also
     * sets their locations graphically.
     */
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

    /**
     * This method adds the piece to the board logically.
     */
    public void addToBoard() {
        for(int i=0; i<4; i++) {
            double logicXLoc = _piece[i].getX()/Constants.SQUARE_WIDTH;
            double logicYLoc = _piece[i].getY()/Constants.SQUARE_WIDTH;
            _board[(int) logicYLoc][(int) logicXLoc] = _piece[i]; //Logically adds square to board
        }
    }

    /**
     * This method is responsible for graphically moving the piece down,
     * left, or right depending on which key is pressed.
     */
    public void move(int xTranslate, int yTranslate) {//remove instance variable
        for(int i=0; i<4; i++) {
            _piece[i].setY(_piece[i].getY() + yTranslate);
            _piece[i].setX(_piece[i].getX() + xTranslate);
        }
    }

    /**
     * This method is responsible for initiating the check to see if a piece can
     * rotate. If it can, it then calls the method that actually rotates the piece.
     * This method first checks to see if the piece in question is a square. If it is,
     * then the piece will not even check to see if it can rotate. If it's not a square,
     * equations are used to determine the future locations of individual squares in
     * the piece. If the future squares are out of the board's bounds or in a spot that's
     * occupied by another square, the piece cannot rotate. The rotate counter checks to
     * see if all 4 squares in the piece can rotate, and if this is true, then the method
     * to rotate the piece is called.
     */
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

    /**
     * This method graphically rotates the piece.
     */
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

    /**
     * This method checks to see if the piece is a square.
     */
    public boolean checkIfSquare() {
        Boolean isSquare;
        if(_coords==Constants.SQUARE_PIECE_COORDS) {
            isSquare=true;
        } else {
            isSquare=false;
        }
        return isSquare;
    }

    /**
     * This method checks to see if a move is legal.
     */
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

    /**
     * This method checks to see if an individual square can rotate. If it can,
     * this method will return 'true'. If it can't, this method will return 'false'.
     */
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
