package game.pieces;

import game.Board;

/**
 * Regra: pode mover-se várias casas em linha reta (horizontal ou vertical)
 * desde que o caminho esteja livre. Se mover mais de uma casa, não pode atacar na mesma rodada.
 */
public class Soldier extends Piece {

    public Soldier(String player, Board board) {
        super(2, player, board);
    }

    public Soldier(Soldier original, Board newBoard) {
        super(original, newBoard);
    }

    @Override
    public boolean canMove(int newX, int newY) {
        if (newX != posX && newY != posY) {
            return false; // não permite movimento diagonal
        }

        int dx = Math.abs(newX - posX);
        int dy = Math.abs(newY - posY);

        if (dx <= 1 && dy <= 1) {
            return super.canMove(newX, newY);
        }

        dx = dy == 0 ? 1 : 0;
        dy = dx == 0 ? 1 : 0;
        int currentX = posX;
        int currentY = posY;
        while (currentX != newX || currentY != newY) {
            currentX += dx;
            currentY += dy;
            if (board.getPiece(currentX, currentY) != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getRepresentation() {
        return "S";
    }

    @Override
    public Soldier copy(Board board) {
        return new Soldier(this, board);
    }

    @Override
    public Piece copyWithoutBoard() {
        return new Soldier(this, null);
    }
}
