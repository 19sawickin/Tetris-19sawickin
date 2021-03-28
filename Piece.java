package tetris;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Piece {
    private Square _square;
    private BorderPane _root;
    private double xVal;
    private double yVal;
    private Color _color;
    private int[][] _coords;

    public Piece(int[][] coords, Color color, BorderPane root) { //add color as well!
        _coords = coords;
        _color = color;
        _root = root;
        this.formPiece();
    }

    public Piece generatePiece() {
        int randInt = (int)(Math.random()*7);
        Piece piece = null;
        switch(randInt) {
            case 0:
                piece = new Piece(Constants.I_PIECE_COORDS, Color.RED, _root);
                break;
            case 1:
                piece = new Piece(Constants.T_PIECE_COORDS, Color.ORANGE, _root);
                break;
            case 2:
                piece = new Piece(Constants.SQUARE_PIECE_COORDS, Color.PINK, _root);
                break;
            case 3:
                piece = new Piece(Constants.R_PIECE_COORDS, Color.YELLOW, _root);
                break;
            case 4:
                piece = new Piece(Constants.SEVEN_PIECE_COORDS, Color.GREEN, _root);
                break;
            case 5:
                piece = new Piece(Constants.BACK_Z_COORDS, Color.LIGHTBLUE, _root);
                break;
            default:
                piece = new Piece(Constants.Z_PIECE_COORDS, Color.BLUE, _root);
                break;
        }
        return piece;
    }

    public void formPiece() {
        for(int i=0; i<4; i++) {
            _square = new Square(_root, _color);
            xVal = _coords[i][0];
            _square.setX(Constants.X_OFFSET + xVal);
            yVal = _coords[i][1];
            _square.setY(Constants.Y_OFFSET + yVal);
        }
    }
}
