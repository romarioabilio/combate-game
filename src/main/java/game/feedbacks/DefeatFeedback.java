package game.feedbacks;

import game.pieces.Piece;

public class DefeatFeedback extends Feedback {
    public Piece attacker;
    public Piece defender;
    public int toX;
    public int toY;

    public DefeatFeedback(Piece attacker, Piece defender, int toX, int toY) {
        super(formateMessage(attacker, defender, toX, toY));
        this.attacker = attacker.copyWithoutBoard();
        this.defender = defender.copyWithoutBoard();
        this.toX = toX;
        this.toY = toY;
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
