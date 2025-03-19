package game.integration;

import game.Board;
import game.feedbacks.Feedback;
import game.pieces.Major;
import game.pieces.PieceAction;
import game.pieces.Soldier;
import game.players.SimplePlayer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoldierMovieTest {

    @SneakyThrows
    @Test
    public void soldierMoveMoreThanOneHouseValid() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Soldier s = new Soldier("player1", board);
        board.setPiece(0, 1, s);
        Major mj = new Major("player2", board);
        board.setPiece(2, 1, mj);
        Major mj1 = new Major("player2", board);
        board.setPiece(2, 2, mj1);
        Major mj2 = new Major("player2", board);
        board.setPiece(2, 0, mj2);
        Major mj3 = new Major("player2", board);
        board.setPiece(3, 1, mj3);

        PieceAction action = new PieceAction(s, 0, 3);
        Feedback roundFeedback = board.executeAction(action);
        assertEquals("S de player1 foi movida para [A, 4]", roundFeedback.getMessage());
    }

    @SneakyThrows
    @Test
    public void soldierMoveMoreThanOneHouseInvalid() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Soldier s = new Soldier("player1", board);
        board.setPiece(0, 1, s);
        Major mj = new Major("player2", board);
        board.setPiece(2, 1, mj);
        Major mj1 = new Major("player2", board);
        board.setPiece(2, 2, mj1);
        Major mj2 = new Major("player2", board);
        board.setPiece(2, 0, mj2);
        Major mj3 = new Major("player2", board);
        board.setPiece(3, 1, mj3);

        PieceAction action = new PieceAction(s, 2, 2);
        Feedback roundFeedback = board.executeAction(action);
        assertEquals("Jogada inválida, passou a vez!", roundFeedback.getMessage());
    }

    @SneakyThrows
    @Test
    public void soldierMoveMoreThanOneHouseToAnEnemy() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Soldier s = new Soldier("player1", board);
        board.setPiece(0, 1, s);
        Major mj = new Major("player2", board);
        board.setPiece(2, 1, mj);
        Major mj1 = new Major("player2", board);
        board.setPiece(2, 2, mj1);
        Major mj2 = new Major("player2", board);
        board.setPiece(2, 0, mj2);
        Major mj3 = new Major("player2", board);
        board.setPiece(3, 1, mj3);
        Major mj4 = new Major("player2", board);
        board.setPiece(1, 0, mj4);
        Major mj5 = new Major("player2", board);
        board.setPiece(1, 2, mj5);
        Major mj6 = new Major("player2", board);
        board.setPiece(0, 3, mj6);

        PieceAction action = new PieceAction(s, 0, 3);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("Jogada inválida, passou a vez!", roundFeedback.getMessage());
    }

    @SneakyThrows
    @Test
    public void soldierMoveMoreThanOneHouseThroughEnemies() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Soldier s = new Soldier("player1", board);
        board.setPiece(0, 1, s);
        Major mj = new Major("player2", board);
        board.setPiece(2, 1, mj);
        Major mj1 = new Major("player2", board);
        board.setPiece(2, 2, mj1);
        Major mj2 = new Major("player2", board);
        board.setPiece(2, 0, mj2);
        Major mj3 = new Major("player2", board);
        board.setPiece(3, 1, mj3);
        Major mj4 = new Major("player2", board);
        board.setPiece(1, 0, mj4);
        Major mj5 = new Major("player2", board);
        board.setPiece(1, 2, mj5);
        Major mj6 = new Major("player2", board);
        board.setPiece(0, 3, mj6);

        PieceAction action = new PieceAction(s, 3, 0);
        Feedback roundFeedback = board.executeAction(action);
        assertEquals("Jogada inválida, passou a vez!", roundFeedback.getMessage());

        PieceAction action2 = new PieceAction(s, 0, 4);
        Feedback roundFeedback2 = board.executeAction(action2);
        assertEquals("Jogada inválida, passou a vez!", roundFeedback2.getMessage());

        PieceAction action3 = new PieceAction(s, 1, 4);
        Feedback roundFeedback3 = board.executeAction(action3);
        assertEquals("Jogada inválida, passou a vez!", roundFeedback3.getMessage());
    }
}
