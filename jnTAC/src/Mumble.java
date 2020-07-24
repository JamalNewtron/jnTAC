import java.util.List;
import java.util.Scanner;

enum PLAYGROUND {

    PRE_FIELD,
    START_FIELD,
    HOME_FIELD
}

public class Mumble {
    // to which player does the mumble belong
    private Player player;
    // current position inside a field
    private int position;
    // current playgroud position
    private PLAYGROUND currentField;
    // made a turn
    private boolean roundDone;

    public Mumble(final Player player) {
        this(player, 0, PLAYGROUND.PRE_FIELD, false);
    }

    public Mumble(final Player player, final int startPosition, final PLAYGROUND startField, final boolean roundDone) {
        this.player = player;
        this.position = startPosition;
        this.currentField = startField;
        this.roundDone = roundDone;
    }

    public Player getPlayer() {
        return player;
    }


    // getter for intPositon
    public int getPosition() {
        return this.position;
    }

    // getter for playgroundPosition
    public PLAYGROUND getPlaygroundPosition() {
        return this.currentField;
    }

    // setter for intPosition and playgroundPosition
    public void setPosition(final int nextPosition,
                            final PLAYGROUND nextField) {
        this.position = nextPosition;
        this.currentField = nextField;
    }


    // Check whether position is free or already occupied and move mumble to specified target
    public void moveMumble(final List<Player> allPlayers,
                           final Player player,
                           final PLAYGROUND targetField,
                           final int steps,
                           final Card card,
                           final boolean moveClockwise) {
        Scanner in = new Scanner(System.in);

        String userInput;
        int startPosition = this.position;
        boolean isOccupied = false;
        FieldResult temp;

        switch (targetField) {
            case PRE_FIELD:

                temp = PlayingField.getPlayingField().checkStartposition(player, this);


                if(temp.getOccupationStatus() == OCCUPATION_STATUS.UNOCCUPIED) {
                    System.out.println("Do you really want to placcceee?");
                    userInput = in.nextLine();
                    if(userInput.equals("y") || userInput.equals("yes")) {
                        PlayingField.getPlayingField().moveMumbleToStartField(temp, this);
                    }
                }

                if(temp.getOccupationStatus() == OCCUPATION_STATUS.OCCUPIED_BY_OPPONENT) {
                    System.out.println("Do you really want to placcceee?");
                    userInput = in.nextLine();
                    if(userInput.equals("y") || userInput.equals("yes")) {
                        // send mumble already on start position to home
                        PlayingField.getPlayingField().moveMumbleToPreField(temp);

                        // place mumble to target
                        PlayingField.getPlayingField().moveMumbleToStartField(temp, this);
                    }
                }

                if(temp.getOccupationStatus() == OCCUPATION_STATUS.OCCUPIED_BY_ONESELF) {
                    System.out.println("occupied by yourself");
                }

                player.discardCard(card);

                break;
            case START_FIELD:
                temp = PlayingField.getPlayingField().checkMove(steps, this, moveClockwise);

                if(temp.getOccupationStatus() == OCCUPATION_STATUS.UNOCCUPIED) {
                    System.out.println("Do you really want to place?");
                    userInput = in.nextLine();
                    if(userInput.equals("y") || userInput.equals("yes")) {
                        PlayingField.getPlayingField().moveMumbleWithinStartField(temp, this);
                    }
                }
                if(temp.getOccupationStatus() == OCCUPATION_STATUS.OCCUPIED_BY_OPPONENT) {
                    System.out.println("Do you really want to place?");
                    userInput = in.nextLine();
                    if(userInput.equals("y") || userInput.equals("yes")) {

                        PlayingField.getPlayingField().moveMumbleToPreField(temp);
                        PlayingField.getPlayingField().moveMumbleWithinStartField(temp, this);

                    }
                }

                if(temp.getOccupationStatus() == OCCUPATION_STATUS.OCCUPIED_BY_ONESELF) {
                    System.out.println("Do you really want to place?");
                    userInput = in.nextLine();
                    if(userInput.equals("y") || userInput.equals("yes")) {

                        PlayingField.getPlayingField().moveMumbleToPreField(temp);
                        PlayingField.getPlayingField().moveMumbleWithinStartField(temp, this);

                    }
                }

                player.discardCard(card);


                break;
            case HOME_FIELD:
                break;
            default:
                System.out.println("out of area");
                break;
        }
    }

    // player has mumbles in PRE_FIELD
    public boolean isMumbleInPreField() {
        if(PLAYGROUND.PRE_FIELD == this.currentField) {
            return true;
        }
        return false;
    }




}
