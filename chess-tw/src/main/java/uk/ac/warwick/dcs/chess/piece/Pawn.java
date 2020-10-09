package uk.ac.warwick.dcs.chess.piece;
import uk.ac.warwick.dcs.chess.*;
public class Pawn extends ChessPiece {
    private static final long serialVersionUID = 1L;
    public Pawn(Board board, boolean isWhite, int vertical, int horizontal) {
        super(board, isWhite, (char)((int)ChessIcons.W_PAWN + (isWhite ? 0 : 6)), vertical, horizontal);
    }

    @Override
    public Move[] getAvailableMoves() {
        availableMoves.clear();
        int forwards = -1;
        if(!this.isWhite){
            forwards = 1;
        }

        int startRank = 1;
        if(this.isWhite)
            startRank = 6;

        int moveH, moveV;
        moveV = this.vertical + forwards;
        moveH = this.horizontal;
        
        //We can move forwards if there is an empty square in front
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null) { //Empty square ahead
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }

        //If we're currently at the starting position for this pawn, we can consider 2 spaces in front
        moveV = this.vertical + forwards*2;
        if(this.vertical == startRank && board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null){
            Move m = new Move(this, moveV,moveH, false);
            availableMoves.add(m);
        }

        //We can consider the square in front and to the right but only if there is a piece of the opposing colour
        moveV = this.vertical + forwards;
        moveH = this.horizontal + 1;

        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) != null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite) {
            //Take a piece ahead
            Move m = new Move(this, moveV,moveH, true);
            availableMoves.add(m);
        }

        //We can consider the square in front and to the left but only if there is a piece of the opposing colour
        moveV = this.vertical + forwards;
        moveH = this.horizontal - 1;
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) != null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite) {
            //Take a piece ahead
            Move m = new Move(this, moveV,moveH, true);
            availableMoves.add(m);
        }
        return availableMoves.movesToArray();
    }

}
