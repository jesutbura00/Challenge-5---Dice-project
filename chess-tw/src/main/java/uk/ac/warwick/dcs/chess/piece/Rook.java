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
        
        int forwards = -1;
        if(!this.isWhite){
            forwards = 1;
        }
        int moveH, moveV;
        moveV = this.vertical;
        moveH = this.horizontal+forwards;

        //just right 
        n=1;
        while(board.locationValid(moveV,moveH){ 
            if(board.pieceAtLocation(moveV,moveH)==null){
                availabelMoves.add(new Move(this,moveV,moveH,false));
            }
            else{
                availabelMoves.add(new Move(this,moveV,moveH,true));
                break;
            }
            moveV=this.vertical;
            moveH=this.horizontal+forwards*n;
            n++;
        }
        //just left 
        n=1;
        while(board.locationValid(moveV,moveH){ 
            if(board.pieceAtLocation(moveV,moveH)==null){
                availabelMoves.add(new Move(this,moveV,moveH,false));
            }
            else{
                availabelMoves.add(new Move(this,moveV,moveH,true));
                break;
            }
            moveV=this.vertical;
            moveH=this.horizontal-forwards*n;
            n++;
        }
        //just forwards 
        n=1;
        while(board.locationValid(moveV,moveH){ 
            if(board.pieceAtLocation(moveV,moveH)==null){
                availabelMoves.add(new Move(this,moveV,moveH,false));
            }
            else{
                availabelMoves.add(new Move(this,moveV,moveH,true));
                break;
            }
            moveV=this.vertical+forwards*n;
            moveH=this.horizontal;
            n++;
        }
=======
>>>>>>> b722ba8e74b9646492a828f1d0cf2e0d9ad22c30

        //just backwards 
        n=1;
        while(board.locationValid(moveV,moveH){ 
            if(board.pieceAtLocation(moveV,moveH)==null){
                availabelMoves.add(new Move(this,moveV,moveH,false));
            }
            else{
                availabelMoves.add(new Move(this,moveV,moveH,true));
                break;
            }
            moveV=this.vertical-forwards*n;
            moveH=this.horizontal;
            n++;
        }
        return availableMoves.movesToArray();
    }

}
