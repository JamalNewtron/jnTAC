import java.util.List;

enum AVAILABLE_CARD_NUMBERS {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    ELEVEN,
    TWELVE,
    THIRTEEN
}

public class NumberedCard implements Card {

    private AVAILABLE_CARD_NUMBERS cardValue;

    public NumberedCard(final AVAILABLE_CARD_NUMBERS cardValue) {
        this.cardValue = cardValue;
    }


    public void setCardValue(final AVAILABLE_CARD_NUMBERS cardValue) {
        this.cardValue = cardValue;
    }

    public AVAILABLE_CARD_NUMBERS getCardValue() {
        return this.cardValue;
    }

    @Override
    public void playSelectedCard(final List<Player> allPlayers,
                                 final Player player,
                                 final int mumble) {

        switch (this.cardValue){

            case TWO:
                this.moveToPosition(allPlayers, player, mumble, 2);
                break;
            case THREE:
                this.moveToPosition(allPlayers, player, mumble, 3);
                break;
            case FIVE:
                this.moveToPosition(allPlayers, player, mumble, 5);
                break;
            case SIX:
                this.moveToPosition(allPlayers, player, mumble, 6);
                break;
            case NINE:
                this.moveToPosition(allPlayers, player, mumble, 9);
                break;
            case TEN:
                this.moveToPosition(allPlayers, player, mumble, 10);
                break;
            case ELEVEN:
                this.moveToPosition(allPlayers, player, mumble, 11);
                break;
            case TWELVE:
                this.moveToPosition(allPlayers, player, mumble, 12);
                break;
            default:
                System.out.println("default playSelectedCard");
                break;

        }
    }



    // move to x position from y position
    public void moveToPosition(final List<Player> allPlayers,
                               final Player player,
                               final int mumble,
                               final int steps) {

        // current position
        int currentMumblePosition = player.getMumbles().get(mumble).getPosition();


        int targetMumblePosition = currentMumblePosition + steps;
        System.out.println(player.getPlayerName()+  ", mumble: " + mumble);
        System.out.println(player.getPlayerName()+  ", start: " + currentMumblePosition);
        System.out.println(player.getPlayerName()+  ", steps: " + steps);
        System.out.println(player.getPlayerName()+  ", target: " + targetMumblePosition);;

        // Mumble enters HOME_FIELD or returns to new cycle

        if(targetMumblePosition < 65){
            player.getMumbles().get(mumble).moveMumble(allPlayers, player, PLAYGROUND.START_FIELD, targetMumblePosition, true);
        } else if(targetMumblePosition == 65){
            player.getMumbles().get(mumble).moveMumble(allPlayers, player, PLAYGROUND.START_FIELD, 1, true);
        } else if(targetMumblePosition > 65 && targetMumblePosition < 70){
            player.getMumbles().get(mumble).moveMumble(allPlayers,player, PLAYGROUND.HOME_FIELD, targetMumblePosition, true);
        } else if(targetMumblePosition > 69){
            targetMumblePosition = targetMumblePosition - 64;
            player.getMumbles().get(mumble).moveMumble(allPlayers, player, PLAYGROUND.START_FIELD, targetMumblePosition, true);
        }
    }
}
