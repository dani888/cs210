import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque implementation using a linked list.
public class LinkedDeque<Item> implements Iterable<Item> {
    private int num;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue
    // Helper doubly-linked list class.
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
        Node(Item item) {
            this.item = item;
        }
        Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
        Node(Node prev, Item item) {
            this.item = item;
            this.prev = prev;
        }
        Node(Node prev, Item item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    // Construct an empty deque.
    public LinkedDeque() {
        first = null;
        last  = null;
        num = 0;
    }

    // Is the dequeue empty?
    public boolean isEmpty() {
        return first == null;
    }

    // The number of items on the deque.
    public int size() {
        return num;
    }

    // Add item to the front of the deque.
    public void addFirst(Item item) {
        num++;
        if (item == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            first = new Node(item);
            last = first;
        } else {
            first = new Node(item, first);
            first.next.prev = first;
        }
    }

    // Add item to the end of the deque.
    public void addLast(Item item) {
        num++;
        if (item == null) {
            throw new NullPointerException();
        } else if (isEmpty()) {
            first = new Node(item);
            last = first;
        } else {
            last = new Node(last, item);
            last.prev.next = last;
        }
    }

    // Remove and return item from the front of the deque.
    public Item removeFirst() {
        num--;
        Item removed;
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            removed = first.item;
            if (first.next != null) {
                first = first.next;
                first.prev = null;
            } else {
                first = null;
                last = null;
            }
        }
        return removed;
    }

    // Remove and return item from the end of the deque.
    public Item removeLast() {
        num--;
        Item removed;
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            removed = last.item;
            if (last.prev != null) {
                last = last.prev;
                last.next = null;
            } else {
                first = null;
                last = null;
            }
        }
        return removed;
    }

    // An iterator over items in the queue in order from front to end.
    public Iterator<Item> iterator() {
        return new DequeIterator(first, last);
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<Item> {
        private Node currentNode = null;
        private Node firstNode;
        private Node lastNode;

        DequeIterator(Node first, Node last) {
            firstNode = first;
            lastNode = last;
        }

        public boolean hasNext()  { return currentNode != lastNode; }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (currentNode == null) {
                currentNode = firstNode;
                return currentNode.item;
            }
            if (currentNode.next == null) {
                throw new NoSuchElementException();
            }
            currentNode = currentNode.next;
            return currentNode.item;
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its "
                       + "several powers, having been originally breathed into a few "
                       + "forms or into one; and that, whilst this planet has gone "
                       + "cycling on according to the fixed law of gravity, from so "
                       + "simple a beginning endless forms most beautiful and most "
                       + "wonderful have been, and are being, evolved. ~ "
                       + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}
