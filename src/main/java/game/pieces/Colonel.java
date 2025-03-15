package game.pieces;

import game.Board;

public class Colonel extends Piece {

    public Colonel(String player, Board board) {
        super(9, player, board);
    }

    @Override
    public String getRepresentation() {
        return "CR";
    }
}
