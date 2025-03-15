package game;

import game.pieces.Captain;
import game.pieces.Colonel;
import game.pieces.SecretAgent;
import game.pieces.Soldier;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FightTest {
    @SneakyThrows
    @Test
    public void sameStrength() {
        Board board = new Board();
        Captain c1 = new Captain("player1", board);
        board.setPiece(0, 1, c1);
        Captain c2 = new Captain("player2", board);
        board.setPiece(0, 2, c2);
        var f = c1.move(0, 2, board);

        assertEquals("CP de player1 e CP de player2 tem a mesma força e se eliminaram", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void higherStrength() {
        Board board = new Board();
        Colonel cr = new Colonel("player1", board);
        board.setPiece(0, 1, cr);
        Soldier s = new Soldier("player2", board);
        board.setPiece(0, 2, s);
        var f = cr.move(0, 2, board);

        assertEquals("CR de player1 eliminou S de player2 e foi movida de [0, 1] para [0, 2]", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void smallerStrength() {
        Board board = new Board();
        Soldier s = new Soldier("player1", board);
        board.setPiece(0, 1, s);
        Colonel cr = new Colonel("player2", board);
        board.setPiece(0, 2, cr);
        var f = s.move(0, 2, board);

        assertEquals("S de player1 foi eliminado por CR de player2 em [0, 2]", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void secretAgentXColonel() {
        Board board = new Board();
        SecretAgent sa = new SecretAgent("player1", board);
        board.setPiece(0, 1, sa);
        Colonel l = new Colonel("player2", board);
        board.setPiece(0, 2, l);
        var f = sa.move(0, 2, board);

        assertEquals("AS de player1 eliminou CR de player2 e foi movida de [0, 1] para [0, 2]", f.getMessage());
    }

    @SneakyThrows
    @Test
    public void colonelXSecretAgent() {
        Board board = new Board();
        Colonel l = new Colonel("player1", board);
        board.setPiece(0, 1, l);
        SecretAgent sa = new SecretAgent("player2", board);
        board.setPiece(0, 2, sa);
        var f = l.move(0, 2, board);

        assertEquals("CR de player1 eliminou AS de player2 e foi movida de [0, 1] para [0, 2]", f.getMessage());
    }
}
