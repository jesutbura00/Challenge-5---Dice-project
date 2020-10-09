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

        int moveH, moveV;

        // add forward moves 
        moveV = this.vertical + 1;
        moveH = this.horizontal;
        if (board.locationValid(moveV,moveH) && board.testCheck(!this.isWhite) == false && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)) {
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
            } else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
            }
        }

        // add backwards moves 
        moveV = this.vertical - 1;
        moveH = this.horizontal;
        if (board.locationValid(moveV,moveH) && board.testCheck(!this.isWhite) == false && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)) {
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
            } else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
            }
        }

        // add right moves 
        moveV = this.vertical;
        moveH = this.horizontal + 1;
        if (board.locationValid(moveV,moveH) && board.testCheck(!this.isWhite) == false && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)) {
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
            } else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
            }
        }

        // add left moves 
        moveV = this.vertical;
        moveH = this.horizontal - 1;
        if (board.locationValid(moveV,moveH) && board.testCheck(!this.isWhite) == false && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)) {
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
            } else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
            }
        }

        // add diag F-R moves
        moveV = this.vertical + 1;
        moveH = this.horizontal + 1;
        if (board.locationValid(moveV,moveH) && board.testCheck(!this.isWhite) == false && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)) {
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
            } else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
            }
        }

        // add diag F-L moves
        moveV = this.vertical + 1;
        moveH = this.horizontal - 1;
        if (board.locationValid(moveV,moveH) && board.testCheck(!this.isWhite) == false && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)) {
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
            } else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
            }
        }

        // add diag B-R moves
        moveV = this.vertical - 1;
        moveH = this.horizontal + 1;
        if (board.locationValid(moveV,moveH) && board.testCheck(!this.isWhite) == false && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)) {
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
            } else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
            }
        }

        // add diag B-L moves
        moveV = this.vertical - 1;
        moveH = this.horizontal - 1;
        if (board.locationValid(moveV,moveH) && board.testCheck(!this.isWhite) == false && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)) {
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
            } else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
            }
        }
        
        return availableMoves.movesToArray();
    }
}
