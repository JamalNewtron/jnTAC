import java.util.List;

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
                           final int targetPosition,
                           final boolean moveClockwise) {

        int startPosition = this.position;
        boolean isOccupied = false;

        switch (targetField) {
            case PRE_FIELD:

                // Check for occupation
                if(this.isOccupied(targetPosition)) {

                        isOccupied = true;
                        // Check whether the target position is occupied by own player
                        if (PlayingField.getPlayingField().getField(targetPosition).player.equals(player)) {
                            System.out.println("Can not move, position occupied by own mumble.");
                        } else {
                            System.out.println("Can not move, position occupied by own mumble.");
                            // If the position is occupied by an opponent mumble we can replace it with ours.
                            // Remove opponent mumble from position and move it to the PRE_FIELD
                            // Place own mumble on the target position
                            PlayingField.getPlayingField().placeMumbleIntoField(startPosition, targetPosition, this);
                        }
                }

                // not occupied by own mumble or by opponent mumble
                if(!isOccupied){
                    PlayingField.getPlayingField().placeMumbleIntoField(startPosition, targetPosition, this);
                }

                break;
            case START_FIELD:

                int plusOrMinusOne;
                if(moveClockwise){
                    plusOrMinusOne = 1;
                } else {
                    plusOrMinusOne = -1;
                }

                // We have to check whether the complete road is free.
                //for(int stepsToTargetPosition = (this.position + plusOrMinusOne);
                 //   stepsToTargetPosition <= targetPosition;
                  //  stepsToTargetPosition = (stepsToTargetPosition + plusOrMinusOne))
                //
                int stepsToTargetPosition = (this.position + plusOrMinusOne);
                while(Math.abs(stepsToTargetPosition - targetPosition) > 0){
                    System.out.println("steps" + stepsToTargetPosition);

                    // Check for occupation
                    if(this.isOccupied(stepsToTargetPosition)){
                        // Check whether this position is the target position
                        if(stepsToTargetPosition == targetPosition){
                            // Check whether the target position is occupied by own player
                            if (PlayingField.getPlayingField().getField(stepsToTargetPosition).player.equals(player)) {
                                System.out.println("Can not move, position occupied by own mumble.");
                            } else {
                                // If the position is occupied by an other mumble we can replace it with ours.
                                // Move the mumble to PRE.FIELD.
                                // Remove mumble from target position.
                                // Place own mumble on the target position.
                                PlayingField.getPlayingField().placeMumbleIntoField(startPosition, targetPosition, this);
                            }
                        // Not the target position but there is something on the road to the target.
                        } else {
                            System.out.println("road blocked @: " + stepsToTargetPosition);
                            // The road is blocked. Because something is on the road.
                            isOccupied = true;
                            // no need to look the other positions since the way is blocked.
                            break;
                        }
                    }
                    stepsToTargetPosition = stepsToTargetPosition + plusOrMinusOne;
                }

                // not occupied by own mumble or by opponent mumble
                if(!isOccupied){
                    PlayingField.getPlayingField().placeMumbleIntoField(startPosition, targetPosition, this);
                }
                break;
            case HOME_FIELD:
                break;
            default:
                System.out.println("out of area");
                break;
        }
    }

    public boolean isOccupied(final int positionToCheck) {
        if(PlayingField.getPlayingField().getField(positionToCheck) != null) {
            return true;
        } else {
            return false;
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
