package game;

import game.pieces.Piece;

/**
 * Pode conter informações como: mensagem geral, se matou uma peça, qual a peça eliminada,
 * se a peça que se moveu morreu e qual peça a eliminou.
 */
public class Feedback {
    private String message;          // Mensagem geral de feedback
    private boolean pieceKilled = false;     // Indica se uma peça foi eliminada
    private Piece killedPiece;       // Referência à peça que foi eliminada (se houver)
    private boolean died = false;            // Indica se a peça que se moveu foi eliminada
    private Piece killer;            // Referência à peça que eliminou (se houver)

    public Feedback(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isPieceKilled() {
        return pieceKilled;
    }

    public void setPieceKilled(boolean pieceKilled) {
        this.pieceKilled = pieceKilled;
    }

    public Piece getKilledPiece() {
        return killedPiece;
    }

    public void setKilledPiece(Piece killedPiece) {
        this.killedPiece = killedPiece;
    }

    public boolean isDied() {
        return died;
    }

    public void setDied(boolean died) {
        this.died = died;
    }

    public Piece getKiller() {
        return killer;
    }

    public void setKiller(Piece killer) {
        this.killer = killer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Feedback: ").append(message);
        if(pieceKilled && killedPiece != null) {
            sb.append(" | Piece killed: ").append(killedPiece.getClass().getSimpleName());
        }
        if(died && killer != null) {
            sb.append(" | Died due to: ").append(killer.getClass().getSimpleName());
        }
        return sb.toString();
    }
}
