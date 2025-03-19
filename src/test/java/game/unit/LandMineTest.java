package game.unit;

import game.Board;
import game.feedbacks.LandMineDeactivationFeedback;
import game.feedbacks.LandmineFeedback;
import game.pieces.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LandMineTest {

    @SneakyThrows
    @Test
    public void captainXLandMine() {
        Board board = new Board();
        Captain c = new Captain("player1", board);
        board.setPiece(0, 1, c);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);
        var f = c.move(0, 2, board);

        assertTrue(f instanceof LandmineFeedback);
        assertEquals("CP de player1 foi eliminado por uma mina terrestre em [A, 3]", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void colonelXLandMine() {
        Board board = new Board();
        Colonel col = new Colonel("player1", board);
        board.setPiece(0, 1, col);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);
        var f = col.move(0, 2, board);

        assertTrue(f instanceof LandmineFeedback);
        assertEquals("CR de player1 foi eliminado por uma mina terrestre em [A, 3]", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void corporalXLandMine() {
        Board board = new Board();
        Corporal corp = new Corporal("player1", board);
        board.setPiece(0, 1, corp);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);
        var f = corp.move(0, 2, board);

        assertTrue(f instanceof LandMineDeactivationFeedback);
        assertEquals("C de player1 desativou uma mina terrestre em [A, 3]", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void generalXLandMine() {
        Board board = new Board();
        General g = new General("player1", board);
        board.setPiece(0, 1, g);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);
        var f = g.move(0, 2, board);

        assertTrue(f instanceof LandmineFeedback);
        assertEquals("G de player1 foi eliminado por uma mina terrestre em [A, 3]", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void lieutenantXLandMine() {
        Board board = new Board();
        Lieutenant lt = new Lieutenant("player1", board);
        board.setPiece(0, 1, lt);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);
        var f = lt.move(0, 2, board);

        assertTrue(f instanceof LandmineFeedback);
        assertEquals("T de player1 foi eliminado por uma mina terrestre em [A, 3]", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void majorXLandMine() {
        Board board = new Board();
        Major mj = new Major("player1", board);
        board.setPiece(0, 1, mj);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);
        var f = mj.move(0, 2, board);

        assertTrue(f instanceof LandmineFeedback);
        assertEquals("MJ de player1 foi eliminado por uma mina terrestre em [A, 3]", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void sargentXLandMine() {
        Board board = new Board();
        Sargent sg = new Sargent("player1", board);
        board.setPiece(0, 1, sg);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);
        var f = sg.move(0, 2, board);

        assertTrue(f instanceof LandmineFeedback);
        assertEquals("SG de player1 foi eliminado por uma mina terrestre em [A, 3]", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void secretAgentXLandMine() {
        Board board = new Board();
        SecretAgent sa = new SecretAgent("player1", board);
        board.setPiece(0, 1, sa);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);
        var f = sa.move(0, 2, board);

        assertTrue(f instanceof LandmineFeedback);
        assertEquals("AS de player1 foi eliminado por uma mina terrestre em [A, 3]", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void soldierXLandMine() {
        Board board = new Board();
        Soldier s = new Soldier("player1", board);
        board.setPiece(0, 1, s);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);
        var f = s.move(0, 2, board);

        assertTrue(f instanceof LandmineFeedback);
        assertEquals("S de player1 foi eliminado por uma mina terrestre em [A, 3]", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void subLieuTenantXLandMine() {
        Board board = new Board();
        Sublieutenant sg = new Sublieutenant("player1", board);
        board.setPiece(0, 1, sg);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);
        var f = sg.move(0, 2, board);

        assertTrue(f instanceof LandmineFeedback);
        assertEquals("ST de player1 foi eliminado por uma mina terrestre em [A, 3]", f.getMessage());
    }

}

