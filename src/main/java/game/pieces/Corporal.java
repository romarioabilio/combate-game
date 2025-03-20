package game.pieces;

import game.Board;
import game.feedbacks.Feedback;
import game.feedbacks.LandMineDeactivationFeedback;

/**
 * Regra especial: É o único que pode desativar minas terrestres.
 */
public class Corporal extends Piece {

    public Corporal(String player, Board board) {
        super(3, player, board);
    }

    public Corporal(Corporal original, Board newBoard) {
        super(original, newBoard);
    }

    @Override
    public Feedback fight(Piece piece) {
        if (piece.getClass().getSimpleName().equals("LandMine")) {
            board.setPiece(piece.posX, piece.posY, this);
            this.setPosition(piece.posX, piece.posY);
            return new LandMineDeactivationFeedback(this);
        }

        return super.fight(piece);
    };

    @Override
    public String getRepresentation() {
        return "C";
    }

    @Override
    public Corporal copy(Board board) {
        return new Corporal(this, board);
    }

    @Override
    public Piece copyWithoutBoard() {
        return new Corporal(this, null);
    }
}
