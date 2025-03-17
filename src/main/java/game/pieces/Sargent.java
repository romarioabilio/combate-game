package game.pieces;

import game.Board;

public class Sargent extends Piece {

    public Sargent(String player, Board board) {
        super(4, player, board);
    }

    @Override
    public String getRepresentation() {
        return "SG";
    }
}
