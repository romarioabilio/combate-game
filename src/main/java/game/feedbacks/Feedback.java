package game.feedbacks;

public abstract class Feedback {
    private String message;

    public Feedback(String message) { this.message = message; }

    public String getMessage() {
        return message;
    }
}
