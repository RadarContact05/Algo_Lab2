import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {
    private class ListNode {
        T data;
        ListNode next;
        ListNode prev;

        ListNode(T data) {
            this.data = data;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T t) {
        ListNode node = new ListNode(t);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    public void add(int index, T t) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == size) {
            add(t);
            return;
        }
        ListNode current = nodeAt(index);
        ListNode node = new ListNode(t);
        node.next = current;
        node.prev = current.prev;
        if (current.prev != null) {
            current.prev.next = node;
        } else {
            head = node;
        }
        current.prev = node;
        size++;
    }

    public T get(int index) {
        return nodeAt(index).data;
    }

    public T getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return head.data;
    }

    public T getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return tail.data;
    }

    public int remove(T t) {
        int removed = 0;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if ((t == null && cur.data == null) || (t != null && t.equals(cur.data))) {
                if (cur.prev != null) cur.prev.next = cur.next; 
                else head = cur.next;

                if (cur.next != null) cur.next.prev = cur.prev; 
                else tail = cur.prev;

                size--;
                removed++;
            }
            cur = next;
        }
        return removed;
    }

    public T remove(int index) {
        ListNode node = nodeAt(index);
        if (node.prev != null) node.prev.next = node.next; 
        else head = node.next;

        if (node.next != null) node.next.prev = node.prev; 
        else tail = node.prev;
        
        size--;
        return node.data;
    }

    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return remove(0);
    }

    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return remove(size - 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

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

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private ListNode current = head;
            public boolean hasNext() {
                return current != null;
            }
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    public void addAtFirstSmaller(T t, Comparator<T> cmp) {
        ListNode current = tail;

        while (current != null) {
            if (cmp.compare(current.data, t) < 0) {
                ListNode newNode = new ListNode(t);
                newNode.prev = current;
                newNode.next = current.next;

                if(current.next != null) {
                    current.next.prev = newNode;
                } else {
                    tail = newNode;
                }

                current.next = newNode;
                size++;
                return;
            }

            current = current.prev;
        }

        ListNode newNode = new ListNode(t);
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        } else {
            tail = newNode;
        }
        head = newNode;
        size++;
    }
}
