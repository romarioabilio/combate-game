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

        int dx = Integer.compare(newX, posX);
        int dy = Integer.compare(newY, posY);
        dx = dy == 0 ? 1 : 0;
        dy = dx == 0 ? 1 : 0;
        int currentX = posX + dx;
        int currentY = posY + dy;
        while (currentX != newX || currentY != newY) {
            if (board.getPiece(currentX, currentY) != null) {
                return false;
            }
            currentX += dx;
            currentY += dy;
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
}
