package game;

import game.pieces.LandMine;
import game.pieces.Prisoner;
import game.pieces.Sargent;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MoveTest {

    @SneakyThrows
    @Test
    public void prisonerXLandMine() {
        Board board = new Board();
        Prisoner ps = new Prisoner("player1", board);
        board.setPiece(0, 1, ps);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);

        assertThrows(UnsupportedOperationException.class, () -> ps.move(0, 2, board));
    }

    @SneakyThrows
    @Test
    public void landMineMove() {
        Board board = new Board();
        Sargent sg = new Sargent("player1", board);
        board.setPiece(0, 1, sg);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);

        assertThrows(UnsupportedOperationException.class, () -> l.move(0, 1, board));
    }

    @SneakyThrows
    @Test
    public void PieceMove() {
        Board board = new Board();
        Sargent sg = new Sargent("player1", board);
        board.setPiece(0, 1, sg);
        LandMine l = new LandMine("player2", board);
        board.setPiece(0, 2, l);

        assertDoesNotThrow(() -> sg.move(0, 1, board));
    }
}
