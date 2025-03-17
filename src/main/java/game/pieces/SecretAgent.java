package game.pieces;

import game.Board;
import game.Feedback;

/**
 * Regra especial: único que pode eliminar o General se atacar primeiro.
 */
public class SecretAgent extends Piece {

    public SecretAgent(String player, Board board) {
        super(1, player, board);
    }

    @Override
    public Feedback fight(Piece piece) {
        if (piece.getClass().getSimpleName().equals("Colonel")) {
            String message = String.format(
                    "%s de %s eliminou %s de %s e foi movida de [%d, %d] para [%d, %d]",
                    this.getRepresentation(), player, piece.getRepresentation(), piece.player, posX, posY, piece.posX, piece.posY
            );

            board.setPiece(piece.posX, piece.posY, this);
            this.setPosition(piece.posX, piece.posY);
            return new Feedback(message);
        }

        return super.fight(piece);
    };

    @Override
    public String getRepresentation() {
        return "AS";
    }
}
