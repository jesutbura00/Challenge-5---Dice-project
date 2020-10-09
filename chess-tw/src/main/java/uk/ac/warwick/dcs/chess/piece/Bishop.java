
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
        for(int i =0; i<8; i++){
            moveV = this.vertical + i;
            moveH = this.horizontal + i;
            if(!board.locationValid(moveV, moveH)){
                break;
            }
            if (board.pieceAtLocation(moveV,moveH) == null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite) { 
                Move m = new Move(this,moveV,moveH, false);
                availableMoves.add(m);
            }

            
        }

        //2
        for(int i =0; i<8; i++){
            moveV = this.vertical + i;
            moveH = this.horizontal - i;
            if(!board.locationValid(moveV, moveH)){
                break;
            }
            if (board.pieceAtLocation(moveV,moveH) == null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite) { 
                Move m = new Move(this,moveV,moveH, false);
                availableMoves.add(m);
            }

            
        }

        //3
        for(int i =0; i<8; i++){
            moveV = this.vertical - i;
            moveH = this.horizontal + i;
            if(!board.locationValid(moveV, moveH)){
                break;
            }
            if (board.pieceAtLocation(moveV,moveH) == null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite) { 
                Move m = new Move(this,moveV,moveH, false);
                availableMoves.add(m);
            }

            
        }

        //4
        for(int i =0; i<8; i++){
            moveV = this.vertical - i;
            moveH = this.horizontal - i;
            if(!board.locationValid(moveV, moveH)){
                break;
            }
            if (board.pieceAtLocation(moveV,moveH) == null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite) { 
                Move m = new Move(this,moveV,moveH, false);
                availableMoves.add(m);
            }

            
        }
        return availableMoves.movesToArray();
    }
}

