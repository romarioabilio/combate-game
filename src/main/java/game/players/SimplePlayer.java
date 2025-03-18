package game.players;

import game.Board;
import game.pieces.PieceFactory;
import game.pieces.QuantityPerPiece;
import game.pieces.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public Piece[][] initialMove(Board board) {
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

//    @Override
//    public Feedback play(Board board) {
//        // Procura a primeira peça que pertença ao jogador e tenta movê-la uma casa para a direita
//        for (int i = 0; i < Board.ROWS; i++) {
//            for (int j = 0; j < Board.COLS; j++) {
//                Piece piece = board.getPiece(i, j);
//                if (piece != null && piece.getPlayer().equals(playerName)) {
//                    int newX = i;
//                    int newY = j + 1;
//                    if (newY < Board.COLS && piece.canMove(newX, newY, board)) {
//                        piece.move(newX, newY, board);
//                        return new Feedback(playerName + " moved " + piece.getClass().getSimpleName() +
//                                " from (" + i + ", " + j + ") to (" + newX + ", " + newY + ")");
//                    }
//                }
//            }
//        }
//        return new Feedback(playerName + " did not move any piece.");
//    }
}
