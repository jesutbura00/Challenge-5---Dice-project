package uk.ac.warwick.dcs.chess;

import java.util.Date;
public class BoardPlay {
    private Board board;
    private boolean whiteToPlay;
    private String createdTime;
    private Move move;

    public BoardPlay(Board board, boolean whiteToPlay,Move move){
        Date date = new Date();
        createdTime = date.toString();
        this.board = board;
        this.whiteToPlay = whiteToPlay;
        this.move = move;
    }
    public Board getBoard(){
        return board;
    }
    public boolean getWhiteToPlay(){
        return whiteToPlay;
    }

    public String getCreatedTime(){
        return createdTime;
    }

    public Move getMove(){
        return move;
    }
}
