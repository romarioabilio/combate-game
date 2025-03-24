package game.feedbacks;

import game.pieces.Piece;

public class InvalidMoveFeedback extends Feedback {
    public int posX, posY, newX, newY;

    public InvalidMoveFeedback(int posX, int posY, int newX, int newY) {
        super("A jogada de [" + convertIntToAlfa(posX) + ", " + (posY + 1) + "] para ["  + convertIntToAlfa(newX) + ", " + (newY + 1) + "] é inválida. Passou a vez");
        this.posX = posX;
        this.posY = posY;
        this.newX = newX;
        this.newY = newY;
    }

    public InvalidMoveFeedback(Piece p1, int newX, int newY) {
        super("A jogada " + p1.getRepresentation() + " de [" + convertIntToAlfa(p1.getPosX()) + ", " + (p1.getPosY() + 1) + "] para ["  + convertIntToAlfa(newX) + ", " + (newY + 1) + "] é inválida. Passou a vez");
        this.posX = p1.getPosX();
        this.posY = p1.getPosY();
        this.newX = newX;
        this.newY = newY;
    }

    public InvalidMoveFeedback() {
        super("Jogada inválida, passou a vez!");
    }

    public InvalidMoveFeedback(String message) {
        super("Jogada inválida: " + message + "\nPassou a vez!");
    }

    public InvalidMoveFeedback(String message, boolean ignoreFormatting) {
        super(message);
    }
}
