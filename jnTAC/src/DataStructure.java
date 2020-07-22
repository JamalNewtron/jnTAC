import java.util.NoSuchElementException;

class ListNode<AnyType> {

    // The actual data
    AnyType data;
    // Reference to the next node
    Player player;
    ListNode<AnyType> next;
    // Reference to the prev node
    ListNode<AnyType> prev;
    // branch
    ListNode<AnyType> branch;


    ListNode(AnyType data) {
        this(data, null, null, null, null);
    }


    ListNode(AnyType data,
             Player player,
             ListNode<AnyType> next,
             ListNode<AnyType> prev,
             ListNode<AnyType> branch) {

        this.data = data;
        this.next = next;
        this.prev = prev;
        this.branch = branch;
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
            this.start = new ListNode<AnyType>(data, null, null, null, null);
            this.size++;
        } else {
            this.add(data, null);
        }
    }

    public void add(AnyType data, Player player) {
        if(isEmpty()){
            this.start = new ListNode<AnyType>(data, player, null, null, null);
            this.size++;
        } else {
            this.add(this.start, data, player);
        }
    }

    private void add(ListNode<AnyType> current, AnyType data, Player player){
        // if there is no next node or the next node is the start -> place the next node
        if(current.next == null || current.next == this.start) {
            current.next = new ListNode<AnyType>(data, player, this.start, current, null);
            // newest node is end
            this.end = current.next;
            // nodeOne has to take the new node as previous node.
            // this.nodeOne.prev = current;
            this.size++;
        } else {
            this.add(current.next, data, player);
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



    public ListNode<AnyType> getNode(final AnyType data){
        return this.getNode(this.start, data);
    }

    private ListNode<AnyType> getNode(final ListNode<AnyType> current, final AnyType data){
        if(current.data.equals(data)) {
            return current;
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

}