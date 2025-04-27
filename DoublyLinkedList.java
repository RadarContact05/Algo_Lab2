import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {
    private class ListNode {
        T data; // Value of the Node.
        ListNode next; // Points at next Node.
        ListNode prev; // Points at previous Node.

        ListNode(T data) {
            this.data = data; //Creates Node with given value of data of type T. Next and prev are null by default.
        }
    }

    private ListNode head; // Points, first node
    private ListNode tail; // Points, last node
    private int size;      // number of nodes

    // Creates and empty LinkedList with no element, hence both pointers set to null and size = 0.
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Adds elements to the LinkedList in a standard way.
    public void add(T t) {
        ListNode node = new ListNode(t);
        if (isEmpty()) {                // checks if list already empty.
            head = tail = node;
        } else {                        // if not empty, add to current LinkedList.
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;                         // increase size accordingly.
    }

    // Adds elements to the LinkedList by a given index.
    public void add(int index, T t) {
        if (index == size) {            // if index is the same int as size, then add there.
            add(t);
            return;
        }
        ListNode current = nodeAt(index);  // find the node at the index
        ListNode node = new ListNode(t);   // create a new node for the index
        node.next = current;               // set new next
        node.prev = current.prev;          // set new prev
        if (current.prev != null) {        // makes sure that the node before points at the new node, if there is a node before.
            current.prev.next = node;
        } else {
            head = node;                   // otherwise set the new node as head
        }
        current.prev = node;               // update prev pointer, so that it points at node
        size++;
    }

    public T get(int index) {
        return nodeAt(index).data;         // returns the data for the node at the index
    }

    public T getFirst() {                  // returns head
        if (isEmpty()) throw new NoSuchElementException();
        return head.data;
    }

    public T getLast() {                   // returns tail
        if (isEmpty()) throw new NoSuchElementException();
        return tail.data;
    }

    // removes t and returns the amount of removed t
    public int remove(T t) {
        int removed = 0;
        ListNode cur = head;    // start at first node
        while (cur != null) {   // loop through the Linkedlist
            ListNode next = cur.next;   // saves the next pointer before removing cur.next
            if ((t == null && cur.data == null) || (t != null && t.equals(cur.data))) {         // checks if there is a t at node
                if (cur.prev != null) 
                cur.prev.next = cur.next;                                                       // points previous nodes next pointer to the cur.next node if there is a node before cur
                else head = cur.next;                                                           // if there is no node before cur, move the head pointer to cur.next

                if (cur.next != null) 
                cur.next.prev = cur.prev;                                                       // link the next node’s prev back to cur.prev
                else tail = cur.prev;

                size--;     // decrease size
                removed++;  // decrease removed counter
            }
            cur = next;     // update pointer for next node
        }
        return removed;
    }

    // removes node at index, and returns its data
    public T remove(int index) {
        ListNode node = nodeAt(index);                              // find node at index
        if (node.prev != null) node.prev.next = node.next;          // if there is a node before, connect it "around" the index node
        else head = node.next;                                      // if the index node was head, move head forward and remove what was head before(index node)

        if (node.next != null) node.next.prev = node.prev;          // if there is a node after, connect it "around" the index node
        else tail = node.prev;                                      // if index node was tail, move tail backward 
        
        size--;     
        return node.data;                                           // returns data from removed node
    }

    // removes first node
    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return remove(0);
    }

    // removes last node
    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return remove(size - 1);
    }

    // checks if LinkedList is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // return the amount of elements in the list
    public int size() {
        return size;
    }

    // clears the list
    public void clear() {
        head = tail = null;
        size = 0;
    }

    // returns the list as a string, for example [a, b, c]
    public String toString() {
        String result = "[";
        ListNode current = head;
        while (current != null) {
            result += current.data;
            if (current.next != null) result += ", ";
            current = current.next;
        }
        return result + "]";
    }

    // help function to find node at index. Works by dividing nodes size/length in 2, and going either from head to tail or tail to head, depending on what is closest to index.
    private ListNode nodeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        ListNode cur;
        if (index < size / 2) {
            cur = head;
            for (int i = 0; i < index; i++){
                cur = cur.next;
            } 
        } else {
            cur = tail;
            for (int i = size - 1; i > index; i--) {
                cur = cur.prev;
            } 
        }
        return cur;
    }

    // makes linkedlist iterable
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private ListNode current = head;                            // start at first node
            public boolean hasNext() {
                return current != null;                                 // true if there are more elements
            }
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;                                  // gets data from current node
                current = current.next;                                 // gets data from current nodes next node pointer
                return data;
            }
        };
    }

    // goes from tail to head and adds t for every node that has a value less than t
    public void addAtFirstSmaller(T t, Comparator<T> cmp) {
        ListNode current = tail;                                        // start at tail

        while (current != null) {
            if (cmp.compare(current.data, t) < 0) {                     // compare t with node
                ListNode newNode = new ListNode(t);                     // creates a node
                newNode.prev = current;
                newNode.next = current.next;

                if(current.next != null) {                              // deletes a node in sequence
                    current.next.prev = newNode;                        
                } else {
                    tail = newNode;                                     // if node is tail, delete that
                }

                current.next = newNode;                                 // fixes connection
                size++;
                return;
            }

            current = current.prev;                                     // checks next node in list in reverse order
        }

        // if no values less than t were found
        ListNode newNode = new ListNode(t);
        newNode.next = head;                                            // add the newNode as the head
        if (head != null) {
            head.prev = newNode;
        } else {
            tail = newNode;
        }
        head = newNode;                                                 // update head
        size++;                             
    }
}
