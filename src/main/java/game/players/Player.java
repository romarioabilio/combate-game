package game.players;

import game.Board;
import game.pieces.Piece;
import game.pieces.PieceAction;

public interface Player {
    String getPlayerName();

    /**
     * Realiza a jogada inicial, posicionar as 40 peças nas 4 primeiras fileiras.
     * @param board o tabuleiro do jogo.
     */
    Piece[][] setup(Board board);

    /**
     * Prepara uma jogada e retorna um objeto da jogada para ser executado pelo Board.
     */
    PieceAction play(Board board);
}
