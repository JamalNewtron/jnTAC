import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.LinkedList;
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
        for (int i = 0; i < allPlayers.size(); i++) {
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
            this.field.get(position).setCurrentField(PLAYGROUND.PRE_FIELD);
            this.field.get(position).setNode(null);
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
        mumble.setNode(this.field.getNode(targetPosition));
        //mumble.setPosition(targetPosition, PLAYGROUND.START_FIELD);

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
        tempResult.setStartNode(mumble.getNode());
        //tempResult.setStartNode(this.field.getNode(mumble));


        this.checkMove(player, tempResult.getStartNode(), tempResult, steps, mumble, 0, moveClockwise);
        return tempResult;
    }

    private void checkMove(final Player player,
                           final ListNode<Mumble> currentNode,
                           final FieldResult result,
                           final int steps,
                           final Mumble mumble,
                           final int incrementalIndex,
                           final boolean moveClockwise) {

        if (currentNode == player.getStartNode()) {
            System.out.println("startNode");

            if (mumble.isRoundDone()) {
                System.out.println("roundDone");

                System.out.println("inkrement: " + incrementalIndex);
                System.out.println("currentNode: " + currentNode);
                if (currentNode.data != null) {
                    System.out.println("occupied");
                    System.out.println(currentNode.index);
                    if (steps == incrementalIndex) {
                        System.out.println("targeposition occupied");
                        if (currentNode.data.getPlayer().equals(mumble.getPlayer())) {
                            System.out.println("target position occupied by own mumble");
                            result.getTargetNodes().add(currentNode);
                            result.getOccupationStatuses().add(OCCUPATION_STATUS.OCCUPIED_BY_ONESELF);
                        } else {
                            System.out.println("target position occupied by opponent mumble");
                            result.getTargetNodes().add(currentNode);
                            result.getOccupationStatuses().add(OCCUPATION_STATUS.OCCUPIED_BY_OPPONENT);
                        }
                    } else {
                        System.out.println("no target position, occupied");

                        if (currentNode.data.equals(mumble)) {
                            System.out.println("no target position, occupied by mumble itself");

                            this.checkMove(player, currentNode.getNextPrev(moveClockwise), result, steps, mumble, incrementalIndex + 1, moveClockwise);

                            // restrict branch only for clockwise, it is not allowed to move into branch when moving backwards e.g. with card 4
                            if (moveClockwise) {
                                this.checkMove(player, currentNode.branch, result, steps, mumble, incrementalIndex + 1, moveClockwise);
                            }

                        } else {
                            if (currentNode.data.getPlayer().equals(mumble.getPlayer())) {
                                System.out.println("no target position, occupied by own mumble");
                                result.getTargetNodes().add(currentNode);
                                result.getOccupationStatuses().add(OCCUPATION_STATUS.OCCUPIED_BY_OPPONENT);
                            } else {
                                System.out.println("target position occupied by opponent mumble");
                                result.getTargetNodes().add(currentNode);
                                result.getOccupationStatuses().add(OCCUPATION_STATUS.BLOCKED_BY_OPPONENT);
                            }
                        }
                    }
                } else {
                    System.out.println("unoccupied");
                    if (steps == incrementalIndex) {
                        System.out.println("targetposition unoccupied");
                        System.out.println("result: " + result.getTargetNodes());
                        result.getTargetNodes().add(currentNode);
                        result.getOccupationStatuses().add(OCCUPATION_STATUS.UNOCCUPIED);
                    } else {
                        System.out.println("not target position, slot unoccupied");

                        if(currentNode.next == null) {
                            System.out.println("no following node");
                        } else {
                            this.checkMove(player, currentNode.getNextPrev(moveClockwise), result, steps, mumble, incrementalIndex + 1, moveClockwise);

                            // restrict branch only for clockwise, it is not allowed to move into branch when moving backwards e.g. with card 4
                            if (moveClockwise) {
                                this.checkMove(player, currentNode.branch, result, steps, mumble, incrementalIndex + 1, moveClockwise);
                            }
                        }
                    }
                }
            } else {
                // todo: ???
                System.out.println("notRoundDone");
                System.out.println("inkrement: " + incrementalIndex);
                System.out.println("currentNode: " + currentNode);
                if (currentNode.data != null) {
                    System.out.println("occupied");
                    System.out.println(currentNode.index);
                    if (steps == incrementalIndex) {
                        System.out.println("targeposition occupied");
                        if (currentNode.data.getPlayer().equals(mumble.getPlayer())) {
                            System.out.println("target position occupied by own mumble");
                            result.getTargetNodes().add(currentNode);
                            result.getOccupationStatuses().add(OCCUPATION_STATUS.OCCUPIED_BY_ONESELF);
                        } else {
                            System.out.println("target position occupied by opponent mumble");
                            result.getTargetNodes().add(currentNode);
                            result.getOccupationStatuses().add(OCCUPATION_STATUS.OCCUPIED_BY_OPPONENT);
                        }
                    } else {
                        System.out.println("no target position, occupied");

                        if (currentNode.data.equals(mumble)) {
                            System.out.println("no target position, occupied by mumble itself");
                            this.checkMove(player, currentNode.getNextPrev(moveClockwise), result, steps, mumble, incrementalIndex + 1, moveClockwise);
                        } else {
                            if (currentNode.data.getPlayer().equals(mumble.getPlayer())) {
                                System.out.println("no target position, occupied by own mumble");
                                result.getTargetNodes().add(currentNode);
                                result.getOccupationStatuses().add(OCCUPATION_STATUS.OCCUPIED_BY_OPPONENT);
                            } else {
                                System.out.println("target position occupied by opponent mumble");
                                result.getTargetNodes().add(currentNode);
                                result.getOccupationStatuses().add(OCCUPATION_STATUS.BLOCKED_BY_OPPONENT);
                            }
                        }
                    }
                } else {
                    System.out.println("unoccupied");
                    if (steps == incrementalIndex) {
                        System.out.println("targetposition unoccupied");
                        System.out.println("result: " + result.getTargetNodes());
                        result.getTargetNodes().add(currentNode);
                        result.getOccupationStatuses().add(OCCUPATION_STATUS.UNOCCUPIED);
                    } else {
                        System.out.println("not target position, slot unoccupied");

                        if(currentNode.next == null) {
                            System.out.println("no following node");
                        } else {
                            this.checkMove(player, currentNode.getNextPrev(moveClockwise), result, steps, mumble, incrementalIndex + 1, moveClockwise);
                        }
                    }
                }
            }
        } else {
            System.out.println("notStartNode");
            System.out.println("inkrement: " + incrementalIndex);
            System.out.println("currentNode: " + currentNode);
            if (currentNode.data != null) {
                System.out.println("occupied");
                System.out.println(currentNode.index);
                if (steps == incrementalIndex) {
                    System.out.println("targeposition occupied");
                    if (currentNode.data.getPlayer().equals(mumble.getPlayer())) {
                        System.out.println("target position occupied by own mumble");
                        result.getTargetNodes().add(currentNode);
                        result.getOccupationStatuses().add(OCCUPATION_STATUS.OCCUPIED_BY_ONESELF);
                    } else {
                        System.out.println("target position occupied by opponent mumble");
                        result.getTargetNodes().add(currentNode);
                        result.getOccupationStatuses().add(OCCUPATION_STATUS.OCCUPIED_BY_OPPONENT);
                    }
                } else {
                    System.out.println("no target position, occupied");

                    if (currentNode.data.equals(mumble)) {
                        System.out.println("no target position, occupied by mumble itself");
                        this.checkMove(player, currentNode.getNextPrev(moveClockwise), result, steps, mumble, incrementalIndex + 1, moveClockwise);
                    } else {
                        if (currentNode.data.getPlayer().equals(mumble.getPlayer())) {
                            System.out.println("no target position, occupied by own mumble");
                            result.getTargetNodes().add(currentNode);
                            result.getOccupationStatuses().add(OCCUPATION_STATUS.BLOCKED_BY_ONESELF);
                        } else {
                            System.out.println("target position occupied by opponent mumble");
                            result.getTargetNodes().add(currentNode);
                            result.getOccupationStatuses().add(OCCUPATION_STATUS.BLOCKED_BY_OPPONENT);
                        }
                    }
                }
            } else {
                System.out.println("unoccupied");
                if (steps == incrementalIndex) {
                    System.out.println("targetposition unoccupied");
                    result.getTargetNodes().add(currentNode);
                    result.getOccupationStatuses().add(OCCUPATION_STATUS.UNOCCUPIED);
                } else {
                    System.out.println("not target position, slot unoccupied");

                    if(currentNode.next == null) {
                        System.out.println("no following node");
                    } else {
                        this.checkMove(player, currentNode.getNextPrev(moveClockwise), result, steps, mumble, incrementalIndex + 1, moveClockwise);
                    }
                }
            }
        }
    }

    // move selected mumble to prefield
    public void moveMumbleToPreField(final FieldResult result, final int selectedPosition) {
        if(result.getTargetNodes().get(selectedPosition).data != null) {
            // is it realy in start field?
            if(result.getTargetNodes().get(selectedPosition).data.getPlaygroundPosition() == PLAYGROUND.START_FIELD) {
                result.getTargetNodes().get(selectedPosition).data.setNode(result.getTargetNodes().get(selectedPosition));
                //result.getTargetNodes().get(selectedPosition).data.setPosition(0, PLAYGROUND.PRE_FIELD);
                result.getTargetNodes().get(selectedPosition).data.setRoundDone(false);
                result.getTargetNodes().get(selectedPosition).data = null;
            }
        }
    }

    // place new mumble into node
    public void moveMumbleToStartField(final FieldResult result, final Mumble mumble, final int selectedPosition) {
        if(result.getTargetNodes().get(selectedPosition).data == null) {
            // place mumble into node data
            result.getTargetNodes().get(selectedPosition).data = mumble;
            // todo: check whether the following statement is needed!
            //result.getTargetNodes().get(selectedPosition).data.setPosition(result.getTargetNodes().get(selectedPosition).index, PLAYGROUND.START_FIELD);
            // write node to mumble
            mumble.setNode(result.getTargetNodes().get(selectedPosition));
        }
    }

    // move mumble within the circular start field
    public void moveMumbleWithinStartField(final FieldResult result, final Mumble mumble, final int selectedPosition) {
        if(result.getTargetNodes().get(selectedPosition).data == null) {
            // when mumble is move first time, it can be moved to home field through the start node
            mumble.setRoundDone(true);
            // place mumble inside node
            result.getTargetNodes().get(selectedPosition).data = mumble;
            // set index of node to position_index of mumble
            // todo: check whether the following statement is needed!
            //result.getTargetNodes().get(selectedPosition).data.setPosition(result.getTargetNodes().get(selectedPosition).index, PLAYGROUND.START_FIELD);
            // write node to mumble
            mumble.setNode(result.getTargetNodes().get(selectedPosition));
            // free up old node
            result.getStartNode().data = null;
        }
    }
}

class FieldResult {

    private ListNode<Mumble> startNode;
    // a list which carries nodes of type mumble
    private List<ListNode<Mumble>> targetNodes;
    // a list which carries occupationStatuses
    private List<OCCUPATION_STATUS> occupationStatuses;

    public FieldResult() {
        this(null);
    }

    public FieldResult(ListNode<Mumble> startNode) {

        this.startNode = startNode;
        this.targetNodes = new LinkedList<ListNode<Mumble>>();
        this.occupationStatuses = new LinkedList<OCCUPATION_STATUS>();
    }

    public ListNode<Mumble> getStartNode() {
        return startNode;
    }

    public void setStartNode(ListNode<Mumble> startNode) {
        this.startNode = startNode;
    }

    public List<ListNode<Mumble>> getTargetNodes() {
        return targetNodes;
    }

    public void setTargetNodes(List<ListNode<Mumble>> targetNodes) {
        this.targetNodes = targetNodes;
    }

    public List<OCCUPATION_STATUS> getOccupationStatuses() {
        return occupationStatuses;
    }

    public void setOccupationStatuses(List<OCCUPATION_STATUS> occupationStatuses) {
        this.occupationStatuses = occupationStatuses;
    }
}
