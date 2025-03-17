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

    @Override
    public boolean canMove(int newX, int newY) {
        // Permite apenas movimento em linha reta (horizontal ou vertical)
        if (newX != posX && newY != posY) {
            return false; // não permite movimento diagonal
        }

        int dx = Integer.compare(newX, posX);
        int dy = Integer.compare(newY, posY);
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
}
