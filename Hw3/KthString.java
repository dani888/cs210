// KthString.java: Takes a command-line argument k and prints
// the kth string from the end found on standard input,
// assuming that standard input has k or more strings.

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class KthString {
	public static void main(String[] args) {
		Queue<String> stack = new Queue<String>();
		while (!StdIn.isEmpty()) {
			String string = StdIn.readString();
			if (string != "  ");
			stack.enqueue(string);
		}

		int size = stack.size();
		for (int i = 0; i < size - Integer.parseInt(args[0]); i++) {
			stack.dequeue();
		}
		System.out.println(stack.dequeue());
	}
}
