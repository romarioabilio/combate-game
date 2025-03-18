package game.feedbacks;

import game.pieces.Piece;

public class LandmineFeedback extends Feedback {

    public LandmineFeedback(Piece victim, Piece landMine) {
        super(formateMessage(victim, landMine));
    }

    private static String formateMessage(Piece victim, Piece landMine) {
        return String.format(
                "%s de %s foi eliminado por uma mina terrestre em [%s, %d]",
                victim.getRepresentation(), victim.getPlayer(),
                convertIntToAlfa(landMine.getPosX()), (landMine.getPosY() + 1)
        );
    }
}