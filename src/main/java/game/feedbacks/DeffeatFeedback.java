package game.feedbacks;

import game.pieces.Piece;

public class DeffeatFeedback extends Feedback {

    public DeffeatFeedback(Piece attacker, Piece defender, int toX, int toY) {
        super(formateMessage(attacker, defender, toX, toY));
    }

    private static String formateMessage(Piece attacker, Piece defender, int toX, int toY) {
        return String.format(
                "%s de %s foi eliminado por %s de %s em [%s, %d]",
                attacker.getRepresentation(), attacker.getPlayer(),
                defender.getRepresentation(), defender.getPlayer(),
                convertIntToAlfa(toX), (toY + 1)
        );
    }
}
