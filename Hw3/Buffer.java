
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

// A data type representing a text editor buffer.
public class Buffer {
    private Stack<Character> left;  // chars left of cursor
    private Stack<Character> right; // chars right of cursor

    // Create an empty buffer.
    public Buffer() {
        left = new Stack<Character>();
        right = new Stack<Character>();
    }

    // Insert c at the cursor position.
    public void insert(char c) {
        left.push(c);
    }

    // Delete and return the character at the cursor.
    public char delete() {
        return right.pop();
    }

    // Move the cursor k positions to the left.
    public void left(int k) {
        for (int i = 0; i < k; i++) {
            if (!left.isEmpty()) {
                right.push(left.pop());
            }
        }
    }

    // Move the cursor k positions to the right.
    public void right(int k) {
        for (int i = 0; i < k; i++) {
            if (!right.isEmpty()) {
                left.push(right.pop());
            }
        }
    }

    // Return the number of characters in the buffer.
    public int size() {
        return left.size() + right.size();
    }

    // Return a string representation of the buffer with a "|" character (not
    // part of the buffer) at the cursor position.
    public String toString() {
        String str = "";
        String leftStr = left.toString();
        String rightStr = right.toString();
        for (int i = leftStr.length() - 2; i >= 0; i -= 2) {
            str += leftStr.charAt(i);
        }
        str += "|";
        for (int i = 0; i < rightStr.length() ; i += 2) {
            str += rightStr.charAt(i);
        }
        return str;
    }


// Test client (DO NOT EDIT).
    public static void main(String[] args) {
        Buffer buf = new Buffer();
        String s = "There is grandeur in this view of life, with its "
                   + "several powers, having been originally breathed into a few "
                   + "forms or into one; and that, whilst this planet has gone "
                   + "cycling on according to the fixed law of gravity, from so "
                   + "simple a beginning endless forms most beautiful and most "
                   + "wonderful have been, and are being, evolved. ~ "
                   + "Charles Darwin, The Origin of Species";
        for (int i = 0; i < s.length(); i++) {
            buf.insert(s.charAt(i));
        }
        buf.left(buf.size());
        buf.right(97);
        s = "by the Creator ";
        for (int i = 0; i < s.length(); i++) {
            buf.insert(s.charAt(i));
        }
        buf.right(228);
        buf.delete();
        buf.insert('-');
        buf.insert('-');
        buf.left(342);
        StdOut.println(buf);
    }
}
