package uk.ac.warwick.dcs.chess.piece;
import uk.ac.warwick.dcs.chess.*;
public class King extends ChessPiece {
    private static final long serialVersionUID = 1L;
    public King(Board board, boolean isWhite, int vertical, int horizontal) {
        super(board, isWhite, (char)((int)ChessIcons.W_KING + (isWhite ? 0 : 6)), vertical, horizontal);
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
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null && !(board.testCheckMate(this.isWhite))) { //Empty square ahead
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }

        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) != null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite && !(board.testCheckMate(this.isWhite))) {
            //Take a piece ahead
            Move m = new Move(this, moveV,moveH, true);
            availableMoves.add(m);
        }

        moveV = this.vertical - forwards;
        moveH = this.horizontal;
        
        //We can move forwards if there is an empty square in front
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null && !(board.testCheckMate(this.isWhite))) { //Empty square ahead
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) != null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite && !(board.testCheckMate(this.isWhite))) {
            //Take a piece ahead
            Move m = new Move(this, moveV,moveH, true);
            availableMoves.add(m);
        }

        moveV = this.vertical;
        moveH = this.horizontal + 1;
        
        //We can move forwards if there is an empty square in front
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null && !(board.testCheckMate(this.isWhite))) { //Empty square ahead
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) != null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite && !(board.testCheckMate(this.isWhite))) {
            //Take a piece ahead
            Move m = new Move(this, moveV,moveH, true);
            availableMoves.add(m);
        }

        moveV = this.vertical;
        moveH = this.horizontal - 1;
        
        //We can move forwards if there is an empty square in front
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null && !(board.testCheckMate(this.isWhite))) { //Empty square ahead
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) != null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite && !(board.testCheckMate(this.isWhite))) {
            //Take a piece ahead
            Move m = new Move(this, moveV,moveH, true);
            availableMoves.add(m);
        }

        moveV = this.vertical + 1;
        moveH = this.horizontal + 1;
        
        //We can move forwards if there is an empty square in front
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null && !(board.testCheckMate(this.isWhite))) { //Empty square ahead
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) != null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite && !(board.testCheckMate(this.isWhite))) {
            //Take a piece ahead
            Move m = new Move(this, moveV,moveH, true);
            availableMoves.add(m);
        }

        moveV = this.vertical - 1;
        moveH = this.horizontal - 1;
        
        //We can move forwards if there is an empty square in front
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null && !(board.testCheckMate(this.isWhite))) { //Empty square ahead
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) != null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite && !(board.testCheckMate(this.isWhite))) {
            //Take a piece ahead
            Move m = new Move(this, moveV,moveH, true);
            availableMoves.add(m);
        }
        
        moveV = this.vertical + 1;
        moveH = this.horizontal - 1;
        
        //We can move forwards if there is an empty square in front
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null && !(board.testCheckMate(this.isWhite))) { //Empty square ahead
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) != null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite && !(board.testCheckMate(this.isWhite))) {
            //Take a piece ahead
            Move m = new Move(this, moveV,moveH, true);
            availableMoves.add(m);
        }

        moveV = this.vertical - 1;
        moveH = this.horizontal + 1;
        
        //We can move forwards if there is an empty square in front
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null && !(board.testCheckMate(this.isWhite))) { //Empty square ahead
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) != null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite && !(board.testCheckMate(this.isWhite))) {
            //Take a piece ahead
            Move m = new Move(this, moveV,moveH, true);
            availableMoves.add(m);
        }
        return availableMoves.movesToArray();
    }
}
