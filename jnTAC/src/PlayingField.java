import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;

enum OCCUPATION_STATUS {
    UNOCCUPIED,
    OCCUPIED_BY_ONESELF,
    OCCUPIED_BY_OPPONENT,
    BLOCKED_BY_ONESELF,
    BLOCKED_BY_OPPONENT
}

public class PlayingField {

    private static PlayingField playingField;
    private DataStructure<Mumble> field;
    private List<Player> allPlayers;

    private PlayingField(List<Player> allPlayers){
        this.field = new DataStructure<Mumble>();
        this.allPlayers = allPlayers;

        // first slot is position 0
        for(int i = 0; i < 64; i++) {
            this.field.add(null, PLAYGROUND.START_FIELD);
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

    public FieldResult checkStartposition(final Player player, final Mumble mumble) {

        FieldResult temp = new FieldResult();
        temp.setStartNode(this.field.getNode(player.getStartPosition()));

        return this.checkMove(temp.getStartNode(), temp, 0, mumble, 0, true);
    }

    public FieldResult checkMove(final int steps,
                                 final Mumble mumble,
                                 final boolean moveClockwise){

        FieldResult temp = new FieldResult();
        temp.setStartNode(this.field.getNode(mumble));

        return this.checkMove(temp.getStartNode().getNextPrev(moveClockwise), temp,steps, mumble, 1, moveClockwise);
    }

    private FieldResult checkMove(final ListNode<Mumble> currentNode,
                                  final FieldResult result,
                                  final int steps,
                                  final Mumble mumble,
                                  final int incrementalIndex,
                                  final boolean moveClockwise) {

        System.out.println("inkrement: " + incrementalIndex);
        if(currentNode.data != null) {
            System.out.println("occupied");
            System.out.println(currentNode.index);
            if(steps == incrementalIndex) {
                System.out.println("targeposition occupied");
                if(currentNode.data.getPlayer().equals(mumble.getPlayer())) {
                    System.out.println("target position occupied by own mumble");
                    result.setTargetNode(currentNode);
                    result.setOccupationStatus(OCCUPATION_STATUS.OCCUPIED_BY_ONESELF);
                } else {
                    System.out.println("target position occupied by opponent mumble");
                    result.setTargetNode(currentNode);
                    result.setOccupationStatus(OCCUPATION_STATUS.OCCUPIED_BY_OPPONENT);
                }
                return result;
            } else {
                System.out.println("no target position, occupied");
                if(currentNode.data.getPlayer().equals(mumble.getPlayer())) {
                    System.out.println("no target position, occupied by own mumble");
                    result.setTargetNode(currentNode);
                    result.setOccupationStatus(OCCUPATION_STATUS.BLOCKED_BY_ONESELF);
                } else {
                    System.out.println("target position occupied by opponent mumble");
                    result.setTargetNode(currentNode);
                    result.setOccupationStatus(OCCUPATION_STATUS.BLOCKED_BY_OPPONENT);
                }
                return result;
            }
        } else {
            System.out.println("unoccupied");
            if(steps == incrementalIndex) {
                System.out.println("targetposition unoccupied");
                result.setTargetNode(currentNode);
                result.setOccupationStatus(OCCUPATION_STATUS.UNOCCUPIED);
                return result;
            } else {
                System.out.println("not target position, slot unoccupied");
                return this.checkMove(currentNode.getNextPrev(moveClockwise), result, steps, mumble, incrementalIndex + 1, moveClockwise);
            }
        }
    }

    public void throwMumble(final FieldResult result, final Mumble mumble) {
        // if there is a mumble, move it to pre field
        if(result.getTargetNode().data != null) {
            result.getTargetNode().data = null;
            //result.getTargetNode().data.setPosition( 0, PLAYGROUND.PRE_FIELD);
        }

        // place new mumble into node
        result.getTargetNode().data = mumble;
        result.getTargetNode().data.setPosition(result.getTargetNode().index, PLAYGROUND.START_FIELD);

        System.out.println("position" + result.getTargetNode().data.getPosition());
        System.out.println("ground" + result.getTargetNode().data.getPlaygroundPosition());

        // clear old position
        //result.getStartNode().data = null;

        System.out.println("position" + result.getTargetNode().data.getPosition());
        System.out.println("ground" + result.getTargetNode().data.getPlaygroundPosition());

    }




}

class FieldResult {

    ListNode<Mumble> startNode;
    ListNode<Mumble> targetNode;
    OCCUPATION_STATUS occupationStatus;

    public FieldResult() {
        this(null, null, null);
    }

    public FieldResult(ListNode<Mumble> startNode, ListNode<Mumble> targetNode, OCCUPATION_STATUS occupationStatus) {
        this.startNode = startNode;
        this.targetNode = targetNode;
        this.occupationStatus = occupationStatus;
    }

    public void setTargetNode(ListNode<Mumble> targetNode) {
        this.targetNode = targetNode;
    }

    public ListNode<Mumble> getStartNode() {
        return startNode;
    }

    public void setStartNode(ListNode<Mumble> startNode) {
        this.startNode = startNode;
    }

    public void setOccupationStatus(OCCUPATION_STATUS occupationStatus) {
        this.occupationStatus = occupationStatus;
    }

    public ListNode<Mumble> getTargetNode() {
        return this.targetNode;
    }

    public OCCUPATION_STATUS getOccupationStatus() {
        return this.occupationStatus;
    }

}
