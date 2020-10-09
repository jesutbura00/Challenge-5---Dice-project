package uk.ac.warwick.dcs.chess;
import java.util.ArrayList;
import java.util.Random;
import uk.ac.warwick.dcs.chess.piece.ChessPiece;

public class RandomPlayer implements Player {
    private boolean isWhite;


    Random rng = new Random(Chess.randomSeed);

    int moveNum;
    ArrayList<Double> myRandomList;

    public String getPlayerName(){
        return "RandomPlayer";
    }

    public RandomPlayer (boolean isWhite) {
        this.isWhite = isWhite;
        moveNum = 0;
        myRandomList = new ArrayList<Double>();
        createRandomList();
    }

    public void createRandomList(){
        int numDoublesToGenerate = 4;
        while(myRandomList.size() < (moveNum+numDoublesToGenerate)){
            double[] newNumbers = rng.doubles(numDoublesToGenerate).toArray();
            for(int i = 0; i < numDoublesToGenerate; i++){
                myRandomList.add(newNumbers[i]);
            }
        }
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
    public boolean getIsWhite() {
        return isWhite;
    }

    @Override
    public Move getMove(int moveNum) {
        // Move myMove = null;
        this.moveNum = moveNum;
        Board board = Chess.getBoard();
        ChessPiece[] pieces = getActivePieces();

        ArrayList<Move> moveList = new ArrayList<Move>();

        String possiblePieceMoves = "";
        for(int i = 0; i < 16; i++){
            if(pieces[i] == null){
                possiblePieceMoves += "--";
                continue;
            }
            Move[] thisPieceMoves = pieces[i].getAvailableMoves();
            possiblePieceMoves += Character.toString(pieces[i].getIcon()) + (thisPieceMoves.length);
            for(int j = 0; j < thisPieceMoves.length; j++){
                moveList.add(thisPieceMoves[j]);
            }
        }

        // Chess.writeOnScreen(1, 22, possiblePieceMoves);

        for(int i = moveList.size()-1; i >= 0;i--){
            Move tmpMove = moveList.get(i);

            int vm = board.validateMove(tmpMove);

            if((vm & Chess.CHECK_CURRENT) == Chess.CHECK_CURRENT){
                moveList.remove(i);

                if(Chess.verbosity > 0){
                    System.err.print("Non Validated move ");
                    System.err.println(tmpMove);
                }

            }else{
                if(Chess.verbosity > 0){
                    System.err.print("Validated move ");
                    System.err.println(tmpMove);
                }
            }

        }
        if(moveList.size() == 0){
            System.err.println("Error - no moves left?!!!!");
            return null;
        }

        int chosenNum = (int)(myRandomList.get(moveNum) * moveList.size());
        
        Move chosenMove = moveList.get(chosenNum);
        if(Chess.verbosity > 0){
            System.err.println(( isWhite ? "White": "Black") + " is choosing move " + chosenNum + " " + chosenMove.toString() + " out of " + moveList.size() + " possible moves");
        }
        moveNum++;
        createRandomList();
        return chosenMove;
    }
}
