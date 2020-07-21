import java.util.LinkedList;
import java.util.List;

public class PlayingField {

    private static PlayingField playingField;
    private DataStructure<Mumble> field;
    private List<Player> allPlayers;

    private PlayingField(List<Player> allPlayers){
        this.field = new DataStructure<>();
        this.allPlayers = allPlayers;

        // first element Mumble[0] is should not be used.
        for(int i = 0; i < 65; i++) {
            this.field.add(null);
        }

        // add player object to available players
        for(int i = 0; i < allPlayers.size(); i++){
            this.field.set(allPlayers.get(i).getStartPosition(), null, allPlayers.get(i));
        }
    }

    public static PlayingField getPlayingField() {
        return getPlayingField(null);
    }

    public static PlayingField getPlayingField(List<Player> allPlayers) {

        if(PlayingField.playingField == null){
            PlayingField.playingField = new PlayingField(allPlayers);
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
            System.out.println("field@ " + position + " is empty.");
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
