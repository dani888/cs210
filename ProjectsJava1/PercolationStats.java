import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.Random;

// Estimates percolation threshold for an N-by-N percolation system.
public class PercolationStats {
    private int T;
    private double[] p;
    // Perform T independent exps (Monte Carlo simulations) on an
    // N-by-N grid.
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Given N <= 0 || T <= 0");
        }
        this.T = T;
        p = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation test = new Percolation(N);
            while (!test.percolates()) {
                Random rand = new Random();
                test.open(rand.nextInt(N),rand.nextInt(N));
            }
            p[i] = (double) test.numberOfOpenSites() / (N * N);
        }
    }
    // Sample mean of percolation threshold.
    public double mean() {
        double sum = 0.0;
        for (int i = 0; i < this.p.length; i++) {
            sum += p[i];
        }
        return sum / this.p.length;
    }
    // Sample standard deviation of percolation threshold.
    public double stddev() {
        double sum = 0;
        double average = mean();
        for (int i=0; i<this.p.length; i++) {
            sum += Math.pow(this.p[i] - average,2);
        }
        return Math.sqrt( sum / (this.p.length - 1) );
    }
    // Low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    }
    // High endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }
    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.printf("mean           = %f\n", stats.mean());
        StdOut.printf("stddev         = %f\n", stats.stddev());
        StdOut.printf("confidenceLow  = %f\n", stats.confidenceLow());
        StdOut.printf("confidenceHigh = %f\n", stats.confidenceHigh());
    }
}