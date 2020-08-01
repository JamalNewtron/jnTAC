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
    // todo: define own node!
    private ListNode<Mumble> node;

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

    public boolean isRoundDone() {
        return roundDone;
    }

    public void setRoundDone(boolean roundDone) {
        this.roundDone = roundDone;
    }

    public ListNode<Mumble> getNode() {
        return node;
    }

    public void setNode(ListNode<Mumble> node) {
        this.node = node;
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

                int selectedPosition = 0;

                if (temp.getOccupationStatuses().get(selectedPosition) == OCCUPATION_STATUS.UNOCCUPIED) {
                    System.out.println("Do you really want to placcceee?");
                    userInput = in.nextLine();
                    if (userInput.equals("y") || userInput.equals("yes")) {
                        PlayingField.getPlayingField().moveMumbleToStartField(temp, this, selectedPosition);
                    }
                }

                if (temp.getOccupationStatuses().get(selectedPosition) == OCCUPATION_STATUS.OCCUPIED_BY_OPPONENT) {
                    System.out.println("Do you really want to placcceee?");
                    userInput = in.nextLine();
                    if (userInput.equals("y") || userInput.equals("yes")) {
                        // send mumble already on start position to home
                        PlayingField.getPlayingField().moveMumbleToPreField(temp, selectedPosition);

                        // place mumble to target
                        PlayingField.getPlayingField().moveMumbleToStartField(temp, this, selectedPosition);
                    }
                }

                if (temp.getOccupationStatuses().get(selectedPosition) == OCCUPATION_STATUS.OCCUPIED_BY_ONESELF) {
                    System.out.println("occupied by yourself");
                }

                //player.discardCard(card);

                break;
            case START_FIELD:
                temp = PlayingField.getPlayingField().checkMove(player, steps, this, moveClockwise);

                int input = 0;
                // todo: when road is blocked -> user should not be allowed to choose blocked way!
                if (temp.getOccupationStatuses().size() > 1) {
                    System.out.println("There are more than one possible position");
                    System.out.println("Position ");
                    for (int i = 0; i < temp.getOccupationStatuses().size(); i++) {
                        System.out.println("Position("
                                + i
                                + "): "
                                + temp.getTargetNodes().get(i).playground
                                + "    "
                                + temp.getTargetNodes().get(i).index);
                    }

                    System.out.println("move to position 0 or 1");

                    userInput = in.nextLine();

                    input = Integer.parseInt(userInput);
                    if (userInput.equals("1") || userInput.equals("2")) {
                        if (temp.getOccupationStatuses().get(input) == OCCUPATION_STATUS.UNOCCUPIED) {
                            System.out.println("UNOCCUPIED: Do you really want to place?");
                            userInput = in.nextLine();
                            if (userInput.equals("y") || userInput.equals("yes")) {
                                PlayingField.getPlayingField().moveMumbleWithinStartField(temp, this, input);
                            }
                        }

                        if (temp.getOccupationStatuses().get(input) == OCCUPATION_STATUS.OCCUPIED_BY_OPPONENT) {
                            System.out.println("OCCUPIED_BY_OPPONENT: Do you really want to place?");
                            userInput = in.nextLine();
                            if (userInput.equals("y") || userInput.equals("yes")) {

                                PlayingField.getPlayingField().moveMumbleToPreField(temp, input);
                                PlayingField.getPlayingField().moveMumbleWithinStartField(temp, this, input);

                            }
                        }

                        if (temp.getOccupationStatuses().get(input) == OCCUPATION_STATUS.OCCUPIED_BY_ONESELF) {
                            System.out.println("OCCUPIED_BY_ONESELF: Do you really want to place?");
                            userInput = in.nextLine();
                            if (userInput.equals("y") || userInput.equals("yes")) {

                                PlayingField.getPlayingField().moveMumbleToPreField(temp, input);
                                PlayingField.getPlayingField().moveMumbleWithinStartField(temp, this, input);
                            }
                        }

                    }
                } else {

                    if (temp.getOccupationStatuses().get(input) == OCCUPATION_STATUS.UNOCCUPIED) {
                        System.out.println("Do you really want to place?");
                        userInput = in.nextLine();
                        if (userInput.equals("y") || userInput.equals("yes")) {
                            PlayingField.getPlayingField().moveMumbleWithinStartField(temp, this, input);
                        }
                    }

                    if (temp.getOccupationStatuses().get(input) == OCCUPATION_STATUS.OCCUPIED_BY_OPPONENT) {
                        System.out.println("Do you really want to place?");
                        userInput = in.nextLine();
                        if (userInput.equals("y") || userInput.equals("yes")) {

                            PlayingField.getPlayingField().moveMumbleToPreField(temp, input);
                            PlayingField.getPlayingField().moveMumbleWithinStartField(temp, this, input);

                        }
                    }

                    if (temp.getOccupationStatuses().get(input) == OCCUPATION_STATUS.OCCUPIED_BY_ONESELF) {
                        System.out.println("Do you really want to place?");
                        userInput = in.nextLine();
                        if (userInput.equals("y") || userInput.equals("yes")) {

                            PlayingField.getPlayingField().moveMumbleToPreField(temp, input);
                            PlayingField.getPlayingField().moveMumbleWithinStartField(temp, this, input);
                        }
                    }


                }


                //player.discardCard(card);


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
        if (PLAYGROUND.PRE_FIELD == this.currentField) {
            return true;
        }
        return false;
    }


}
