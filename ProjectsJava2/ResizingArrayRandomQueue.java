import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.*;
import java.util.NoSuchElementException;

// Random queue implementation using a resizing array.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;  // number of elements on queue
    private int capacity;

    // Construct an empty queue.
    public ResizingArrayRandomQueue() {
        capacity = 2;
        q = (Item[]) new Object[capacity];
        n = 0;
    }

    // Is the queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // The number of items on the queue.
    public int size() {
        return n;
    }

    // Add item to the queue.
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (n == capacity) {
            capacity *= 2;
            resize(capacity);
        }
        int index = randIndex();
        Item[] temp = Arrays.copyOfRange(q, index, capacity);
        q[index] = item;
        System.arraycopy(temp, 0, q, index + 1, temp.length - 1);
        n++;
    }

    // return random index within n
    private int randIndex() {
        if (n == 0) {
            return n;
        }
        Random rnd = new Random();
        return rnd.nextInt(n);
    }

    // Remove and return a random item from the queue.
    public Item dequeue() {
        if (capacity / 4 >= n) {
            capacity /= 2;
            resize(capacity);
        }
        int index = randIndex();
        Item removed = q[index];
        Item[] temp = Arrays.copyOfRange(q, index + 1, capacity);
        System.arraycopy(temp, 0, q, index, temp.length);
        q[n - 1] = null;
        n--;
        return removed;
    }

    // Return a random item from the queue, but do not remove it.
    public Item sample() {
        int index = randIndex();
        return q[index];
    }

    // An independent iterator over items in the queue in random order.
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        private int i;

        RandomQueueIterator() {
            i = 0;
        }

        public boolean hasNext()  { return i < n; }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (hasNext()) {
                return q[i++];
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    // A string representation of the queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        if (s.length() == 0) {
            return "empty";
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Helper method for resizing the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
        q = temp;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q =
            new ResizingArrayRandomQueue<Integer>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readInt());
        }
        int sum1 = 0;
        for (int x : q) {
            sum1 += x;
        }
        int sum2 = sum1;
        for (int x : q) {
            sum2 -= x;
        }
        int sum3 = 0;
        while (q.size() > 0) {
            sum3 += q.dequeue();
        }
        StdOut.println(sum1);
        StdOut.println(sum2);
        StdOut.println(sum3);
        StdOut.println(q.isEmpty());
    }
}
