package uk.ac.warwick.dcs.chess;
import uk.ac.warwick.dcs.chess.piece.ChessPiece;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private Board board;
    private boolean isWhite;
    private String color;
    private ChessPiece[] pieces;

    public String getPlayerName(){
        return "HumanPlayer";
    }
    
    public HumanPlayer(Board board, boolean isWhite) {
        this.board = board;
        this.isWhite = isWhite;
        if(this.isWhite) {
            color = "White";
        } else {
            color = "Black";
        }
    }
    @Override
    public boolean getIsWhite() {
        return isWhite;
    }

    @Override
    public ChessPiece[] getActivePieces(){
        Board board = Chess.getBoard();
        ChessPiece[] myPieces = new ChessPiece[16];
        int p = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                ChessPiece cp = board.pieceAtLocation(i, j);
                if(cp != null && cp.isWhite() == isWhite){
                    myPieces[p] = cp;
                    p++;
                }
            }
        }
        return myPieces;
    }


    @Override
    public Move getMove(int moveNum) {
        Move intendedMove = null;
        Scanner s = new Scanner(System.in);

        //Something like the below can be used in a standard Scanner mode (headless), but the 
        // lanterna view will need a separate input scanner

/*        do {
            Chess.writeOnScreen(0, 1, "text");
            String inputString = s.next();

            if(inputString.length() != 4) {
                Chess.ChessOut.println("Error incorrect length input string");
                continue;
            }
            String pieceString = inputString.substring(0,2);
            String moveString = inputString.substring(2,4);

            ChessPiece piece = board.pieceAtLocation(pieceString);

            if(piece == null || piece.isWhite() != isWhite) {
                Chess.ChessOut.println("Invalid piece selected. Choose again");
                continue;
            }

            Chess.ChessOut.println("Selected piece " + piece);

            String horizontalString = moveString.substring(0,1).toLowerCase();
            String verticalString = moveString.substring(1,2);

            int horizontal = Utilities.letterToIndex(horizontalString);
            int vertical = Utilities.numberToIndex(verticalString);

            intendedMove = piece.validMove(vertical,horizontal);

            Board tmpBoard = board.cloneBoard();
            tmpBoard.applyMove(intendedMove);
            if(tmpBoard.testCheck(pieces[15].isWhite())){
                Chess.ChessOut.println("Invalid move: You are in check");
            }

        } while (intendedMove == null);*/

        // Chess.ChessOut.println("Human chose: " + intendedMove.toString());
        return intendedMove;
    }



}
