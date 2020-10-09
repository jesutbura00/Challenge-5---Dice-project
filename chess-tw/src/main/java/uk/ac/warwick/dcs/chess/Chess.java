package uk.ac.warwick.dcs.chess;
import uk.ac.warwick.dcs.chess.piece.*;
import java.util.Scanner;

import java.io.PrintStream;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import java.nio.file.*;

import java.util.Stack;

import java.io.IOException;

public class Chess {
    private static Board board;
    private static Stack<BoardPlay> boardPlayStack;

    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private static String loadfile;
    public static long randomSeed;
    private boolean gameFinished = false;
    private static boolean draw2Boards = false;

    public static PrintStream ChessOut;
    public static final int CHECK_CURRENT = 1;
    public static final int CHECK_OPPOSE = 2;
    public static final int CHECKMATE_CURRENT = 4;
    public static final int CHECKMATE_OPPOSE = 8;
    public static final int OK = 0;
    public static final int INVALID = -3;
    public static int verbosity = 0;
    public static boolean headless = true;
    private int m;

    private static boolean iconMode = true;

    public static boolean getIconMode(){
        return iconMode;
    }

    public static void setIconMode(boolean iconMode){
        Chess.iconMode = iconMode;
    }

    Terminal terminal;
    static Screen screen;

    private static TextGraphics tg;
    Scanner scan = new Scanner(System.in);

    public static Board getBoard(){
        return board;
    }

    public Chess(){
        whitePlayer = new RandomPlayer(true);
        blackPlayer = new RandomPlayer(false);
        Chess.board = new Board();
        setupGame(false);
    }

    public Chess(Board board, Player whitePlayer, Player blackPlayer) {
        Chess.board = board;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        setupGame(false);
    }

    public Chess(Board board, Player whitePlayer, Player blackPlayer, boolean blackToStart) {
        Chess.board = board;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        setupGame(blackToStart);
    }

    public static void writeOnScreen(int col, int row, String text){
        if(!headless){
            tg.putString(col,row,text);
            Chess.refreshScreen();
        }else{
            System.out.println(text);
        }
    }

    public void setupGame(){
        setupGame(false);
    }

