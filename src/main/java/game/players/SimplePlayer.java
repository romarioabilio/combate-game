package game.players;

import game.Board;
import game.feedbacks.Feedback;
import game.pieces.PieceAction;
import game.pieces.PieceFactory;
import game.pieces.QuantityPerPiece;
import game.pieces.Piece;

import java.util.*;

/**
 * Implementação simples de um Player.
 * Esse jogador monta o tabuleiro posicionando uma peça e, em cada jogada, tenta mover
 * a primeira peça encontrada que pertença a ele para a direita (se possível).
 */
public class SimplePlayer implements Player {
    private final String playerName;

    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Neste caso o nome foi passado como parâmetro a fins de teste,
     * mas nas outras implentações deve se retornado de forma fixa em "getPlayerName".
     */
    public SimplePlayer(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Posicina as peças no tabuleiro
     */
    public Piece[][] setup(Board board) {
        /**
         * Jogador que posiciona suas peças de forma aleatória
         */
        var result = new Piece[4][10];
        List<String> piecesRepresentations = new ArrayList<>();
        for (QuantityPerPiece piece : QuantityPerPiece.values()) {
            int quantity = piece.getQuantity();
            for (int i = 0; i < quantity; i++) {
                piecesRepresentations.add(piece.getCode());
            }
        }
        Collections.shuffle(piecesRepresentations);
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                String pieceRepresentation = piecesRepresentations.get(index);
                result[i][j] = PieceFactory.createPiece(pieceRepresentation, this.playerName, board);
                index++;
            }
        }
        return result;
    }

    /**
     * Executa uma jogada simples:
     * - Tenta mover cada peça para frente (conforme a direção definida pelo jogador).
     * - Se não for possível mover para frente, tenta mover para a esquerda ou direita,
     *   escolhendo a direção com menos peças aliadas na vizinhança da célula destino.
     * - Nunca move para trás.
     * @param board O tabuleiro oculto (com peças adversárias mascaradas).
     * @return Uma ação (PieceAction) com a peça escolhida e a posição destino.
     */
    @Override
    public PieceAction play(Board board, Feedback myLastFeedback, Feedback enemyLastFeedback) {
        String playerName = getPlayerName();
        // Define a direção de "frente": para Player1 (+1 em X) e para Player2 (-1 em X)
        int forwardDir = playerName.equals("Player1") ? 1 : -1;

        // Percorre o tabuleiro para encontrar peças deste jogador
        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLS; j++) {
                Piece piece = board.getPiece(i, j);
                // Verifica se a peça não é nula e se o jogador da peça é válido e corresponde ao jogador atual
                if (piece != null && piece.getPlayer() != null && piece.getPlayer().equals(playerName)) {
                    // Tenta mover para frente
                    int newX = piece.getPosX() + forwardDir;
                    int newY = piece.getPosY();
                    if (Board.isValidPosition(newX, newY)) {
                        return new PieceAction(piece, newX, newY);
                    }

                    // Se não conseguir mover para frente, tenta as laterais: esquerda e direita
                    int[][] lateralDirs = { {0, -1}, {0, 1} };
                    PieceAction chosenAction = null;
                    int bestAllyCount = Integer.MAX_VALUE;

                    for (int[] dir : lateralDirs) {
                        newX = piece.getPosX();
                        newY = piece.getPosY() + dir[1];
                        if (Board.isValidPosition(newX, newY)) {
                            // Conta aliados próximos à célula destino
                            int allyCount = countAlliedNeighbors(newX, newY, board, playerName);
                            if (allyCount < bestAllyCount) {
                                bestAllyCount = allyCount;
                                chosenAction = new PieceAction(piece, newX, newY);
                            }
                        }
                    }

                    if (chosenAction != null) {
                        return chosenAction;
                    }
                }
            }
        }
        // Se nenhuma jogada válida for encontrada, retorna null (pode ser tratado como "passa a vez")
        return null;
    }

    /**
     * Conta quantas peças aliadas estão nas células vizinhas da posição (x, y).
     * Considera as 8 células adjacentes.
     *
     * @param x Coordenada X da célula.
     * @param y Coordenada Y da célula.
     * @param board O tabuleiro.
     * @param playerName Nome do jogador (para identificar peças aliadas).
     * @return Número de peças aliadas ao redor da célula (x, y).
     */
    private int countAlliedNeighbors(int x, int y, Board board, String playerName) {
        int count = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue; // ignora a própria célula
                int nx = x + dx;
                int ny = y + dy;
                if (Board.isValidPosition(nx, ny)) {
                    Piece neighbor = board.getPiece(nx, ny);
                    if (neighbor != null && neighbor.getPlayer() != null && neighbor.getPlayer().equals(playerName)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
