package uk.ac.warwick.dcs.chess.piece;
import uk.ac.warwick.dcs.chess.*;
public class TestPiece extends ChessPiece {
    private static final long serialVersionUID = 1L;
    public TestPiece(Board board, boolean isWhite, int vertical, int horizontal) {
        super(board, isWhite, (char)((int)ChessIcons.W_PAWN + (isWhite ? 0 : 6)), vertical, horizontal);
    }

    @Override
    public Move[] getAvailableMoves() {
        availableMoves.clear();

        if(this.isWhite) {

            //Move upwards if going upwards is valid
            if(board.locationValid(this.vertical-1, this.horizontal)) {
                ChessPiece pieceAtIntendedLocation = board.pieceAtLocation(this.vertical-1,this.horizontal);
                if(pieceAtIntendedLocation == null) { //The intended locaion is empty
                    Move m = new Move(this,this.vertical-1,this.horizontal,false);
                    availableMoves.add(m);
                } else { //We have a peice at the intended lcoation
                    if(pieceAtIntendedLocation.isWhite != this.isWhite) { //The piece is an opponenets piece
                        Move m = new Move(this, this.vertical-1,this.horizontal,true);
                        availableMoves.add(m);
                    }
                }
            }

            //Move upwards left if it is valid
            if(board.locationValid(this.vertical-1, this.horizontal-1)) {
                ChessPiece pieceAtIntendedLocation = board.pieceAtLocation(this.vertical-1,this.horizontal-1);
                if(pieceAtIntendedLocation == null) { //The intended locaion is empty
                    Move m = new Move(this,this.vertical-1,this.horizontal-1,false);
                    availableMoves.add(m);
                } else { //We have a peice at the intended lcoation
                    if(pieceAtIntendedLocation.isWhite != this.isWhite) { //The piece is an opponenets piece
                        Move m = new Move(this, this.vertical-1,this.horizontal-1,true);
                        availableMoves.add(m);
                    }
                }
            }

            //Move upwards right if it is valid
            if(board.locationValid(this.vertical-1, this.horizontal+1)) {
                ChessPiece pieceAtIntendedLocation = board.pieceAtLocation(this.vertical-1,this.horizontal+1);
                if(pieceAtIntendedLocation == null) { //The intended locaion is empty
                    Move m = new Move(this,this.vertical-1,this.horizontal+1,false);
                    availableMoves.add(m);
                } else { //We have a peice at the intended lcoation
                    if(pieceAtIntendedLocation.isWhite != this.isWhite) { //The piece is an opponenets piece
                        Move m = new Move(this, this.vertical-1,this.horizontal+1,true);
                        availableMoves.add(m);
                    }
                }
            }



        }else {
            //Move downwards if going downwards is valid
            if(board.locationValid(this.vertical+1, this.horizontal)) {
                ChessPiece pieceAtIntendedLocation = board.pieceAtLocation(this.vertical-1,this.horizontal);
                if(pieceAtIntendedLocation == null) { //The intended locaion is empty
                    Move m = new Move(this,this.vertical+1,this.horizontal,false);
                    availableMoves.add(m);
                } else { //We have a peice at the intended lcoation
                    if(pieceAtIntendedLocation.isWhite != this.isWhite) { //The piece is an opponenets piece
                        Move m = new Move(this, this.vertical+1,this.horizontal,true);
                        availableMoves.add(m);
                    }
                }
            }

            //Move downwards left if it is valid
            if(board.locationValid(this.vertical+1, this.horizontal-1)) {
                ChessPiece pieceAtIntendedLocation = board.pieceAtLocation(this.vertical+1,this.horizontal-1);
                if(pieceAtIntendedLocation == null) { //The intended locaion is empty
                    Move m = new Move(this,this.vertical+1,this.horizontal-1,false);
                    availableMoves.add(m);
                } else { //We have a peice at the intended lcoation
                    if(pieceAtIntendedLocation.isWhite != this.isWhite) { //The piece is an opponenets piece
                        Move m = new Move(this, this.vertical+1,this.horizontal-1,true);
                        availableMoves.add(m);
                    }
                }
            }

            //Move downwards right if it is valid
            if(board.locationValid(this.vertical+1, this.horizontal+1)) {
                ChessPiece pieceAtIntendedLocation = board.pieceAtLocation(this.vertical+1,this.horizontal+1);
                if(pieceAtIntendedLocation == null) { //The intended locaion is empty
                    Move m = new Move(this,this.vertical+1,this.horizontal+1,false);
                    availableMoves.add(m);
                } else { //We have a peice at the intended lcoation
                    if(pieceAtIntendedLocation.isWhite != this.isWhite) { //The piece is an opponenets piece
                        Move m = new Move(this, this.vertical+1,this.horizontal+1,true);
                        availableMoves.add(m);
                    }
                }
            }
        }

        return availableMoves.movesToArray();
    }
}