package game.pieces;

import game.Board;
import game.Feedback;

/**
 * Regra: não pode ser movido. O objetivo do jogo é resgatar o prisioneiro adversário.
 */
public class Prisioner extends Piece {

    public Prisioner(String player, Board board) {
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
    public String getRepresentation() {
        return "PZ";
    }
}
