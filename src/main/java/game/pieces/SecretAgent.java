package game.pieces;

import game.Board;

/**
 * Regra especial: único que pode eliminar o General se atacar primeiro.
 */
public class SecretAgent extends Piece {

    public SecretAgent(String player, Board board) {
        super(1, player, board);
    }

    @Override
    public String getRepresentation() {
        return "SA";
    }
}
