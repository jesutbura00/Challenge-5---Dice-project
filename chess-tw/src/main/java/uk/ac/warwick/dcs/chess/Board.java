package uk.ac.warwick.dcs.chess;
import uk.ac.warwick.dcs.chess.piece.*;

import java.io.*;
import java.util.Base64;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {
    private static final long serialVersionUID = 1L;
    private ChessPiece[][] board;
    private char blackSquare = Character.toChars(0x25A0)[0];
    private char whiteSquare = Character.toChars(0x25A1)[0];
    private static final char BOARD_NOSETUP = 'n';
    private boolean debugMoves;
    private ArrayList<Move> history;
    

    public Board() {
        initBoard();
        setupBoard();
    }

    public Board(char m) {
        initBoard();
        if(m != BOARD_NOSETUP){
            setupBoard();
        }
    }

    public void initBoard(){
        this.board = new ChessPiece[8][8];
        history = new ArrayList<Move>();
    }

    public Board(List<String> contentsList){
        initBoard();
        for(int i = 0; i < 8;i++){
            for(int j = 0; j < 8; j++){
                ChessPiece cp = ChessIcons.charToPiece(this,contentsList.get(i).charAt(j), i, j);
                if(cp != null){
                    addPiece(cp);
                }
            }
        }
    }

    public int validateMove(Move move){
        Board tmpBoard = cloneBoard();

        if(move.isTakePiece()) {
            //Remove piece from board.
            tmpBoard.removePiece(move.getDestinationVertical(),move.getDestinationHorizontal());
        }

        tmpBoard.applyMove(move);

        int checkResult = Chess.OK;


        if(tmpBoard.testCheck(!move.getPiece().isWhite())) {
            checkResult = checkResult | Chess.CHECK_CURRENT;
        }

        if(tmpBoard.testCheck(move.getPiece().isWhite())) {
            checkResult = checkResult | Chess.CHECK_OPPOSE;
        }
        return checkResult;
    }

    public int getScore(boolean isWhite){
        int score = 0;
        ChessPiece cp;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                cp = pieceAtLocation(i, j);
                if(cp != null && cp.isWhite() == isWhite){
                    score++;
                }
            }
        } 
        return score;
    }

    public void setupBoard(){
        //Create white pieces first
        for(int i=0; i<8; i++) {
            addPiece(new Pawn(this, true, 6, i));
        }

        addPiece(new Rook(this,true,7,0));
        addPiece(new Knight(this,true, 7,1));
        addPiece(new Bishop(this, true,7,2));
        addPiece(new Queen(this, true,7,3));
        addPiece(new King(this,true,7,4));
        addPiece(new Bishop(this,true,7,5));
        addPiece(new Knight(this,true, 7,6));
        addPiece(new Rook(this,true,7,7));

        for(int i=0; i<8; i++) {
            addPiece(new Pawn(this,false,1,i));
        }
        addPiece(new Rook(this,false,0,0));
        addPiece(new Knight(this,false, 0,1));
        addPiece(new Bishop(this, false,0,2));
        addPiece(new Queen(this, false, 0,3));
        addPiece(new King(this,false,0,4));
        addPiece(new Bishop(this,false,0,5));
        addPiece(new Knight(this,false,0,6));
        addPiece(new Rook(this,false,0,7));
    }

    public ChessPiece pieceAtLocation(int vertical, int horizontal) {
        return board[vertical][horizontal];
    }

    public boolean testWhiteInCheck(){
        return testCheck(false);
    }

    public boolean testBlackInCheck(){
        return testCheck(true);
    }

    /**
     * Tests whether the opponent is in check.
     * 
     * @param currentIsWhite - True if current player is white
     * @return true if opponent king (i.e., the opposite of currentIsWhite) is in check
     */
    public boolean testCheck(boolean currentIsWhite) {
        ChessPiece[] currentPlayerPieces = new ChessPiece[16];
        int currentPlayerPiecesNum = 0;
        ChessPiece opponentKing = null;
        
        //Go through every square
        //i is row, j is col
        //8-i is the number, j is the letter, so 0 7 is top right corner

        //For each row
        for(int i = 0; i < 8; i++){
            //For each column
            for(int j = 0; j < 8; j++){
                //If the current square is occupied by a piece belonging to the current player
                if(board[i][j] != null && (board[i][j].isWhite()) == currentIsWhite){
                    currentPlayerPieces[currentPlayerPiecesNum] = board[i][j];
                    currentPlayerPiecesNum++;
                }
                if(board[i][j] != null && (board[i][j].isWhite() != currentIsWhite) && (board[i][j] instanceof King)){
                    //We've found the opponent's King
                    opponentKing = board[i][j];
                }
            }
        }

        //There's a problem if it gets here and there's no opponent King
        if(opponentKing == null){
            Chess.writeOnScreen(1, 1, "Error: Can't find opponent King");
            return false;
        }

        for(int i=0;i<currentPlayerPieces.length;i++) {
            //Go through each of the current player's pieces
            ChessPiece cp = currentPlayerPieces[i];
            if(cp == null) {
                continue;
            } else {
                //For every move the current player can make, if they could take the opponent's King, the opponent is in check
                Move[] moves = cp.getAvailableMoves();
                for(int j=0;j<moves.length;j++) {
                    if(moves[j].getDestinationHorizontal() == opponentKing.getHorizontal() && moves[j].getDestinationVertical() == opponentKing.getVertical()) {
                        if(Chess.verbosity > 0){
                              System.err.println("For the currently evaluated move " + opponentKing.toString() +" would be in check with " + moves[j].getPiece().toString());
                        }
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean testCheckMate(boolean currentIsWhite){
        boolean currentCheck = true;

        currentCheck = testCheck(currentIsWhite);
        if(currentCheck == false){
            return false;
        }

        ChessPiece[] opponentPieces = new ChessPiece[16];
        int opponentPiecesNum = 0;
        
        //Go through every square
        //i is row, j is col
        //8-i is the number, j is the letter, so 0 7 is top right corner

        //For each row
        for(int i = 0; i < 8; i++){
            //For each column
            for(int j = 0; j < 8; j++){
                //If the current square is occupied by a piece belonging to the opponent
                if(board[i][j] != null && (board[i][j].isWhite()) != currentIsWhite){
                    opponentPieces[opponentPiecesNum] = board[i][j];
                    opponentPiecesNum++;
                }
            }
        }

        for(int i=0;i<opponentPieces.length;i++) {
            //Go through each of the current player's pieces
            ChessPiece cp = opponentPieces[i];
            if(cp == null) {
                continue;
            } else {
                //For every move the current player can make, if they could take the opponent's King, the opponent is in check
                Move[] moves = cp.getAvailableMoves();
                for(int j=0;j<moves.length;j++) {
                    Board tmpBoard = cloneBoard();
                    tmpBoard.applyMove(moves[j]);
                    boolean thisCheck = tmpBoard.testCheck(currentIsWhite);
                    if(!thisCheck)
                        return false;
                }
            }
        }
        return true;
    }

    public boolean locationValid(int vertical, int horizontal) {
        if(horizontal < 0 || vertical < 0 || horizontal > 7 || vertical > 7) {
            return false;
        }
        return true;
    }

    public void setDebugMoves(boolean debugMoves) {
        this.debugMoves = debugMoves;
    }

    public ChessPiece pieceAtLocation(String coordinate) {
        String horizontalString = coordinate.substring(0,1).toLowerCase();
        String verticalString = coordinate.substring(1,2);

        int horizontal = Utilities.letterToIndex(horizontalString);
        int vertical = Utilities.numberToIndex(verticalString);

        return board[vertical][horizontal];
    }

    public void applyMove(Move move) {
        ChessPiece piece = board[move.getPiece().getVertical()][move.getPiece().getHorizontal()];
    
        board[piece.getVertical()][piece.getHorizontal()] = null;

        piece.setHorizontal(move.getDestinationHorizontal());
        piece.setVertical(move.getDestinationVertical());

        if(piece instanceof Pawn){
            if(move.getDestinationVertical() == 0 || move.getDestinationVertical() == 7){
                piece = new Queen(this,piece.isWhite(),move.getDestinationVertical(),move.getDestinationHorizontal());
            }
        }
        board[move.getDestinationVertical()][move.getDestinationHorizontal()] = piece;
        history.add(move);

    }

    public String getHistory(){
        String histString = "";
        for(int i = 0; i < history.size();i++)
            histString += "," + history.get(i).getOriginSquare() + history.get(i).getDestinationSquare();
        return histString;
    }

    public void addPiece(ChessPiece piece) {
        if(piece != null)
            board[piece.getVertical()][piece.getHorizontal()] = piece;
    }

    public void removePiece(int vertical, int horizontal) {
        board[vertical][horizontal] = null;
    }

    public void drawBoard(){
        if(Chess.ChessOut != null)
            Chess.ChessOut.println(toString());
        else
            System.out.println(toString());
    }

    @Override
    public String toString() {
        return mainToString(true,Chess.getIconMode());
    }

    public String mainToString(boolean showCoords,boolean iconMode){
        String res = "";
        if(showCoords)
            res = res + "   abcdefgh   \n";
        int[][] moves = new int[8][8];
        if(debugMoves) {
            for(int i=0;i<8;i++) {
                for(int j=0;j<8;j++) {
                    if(pieceAtLocation(i,j)!=null) {
                        Move[] available_moves = pieceAtLocation(i,j).getAvailableMoves();
                        for(int m=0;m<available_moves.length;m++) {
                            moves[available_moves[m].getDestinationVertical()][available_moves[m].getDestinationHorizontal()] = 1;
                        }
                    }
                }
            }
        }

        for(int i=0;i<board.length;i++) {

            //We need to reverse indices, so subtract i from 8 to make this work in the correct way.
            int current_row = 8-i;

            if(showCoords)
                res = res + (current_row)+"  ";

            for(int j=0;j<board.length;j++) {
                if (board[i][j] != null) {
                    res = res + board[i][j].getIcon(iconMode);
                } else if(moves[i][j] == 1) {
                    res = res + "X";
                } else {
                    if (i % 2 == 0 && j % 2 == 1 || i % 2 == 1 && j % 2 == 0) {
                        if(iconMode)
                            res = res + blackSquare;
                        else
                            res = res + "-";
                    } else {
                        if(iconMode)
                            res = res + whiteSquare;
                        else
                            res = res + ".";
                    }
                }
            }

            if(showCoords)
                res = res + "  " + current_row;

            res = res + "\n";
        }
        if(showCoords)
            res = res + "   abcdefgh   ";
        return res;
    }

    public String toSaveString(boolean iconMode) {
        return mainToString(false,iconMode);
    }

    public String toSaveString() {
        return mainToString(false,false);
    }

    public Board cloneBoard() {
        Board b =null;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            bos.close();
            byte[] byteData = bos.toByteArray();

            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            b = (Board)new ObjectInputStream(bais).readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return b;
    }

    public String serialize() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        byte[] byteData = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            bos.close();
            byteData = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(byteData);
    }

    public static void main(String[] args) {
        Board board = new Board(BOARD_NOSETUP);
        board.setDebugMoves(true);

        //Add your piece below
        ChessPiece piece = new King(board,true,3,5);
        ChessPiece piece2 = new King(board,false,1,6);

        board.addPiece(piece);
        board.addPiece(piece2);
        System.out.println(board.testCheck(true));
        System.out.println(board.testCheck(false));
        board.drawBoard();
    }


}
