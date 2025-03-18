package game.feedbacks;

public class InvalidMoveFeedback extends Feedback {
    public InvalidMoveFeedback() {
        super("Jogada inválida, passou a vez!");
    }

    public InvalidMoveFeedback(String message) {
        super("Jogada inválida: " + message + "\nPassou a vez!");
    }
}

