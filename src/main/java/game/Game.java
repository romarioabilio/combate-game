package game;

import game.players.Player;
import game.players.SimplePlayer;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Integer round = 0;

    public Game() {
        board = new Board();
        player1 = new SimplePlayer("Player1");
        player2 = new SimplePlayer("Player2");
    }

    /**
     * Inicia o jogo.
     */
    public void start() {
        player1.initialMove();
        player2.initialMove();
        System.out.println("Estado inicial do tabuleiro:");
        System.out.println(board.getFeedback());

//        // Loop simples para alternar as jogadas (10 rodadas para demonstração)
//        int rounds = 10;
//        for (int i = 1; i <= rounds; i++) {
//            System.out.println("Rodada " + i + ":");
//
//            // Jogada do Player1
//            Feedback feedback1 = player1.play(board);
//            System.out.println("Player1: " + feedback1);
//            System.out.println(board.getFeedback());
//
//            // Jogada do Player2
//            Feedback feedback2 = player2.play(board);
//            System.out.println("Player2: " + feedback2);
//            System.out.println(board.getFeedback());
//        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
