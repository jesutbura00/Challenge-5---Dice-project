
package uk.ac.warwick.dcs.chess.piece;
import uk.ac.warwick.dcs.chess.*;
public class Rook extends ChessPiece {
    private static final long serialVersionUID = 1L;
    public Rook(Board board, boolean isWhite, int vertical, int horizontal) {
        super(board, isWhite, (char)((int)ChessIcons.W_ROOK + (isWhite ? 0 : 6)), vertical, horizontal);
    }

    @Override
    public Move[] getAvailableMoves() {
        availableMoves.clear();
        int forwards = -1;
        if(!this.isWhite){
            forwards = 1;
        }

        int startRank = 0;
        if(this.isWhite)
            startRank = 7;

        int moveH, moveV;

        // add forward moves 
        moveV = this.vertical + forwards;
        moveH = this.horizontal;
        while (board.locationValid(moveV,moveH) && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)) {
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
                moveV += forwards;
            } else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
                break;
            }
        }

        // add backward moves 
        moveV = this.vertical - forwards;
        moveH = this.horizontal;
        while (board.locationValid(moveV,moveH) && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)) {
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
                moveV -= forwards;
            } else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
                break;
            }
        }

        // add right moves 
        moveV = this.vertical;
        moveH = this.horizontal + 1;
        while (board.locationValid(moveV,moveH) && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)) {
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
                moveH += 1;
            } else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
                break;
            }
        }

        // add left moves
        moveV = this.vertical;
        moveH = this.horizontal - 1;
        while (board.locationValid(moveV,moveH) && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)) {
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
                moveH -= 1;
            } else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
                break;
            }
        }
        
        return availableMoves.movesToArray();
    }

}
