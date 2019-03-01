import edu.princeton.cs.algs4.StdOut;

// A data type representing a rational number.
public class Rational {
    private long x; // numerator
    private long y; // denominator

    // Construct a rational number whose numerator is x and denominator is 1.
    public Rational(long x) {
        this(x, 1);
        // x = x;
        // y = 1;
    }

    // Construct a rational number given its numerator and denominator.
    public Rational(long x, long y) {
        long gcd = gcd(x, y);
        this.x = x / gcd;
        this.y = y / gcd;
    }

    // The sum of this and that rational number.
    public Rational add(Rational that) {
        Rational add = new Rational((x * that.y) + (y * that.x), y * that.y);
        return add;
        // Rational add;
        // add.x = (x * that.y) + (y * that.x;
        // add.y = y * that.y;
        // return add;
    }

    // The product of this and that rational number.
    public Rational multiply(Rational that) {
        Rational mul = new Rational(x * that.x, y * that.y);
        return mul;
    }

    // A string representation of the rational number.
    public String toString() {
        long a = x, b = y;
        if (a == 0 || b == 1) {
            return a + "";
        }
        if (b < 0) {
            a *= -1;
            b *= -1;
        }
        return a + "/" + b;
    }

    // gcd(p, q), computed using Euclid's algorithm.
    private static long gcd(long p, long q) {
        return q == 0 ? p : gcd(q, p % q);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Rational total = new Rational(0);
        Rational term = new Rational(1);
        for (int i = 1; i <= n; i++) {
            total = total.add(term);
            term = term.multiply(new Rational(1, 2));
        }
        StdOut.println(total);
    }
}
