package game.pieces;

import game.Board;

public class Sargent extends Piece {

    public Sargent(String player, Board board) {
        super(4, player, board);
    }

    public Sargent(Sargent original, Board newBoard) {
        super(original, newBoard);
    }

    @Override
    public String getRepresentation() {
        return "SG";
    }

    @Override
    public Sargent copy(Board board) {
        return new Sargent(this, board);
    }

    @Override
    public Piece copyWithoutBoard() {
        return new Sargent(this, null);
    }
}
