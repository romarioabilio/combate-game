package game.pieces;

import game.Board;

/**
 * Regra: pode mover-se uma casa na horizontal ou vertical.
 * É o único que pode desativar minas terrestres.
 */
public class Corporal extends Piece {

    public Corporal(String player, Board board) {
        super(3, player, board);
    }

    @Override
    public String getRepresentation() {
        return "C";
    }
}
