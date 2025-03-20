package game.feedbacks;

import game.pieces.Piece;

public class PrisonerFeedback extends Feedback {

    public PrisonerFeedback(Piece piece, Piece prisoner) {
        super(formateMessage(piece, prisoner));
    }

    private static String formateMessage(Piece piece, Piece prisoner) {
        String posX = convertIntToAlfa(prisoner.getPosX());
        int posY = (prisoner.getPosY() + 1);

        return String.format(
                "%s de %s achou o prisioneiro em [%s, %d]!",
                piece.getRepresentation(), piece.getPlayer(), posX, posY
        );
    }
}
