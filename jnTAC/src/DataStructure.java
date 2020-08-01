import java.util.NoSuchElementException;

class ListNode<AnyType> {

    // The actual data
    AnyType data;
    // index
    int index;
    // Playground
    PLAYGROUND playground;
    // player
    Player player;
    // Reference to the next node
    ListNode<AnyType> next;
    // Reference to the prev node
    ListNode<AnyType> prev;
    // branch
    ListNode<AnyType> branch;


    ListNode(AnyType data) {
        this(data, 0, null, null, null, null, null);
    }

    ListNode(AnyType data,
             int index,
             PLAYGROUND playground,
             Player player,
             ListNode<AnyType> next,
             ListNode<AnyType> prev,
             ListNode<AnyType> branch) {

        this.data = data;
        this.index = index;
        this.playground = playground;
        this.player = player;
        this.next = next;
        this.prev = prev;
        this.branch = branch;
    }

    public ListNode<AnyType> getNextPrev(final boolean clockwise) {
        if(clockwise){
            return this.next;
        } else {
            return this.prev;
        }
    }


}

public class DataStructure<AnyType> {

    // head node
    private ListNode<AnyType> start;
    private ListNode<AnyType> end;
    // Helper, keeping track of size.
    private int size;


    public DataStructure() {
        this.start = null;
        this.end = null;
        this.size = 0;
    }

    public ListNode<AnyType> getStart() {
        return start;
    }

    public void setStart(ListNode<AnyType> start) {
        this.start = start;
    }

    public ListNode<AnyType> getEnd() {
        return end;
    }

    public void setEnd(ListNode<AnyType> end) {
        this.end = end;
    }

    public void add(AnyType data) {
        if(isEmpty()){
            this.start = new ListNode<AnyType>(data, this.size, null, null, null, this.end, null);
            this.size++;
        } else {
            this.add(data, null);
        }
    }

    public void add(AnyType data, PLAYGROUND playground) {
        if(isEmpty()){
            this.start = new ListNode<AnyType>(data, this.size, playground, null, null, this.end, null);
            this.size++;
        } else {
            this.add(data, playground, null);
        }
    }

    public void add(AnyType data, PLAYGROUND playground, Player player) {
        if(isEmpty()){
            this.start = new ListNode<AnyType>(data, this.size, playground, player, null, this.end, null);
            this.size++;
        } else {
            this.add(this.start, data, player, playground);
        }
    }

    private void add(ListNode<AnyType> current, AnyType data, Player player, PLAYGROUND playground){
        // if there is no next node or the next node is the start -> place the next node
        if(current.next == null || current.next == this.start) {
            current.next = new ListNode<AnyType>(data, this.size, playground, player, this.start, current, null);
            // newest node is end
            this.end = current.next;
            // star node has to take the new node as previous node.
            this.start.prev = this.end;
            this.size++;
        } else {
            this.add(current.next, data, player, playground);
        }
    }

    public void set(int index, AnyType data) {
        this.set(index, data, null);
    }

    public void set(int index, AnyType data, Player player) {
        this.set(index, data, this.start, player, 0);
    }

    private void set(int index, AnyType data, ListNode<AnyType> current, Player player, int incrementalIndex) {
        if(index == incrementalIndex){
            current.data = data;
            current.player = player;
        }

        if(current.next == null || current == this.end) {
            return;
        } else {
            set(index, data, current.next, player, incrementalIndex + 1);
        }
    }

    public void setBranch(int index, ListNode<AnyType> branch) {
        this.setBranch(index, this.start, branch, 0);
    }

    private void setBranch(int index, ListNode<AnyType> current, ListNode<AnyType> branch, int incrementalIndex) {
        if(index == incrementalIndex) {
            current.branch = branch;
        }

        if(current.next == null || current == this.end) {

        } else {
            this.setBranch(index, current.next, branch, incrementalIndex + 1);
        }

    }

    public AnyType get(int index) {
        return this.get(this.start, index, 0);
    }

    private AnyType get(ListNode<AnyType> current, int index, int incrementalIndex) {
        if(index == incrementalIndex){
            return current.data;
        }

        if(current.next == null || current == this.end){
            return null;
        }
        return this.get(current.next, index, incrementalIndex + 1);
    }

    public ListNode<AnyType> getNode(int index) {
        return this.getNode(index, this.start, 0);
    }

    private ListNode<AnyType> getNode(final int index, final ListNode<AnyType> current, final int incrementalIndex) {
        if(index == incrementalIndex) {
            return current;
        }
        if(current.next == null || current.next == this.start) {
            return null;
        }
        return this.getNode(index, current.next, incrementalIndex + 1);
    }

    public ListNode<AnyType> getNode(final AnyType data){
        return this.getNode(this.start, data);
    }

    private ListNode<AnyType> getNode(final ListNode<AnyType> current, final AnyType data){
        if(current.data != null) {
            if (current.data.equals(data)) {
                return current;
            }
        }
        if(current.next == null || current.next == this.start){
            return null;
        }
        return this.getNode(current.next, data);
    }


    public void clear(int index) {
        this.clear(this.start, index, 0);
    }

    private void clear(ListNode<AnyType> current, int index, int incrementalIndex) {
        if(index == incrementalIndex) {
            current.data = null;
        }
        if(current.next == null){
            return;
        }
        this.clear(current.next, index, incrementalIndex + 1);
    }


    public void remove(AnyType data) {

        if (isEmpty())
            throw new NoSuchElementException("Element " + data.toString() + " not found");

        // Removing front element
        if (this.start.data.equals(data)) {
            this.start = this.start.next;
            return;
        }

        ListNode<AnyType> current = this.start;
        // Looping through until found
        // !current.data.equals(data)   liefert false, wenn Gleichheit vorliegt
        // current != null              liefert false, wenn current null ist
        while (current != null && !current.data.equals(data))
            current = current.next;
        // If null, not found
        if (current == null)
            throw new NoSuchElementException("Element " + data.toString() + " not found");
        // It has a next pointer, so not the last node.
        if (current.next != null)
            current.next.prev = current.prev;
        current.prev.next = current.next;
        size--;
    }

    public boolean isEmpty() {
        if(0 == this.size) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return this.size;
    }

    // linear doubly linked list

    public void addLinear(AnyType data, PLAYGROUND playground, Player player) {
        if(isEmpty()){
            this.start = new ListNode<AnyType>(data, this.size, playground, player, null, null, null);
            this.size++;
        } else {
            this.addLinear(this.start, data, player, playground);
        }
    }

    private void addLinear(ListNode<AnyType> current, AnyType data, Player player, PLAYGROUND playground){
        // if there is no next node or the next node is the start -> place the next node
        if(current.next == null) {
            current.next = new ListNode<AnyType>(data, this.size, playground, player, null, current, null);
            // newest node is end
            this.end = current.next;
            this.size++;
        } else {
            this.addLinear(current.next, data, player, playground);
        }
    }
}