package game.pieces;

public class PieceAction {
    protected Piece piece;
    protected int newPosX;
    protected int newPosY;

    public PieceAction(Piece piece, int newPosX, int newPosY) {
        this.piece = piece;
        this.newPosX = newPosX;
        this.newPosY = newPosY;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getNewPosX() {
        return newPosX;
    }

    public void setNewPosX(int newPosX) {
        this.newPosX = newPosX;
    }

    public int getNewPosY() {
        return newPosY;
    }

    public void setNewPosY(int newPosY) {
        this.newPosY = newPosY;
    }
}
