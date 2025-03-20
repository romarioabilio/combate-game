package game.feedbacks;

import game.pieces.Piece;
import lombok.Getter;

@Getter
public abstract class Feedback {
    protected Piece piece;
    protected String message;

    public Feedback(String message) {
        this.message = message;
    }

    public Feedback(Piece piece) {
        this.piece = piece;
    }

    static String convertIntToAlfa(int value) {
        if (value < 0 || value > 26) {
            throw new IllegalArgumentException("Número deve estar entre 1 e 26");
        }
        return String.valueOf((char) ('A' + value));
    }
}
