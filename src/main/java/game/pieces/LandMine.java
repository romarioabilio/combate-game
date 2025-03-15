package game.pieces;

import game.Board;
import game.Feedback;

/**
 * Regra: não pode ser movida; se uma peça atacar a mina, ambas são eliminadas (exceto se for o Cabo, que a desativa).
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
    public String getRepresentation() {
        return "M";
    }
}
