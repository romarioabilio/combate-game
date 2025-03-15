package game.pieces;

import game.Board;

public class Lieutenant extends Piece {

    public Lieutenant(String player, Board board) {
        super(6, player, board);
    }

    @Override
    public String getRepresentation() {
        return "T";
    }
}
