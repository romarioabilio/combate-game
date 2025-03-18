package game.feedbacks;

public abstract class Feedback {
    private String message;

    public Feedback(String message) { this.message = message; }

    public String getMessage() {
        return message;
    }

    static String convertIntToAlfa(int value) {
        if (value < 0 || value > 26) {
            throw new IllegalArgumentException("Número deve estar entre 1 e 26");
        }
        return String.valueOf((char) ('A' + value));
    }
}
