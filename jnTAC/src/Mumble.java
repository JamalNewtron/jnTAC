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
                           final Player player,
                           final PLAYGROUND targetField,
                           final int targetPosition,
                           final boolean moveClockwise) {

        boolean isOccupied = false;

        switch (targetField) {
            case PRE_FIELD:

                // itterate through all players
                for(int i = 0; i < allPlayers.size(); i++) {
                    // itterate through all mumbles of a player
                    for(int n = 0; n < allPlayers.get(i).getMumbles().size(); n++) {
                        // Check whether position is occupied by same or different player
                        if(allPlayers.get(i).getMumbles().get(n).currentPosition == targetPosition) {
                            isOccupied = true;
                            // Check whether the target position is occupied by own player
                            if (allPlayers.get(i).equals(player)) {
                                System.out.println("Can not move, position occupied by own mumble.");
                            } else {
                                // If the position is occupied by an other mumble we can replace it with ours.
                                // Remove mumble from position and move it to the PRE_FIELD
                                allPlayers.get(i).getMumbles().get(n).setCurrentPosition(0, PLAYGROUND.PRE_FIELD);
                                // Place own mumble on the target position
                                this.currentPosition = targetPosition;
                            }
                        }
                    }
                }

                // not occupied by own mumble or by opponent mumble
                if(!isOccupied){
                    this.currentPosition = targetPosition;
                }

                break;
            case START_FIELD:

                int plusOrMinusOne;
                if(moveClockwise){
                    plusOrMinusOne = 1;
                } else {
                    plusOrMinusOne = -1;
                }

                // We have to check whether the complete way is free or not
                outerLoop:
                for(int stepsToTargetPosition = (this.currentPosition + 1);
                        stepsToTargetPosition <= targetPosition;
                        stepsToTargetPosition = stepsToTargetPosition + plusOrMinusOne) {

                    // itterate through all players
                    for(int i = 0; i < allPlayers.size(); i++) {
                        // itterate through all mumbles of a player
                        for(int n = 0; n < allPlayers.get(i).getMumbles().size(); n++) {

                            // Check whether position is occupied by same or different player
                            if(allPlayers.get(i).getMumbles().get(n).currentPosition == stepsToTargetPosition) {
                                // Check whether this position is the target position
                                if(stepsToTargetPosition == targetPosition){
                                    // Check whether the target position is occupied by own player
                                    if (allPlayers.get(i).equals(player)) {
                                        System.out.println("Can not move, position occupied by own mumble.");
                                    } else {
                                        // If the position is occupied by an other mumble we can replace it with ours.
                                        // Remove mumble from position and move it to the PRE_FIELD
                                        allPlayers.get(i).getMumbles().get(n).setCurrentPosition(0, PLAYGROUND.PRE_FIELD);
                                        // Place own mumble on the target position
                                        this.currentPosition = targetPosition;
                                    }
                                // Not the target position but there is something on the road to the target.
                                } else {
                                    System.out.println("road blocked @: " + stepsToTargetPosition);
                                    // The road is blocked. Because something is on the road.
                                    isOccupied = true;
                                    // no need to look the other positions since the way is blocked.
                                    break outerLoop;
                                }
                            }
                        }
                    }
                }

                // not occupied by own mumble or by opponent mumble
                if(!isOccupied){
                    this.currentPosition = targetPosition;
                }

                break;
            case HOME_FIELD:
                break;
            default:
                System.out.println("out of area");
                break;
        }
    }


    public void isOccupied() {



    }








    // player has mumbles in PRE_FIELD
    public boolean isMumbleInPreField() {

        if (PLAYGROUND.PRE_FIELD == this.currentField) {
            return true;
        }
        return false;
    }




}
