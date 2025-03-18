package game.feedbacks;

import game.pieces.Piece;

public class LandMineDeactivationFeedback extends Feedback {

    public LandMineDeactivationFeedback(Piece piece) {
        super(formatMessage(piece));
    }

    private static String formatMessage(Piece piece) {
        return String.format(
                "%s de %s desativou uma mina terrestre em [%d, %d]",
                piece.getRepresentation(), piece.getPlayer(), piece.getPosX(), piece.getPosY()
        );
    }
}
