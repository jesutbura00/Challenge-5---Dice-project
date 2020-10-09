package uk.ac.warwick.dcs.chess.piece;
import uk.ac.warwick.dcs.chess.*;
public class Knight extends ChessPiece {
    private static final long serialVersionUID = 1L;
    public Knight(Board board, boolean isWhite, int vertical, int horizontal) {
        super(board, isWhite, (char)((int)ChessIcons.W_KNIGHT + (isWhite ? 0 : 6)), vertical, horizontal);
    }

    @Override
    public Move[] getAvailableMoves() {
        availableMoves.clear();
        

        int startRankx = 0;
        int startRanky =1;
        if(this.isWhite)
            startRankx = 7;

        int moveH, moveV;
        moveV = this.vertical + 2;
        moveH = this.horizontal+1;
        
        
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == nul && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhitel) { 
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }

        moveV = this.vertical - 2;
        moveH = this.horizontal+1;
        
        
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite) {
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }

        moveV = this.vertical + 2;
        moveH = this.horizontal-1;
        
        
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite) { 
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }

        moveV = this.vertical - 2;
        moveH = this.horizontal-1;
        
       
        if (board.locationValid(moveV,moveH) && board.pieceAtLocation(moveV,moveH) == null && board.pieceAtLocation(moveV,moveH).isWhite != this.isWhite) { 
            Move m = new Move(this,moveV,moveH, false);
            availableMoves.add(m);
        }



        return availableMoves.movesToArray();
    }

}
