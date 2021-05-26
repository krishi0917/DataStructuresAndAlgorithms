package DesignPatterns.Chess;

/**
 * Created by rkhurana on 12/3/18.
 */
public class Rook extends Piece {
    public Rook(boolean b) {
        super(b);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return false;
    }
}
