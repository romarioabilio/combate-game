package game.feedbacks;

import game.players.Player;

public class PlayerWithoutPiecesFeedback extends Feedback {

    public PlayerWithoutPiecesFeedback(Player p) {
        super(formateMessage(p));
    }

    private static String formateMessage(Player p) {
        return String.format("Fim de Jogo! Jogador %s sem peças móveis", p.getPlayerName());
    }
}
