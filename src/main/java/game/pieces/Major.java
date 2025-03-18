package game.pieces;

import game.Board;

public class Major extends Piece {

    public Major(String player, Board board) {
        super(8, player, board);
    }

    public Major(Major original, Board newBoard) {
        super(original, newBoard);
    }

    @Override
    public String getRepresentation() {
        return "MJ";
    }

    @Override
    public Major copy(Board board) {
        return new Major(this, board);
    }
}
