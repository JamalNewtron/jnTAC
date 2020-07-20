import java.util.NoSuchElementException;

class ListNode<AnyType> {

    // The actual data
    AnyType data;
    // Reference to the next node
    ListNode<AnyType> next;
    // Reference to the prev node
    ListNode<AnyType> prev;
    // branch
    ListNode<AnyType> branch;


    ListNode(AnyType data) {
        this(data, null, null, null);
    }


    ListNode(AnyType data,
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
    private ListNode<AnyType> head;
    // Helper, keeping track of size.
    private int size;


    public DataStructure() {
        this.head = null;
        this.size = 0;
    }

    public void add(AnyType data) {
        if (isEmpty())
            this.head = new ListNode<AnyType>(data);
        else {
            ListNode<AnyType> temp = this.head;
            // Traverse till end of list
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new ListNode<AnyType>(data, null, temp, null);
        }
        this.size++;
    }

    public void set(int index, AnyType data) {
        this.set(this.head, data, index, 0);
    }

    private void set(ListNode<AnyType> head, AnyType data, int index, int startIndex) {
        if(index == startIndex){
            head.data = data;
        }
        if(head.next == null) {

        } else {
            set(head.next, data, index, startIndex + 1);
        }
    }



    public AnyType get(int index) {
        return this.get(this.head, index, 0);
    }

    private AnyType get(ListNode<AnyType> head, int index, int startIndex ) {
        if(index == startIndex){
            return head.data;
        };

        if(head.next == null){
            return null;
        }
        return get(head.next, index, startIndex + 1);
    }


    public void remove(AnyType data) {

        if (isEmpty())
            throw new NoSuchElementException("Element " + data.toString() + " not found");

        // Removing front element
        if (this.head.data.equals(data)) {
            this.head = this.head.next;
            return;
        }

        ListNode<AnyType> current = this.head;
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