    public void setupGame(boolean blackToStart) {
        
        boardPlayStack = new Stack<BoardPlay>();        
        
        boardPlayStack.push(new BoardPlay(board.cloneBoard(),false,null));

        try{
            if(!headless){
                terminal = new DefaultTerminalFactory().createTerminal();
                screen = new TerminalScreen(terminal);
                tg = screen.newTextGraphics();
                screen.startScreen();
                tg.setForegroundColor(new TextColor.RGB(0,0,0));
                tg.setBackgroundColor(new TextColor.RGB(255,255,255));
                tg.putString(5,1, "Menu: [n]ext, [p]rev, [s]ave, [q]uit");
            }

            if(blackToStart){
                this.currentPlayer = blackPlayer;
            }else{
                this.currentPlayer = whitePlayer;
            }
            drawLastTwoBoards();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public int playMove(Move move){
        if(move.isTakePiece()) {
            //Remove piece from board.
            board.removePiece(move.getDestinationVertical(),move.getDestinationHorizontal());
        }
        // Chess.ChessOut.println("Applying move " + move);
        // board.drawBoard();
        board.applyMove(move);
        // Chess.ChessOut.println("Board is now:");
        board.drawBoard();
        boardPlayStack.push(new BoardPlay(board.cloneBoard(),currentPlayer.getIsWhite(),move));
        if(board.testCheck(currentPlayer.getIsWhite())) {
            writeOnScreen(1, 4, (!(currentPlayer.getIsWhite()) ? "White" : "Black") + " is in Check!");
            if(board.testCheckMate(currentPlayer.getIsWhite()))
                return CHECKMATE_OPPOSE;
            return CHECK_OPPOSE;
        }else{
            writeOnScreen(1, 4, "                  ");
        }
        return OK;
    }

    public void switchPlayers(){
        if(currentPlayer.getIsWhite()){
            currentPlayer = blackPlayer;
        }else{
            currentPlayer = whitePlayer;
        }
    }

    public void makeMove(){
        Move move;

        int currentMove = (int)(boardPlayStack.size()/2);
        if(currentPlayer == whitePlayer) {
            move = whitePlayer.getMove(currentMove);
        } else {
            move = blackPlayer.getMove(currentMove);
        }

        if(move == null){
            if(board.testCheck(!currentPlayer.getIsWhite())){
                writeOnScreen(1, 4, "CHECKMATE - " + ((!currentPlayer.getIsWhite()) ? "White" : "Black") + "wins! Press Ctrl+c to exit");
            }else{
                writeOnScreen(1, 4, "STALEMATE it's a draw! Press Ctrl+c to exit");
            }
            gameFinished = true;
            return;
        }

        if(headless){
            System.out.println(move.toString());
        }

        int tM = board.validateMove(move);

        if((tM & Chess.CHECK_CURRENT) == Chess.CHECK_CURRENT){
            writeOnScreen(1, 3, "Check: Invalid move");
        }else{
            int res = playMove(move);
            //I think checkMateCurrent is unlikely (impossible)- it would have to move into check!
            boolean checkMateCurrent = ((res & CHECKMATE_CURRENT) == CHECKMATE_CURRENT);
            boolean checkMateOppose = ((res & CHECKMATE_CURRENT) == CHECKMATE_OPPOSE);
            if(checkMateCurrent || checkMateOppose){
                writeOnScreen(1, 4, "CHECKMATE" + (currentPlayer.getIsWhite() ? "White" : "Black") + " wins! Press Ctrl+c to exit");
                gameFinished = true;
                return;
            }
            switchPlayers();
        }

    }
    
    public void playGame() {
        m = 0;
        while(!headless || (!gameFinished && headless)){
            doMenu();
        }
    }

    public static String padStringRight(String m, int l){
        int d = l - m.length();
        while(d > 0){
            m = m + " ";
            d--;
        }
        return m;
    }

    public void previousMove(){
        if(boardPlayStack.size()>1){
            m--;
            writeOnScreen(1, 3, padStringRight(Integer.toString(m),5));
            boardPlayStack.pop();
            BoardPlay bp = boardPlayStack.peek();
            board = bp.getBoard().cloneBoard();

            if(board.testCheck(currentPlayer.getIsWhite())) {
                writeOnScreen(1, 4, (!(currentPlayer.getIsWhite()) ? "White" : "Black") + " is in Check!");
            }else{
                writeOnScreen(1, 4, "                  ");
            }
            // Chess.ChessOut.println("White to play is " + bp.getWhiteToPlay());
            switchPlayers();
        }
        drawLastTwoBoards();
    }
    
    public void doMenu(){

        if(headless){
            runMoves(1);
            return;
        }

        KeyStroke keyStroke = null;
        try{
            
            keyStroke = terminal.readInput();
            Character c = keyStroke.getCharacter();

            if(c != null && c.charValue() == 'p'){
                previousMove();
            }
            if(c != null && c.charValue() == 'n'){
                int limit = 1;
                runMoves(limit);
            }
            if(c != null && c.charValue() == 'q'){
                gameFinished = true;
                return;
            }

            if(c != null && c.charValue() == 's'){
                saveBoard();
            }


        }catch(IOException e ){
            e.printStackTrace();
        }

    }

    public void runMoves(int limit){
        int it = 0;
        while(it < limit && !gameFinished){
            m++;
            writeOnScreen(1, 3, Integer.toString(m));
            if(m >= 800){
                writeOnScreen(1, 3, "Stalemate - no winner after 800 moves");
                gameFinished = true;
            }
            makeMove();
            drawLastTwoBoards();
            it++;
        }
    }

    public void saveBoard(){
        try{
            Path path = Paths.get("savedFile.txt");
            Files.write(path,(board.toSaveString()+(currentPlayer.getIsWhite() ? "White":"Black")+"\n").getBytes());
        }catch(IOException e){
            System.err.println(e.getStackTrace());
        }
    }

    public static void refreshScreen(){
        try{
            screen.refresh();

        }catch(Exception e){
            
        }
    }


    public void drawLastTwoBoards(){
        if(headless){
            BoardPlay current = boardPlayStack.peek();
            String[] boardStrings;
            boardStrings = current.getBoard().toString().split("\\r?\\n");
            for(int i = 0; i < boardStrings.length;i++){
                System.out.println(boardStrings[i]);
            }
        }else{
            BoardPlay current = boardPlayStack.peek();
            BoardPlay previous = null;
            String[] boardStrings;
            if(boardPlayStack.size() > 1 && Chess.draw2Boards){
                previous = boardPlayStack.get(boardPlayStack.size() - 2);
                boardStrings = previous.getBoard().toString().split("\\r?\\n");
                String turnString = "" + (boardPlayStack.size() - 1);
                turnString = "Before";
                tg.putString(1,17,turnString);

                // System.err.println("Showing move: " + current.getMove().toString());
                String scoreString = "W:" + previous.getBoard().getScore(true) + ", B:" + previous.getBoard().getScore(false);
                tg.putString(1,19,padStringRight(scoreString,20));
                for(int i = 0; i < boardStrings.length;i++){
                    tg.putString(1,6+i, boardStrings[i]);
                }
            }
            String turnString = "";
            // turnString = (current.getWhiteToPlay() ? "W":"B") + "'s move next";
            // System.err.println(current.getCreatedTime() + current.getBoard().toString());
            boardStrings = current.getBoard().toString().split("\\r?\\n");
            tg.putString(18,17,turnString);
            String scoreString = "W:" + current.getBoard().getScore(true) + ", B:" + current.getBoard().getScore(false);
            tg.putString(18,19,padStringRight(scoreString,20));

            if(current.getMove() != null){
                String s = current.getMove().toString();
                int origLength = current.getMove().toString().length();
                while(s.length() < origLength + 5){
                    s = s + " ";
                }
                tg.putString(18,5,s);
            }
            for(int i = 0; i < boardStrings.length;i++){
                tg.putString(18,6+i, boardStrings[i]);
            }
            String historyString = current.getBoard().getHistory();
            while(historyString.length() < 80){
                historyString = historyString + " ";
            }
            tg.putString(18,16,historyString);
            refreshScreen();
        }
    }

    public static void main(String[] args) {
        Board board;
        Chess.headless = false;
        Chess.iconMode = true;
        Chess.loadfile = null;
        Chess.randomSeed = 20312324242l;
        for(int i = 0; i < args.length;i++){
            switch(args[i]){
                case "--headless":
                    Chess.headless = true;
                break;
                case "--verbosity":
                    i = i + 1;
                    try{
                        Chess.verbosity = Integer.parseInt(args[i]);
                        // System.err.println("verbosity is " + Chess.verbosity);
                    }catch(Exception e){
                        System.out.println("--verbosity must be a followed by a number");
                        return;
                    }
                    break;
                case "--noicons":
                    Chess.iconMode = false;
                    break;
                case "--random":
                    i = i + 1;
                    try{
                        Chess.randomSeed = Long.parseLong(args[i]);
                        // System.err.println("verbosity is " + Chess.verbosity);
                    }catch(Exception e){
                        System.out.println("--random must be a followed by a number");
                        return;
                    }
                    break;
                case "--load":
                    i = i + 1;
                    loadfile = args[i];
                    break;
                case "--drawprevious":
                    Chess.draw2Boards = true;
                    break;
            }
        }

        boolean blackToStart = false;
        if(loadfile != null){
            try{
                LoadedBoard lb = loadBoard(loadfile);                
                board = lb.board;
                blackToStart = lb.blackToStart;
            }catch(IOException e){
                board = new Board();
                System.err.println("IOException");
            }
        }else{
            board = new Board();
        }
        Chess c = new Chess(board,new RandomPlayer(true), new RandomPlayer(false),blackToStart);
        // c.setupGame();

        try{
            Chess.ChessOut = new PrintStream("output.txt");
        }catch(Exception e){

        }

        //System.err.println("Need to loop through and println of every combination of moves for the king");
        c.playGame();
    }

    public static LoadedBoard loadBoard(String loadfile) throws IOException{
        try{
            Path path = Paths.get(loadfile);
            java.util.List<String> loadList = Files.readAllLines(path);
            String lastLine = loadList.get(loadList.size()-1);
            boolean blackStart = false;
            if(lastLine.toLowerCase().equals("black")){
                blackStart = true;
            }
            return new LoadedBoard(new Board(loadList),blackStart);
        }catch(IOException e){
            throw e;
        }
    }

    static class LoadedBoard{
        public Board board;
        public boolean blackToStart;
        public LoadedBoard(Board board, boolean blackToStart){
            this.board = board;
            this.blackToStart = blackToStart;
        }
    }

}
