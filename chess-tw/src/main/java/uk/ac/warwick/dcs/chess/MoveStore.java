package uk.ac.warwick.dcs.chess;
import java.io.Serializable;
import java.util.ArrayList;

public class MoveStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Move> moves;

    public MoveStore() {
        this.moves = new ArrayList<Move>();
    }

    public void clear() {
        this.moves.clear();
    }

    public void add(Move m) {
        this.moves.add(m);
    }

    public Move[] movesToArray() {
        return moves.toArray(new Move[moves.size()]);
    }

}
