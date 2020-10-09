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

        return availableMoves.movesToArray();
    }

}
