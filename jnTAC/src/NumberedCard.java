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
    public String getCardName() {
        switch (this.cardValue){
            case TWO:
                return "2";
            case THREE:
                return "3";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case NINE:
                return "9";
            case TEN:
                return "10";
            case ELEVEN:
                return "11";
            case TWELVE:
                return "12";
            default:
                return "NumberedCard";
        }
    }

    @Override
    public void playSelectedCard(final List<Player> allPlayers,
                                 final Player player,
                                 final int mumble) {

        switch (this.cardValue){

            case TWO:
                this.calculateTargetPosition(allPlayers, player, mumble, 2);
                break;
            case THREE:
                this.calculateTargetPosition(allPlayers, player, mumble, 3);
                break;
            case FIVE:
                this.calculateTargetPosition(allPlayers, player, mumble, 5);
                break;
            case SIX:
                this.calculateTargetPosition(allPlayers, player, mumble, 6);
                break;
            case NINE:
                this.calculateTargetPosition(allPlayers, player, mumble, 9);
                break;
            case TEN:
                this.calculateTargetPosition(allPlayers, player, mumble, 10);
                break;
            case ELEVEN:
                this.calculateTargetPosition(allPlayers, player, mumble, 11);
                break;
            case TWELVE:
                this.calculateTargetPosition(allPlayers, player, mumble, 12);
                break;
            default:
                System.out.println("default playSelectedCard");
                break;

        }
    }




    // move to x position from y position
    public void calculateTargetPosition(final List<Player> allPlayers,
                                        final Player player,
                                        final int mumble,
                                        final int steps) {

        // current position
        int currentMumblePosition = player.getMumbles().get(mumble).getPosition();

        int tempCurrentMumblePosition = currentMumblePosition - player.getStartPosition();
        if(tempCurrentMumblePosition < 0) {
            tempCurrentMumblePosition += 64;
        }

        int targetMumblePosition = currentMumblePosition + steps;

        System.out.println(player.getPlayerName()+  ", mumble: " + mumble);
        System.out.println(player.getPlayerName()+  ", start: " + currentMumblePosition);
        System.out.println(player.getPlayerName()+  ", steps: " + steps);
        System.out.println(player.getPlayerName()+  ", target: " + targetMumblePosition);

        // Mumble enters HOME_FIELD or returns to new cycle

        player.getMumbles().get(mumble).moveMumble(allPlayers, player, PLAYGROUND.START_FIELD, steps, this , true);


/*        // 0 bis 63 erlaubt
        if(tempCurrentMumblePosition <= 63){
            player.getMumbles().get(mumble).moveMumble(allPlayers, player, PLAYGROUND.START_FIELD, targetMumblePosition, this , true);

        // positions  63
        } else if(targetMumblePosition == 64){
            player.getMumbles().get(mumble).moveMumble(allPlayers, player, PLAYGROUND.START_FIELD, targetMumblePosition, this, true);

        // could land in the
        } else if(targetMumblePosition > 64 && targetMumblePosition < 69){
            player.getMumbles().get(mumble).moveMumble(allPlayers,player, PLAYGROUND.HOME_FIELD, targetMumblePosition, this, true);

        //
        } else if(targetMumblePosition > 68){
            targetMumblePosition = targetMumblePosition - 64;
            player.getMumbles().get(mumble).moveMumble(allPlayers, player, PLAYGROUND.START_FIELD, targetMumblePosition, this, true);
        }*/
    }
}
