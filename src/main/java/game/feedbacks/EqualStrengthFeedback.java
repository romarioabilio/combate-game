package game.feedbacks;

import game.pieces.Piece;

public class EqualStrengthFeedback extends Feedback {

    public EqualStrengthFeedback(Piece piece1, Piece piece2) {
        super(formateMessage(piece1, piece2));
    }

    private static String formateMessage(Piece piece1, Piece piece2) {
        return String.format(
                "%s de %s e %s de %s possuem a mesma força e se eliminaram",
                piece1.getRepresentation(), piece1.getPlayer(),
                piece2.getRepresentation(), piece2.getPlayer()
        );
    }
}