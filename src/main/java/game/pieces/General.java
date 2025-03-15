package game.pieces;

import game.Board;

public class General extends Piece {

    public General(String player, Board board) {
        super(10, player, board);
    }

    @Override
    public String getRepresentation() {
        return "G";
    }
}
