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

        return availableMoves.movesToArray();
    }

}
