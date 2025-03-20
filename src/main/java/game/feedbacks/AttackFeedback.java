package game.feedbacks;

import game.pieces.Piece;

public class AttackFeedback extends Feedback {
    public Piece attacker;
    public Piece defender;
    public int fromX;
    public int fromY;
    public int toX;
    public int toY;

    public AttackFeedback(Piece attacker, Piece defender, int fromX, int fromY, int toX, int toY) {
        super(formateMessage(attacker, defender, fromX, fromY, toX, toY));
        this.attacker = attacker.copyWithoutBoard();
        this.defender = defender.copyWithoutBoard();
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    private static String formateMessage(Piece attacker, Piece defender, int fromX, int fromY, int toX, int toY) {
        return String.format(
                "%s de %s eliminou %s de %s e se moveu de [%s, %d] para [%s, %d]",
                attacker.getRepresentation(), attacker.getPlayer(),
                defender.getRepresentation(), defender.getPlayer(),
                convertIntToAlfa(fromX), (fromY + 1), convertIntToAlfa(toX), (toY + 1)
        );
    }
}