// FrequencyCounter.java: Reads in a command-line integer and sequence of words
// from standard input and prints out all the words (whose length exceeds the
// threshold) that occur most frequently to standard output. It also prints out
// the number of words whose length exceeds the threshold and the number of
// distinct such words.

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.ST;
import java.util.*;

public class FrequencyCounter {
	public static void main(String[] args) {
		int distinct = 0;
		int words = 0;
		int minlen = Integer.parseInt(args[0]);
		ArrayST<String, Integer> st = new ArrayST<String, Integer>();
		Set<String> wordArray = new LinkedHashSet<>();

		// compute frequency counts
		while (!StdIn.isEmpty()) {
			String key = StdIn.readString();
			if (key.length() <= minlen) continue;
			wordArray.add(key);
			words++;
			if (st.contains(key)) {
				st.put(key, st.get(key) + 1);
			} else {
				st.put(key, 1);
				distinct++;
			}
		}


		int maxWordCount = 0;

		for (String word : st.keys()) {
			if(st.get(word) > maxWordCount){
				maxWordCount = st.get(word);
			}
		}

		for (String word : wordArray) {
			if(st.get(word) == maxWordCount){
				StdOut.print(word + " ");
			}
		}




		// String max = "";
		// st.put(max, 0);
		// for (String word : st.keys()) {
		// 	if (st.get(word) > st.get(max))
		// 		max = word;
		// 	// else if (st.get(word) == st.get(max))
		// 	// 	StdOut.print(word);
		// }
		// // String result = "";
		// for (String word : st.keys()) {
		// 	if (st.get(word) >= st.get(max))
		// 		// max = word;
		// 		StdOut.print(word + " ");
		// 	// result = word + " " + result;
		// }

		StdOut.println(maxWordCount);
		StdOut.println("distinct = " + distinct);
		StdOut.println("words = " + words);
	}
}