public class PlayingField {

    private static PlayingField playingField;
    private Mumble[] field;

    private PlayingField(){
        this.field = new Mumble[65];
    }

    public static PlayingField getPlayingField() {

        if(PlayingField.playingField == null){
            PlayingField.playingField = new PlayingField();
        }
        return PlayingField.playingField;
    }

    public Mumble getField(final int position) {
        return this.field[position];
    }

    public void removeMumbleFromField(final int position,
                                      final Mumble mumble) {
        // Check whether field is already empty.
        if(this.field[position] != null){
            this.field[position].setPosition(0, PLAYGROUND.PRE_FIELD);
            this.field[position] = null;
        } else {
            System.out.println("field@ " + position + " already empty.");
        }
    }

    public void placeMumbleIntoField(final int startPosition,
                                     final int targetPosition,
                                     final Mumble mumble){
        // Check whether target field is already empty.
        this.removeMumbleFromField(targetPosition, mumble);

        // Next we place the new mumble into place.
        this.field[targetPosition] = mumble;

        // We also store the position information inside the mumble.
        mumble.setPosition(targetPosition, PLAYGROUND.START_FIELD);

        // remove start entry
        this.field[startPosition] = null;
    }
}
