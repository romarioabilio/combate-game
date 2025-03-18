package game.pieces;

import game.Board;
import game.feedbacks.Feedback;

/**
 * Regra: não pode ser movido. O objetivo do jogo é resgatar o prisioneiro adversário.
 */
public class Prisoner extends Piece {

    public Prisoner(String player, Board board) {
        super(0, player, board);
    }

    @Override
    public boolean canMove(int newX, int newY) {
        return false;
    }

    @Override
    public Feedback move(int newX, int newY, Board board) {
        throw new UnsupportedOperationException("Prisioneiro não pode ser movido.");
    }

    @Override
    public Feedback fight(Piece piece) {
        throw new UnsupportedOperationException("Prisioneiro não pode batalhar");
    }

    @Override
    public String getRepresentation() {
        return "PS";
    }
}
