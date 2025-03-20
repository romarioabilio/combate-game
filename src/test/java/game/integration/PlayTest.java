package game.integration;

import game.Board;
import game.feedbacks.*;
import game.pieces.*;
import game.players.SimplePlayer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayTest {

    @SneakyThrows
    @Test
    public void movePieceToValidPosition() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        board.player1 = player1;
        Sargent sg = new Sargent("player1", board);
        board.setPiece(3, 2, sg);

        PieceAction action = new PieceAction(sg, 2, 2);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("SG de player1 foi movido para [C, 3]", roundFeedback.getMessage());
        assertInstanceOf(MoveFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(2, 2));
        assertEquals(board.getPiece(2, 2).getPlayer(), player1.getPlayerName());
        assertNull(board.getPiece(3, 2));
    }

    @SneakyThrows
    @Test
    public void movePieceToInvalidPosition() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        board.player1 = player1;
        Sargent sg = new Sargent("player1", board);
        board.setPiece(3, 2, sg);

        PieceAction action = new PieceAction(sg, 4, 2);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("Jogada inválida, passou a vez!", roundFeedback.getMessage());
        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(3, 2));
        assertEquals(board.getPiece(3, 2).getPlayer(), player1.getPlayerName());
        assertNull(board.getPiece(4, 2));
    }

    @SneakyThrows
    @Test
    public void moveInvalidPiece() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        board.player1 = player1;
        LandMine l = new LandMine("player1", board);
        board.setPiece(3, 2, l);

        PieceAction action = new PieceAction(l, 2, 2);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("Jogada inválida: Mina Terrestre não pode ser movida.\nPassou a vez!", roundFeedback.getMessage());
        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(3, 2));
        assertEquals(board.getPiece(3, 2).getPlayer(), player1.getPlayerName());
        assertNull(board.getPiece(2, 2));

        Prisoner ps = new Prisoner("player1", board);
        board.setPiece(3, 2, l);

        PieceAction action2 = new PieceAction(ps, 2, 2);
        Feedback roundFeedback2 = board.executeAction(action2);

        assertEquals("Jogada inválida: Prisioneiro não pode ser movido.\nPassou a vez!", roundFeedback2.getMessage());
        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(3, 2));
        assertEquals(board.getPiece(3, 2).getPlayer(), player1.getPlayerName());
        assertNull(board.getPiece(2, 2));
    }

    @SneakyThrows
    @Test
    public void movePieceToEnemyStronger() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Sargent sg = new Sargent("player1", board);
        board.setPiece(0, 1, sg);
        Major mj = new Major("player2", board);
        board.setPiece(0, 2, mj);

        PieceAction action = new PieceAction(sg, 0, 2);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("SG de player1 foi eliminado por MJ de player2 em [A, 3]", roundFeedback.getMessage());
        assertInstanceOf(DeffeatFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(0, 2));
        assertEquals(board.getPiece(0, 2).getPlayer(), player2.getPlayerName());
        assertNull(board.getPiece(0, 1));
    }

    @SneakyThrows
    @Test
    public void movePieceToEnemyWeaker() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Sargent sg = new Sargent("player1", board);
        board.setPiece(0, 1, sg);
        Major mj = new Major("player2", board);
        board.setPiece(0, 2, mj);

        PieceAction action = new PieceAction(mj, 0, 1);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("MJ de player2 eliminou SG de player1 e se moveu de [A, 3] para [A, 2]", roundFeedback.getMessage());
        assertInstanceOf(AttackFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(0, 1));
        assertEquals(board.getPiece(0, 1).getPlayer(), player2.getPlayerName());
        assertNull(board.getPiece(0, 2));
    }

    @SneakyThrows
    @Test
    public void movePieceToEnemyEqualForce() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Sargent sg = new Sargent("player1", board);
        board.setPiece(0, 1, sg);
        Sargent sg2 = new Sargent("player2", board);
        board.setPiece(0, 2, sg2);

        PieceAction action = new PieceAction(sg, 0, 2);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("SG de player1 e SG de player2 possuem a mesma força e se eliminaram", roundFeedback.getMessage());
        assertInstanceOf(EqualStrengthFeedback.class, roundFeedback);
        assertNull(board.getPiece(0, 1));
        assertNull(board.getPiece(0, 2));
    }

    @SneakyThrows
    @Test
    public void movePieceMoreThanOneHouseWhenNotSoldier() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        board.player1 = player1;
        Sargent sg = new Sargent("player1", board);
        board.setPiece(0, 1, sg);

        PieceAction action = new PieceAction(sg, 0, 3);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("Jogada inválida, passou a vez!", roundFeedback.getMessage());
        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(0, 1));
        assertEquals(board.getPiece(0, 1).getPlayer(), player1.getPlayerName());
        assertNull(board.getPiece(0, 3));
    }

    @SneakyThrows
    @Test
    public void moveCorporalToLandMine() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        board.player1 = player1;
        Corporal c = new Corporal("player1", board);
        board.setPiece(0, 1, c);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);

        PieceAction action = new PieceAction(c, 0, 2);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("C de player1 desativou uma mina terrestre em [A, 3]", roundFeedback.getMessage());
        assertInstanceOf(LandMineDeactivationFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(0, 2));
        assertEquals(board.getPiece(0, 2).getPlayer(), player1.getPlayerName());
        assertNull(board.getPiece(0, 1));
    }

    @SneakyThrows
    @Test
    public void moveBasicSoldierToLandMine() {
        Board board = new Board();
        board.player1 = new SimplePlayer("player1");
        Captain cp = new Captain("player1", board);
        board.setPiece(0, 1, cp);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);

        PieceAction action = new PieceAction(cp, 0, 2);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("CP de player1 foi eliminado por uma mina terrestre em [A, 3]", roundFeedback.getMessage());
        assertInstanceOf(LandmineFeedback.class, roundFeedback);
        assertNull(board.getPiece(0, 2));
        assertNull(board.getPiece(0, 1));
    }

    @SneakyThrows
    @Test
    public void winGameByFoundingPrisoner() {
        Board board = new Board();
        board.player1 = new SimplePlayer("player1");
        Captain cp = new Captain("player1", board);
        board.setPiece(0, 1, cp);
        Prisoner p = new Prisoner("player2", board);
        board.setPiece(0, 2, p);

        PieceAction action = new PieceAction(cp, 0, 2);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("CP de player1 achou o prisioneiro em [A, 3]!", roundFeedback.getMessage());
        assertInstanceOf(PrisonerFeedback.class, roundFeedback);
    }

    @SneakyThrows
    @Test
    public void movePieceToPieceOfSamePlayer() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Sargent sg = new Sargent("player1", board);
        board.setPiece(0, 1, sg);
        Sargent sg2 = new Sargent("player1", board);
        board.setPiece(0, 2, sg2);

        PieceAction action = new PieceAction(sg, 0, 2);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("Jogada inválida, passou a vez!", roundFeedback.getMessage());
        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback);
        assertNotNull(board.getPiece(0, 1));
        assertNotNull(board.getPiece(0, 2));
    }
}
