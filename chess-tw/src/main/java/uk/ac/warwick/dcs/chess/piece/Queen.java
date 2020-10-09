package uk.ac.warwick.dcs.chess.piece;
import uk.ac.warwick.dcs.chess.*;
public class Queen extends ChessPiece {
    private static final long serialVersionUID = 1L;
    public Queen(Board board, boolean isWhite, int vertical, int horizontal) {
        super(board, isWhite, (char)((int)ChessIcons.W_QUEEN + (isWhite ? 0 : 6)), vertical, horizontal);
    }

    @Override
    public Move[] getAvailableMoves() {
        availableMoves.clear();
        int moveH, moveV;
        int forwards = -1;
        if(!this.isWhite){
            forwards = 1;
        }

        int startRank = 1;
        if(this.isWhite)
            startRank = 6;
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

        int i = 1;
        moveV = this.vertical + i;
        moveH = this.horizontal + i;

        //forward - Right
        while(board.locationValid(moveV,moveH) && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)){
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
                i++;
                moveV += i;
                moveH += i;
            }else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
                break;
            }
        }


        i = 1;
        moveV = this.vertical - i;
        moveH = this.horizontal - i;

        //BackWard - Left
        while(board.locationValid(moveV,moveH) && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)){
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
                i++;
                moveV += i;
                moveH += i;
            }else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
                break;
            }
        }
        
        i = 1;
        moveV = this.vertical + i;
        moveH = this.horizontal - i;

        //Forward - Left
        while(board.locationValid(moveV,moveH) && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)){
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
                i++;
                moveV += i;
                moveH += i;
            }else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
                break;
            }
        }

        
        i = 1;
        moveV = this.vertical - i;
        moveH = this.horizontal + i;

        //Backward - Right
        while(board.locationValid(moveV,moveH) && (board.pieceAtLocation(moveV,moveH) == null || board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite)){
            if (board.pieceAtLocation(moveV,moveH) == null) {
                Move m = new Move(this, moveV, moveH, false);
                availableMoves.add(m);
                i++;
                moveV += i;
                moveH += i;
            }else {
                Move m = new Move(this, moveV, moveH, true);
                availableMoves.add(m);
                break;
            }
        }
        return availableMoves.movesToArray();
    }
}
