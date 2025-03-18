package game.pieces;

import game.Board;

public class Captain extends Piece {

    public Captain(String player, Board board) {
        super(7, player, board);
    }

    public Captain(Captain original, Board newBoard) {
        super(original, newBoard);
    }

    @Override
    public String getRepresentation() {
        return "CP";
    }

    @Override
    public Captain copy(Board board) {
        return new Captain(this, board);
    }
}
