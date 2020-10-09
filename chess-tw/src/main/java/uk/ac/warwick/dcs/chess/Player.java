package uk.ac.warwick.dcs.chess;
import uk.ac.warwick.dcs.chess.piece.ChessPiece;

public interface Player {
    /**
    * @param moveNum a number representing the turn number - if the same number is provided, it should alway return the same move
    * @return A valid Move
    */
    public Move getMove(int moveNum);
    
    /**
     * Whether the player class is playing as White or Black
     * @return
     */
    public boolean getIsWhite();

    /**
     * Return all the pieces belonging to this player - you can just copy the functionality from RandomPlayer
     */
    public ChessPiece[] getActivePieces();
    
    /**
     * @return An individual player or team name - anything you like that identifies your group/team
     */
    public String getPlayerName();
}
