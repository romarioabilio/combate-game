package game.pieces;

import game.Board;
import game.Feedback;

/**
 * Regra especial: É o único que pode desativar minas terrestres.
 */
public class Corporal extends Piece {

    public Corporal(String player, Board board) {
        super(3, player, board);
    }

    @Override
    public Feedback fight(Piece piece) {
        if (piece.getClass().getSimpleName().equals("LandMine")) {
            String message = String.format(
                    "%s de %s desativou uma mina terrestre em [%d, %d]",
                    this.getRepresentation(), player, piece.posX, piece.posY
            );
            board.setPiece(piece.posX, piece.posY, this);
            this.setPosition(piece.posX, piece.posY);
            return new Feedback(message);
        }

        return super.fight(piece);
    };

    @Override
    public String getRepresentation() {
        return "C";
    }
}
