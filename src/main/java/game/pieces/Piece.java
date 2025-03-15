package game.pieces;

import game.Board;
import game.Feedback;

public abstract class Piece {
    protected int strength;
    protected int posX;
    protected int posY;
    protected String player; // Identifica o dono da peça (ex.: "Player1" ou "Player2")
    protected Board board;

    public Piece(int strength, String player, Board board) {
        this.strength = strength;
        this.player = player;
        this.board = board;
    }

    /**
     * Verifica se a peça pode se mover para a posição (newX, newY) de acordo com as regras.
     */
    public boolean canMove(int newX, int newY) {
        return Math.abs(newX - posX) + Math.abs(newY - posY) == 1;
    }

    /**
     * Realiza o movimento para a posição (newX, newY) se for válido.
     */
    public Feedback move(int newX, int newY, Board board) {
        if (canMove(newX, newY)) {
            board.setPiece(posX, posY, null);

            Piece opponent = board.getPiece(newX, newY);
            if (opponent != null) {
                return this.fight(opponent);
            }

            board.setPiece(newX, newY, this);
            this.setPosition(newX, newY);

            return new Feedback(String.format(
                "%s de %s foi movida de [%d, %d] para [%d, %d]",
                this.getRepresentation(), player, posX, posY, newX, newY
            ));
        }

        return new Feedback("Jogada inválida, passou a vez");
    }

    public abstract Feedback fight(Piece piece);

    public int getStrength() {
        return strength;
    }

    public String getPlayer() {
        return player;
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    /**
     * Retorna uma representação simplificada da peça para exibição no tabuleiro.
     * Cada peça pode sobrescrever esse método para retornar sua sigla.
     */
    public String getRepresentation() {
        return "P";
    }
}
