package game;

import game.pieces.Piece;

public class Board {
    private Piece[][] board;
    public static final int ROWS = 10;
    public static final int COLS = 10;

    public Board() {
        board = new Piece[ROWS][COLS];
    }

    public Piece getPiece(int x, int y) {
        if (x < 0 || x >= ROWS || y < 0 || y >= COLS) {
            return null;
        }
        return board[x][y];
    }

    public void setPiece(int x, int y, Piece piece) {
        board[x][y] = piece;
        if (piece != null) {
            piece.setPosition(x, y);
        }
    }

    /**
    * Adiciona posicionamento inicial do jogador ao tabuleiro
    */
    public void setPlayerInitialMove(Piece[][] playerInitialMove, int player) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                int col = player == 1 ? i : i + 6;
                this.setPiece(col, j, playerInitialMove[i][j]);
            }
        }
    }

    /**
     * Retorna uma representação em String do estado atual do tabuleiro.
     */
    public String getFeedback() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == null) {
                    sb.append(String.format("[%-2s]", " "));
                } else {
                    sb.append(String.format("[%-2s]", board[i][j].getRepresentation()));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
