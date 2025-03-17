package game.pieces;

import game.Board;

public class Major extends Piece {

    public Major(String player, Board board) {
        super(8, player, board);
    }

    @Override
    public String getRepresentation() {
        return "MJ";
    }
}
