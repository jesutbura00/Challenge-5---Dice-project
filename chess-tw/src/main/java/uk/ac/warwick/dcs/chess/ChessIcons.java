package uk.ac.warwick.dcs.chess;

import uk.ac.warwick.dcs.chess.piece.*;

public class ChessIcons {

    //White Pieces
    public static char W_KING = Character.toChars(0x2654)[0];
    public static char W_QUEEN = Character.toChars(0x2655)[0];
    public static char W_ROOK = Character.toChars(0x2656)[0];
    public static char W_BISHOP = Character.toChars(0x2657)[0];
    public static char W_KNIGHT = Character.toChars(0x2658)[0];
    public static char W_PAWN = Character.toChars(0x2659)[0];


    //Black Pieces
    public static char B_KING = Character.toChars(0x265A)[0];
    public static char B_QUEEN = Character.toChars(0x265B)[0];
    public static char B_ROOK = Character.toChars(0x265C)[0];
    public static char B_BISHOP = Character.toChars(0x265D)[0];
    public static char B_KNIGHT = Character.toChars(0x265E)[0];
    public static char B_PAWN = Character.toChars(0x265F)[0];

    public static char KING_CHAR = 'K';
    public static char QUEEN_CHAR = 'Q';
    public static char ROOK_CHAR = 'R';
    public static char BISHOP_CHAR = 'B';
    public static char KNIGHT_CHAR = 'N';
    public static char PAWN_CHAR = 'P';

    public static char iconToChar(char icon){
        char[] convArray = {KING_CHAR,QUEEN_CHAR,ROOK_CHAR,BISHOP_CHAR,KNIGHT_CHAR,PAWN_CHAR};
        //If it's >= 0x265A, it must be black
        if(icon >= 0x265A){
            char c = convArray[((int)icon - 0x265A)];
            return (char)((int)c + (97 - 65));
        }else{
            char c = convArray[((int)icon - 0x2654)];
            return c;
        }
    }

    public static ChessPiece charToPiece(Board board, char icon,int vertical,int horizontal){
        char[] convArray = {'k','q','r','b','n','p'};
        char c = icon;
        boolean isWhite = true;
        if((int)c >= 0x2654){
            if((int) c >= 0x265A){
                isWhite = false;
                c = convArray[((int)c - 0x265A)];
            }else{
                c = convArray[((int)c - 0x2654)];
            }
        }
        if((int)c > 97){
            isWhite = false;
            c = (char)((int)c - (97 - 65));
        }
        switch(c){
            case 'K':
                return new King(board,isWhite,vertical,horizontal);
            case 'Q':
                return new Queen(board,isWhite,vertical,horizontal);
            case 'R':
                return new Rook(board,isWhite,vertical,horizontal);
            case 'B':
                return new Bishop(board,isWhite,vertical,horizontal);
            case 'N':
                return new Knight(board,isWhite,vertical,horizontal);
            case 'P':
                return new Pawn(board,isWhite,vertical,horizontal);
        }
        return null;
    }
}
