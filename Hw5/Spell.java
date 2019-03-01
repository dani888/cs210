// Spell.java: Takes a command-line argument specifying the name of the file
// containing common misspellings (a line-oriented file with each
// comma-separated line containing a misspelled word and the correct spelling),
// then reads text from standard input and prints out the misspelled words in
// the text along with the line numbers where they occurred and their correct
// spellings.

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.util.*;

public class Spell {
	public static void main(String[] args) {
		// System.out.println(Arrays.toString(args));
		SeparateChainingHashST<String, String> st = new SeparateChainingHashST<String, String>();

		// try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
		// String line;
		In in1 = new In(args[0]);
		while (in1.hasNextLine()) {
			String line = in1.readLine();
			// System.out.println(line);
			String[] dict = line.split(",");
			st.put(dict[0], dict[1]);
			// System.out.println(dict[0] + " " + dict[1]);
		}
		int count = 0;
		// In in2 = new In(args[0]);
		while (StdIn.hasNextLine()) {
			count++;
			String line = StdIn.readLine();
			String[] words = line.split("\\W");
			for (int i = 0; i < words.length; i++) {
				if (st.contains(words[i]))
					StdOut.println(words[i] + ":" + count + " -> " + st.get(words[i]));
			}
		}
	}
}