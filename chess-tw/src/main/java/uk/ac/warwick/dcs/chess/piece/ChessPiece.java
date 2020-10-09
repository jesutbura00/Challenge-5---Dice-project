package uk.ac.warwick.dcs.chess.piece;
import uk.ac.warwick.dcs.chess.*;
import java.io.Serializable;

public abstract class ChessPiece implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Board board;
    protected boolean isWhite;
    protected char icon;
    protected int horizontal;
    protected int vertical;

    protected MoveStore availableMoves;

    public ChessPiece(Board board, boolean isWhite, char icon,int vertical, int horizontal) {
        this.board = board;
        this.isWhite = isWhite;
        this.icon = icon;
        this.horizontal = horizontal;
        this.vertical = vertical;

        this.availableMoves = new MoveStore();

    }

    // public char getASCII(){
    //     if(icon == )
    // }

    public int getHorizontal(){return horizontal;}
    public int getVertical(){return vertical;}
    public void setHorizontal(int h){horizontal = h;}
    public void setVertical(int v){vertical = v;}
    public boolean isWhite(){return isWhite;}
    public char getIcon(){
        if(Chess.getIconMode())
            return icon;
        else
            return ChessIcons.iconToChar(icon);
    }

    public char getIcon(boolean iconMode){
        if(iconMode)
            return icon;
        else
            return ChessIcons.iconToChar(icon);
    }

    public abstract Move[] getAvailableMoves();

    public Move[] getTakingMoves() {
        MoveStore m = new MoveStore();
        Move[] moves = getAvailableMoves();
        for(int i=0;i<moves.length;i++) {
            if(moves[i].isTakePiece()) {
                m.add(moves[i]);
            }
        }

        return m.movesToArray();
    }

    public Move randomMove() {
        Move[] moves = getAvailableMoves();

        if(moves.length > 0) {
            int index = (int)(Math.random() * moves.length);
            return moves[index];
        } else {
            return null;
        }

    }

    public Move validMove(int destinationVertical, int destinationHorizontal) {
        for(Move move : getAvailableMoves()) {
            if(move.getDestinationHorizontal() == destinationHorizontal && move.getDestinationVertical() == destinationVertical) {
                return move;
            }
        }
        return null; //We didn't find the destination as a valid move;
    }

    public String getSquare(){
        return Character.toString((char)('a' + horizontal)) + (8 -vertical);
    }

    @Override
    public String toString() {
        return "{" +
                // "board=" + board +
                // ", isWhite=" + isWhite +
                icon + getSquare() +
                // ", horizontal=" + horizontal +
                // ", vertical=" + vertical +
                // ", availableMoves=" + availableMoves +
                '}';
    }
}
