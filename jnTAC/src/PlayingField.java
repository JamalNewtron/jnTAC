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

            // set player startNode to created start nodes
            allPlayers.get(i).setStartNode(this.field.getNode(allPlayers.get(i).getStartPosition()));

            // field element points with its branch node towards the players home field start
            this.field.setBranch(allPlayers.get(i).getStartPosition(), allPlayers.get(i).getHomeField().getStart());
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

    public DataStructure<Mumble> getField() {
        return this.field;
    }

    public Mumble getFieldElement(final int position) {
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

        FieldResult tempResult = new FieldResult();
        tempResult.setStartNode(this.field.getNode(player.getStartPosition()));
        System.out.println("startposition: " + player.getStartPosition() + " of player: " + player);
        this.checkMove(player, tempResult.getStartNode(), tempResult, player.getStartPosition(), mumble, player.getStartPosition(), true);
        return tempResult;
    }

    public FieldResult checkMove(final Player player,
                                 final int steps,
                                 final Mumble mumble,
                                 final boolean moveClockwise){

        FieldResult tempResult = new FieldResult();
        System.out.println("mumble: " + mumble);
        tempResult.setStartNode(this.field.getNode(mumble));

        this.checkMove(player, tempResult.getStartNode().getNextPrev(moveClockwise), tempResult, steps, mumble, 1, moveClockwise);
        return tempResult;
    }

    private void checkMove(final Player player,
                                  final ListNode<Mumble> currentNode,
                                  final FieldResult result,
                                  final int steps,
                                  final Mumble mumble,
                                  final int incrementalIndex,
                                  final boolean moveClockwise) {

        System.out.println("inkrement: " + incrementalIndex);
        System.out.println("currentNode: " + currentNode);
        if (currentNode.data != null) {
            System.out.println("occupied");
            System.out.println(currentNode.index);
            if (steps == incrementalIndex) {
                System.out.println("targeposition occupied");
                if (currentNode.data.getPlayer().equals(mumble.getPlayer())) {
                    System.out.println("target position occupied by own mumble");
                    result.setTargetNode(currentNode);
                    result.setOccupationStatus(OCCUPATION_STATUS.OCCUPIED_BY_ONESELF);
                } else {
                    System.out.println("target position occupied by opponent mumble");
                    result.setTargetNode(currentNode);
                    result.setOccupationStatus(OCCUPATION_STATUS.OCCUPIED_BY_OPPONENT);
                }
            } else {
                System.out.println("no target position, occupied");
                if (currentNode.data.getPlayer().equals(mumble.getPlayer())) {
                    System.out.println("no target position, occupied by own mumble");
                    result.setTargetNode(currentNode);
                    result.setOccupationStatus(OCCUPATION_STATUS.BLOCKED_BY_ONESELF);
                } else {
                    System.out.println("target position occupied by opponent mumble");
                    result.setTargetNode(currentNode);
                    result.setOccupationStatus(OCCUPATION_STATUS.BLOCKED_BY_OPPONENT);
                }
            }
        } else {
            System.out.println("unoccupied");
            if (steps == incrementalIndex) {
                System.out.println("targetposition unoccupied");
                result.setTargetNode(currentNode);
                result.setOccupationStatus(OCCUPATION_STATUS.UNOCCUPIED);
            } else {
                System.out.println("not target position, slot unoccupied");

                // landed on start position
                if (currentNode == player.getStartNode()) {

                    System.out.println("inside");
                    // mumble ready to ho home
                    if (mumble.isRoundDone()) {
                        this.checkMove(player, currentNode.branch, result, steps, mumble, incrementalIndex + 1, moveClockwise);
                    }
                }
                this.checkMove(player, currentNode.getNextPrev(moveClockwise), result, steps, mumble, incrementalIndex + 1, moveClockwise);
            }
        }
    }

    public void moveMumbleToPreField(final FieldResult result) {

        if(result.getTargetNode().data != null) {
            if(result.getTargetNode().data.getPlaygroundPosition() == PLAYGROUND.START_FIELD) {
                result.getTargetNode().data.setPosition(0, PLAYGROUND.PRE_FIELD);
                result.getTargetNode().data = null;
            }
        }
    }

    public void moveMumbleToStartField(final FieldResult result, final Mumble mumble) {
        // place new mumble into node
        if(result.getTargetNode().data == null) {
            result.getTargetNode().data = mumble;
            result.getTargetNode().data.setPosition(result.getTargetNode().index, PLAYGROUND.START_FIELD);
        }
    }

    public void moveMumbleWithinStartField(final FieldResult result, final Mumble mumble) {

        if(result.getTargetNode().data == null) {
            result.getTargetNode().data = mumble;
            result.getTargetNode().data.setPosition(result.getTargetNode().index, PLAYGROUND.START_FIELD);

            // free up old node
            result.getStartNode().data = null;

        }


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
