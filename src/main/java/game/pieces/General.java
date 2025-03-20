package game.pieces;

import game.Board;

public class General extends Piece {

    public General(String player, Board board) {
        super(10, player, board);
    }

    public General(General original, Board newBoard) {
        super(original, newBoard);
    }

    @Override
    public String getRepresentation() {
        return "G";
    }

    @Override
    public General copy(Board board) {
        return new General(this, board);
    }

    @Override
    public Piece copyWithoutBoard() {
        return new General(this, null);
    }
}
