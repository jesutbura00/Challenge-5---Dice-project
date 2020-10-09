package uk.ac.warwick.dcs.chess;
import uk.ac.warwick.dcs.chess.piece.*;
import java.io.Serializable;

public class Move implements Serializable {

    private ChessPiece piece;
    private int destinationHorizontal;
    private int destinationVertical;
    private int origHorizontal;
    private int origVertical;
    private boolean takePiece;
    private static final long serialVersionUID = 1L;
    
    public Move(ChessPiece piece, int destinationVertical, int destinationHorizontal, boolean takePiece) {
        this.piece = piece;
        this.destinationHorizontal = destinationHorizontal;
        this.destinationVertical = destinationVertical;
        origHorizontal = this.piece.getHorizontal();
        origVertical = this.piece.getVertical();
        this.takePiece = takePiece;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public int getDestinationHorizontal() {
        return destinationHorizontal;
    }

    public int getDestinationVertical() {
        return destinationVertical;
    }

    public String getOriginSquare(){
        return Character.toString((char)('a' + origHorizontal)) + (8 -origVertical);
    }

    public String getDestinationSquare(){
        return Character.toString((char)('a' + destinationHorizontal)) + (8 -destinationVertical);
    }

    public boolean isTakePiece() {
        return takePiece;
    }

    @Override
    public String toString() {
        return "Move{" + this.piece.getIcon() +
                ", from=" + getOriginSquare() +
                ", to=" + getDestinationSquare() + 
                ", takePiece=" + takePiece +
                '}';
    }
}

