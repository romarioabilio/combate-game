package game.pieces;

import game.Board;

public class Sublieutenant extends Piece {

    public Sublieutenant(String player, Board board) {
        super(5, player, board);
    }

    public Sublieutenant(Sublieutenant original, Board newBoard) {
        super(original, newBoard);
    }

    @Override
    public String getRepresentation() {
        return "ST";
    }

    @Override
    public Sublieutenant copy(Board board) {
        return new Sublieutenant(this, board);
    }
}
