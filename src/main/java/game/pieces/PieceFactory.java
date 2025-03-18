package game.pieces;

import game.Board;

public class PieceFactory {
    public static Piece createPiece(String pieceRepresentation, String player, Board board) {
        return switch (pieceRepresentation) {
            case "CP" ->
                    new Captain(player, board);
            case "CR" ->
                    new Colonel(player, board);
            case "C" ->
                    new Corporal(player, board);
            case "G" ->
                    new General(player, board);
            case "M" ->
                    new LandMine(player, board);
            case "T" ->
                    new Lieutenant(player, board);
            case "MJ" ->
                    new Major(player, board);
            case "PS" ->
                    new Prisoner(player, board);
            case "SG" ->
                    new Sargent(player, board);
            case "AS" ->
                    new SecretAgent(player, board);
            case "S" ->
                    new Soldier(player, board);
            case "ST" ->
                    new Sublieutenant(player, board);
            default -> throw new IllegalArgumentException("Tipo de peça desconhecido: " + pieceRepresentation);
        };
    }
}
