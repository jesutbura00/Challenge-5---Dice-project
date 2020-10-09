
package uk.ac.warwick.dcs.chess.piece;
import uk.ac.warwick.dcs.chess.*;
public class Bishop extends ChessPiece {

    private static final long serialVersionUID = 1L;
    public Bishop(Board board, boolean isWhite, int vertical, int horizontal) {
        super(board, isWhite, (char)((int)ChessIcons.W_BISHOP + (isWhite ? 0 : 6)), vertical, horizontal);
    }

    @Override
    public Move[] getAvailableMoves() {
        availableMoves.clear();
        int moveH,moveV;
        
        int i = 1;
        moveV = this.vertical + i;
        moveH = this.horizontal + i;

        //forward
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

        //forward
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

        //forward
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

        //forward
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
