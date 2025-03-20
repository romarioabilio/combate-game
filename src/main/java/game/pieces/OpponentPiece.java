package game.pieces;

import game.Board;

public class OpponentPiece extends Piece {

    public OpponentPiece(Board board) {
        super(999, board);
    }

    public OpponentPiece(OpponentPiece original, Board newBoard) {
        super(original, newBoard);
    }

    public OpponentPiece(Piece enemy) {
        super(enemy);
        this.strength = 999;
    }

    @Override
    public String getRepresentation() {return "OP"; }

    @Override
    public OpponentPiece copy(Board board) {
        return new OpponentPiece(this, board);
    }

    @Override
    public Piece copyWithoutBoard() {
        return new OpponentPiece(this, null);
    }
}
