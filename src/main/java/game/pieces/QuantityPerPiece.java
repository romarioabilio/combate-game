package game.pieces;

public enum QuantityPerPiece {
    CAPTAIN("CP", 3),
    COLONEL("CR", 1),
    CORPORAL("C", 5),
    GENERAL("G", 1),
    LAND_MINE("M", 6),
    LIEUTENANT("T", 4),
    MAJOR("MJ", 2),
    PRISONER("PS", 1),
    SARGENT("SG", 4),
    SECRET_AGENT("AS", 1),
    SOLDIER("S", 8),
    SUBLIEUTENANT("ST", 4);

    private final String code;
    private final int quantity;

    QuantityPerPiece(String code, int quantity) {
        this.code = code;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCode() {
        return code;
    }
}

