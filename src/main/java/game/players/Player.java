package game.players;

import game.Board;
import game.pieces.Piece;

public interface Player {
    String getPlayerName();

    /**
     * Realiza a jogada inicial, posicionar as 40 peças nas 4 primeiras fileiras.
     * @param board o tabuleiro do jogo.
     */
    Piece[][] initialMove(Board board);

//    /**
//     * Realiza uma jogada durante o jogo e retorna um feedback do estado atual do tabuleiro.
//     * @param board o tabuleiro do jogo.
//     * @return feedback em forma de String.
//     */
//    String play(Board board);
}
