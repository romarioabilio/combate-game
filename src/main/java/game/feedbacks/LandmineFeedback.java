package game.feedbacks;

import game.pieces.Piece;

public class LandmineFeedback extends Feedback {
    private Piece victim;

    public LandmineFeedback(Piece victim, Piece landMine) {
        super(formateMessage(victim, landMine));
        this.piece = landMine.copyWithoutBoard();
        this.victim = victim.copyWithoutBoard();
    }

    public Piece getVictim() {
        return victim;
    }

    private static String formateMessage(Piece victim, Piece landMine) {
        return String.format(
                "%s de %s foi eliminado por uma mina terrestre em [%s, %d]",
                victim.getRepresentation(), victim.getPlayer(),
                convertIntToAlfa(landMine.getPosX()), (landMine.getPosY() + 1)
        );
    }
}