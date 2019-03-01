// Josephus.java: Takes N and M from the command line and prints out the order
// in which people are eliminated (and thus would show Josephus where to sit in
// the circle).

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Josephus {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int m = Integer.parseInt(args[1]);
		Queue<Integer> line = new Queue<Integer>();
		for (int i = 0; i <= n - 1; i++)
			line.enqueue(i);
		while (!line.isEmpty()) {
			for (int i = 0; i < m - 1; i++)
				line.enqueue(line.dequeue());
			StdOut.print(line.dequeue() + " ");
		}
		StdOut.println();
	}
}


