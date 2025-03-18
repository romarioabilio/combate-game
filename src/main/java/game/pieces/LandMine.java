package game.pieces;

import game.Board;
import game.feedbacks.Feedback;

/**
 * Regra especial: não pode ser movida; se uma peça atacar a mina, ambas são eliminadas (exceto se for o Cabo, que a desativa).
 */
public class LandMine extends Piece {

    public LandMine(String player, Board board) {
        super(0, player, board);
    }

    @Override
    public boolean canMove(int newX, int newY) {
        return false;
    }

    @Override
    public Feedback move(int newX, int newY, Board board) {
        throw new UnsupportedOperationException("Mina Terrestre não pode ser movida.");
    }

    @Override
    public Feedback fight(Piece piece) {
        throw new UnsupportedOperationException("Mina Terrestre não pode batalhar");
    }

    @Override
    public String getRepresentation() {
        return "M";
    }
}
