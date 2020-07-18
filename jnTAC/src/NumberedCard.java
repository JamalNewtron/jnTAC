import java.util.List;

public class NumberedCard extends Card {

    private int cardValue;

    public NumberedCard(final int cardValue) {
        this.cardValue = cardValue;
    }


    public void setCardValue(final int cardValue) {
        this.cardValue = cardValue;
    }

    public int getCardValue() {
        return this.cardValue;
    }

    // move to x position from y position
    public void moveToPosition(final List<Player> allPlayers,
                               final Player player,
                               final int mumble) {

        // current position
        int currentNumblePosition = player.getMumbles().get(mumble).getCurrentPosition();

        int targetMumblePosition = currentNumblePosition + this.cardValue;

        // Mumble enters HOME_FIELD or returns to new cycle
        if(targetMumblePosition > 65 && targetMumblePosition < 70) {
            player.getMumbles().get(mumble).moveMumble(allPlayers, player, PLAYGROUND.HOME_FIELD, targetMumblePosition);
        } else {

            targetMumblePosition = targetMumblePosition - 64;
            player.getMumbles().get(mumble).moveMumble(allPlayers, player, PLAYGROUND.HOME_FIELD, targetMumblePosition);

        }

    }

}
