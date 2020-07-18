import java.util.List;

enum PLAYGROUND {

    PRE_FIELD,
    START_FIELD,
    HOME_FIELD
}

public class Mumble {
    // current position inside a field
    private int currentPosition;
    // current playgroud position
    private PLAYGROUND currentField;

    public Mumble() {
        this(0, PLAYGROUND.PRE_FIELD);
    }

    public Mumble(final int startPosition, final PLAYGROUND startField) {
        this.currentPosition = startPosition;
        this.currentField = startField;
    }


    // getter for intPositon
    public int getCurrentPosition() {
        return this.currentPosition;
    }

    // getter for playgroundPosition
    public PLAYGROUND getPlaygroundPosition() {
        return this.currentField;
    }

    // setter for intPosition and playgroundPosition
    private void setCurrentPosition(final int nextPosition,
                                    final PLAYGROUND nextField) {
        this.currentPosition = nextPosition;
        this.currentField = nextField;
    }


    // Check whether position is free or already occupied and move mumble to specified target
    public void moveMumble(final List<Player> allPlayers,
                           final Player currentPlayer,
                           final PLAYGROUND targetField,
                           final int targetPosition) {

        switch (targetField) {
            case PRE_FIELD:
                System.out.println("Only position '0' permitted at PRE_FIELD");
                break;
            case START_FIELD:

                boolean isOccupied = false;

                // itterate through all players
                for (int i = 0; i < allPlayers.size(); i++) {

                    // itterate through all mumbles of a player
                    for (int n = 0; n < allPlayers.get(i).getMumbles().size(); n++) {

                        // Check whether position is occupied by same or different player
                        if (allPlayers.get(i).getMumbles().get(n).currentPosition == targetPosition) {

                            isOccupied = true;

                            System.out.println("Player: " +
                                    allPlayers.get(i).getPlayerNumber() +
                                    " has a mumble at the Position: " +
                                    allPlayers.get(i).getMumbles().get(n).currentPosition);


                            // Check wheter position is occupied by own player
                            if (allPlayers.get(i).equals(currentPlayer)) {
                                System.out.println("Can not move, position occupied by own mumble.");
                            } else {
                                // Remove mumble from position and move it to the PRE_FIELD
                                allPlayers.get(i).getMumbles().get(n).setCurrentPosition(0, PLAYGROUND.PRE_FIELD);

                                // Place Mumble on the position
                                this.currentPosition = targetPosition;
                            }
                        }
                    }
                }

                if(!isOccupied){
                    this.currentPosition = targetPosition;
                }

                break;
            case HOME_FIELD:
                break;
            default:
                System.out.println("außerhalb des Bereichs");
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
