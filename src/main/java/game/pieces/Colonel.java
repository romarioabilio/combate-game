package game.pieces;

import game.Board;

public class Colonel extends Piece {

    public Colonel(String player, Board board) {
        super(9, player, board);
    }

    public Colonel(Colonel original, Board newBoard) {
        super(original, newBoard);
    }

    @Override
    public String getRepresentation() {
        return "CR";
    }

    @Override
    public Colonel copy(Board board) {
        return new Colonel(this, board);
    }

    @Override
    public Piece copyWithoutBoard() {
        return new Colonel(this, null);
    }
}
