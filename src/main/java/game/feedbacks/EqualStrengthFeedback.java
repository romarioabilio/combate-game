package game.feedbacks;

import game.pieces.Piece;

public class EqualStrengthFeedback extends Feedback {
    public Piece attacker;
    public Piece defender;

    public EqualStrengthFeedback(Piece attacker, Piece defender) {
        super(formateMessage(attacker, defender));
        this.attacker = attacker.copyWithoutBoard();
        this.defender = defender.copyWithoutBoard();
    }

    private static String formateMessage(Piece piece1, Piece piece2) {
        return String.format(
                "%s de %s e %s de %s possuem a mesma força e se eliminaram",
                piece1.getRepresentation(), piece1.getPlayer(),
                piece2.getRepresentation(), piece2.getPlayer()
        );
    }
}