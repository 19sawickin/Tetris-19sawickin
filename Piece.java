package tetris;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Piece {
    private Square _square;
    private BorderPane _root;
    private double xVal;
    private double yVal;

    public Piece(BorderPane root) {
        _root = root;
        this.generateI();
    }

    public void generateI() {
        for(int i=0; i<4; i++) {
            _square = new Square(_root, Color.ORANGE);
            xVal = Constants.I_PIECE_COORDS[i][0];
            _square.setX(Constants.X_OFFSET + xVal);
            yVal = Constants.I_PIECE_COORDS[i][1];
            _square.setY(Constants.Y_OFFSET + yVal);
        }
    }

}
