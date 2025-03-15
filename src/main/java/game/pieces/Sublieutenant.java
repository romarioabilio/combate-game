package game.pieces;

import game.Board;

public class Sublieutenant extends Piece {

    public Sublieutenant(String player, Board board) {
        super(5, player, board);
    }

    @Override
    public String getRepresentation() {
        return "ST";
    }
}
