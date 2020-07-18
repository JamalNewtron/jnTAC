import java.util.List;

public class NumberedSpecialCard extends NumberedCard {

    public NumberedSpecialCard(final int cardValue) {
        super(cardValue);
    }


    // move 1 or 13

    // CARD FOUR
    public void moveFourBack(final List<Player> allPlayers,
                             final Player player,
                             final NumberedCard card,
                             final int mumble) {

        if(4 == this.getCardValue()){

        }
    }


    // Move mumble from PRE_FIELD to START_FIELD
    // - with 1 or 13
    // - angle card
    public void bringMumbleIntoPlay(final List<Player> allPlayers,
                                    final Player player,
                                    final int mumble) {

        // Is the selected mumble in the PRE_FIELD of the player?
        if(player.getMumbles().get(mumble).isMumbleInPreField()){

            // Check whether card equals card 1 or 13
            // todo: angle card
            if(1 == this.getCardValue() || 13 == this.getCardValue()) {

                player.getMumbles().get(mumble).moveMumble(
                        allPlayers,
                        player,
                        PLAYGROUND.START_FIELD,
                        player.getStartPosition());
            } else {
                System.out.println("not 1 or 13");
            }
        }
    }

}

