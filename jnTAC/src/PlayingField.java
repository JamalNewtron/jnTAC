import java.util.LinkedList;
import java.util.List;

public class PlayingField {

    private static PlayingField playingField;
    private DataStructure<Mumble> field;
    private List<Player> allPlayers;

    private PlayingField(List<Player> allPlayers){
        this.field = new DataStructure<>();
        this.allPlayers = allPlayers;

        // first slot is position 0
        for(int i = 0; i < 64; i++) {
            this.field.add(null);
        }

        // player specific modifications
        for(int i = 0; i < allPlayers.size(); i++) {
            // mark the players start position with the player
            this.field.set(allPlayers.get(i).getStartPosition(), null, allPlayers.get(i));

            // field element points with its branch node towards the players home field start
            this.field.setBranch(i, allPlayers.get(i).getHomeField().getStart());
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

    public void check(final int steps,
                      final Mumble mumble,
                      final boolean moveClockwise){

        this.check(this.field.getNode(mumble).next, steps, mumble, 0, moveClockwise);
    }

    private void check(final ListNode<Mumble> current,
                       final int steps,
                       final Mumble mumble,
                       final int incrementalIndex,
                       final boolean moveClockwise) {


        if(moveClockwise){
            if(current.data != null){
                System.out.println("besetzt");
                if(steps == incrementalIndex) {
                    System.out.println("besetzt und Ziel");
                    if()
                }

            } else {
                System.out.println("unbesetzt");
                if(steps == incrementalIndex) {
                    System.out.println("unbesetzt und Ziel");
                }
            }
        } else {

            if(this.field.getNode(mumble).data != null){
                System.out.println("besetzt");
                if(steps == incrementalIndex) {
                    System.out.println("besetzt und Ziel");
                    if()
                }
            } else {
                System.out.println("unbesetzt");
                if(steps == incrementalIndex) {
                    System.out.println("unbesetzt und Ziel");
                }
            }
        }

        this.check(current.next, steps, mumble, incrementalIndex + 1, moveClockwise);
    }

}
