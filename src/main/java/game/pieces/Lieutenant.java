package game.pieces;

import game.Board;

public class Lieutenant extends Piece {

    public Lieutenant(String player, Board board) {
        super(6, player, board);
    }

    public Lieutenant(Lieutenant original, Board newBoard) {
        super(original, newBoard);
    }

    @Override
    public String getRepresentation() {
        return "T";
    }

    @Override
    public Lieutenant copy(Board board) {
        return new Lieutenant(this, board);
    }
}
