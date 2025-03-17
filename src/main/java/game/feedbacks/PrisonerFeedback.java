package game.feedbacks;

import game.pieces.Piece;

public class PrisonerFeedback extends Feedback {

    public PrisonerFeedback(Piece piece) {
        super(formateMessage(piece));
    }

    private static String formateMessage(Piece piece) {
        return String.format(
                "%s de %s achou o prisioneiro!",
                piece.getRepresentation(), piece.getPlayer()
        );
    }
}
