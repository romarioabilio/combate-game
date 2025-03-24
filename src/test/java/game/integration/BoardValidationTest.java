package game.integration;

import game.Board;
import game.feedbacks.*;
import game.pieces.*;
import game.players.SimplePlayer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardValidationTest {

    @SneakyThrows
    @Test
    public void endGameByPlayerWithNoMovingPieces() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Captain cp = new Captain("player1", board);
        board.setPiece(0, 1, cp);
        Captain cp2 = new Captain("player1", board);
        board.setPiece(0, 2, cp2);
        Major mj = new Major("player1", board);
        board.setPiece(1, 1, mj);
        Prisoner p1 = new Prisoner("player1", board);
        board.setPiece(0, 0, p1);

        Prisoner p2 = new Prisoner("player2", board);
        board.setPiece(8, 2, p2);
        LandMine l1 = new LandMine("player2", board);
        board.setPiece(7, 3, l1);
        LandMine l2 = new LandMine("player2", board);
        board.setPiece(7, 2, l2);

        Feedback roundFeedback = board.isGameFinished();

        assertEquals("Fim de Jogo! Jogador player2 sem peças móveis", roundFeedback.getMessage());
        assertInstanceOf(PlayerWithoutPiecesFeedback.class, roundFeedback);
    }

    @SneakyThrows
    @Test
    public void endGameByMaxNumberOfRounds() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Captain cp = new Captain("player1", board);
        board.setPiece(0, 1, cp);
        Major mj = new Major("player1", board);
        board.setPiece(1, 1, mj);
        Prisoner p1 = new Prisoner("player1", board);
        board.setPiece(0, 0, p1);

        Prisoner p2 = new Prisoner("player2", board);
        board.setPiece(8, 2, p2);
        Soldier s = new Soldier("player2", board);
        board.setPiece(7, 3, s);
        LandMine l2 = new LandMine("player2", board);
        board.setPiece(7, 2, l2);

        for (int i = 0; i < 5000; i++) {
            Feedback roundFeedback = board.executeAction(null);
            if (!(roundFeedback instanceof InvalidMoveFeedback)) break;
        }
        Feedback roundFeedback = board.isGameFinished();
        assertEquals("Número máximo de jogadas alcançadas", roundFeedback.getMessage());
        assertInstanceOf(MaxNumberOfMovesFeedback.class, roundFeedback);
    }

    @SneakyThrows
    @Test
    public void moveSamePieceMoreThen3Times() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        SimplePlayer player2 = new SimplePlayer("player2");
        board.player1 = player1;
        board.player2 = player2;

        Captain cp = new Captain("player1", board);
        board.setPiece(0, 1, cp);
        Major mj = new Major("player1", board);
        board.setPiece(1, 1, mj);
        Prisoner p1 = new Prisoner("player1", board);
        board.setPiece(0, 0, p1);

        Prisoner p2 = new Prisoner("player2", board);
        board.setPiece(8, 2, p2);
        Soldier s = new Soldier("player2", board);
        board.setPiece(7, 3, s);
        LandMine l2 = new LandMine("player2", board);
        board.setPiece(7, 2, l2);

        for (int i = 0; i < 3; i++) {
            Feedback roundFeedback = null;
            PieceAction action;
            if (i % 2 == 0) {
                action = new PieceAction(cp, 0, 2);
            } else {
                action = new PieceAction(cp, 0, 1);
            }
            roundFeedback = board.executeAction(action);
            if (!(roundFeedback instanceof MoveFeedback)) break;
        }

        PieceAction action = new PieceAction(cp, 0, 1);
        Feedback roundFeedback = board.executeAction(action);

        assertEquals("Jogada inválida: player1 moveu a mesma peça mais de 3 vezes, CP em [A, 2]\nPassou a vez!", roundFeedback.getMessage());
        assertInstanceOf(InvalidMoveFeedback.class, roundFeedback);
    }

    @SneakyThrows
    @Test
    public void validateValidSetupBoard() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        board.player1 = player1;

        var result = new Piece[4][10];
        List<String> piecesRepresentations = new ArrayList<>();
        for (QuantityPerPiece piece : QuantityPerPiece.values()) {
            int quantity = piece.getQuantity();
            for (int i = 0; i < quantity; i++) {
                piecesRepresentations.add(piece.getCode());
            }
        }
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                String pieceRepresentation = piecesRepresentations.get(index);
                result[i][j] = PieceFactory.createPiece(pieceRepresentation, player1.getPlayerName(), board);
                index++;
            }
        }

        var player1SetupIsValid = board.addPlayerSetup(result, 1);
        assertTrue(player1SetupIsValid);
    }

    @SneakyThrows
    @Test
    public void validateFewerPiecesOnTheBoard() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        board.player1 = player1;

        var result = new Piece[4][10];
        List<String> piecesRepresentations = new ArrayList<>();
        for (QuantityPerPiece piece : QuantityPerPiece.values()) {
            int quantity = piece.getQuantity();
            for (int i = 0; i < quantity-1; i++) {
                piecesRepresentations.add(piece.getCode());
            }
        }
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    String pieceRepresentation = piecesRepresentations.get(index);
                    result[i][j] = PieceFactory.createPiece(pieceRepresentation, player1.getPlayerName(), board);
                    index++;
                } catch (Exception ignored) {};
            }
        }

        var player1SetupIsValid = board.addPlayerSetup(result, 1);
        assertFalse(player1SetupIsValid);
    }

    @SneakyThrows
    @Test
    public void validateMorePiecesOnTheBoard() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        board.player1 = player1;

        var result = new Piece[4][10];
        List<String> piecesRepresentations = new ArrayList<>();
        for (QuantityPerPiece piece : QuantityPerPiece.values()) {
            int quantity = piece.getQuantity();
            for (int i = 0; i < quantity+1; i++) {
                piecesRepresentations.add(piece.getCode());
            }
        }
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    String pieceRepresentation = piecesRepresentations.get(index);
                    result[i][j] = PieceFactory.createPiece(pieceRepresentation, player1.getPlayerName(), board);
                    index++;
                } catch (Exception ignored) {};
            }
        }

        var player1SetupIsValid = board.addPlayerSetup(result, 1);
        assertFalse(player1SetupIsValid);
    }

    @SneakyThrows
    @Test
    public void validateAPartWithExchangedQuantity() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        board.player1 = player1;

        var result = new Piece[4][10];
        List<String> piecesRepresentations = new ArrayList<>();
        for (QuantityPerPiece piece : QuantityPerPiece.values()) {
            int quantity = piece.getQuantity();
            if (piece.getCode().equals("s")) {
                quantity--;
            } else if (piece.getCode().equals("M")) {
                quantity++;
            }
            for (int i = 0; i < quantity; i++) {
                piecesRepresentations.add(piece.getCode());
            }
        }
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    String pieceRepresentation = piecesRepresentations.get(index);
                    result[i][j] = PieceFactory.createPiece(pieceRepresentation, player1.getPlayerName(), board);
                    index++;
                } catch (Exception ignored) {};
            }
        }

        var player1SetupIsValid = board.addPlayerSetup(result, 1);
        assertFalse(player1SetupIsValid);
    }

    @SneakyThrows
    @Test
    public void validateSetupWithInvalidArray() {
        Board board = new Board();
        SimplePlayer player1 = new SimplePlayer("player1");
        board.player1 = player1;

        var result = new Piece[3][10];
        List<String> piecesRepresentations = new ArrayList<>();
        for (QuantityPerPiece piece : QuantityPerPiece.values()) {
            int quantity = piece.getQuantity();
            if (piece.getCode().equals("s")) {
                quantity--;
            } else if (piece.getCode().equals("M")) {
                quantity++;
            }
            for (int i = 0; i < quantity; i++) {
                piecesRepresentations.add(piece.getCode());
            }
        }
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    String pieceRepresentation = piecesRepresentations.get(index);
                    result[i][j] = PieceFactory.createPiece(pieceRepresentation, player1.getPlayerName(), board);
                    index++;
                } catch (Exception ignored) {};
            }
        }

        var player1SetupIsValid = board.addPlayerSetup(result, 1);
        assertFalse(player1SetupIsValid);
    }
}
