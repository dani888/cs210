import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

// A data type that provides autocomplete functionality for a given set of
// string and weights, using Term and BinarySearchDeluxe.
public class Autocomplete {
    private Term[] terms;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        this.terms = Arrays.copyOf(terms, terms.length);
        Arrays.sort(this.terms);
    }

    // All terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix), Term.byPrefixOrder(prefix.length()));
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix), Term.byPrefixOrder(prefix.length()));
        ArrayList<Term> result = new ArrayList<Term>();
        for (int i = firstIndex; i <= lastIndex ; i++) {
            result.add(terms[i]);
        }
        Term[] res = result.toArray(new Term[0]);
        Arrays.sort(res, Term.byReverseWeightOrder());
        return res;
    }

    // The number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix), Term.byPrefixOrder(prefix.length()));
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix), Term.byPrefixOrder(prefix.length()));
        return Math.abs(firstIndex - lastIndex);
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query, weight);
        }
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println(results[i]);
            }
        }
    }
}
