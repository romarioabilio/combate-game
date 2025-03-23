package game.integration;

import game.Board;
import game.feedbacks.DefeatFeedback;
import game.feedbacks.Feedback;
import game.feedbacks.InvalidMoveFeedback;
import game.feedbacks.MoveFeedback;
import game.pieces.*;
import game.players.SimplePlayer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SoldierMovieTest {

    @SneakyThrows
    @Test
    public void soldierMoveOneHouseValid() {
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

        PieceAction action = new PieceAction(s, 1, 1);
        Feedback roundFeedback = board.executeAction(action);
        assertEquals("S de player1 foi movido para [B, 2]", roundFeedback.getMessage());
        assertInstanceOf(MoveFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(1, 1));
        assertEquals(board.getPiece(1, 1).getPlayer(), player1.getPlayerName());
        assertNull(board.getPiece(0, 1));
    }


    @SneakyThrows
    @Test
    public void soldierMoveOneHouseToAnEnemy() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Soldier s = new Soldier("player1", board);
        board.setPiece(1, 1, s);
        Major mj = new Major("player2", board);
        board.setPiece(2, 1, mj);
        Major mj1 = new Major("player2", board);
        board.setPiece(2, 2, mj1);
        Major mj2 = new Major("player2", board);
        board.setPiece(2, 0, mj2);
        Major mj3 = new Major("player2", board);
        board.setPiece(3, 1, mj3);

        PieceAction action = new PieceAction(s, 2, 1);
        Feedback roundFeedback = board.executeAction(action);
        assertEquals("S de player1 foi eliminado por MJ de player2 em [C, 2]", roundFeedback.getMessage());
        assertInstanceOf(DefeatFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(2, 1));
        assertEquals(board.getPiece(2, 1).getPlayer(), player2.getPlayerName());
        assertNull(board.getPiece(1, 1));
    }

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
        assertEquals("S de player1 foi movido para [A, 4]", roundFeedback.getMessage());
        assertInstanceOf(MoveFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(0, 3));
        assertEquals(board.getPiece(0, 3).getPlayer(), player1.getPlayerName());
        assertNull(board.getPiece(0, 1));
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

        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(0, 1));
        assertEquals(board.getPiece(0, 1).getPlayer(), player1.getPlayerName());
        assertNotNull(board.getPiece(2, 2));
        assertEquals(board.getPiece(2, 2).getPlayer(), player2.getPlayerName());
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

        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(0, 1));
        assertEquals(board.getPiece(0, 1).getPlayer(), player1.getPlayerName());
        assertNotNull(board.getPiece(0, 3));
        assertEquals(board.getPiece(0, 3).getPlayer(), player2.getPlayerName());
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
        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(0, 1));
        assertEquals(board.getPiece(0, 1).getPlayer(), player1.getPlayerName());
        assertNull(board.getPiece(3, 0));

        PieceAction action2 = new PieceAction(s, 0, 4);
        Feedback roundFeedback2 = board.executeAction(action2);
        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback2);
        assertNotNull(board.getPiece(0, 1));
        assertEquals(board.getPiece(0, 1).getPlayer(), player1.getPlayerName());
        assertNull(board.getPiece(0, 4));

        PieceAction action3 = new PieceAction(s, 0, 3);
        Feedback roundFeedback3 = board.executeAction(action3);
        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback3);
        assertNotNull(board.getPiece(0, 1));
        assertEquals(board.getPiece(0, 1).getPlayer(), player1.getPlayerName());
        assertNull(board.getPiece(1, 4));
    }

    @SneakyThrows
    @Test
    public void soldierMoveValidCellsBack() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Soldier s = new Soldier("player1", board);
        board.setPiece(5, 8, s);
        SecretAgent sa = new SecretAgent("player1", board);
        board.setPiece(3, 7, sa);
        General g = new General("player1", board);
        board.setPiece(3, 9, g);
        Lieutenant l = new Lieutenant("player1", board);
        board.setPiece(2, 8, l);

        Soldier s2 = new Soldier("player2", board);
        board.setPiece(6, 8, s2);
        Sargent sg = new Sargent("player2", board);
        board.setPiece(6, 7, sg);
        Soldier s3 = new Soldier("player2", board);
        board.setPiece(6, 9, s3);


        PieceAction action = new PieceAction(s, 3, 8);
        Feedback roundFeedback = board.executeAction(action);
        assertInstanceOf(MoveFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(3, 8));
        assertEquals(board.getPiece(3, 8).getPlayer(), player1.getPlayerName());
        assertNull(board.getPiece(5, 8));
    }

    @SneakyThrows
    @Test
    public void soldierMoveInvalidCellsBack() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Soldier s = new Soldier("player1", board);
        board.setPiece(5, 8, s);
        Soldier s2 = new Soldier("player1", board);
        board.setPiece(3, 8, s2);
        SecretAgent sa = new SecretAgent("player1", board);
        board.setPiece(3, 7, sa);
        General g = new General("player1", board);
        board.setPiece(3, 9, g);
        Lieutenant l = new Lieutenant("player1", board);
        board.setPiece(2, 8, l);

        Soldier s3 = new Soldier("player2", board);
        board.setPiece(6, 8, s3);
        Sargent sg = new Sargent("player2", board);
        board.setPiece(6, 7, sg);
        Soldier s4 = new Soldier("player2", board);
        board.setPiece(6, 9, s4);


        PieceAction action = new PieceAction(s, 3, 8);
        Feedback roundFeedback = board.executeAction(action);
        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback);
        assertEquals(board.getPiece(3, 8).getPlayer(), player1.getPlayerName());
        assertEquals(board.getPiece(5, 8).getPlayer(), player1.getPlayerName());
        assertNotNull(board.getPiece(3, 8));
        assertNotNull(board.getPiece(5, 8));
    }

    @SneakyThrows
    @Test
    public void soldierMoveValidCellsToTheLeft() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Soldier s = new Soldier("player1", board);
        board.setPiece(3, 9, s);
        Soldier s2 = new Soldier("player1", board);
        board.setPiece(2, 9, s2);
        SecretAgent sa = new SecretAgent("player1", board);
        board.setPiece(2, 8, sa);
        General g = new General("player1", board);
        board.setPiece(2, 7, g);
        Lieutenant l = new Lieutenant("player1", board);
        board.setPiece(3, 6, l);

        Soldier s3 = new Soldier("player2", board);
        board.setPiece(6, 8, s3);
        Sargent sg = new Sargent("player2", board);
        board.setPiece(6, 7, sg);
        Soldier s4 = new Soldier("player2", board);
        board.setPiece(6, 9, s4);


        PieceAction action = new PieceAction(s, 3, 7);
        Feedback roundFeedback = board.executeAction(action);
        assertInstanceOf(MoveFeedback.class, roundFeedback);
        assertEquals(board.getPiece(3, 7).getPlayer(), player1.getPlayerName());
        assertNotNull(board.getPiece(3, 7));
        assertNull(board.getPiece(3, 9));
    }

    @SneakyThrows
    @Test
    public void soldierMoveInvalidCellsToTheLeft() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Soldier s = new Soldier("player1", board);
        board.setPiece(3, 9, s);
        Prisoner p = new Prisoner("player1", board);
        board.setPiece(3, 7, p);
        Soldier s2 = new Soldier("player1", board);
        board.setPiece(2, 9, s2);
        SecretAgent sa = new SecretAgent("player1", board);
        board.setPiece(2, 8, sa);
        General g = new General("player1", board);
        board.setPiece(2, 7, g);
        Lieutenant l = new Lieutenant("player1", board);
        board.setPiece(3, 6, l);

        Soldier s3 = new Soldier("player2", board);
        board.setPiece(6, 8, s3);
        Sargent sg = new Sargent("player2", board);
        board.setPiece(6, 7, sg);
        Soldier s4 = new Soldier("player2", board);
        board.setPiece(6, 9, s4);


        PieceAction action = new PieceAction(s, 3, 7);
        Feedback roundFeedback = board.executeAction(action);
        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback);
        assertEquals(board.getPiece(3, 7).getPlayer(), player1.getPlayerName());
        assertEquals(board.getPiece(3, 9).getPlayer(), player1.getPlayerName());
        assertNotNull(board.getPiece(3, 7));
        assertNotNull(board.getPiece(3, 9));
    }
}
