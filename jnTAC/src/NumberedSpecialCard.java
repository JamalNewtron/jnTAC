import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Scanner;

public class NumberedSpecialCard extends NumberedCard {


    public NumberedSpecialCard(final AVAILABLE_CARD_NUMBERS cardValue) {
        super(cardValue);
    }

    public String getCardName() {
        switch (this.getCardValue()){
            case ONE:
                return "1";
            case FOUR:
                return "4";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case THIRTEEN:
                return "13";
            default:
                return "NumberedSpecialCard";
        }
    }

    public void playSelectedCard(final List<Player> allPlayers,
                                 final Player player,
                                 final int mumble) {

        switch (this.getCardValue()){
            case ONE:
                //this.moveToPosition(allPlayers, player, mumble, 1);
                this.bringMumbleIntoPlay(allPlayers, player, mumble);
                break;
            case FOUR:
                this.moveFourBack(allPlayers, player, mumble);
                break;
            case SEVEN:
                this.executeSplitMovement(allPlayers, player, mumble);
                break;
            case EIGHT:
                break;
            case THIRTEEN:
                //this.moveToPosition(allPlayers, player, mumble, 13);
                this.bringMumbleIntoPlay(allPlayers, player, mumble);
                break;
            default:
                System.out.println("default playSelectedCard");
                break;
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
            if(AVAILABLE_CARD_NUMBERS.ONE == this.getCardValue() ||
                    AVAILABLE_CARD_NUMBERS.THIRTEEN == this.getCardValue()) {

                player.getMumbles().get(mumble).moveMumble(
                        allPlayers,
                        player,
                        PLAYGROUND.PRE_FIELD,
                        player.getStartPosition(),
                        this,
                        true);
            } else {
                System.out.println("not 1 or 13");
            }
        }
    }

    // CARD FOUR
    public void moveFourBack(final List<Player> allPlayers,
                             final Player player,
                             final int mumble) {

        // Additional check
        if(AVAILABLE_CARD_NUMBERS.FOUR == this.getCardValue()){

            player.getMumbles().get(mumble).moveMumble(
                    allPlayers,
                    player,
                    PLAYGROUND.START_FIELD,
                    4,
                    this,
                    false);
        }
    }

    public void executeSplitMovement(final List<Player> allPlayers,
                                     final Player player,
                                     final int mumble) {
        // Additional check
        if(AVAILABLE_CARD_NUMBERS.SEVEN == this.getCardValue()) {
            Scanner in = new Scanner(System.in);
            String userInput;
            int stepsLeft = 7;
            boolean moveClockwise = true;

            while (stepsLeft > 0) {

                System.out.println("Input split move: ");
                userInput = in.nextLine();
                int splitMove = Integer.parseInt(userInput);

                // direction selection is only allowed inside home!
                // todo: remove playground attribute inside mumble
                if (player.getMumbles().get(mumble).getNode().playground == PLAYGROUND.HOME_FIELD) {
                    System.out.println("Select direction (1 = clockwise): ");
                    userInput = in.nextLine();
                    if (Integer.parseInt(userInput) == 1) {
                        moveClockwise = true;
                    } else {
                        moveClockwise = false;
                    }
                }

                // decrease left steps
                stepsLeft = stepsLeft - splitMove;

                player.getMumbles().get(mumble).moveMumble(
                        allPlayers,
                        player,
                        PLAYGROUND.START_FIELD,
                        splitMove,
                        this,
                        moveClockwise);
            }
        }
    }

}

