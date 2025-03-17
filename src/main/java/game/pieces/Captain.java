package game.pieces;

import game.Board;

public class Captain extends Piece {

    public Captain(String player, Board board) {
        super(7, player, board);
    }

    @Override
    public String getRepresentation() {
        return "CP";
    }
}
