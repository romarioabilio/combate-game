package game;

import game.pieces.Piece;
import game.players.Player;

public class Board {
    private static final int MAX_NUMBER_OF_MOVES = 5000;

    private Piece[][] board;
    public static final int ROWS = 10;
    public static final int COLS = 10;
    public Player player1;
    public Player player2;
    public int numberMoves;
    public static final String PLAYER1_COLOR_OPEN = "\u001B[32m";
    public static final String PLAYER2_COLOR_OPEN = "\u001B[31m";
    public static final String LAKE_COLOR_OPEN = "\u001B[34m";
    public static final String COLOR_CLOSE = "\u001B[0m";

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

    public static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10 && !Board.isLake(x, y);
    }

    private static boolean isLake(int x, int y) {
        return ((((x == 4 && y == 2) || (x == 5 && y == 2)) || ((x == 4 && y == 3) || (x == 5 && y == 3))) ||
                (((x == 4 && y == 6) || (x == 5 && y == 6)) || ((x == 4 && y == 7) || (x == 5 && y == 7))));
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

        sb.append("   ");
        for (int j = 0; j < COLS; j++) {
            sb.append(String.format(" %-2d ", j + 1));
        }
        sb.append("\n");

        for (int i = 0; i < ROWS; i++) {
            sb.append(String.format("%-2s ", (char) ('A' + i)));

            for (int j = 0; j < COLS; j++) {
                if (Board.isLake(i, j)) {
                    sb.append(String.format("[%s%-2s%s]", LAKE_COLOR_OPEN, "XX", COLOR_CLOSE));
                } else if (board[i][j] == null) {
                    sb.append(String.format("[%-2s]", " "));
                } else {
                    Piece piece = board[i][j];
                    String playerColor = piece.getPlayer().equals(player1.getPlayerName()) ? PLAYER1_COLOR_OPEN : PLAYER2_COLOR_OPEN;
                    sb.append(String.format("[%s%-2s%s]", playerColor, piece.getRepresentation(), COLOR_CLOSE));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Retorna a indicação se o jogo acabou por um dos motivos:
     *  - Algum player sem peças móveis
     *  - Número máximo de jogadas
     */
    public Feedback isGameFinished() {
        Player p = somePlayerHasMove();
        if (p != null) {
            return new Feedback(String.format("Fim do jogo, %s está sem peças", p.getPlayerName()));
        }

        if (numberMoves >= MAX_NUMBER_OF_MOVES) {
            return new Feedback("Número máximo de jogadas alcançadas");
        }

        return null;
    }

    private Player somePlayerHasMove() {
        boolean player1HasMovablePiece = false;
        boolean player2HasMovablePiece = false;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] != null) {
                    Piece p = board[i][j];
                    if (p.getPlayer().equals(player1.getPlayerName())) {
                        if (isMovablePiece(p)) {
                            player1HasMovablePiece = true;
                        }
                    } else if (p.getPlayer().equals(player2.getPlayerName())) {
                        if (isMovablePiece(p)) {
                            player2HasMovablePiece = true;
                        }
                    }
                }
            }
        }

        if (player1HasMovablePiece && player2HasMovablePiece) {
            return null;
        } else if (!player1HasMovablePiece) {
            return player1;
        } else {
            return player2;
        }
    };

    private boolean isMovablePiece(Piece piece) {
        if (piece == null) {
            return false;
        }

        return !piece.getRepresentation().equals("M") && !piece.getRepresentation().equals("PS");
    }

}
