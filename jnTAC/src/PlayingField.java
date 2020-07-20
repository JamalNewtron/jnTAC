import java.util.LinkedList;
import java.util.List;

public class PlayingField {

    private static PlayingField playingField;
    private List<Mumble> field;

    private PlayingField(){
        // first element Mumble[0] is should not be used.
        this.field = new LinkedList<Mumble>();
        for(int i = 0; i < 65; i++) {
            this.field.add(null);
        }
    }

    public static PlayingField getPlayingField() {

        if(PlayingField.playingField == null){
            PlayingField.playingField = new PlayingField();
        }
        return PlayingField.playingField;
    }

    public Mumble getField(final int position) {
        return this.field.get(position);
    }

    public void removeMumbleFromField(final int position,
                                      final Mumble mumble) {
        // Check whether field is already empty.
        if(this.field.get(position) != null){
            this.field.get(position).setPosition(0, PLAYGROUND.PRE_FIELD);
            this.field.set(position, null);
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
        this.field.set(targetPosition, mumble);

        // We also store the position information inside the mumble.
        mumble.setPosition(targetPosition, PLAYGROUND.START_FIELD);

        // Remove entry at start slot.
        this.field.set(startPosition, null);
    }
}
