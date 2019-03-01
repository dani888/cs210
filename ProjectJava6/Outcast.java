import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

// An immutable data type for outcast detection.
public class Outcast {
    private WordNet net;

    // Construct an Outcast object given a WordNet object.
    public Outcast(WordNet wordnet) {
        this.net = wordnet;
    }

    // The outcast noun from nouns.
    public String outcast(String[] nouns) {
        String outcast = "";
        int outcastRating = -1;
        for (String noun : nouns) {
            int rating = 0;
            for (String noun2 : nouns) {
                if (noun != noun2) {
                    rating += net.distance(noun, noun2);
                }
            }
            if (outcastRating < 0 || outcastRating < rating) {
                outcast = noun;
                outcastRating = rating;
            }
        }
        return outcast;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println("outcast(" + args[t] + ") = "
                           + outcast.outcast(nouns));
        }
    }
}
