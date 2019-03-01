import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// Models an N-by-N percolation system.
public class Percolation {
    public boolean grid[][];
    public WeightedQuickUnionUF ufGrid;
    public WeightedQuickUnionUF ufGridBackWash;
    public int openSites;
    public int N;

    // Create an N-by-N grid, with all sites blocked.
    public Percolation(int N) {
        this.grid = new boolean [N][N];
        this.ufGrid = new WeightedQuickUnionUF(N * N + 2);
        this.ufGridBackWash = new WeightedQuickUnionUF(N * N + 1);
        this.N = N;
        this.openSites = 0;
        for (int i = 0; i < N; i++ ) {
            for (int j = 0; j < N; j++) {
                this.grid[i][j] = false;

                if (i == 0) {
                    this.ufGrid.union(0, this.encode(i, j));
                    this.ufGridBackWash.union(0, this.encode(i, j));

                } else if (i == N - 1) {
                    this.ufGrid.union(N * N + 1, this.encode(i, j));
                }
            }
        }

    }
    // Open site (i, j) if it is not open already.
    public void open(int i, int j) {
        if (!isOpen(i, j)) {
            openSites++;
            this.grid[i][j] = true;
            if (i > 0 && isOpen(i - 1, j)) {
                this.ufGrid.union(encode(i - 1, j), encode(i, j));
                this.ufGridBackWash.union(encode(i - 1, j), encode(i, j));
            }
            if (i < N - 1 && isOpen(i + 1, j)) {
                this.ufGrid.union(encode(i + 1, j), encode(i, j));
                this.ufGridBackWash.union(encode(i + 1, j), encode(i, j));
            }
            if (j > 0 && isOpen(i, j - 1)) {
                this.ufGrid.union(encode(i, j - 1), encode(i, j));
                this.ufGridBackWash.union(encode(i, j - 1), encode(i, j));
            }
            if (j < N - 1 && isOpen(i, j + 1)) {
                this.ufGrid.union(encode(i, j + 1), encode(i, j));
                this.ufGridBackWash.union(encode(i, j + 1), encode(i, j));
            }
        }
    }
    // Is site (i, j) open?
    public boolean isOpen(int i, int j) {
        return this.grid[i][j];
    }
    // Is site (i, j) full?
    public boolean isFull(int i, int j) {
        //System.out.print("checking if i:" + String.valueOf(i) + ", j" + String.valueOf(j) + " is full : ");
        //System.out.println(this.ufGridBackWash.connected(0,encode(i,j)));
        return isOpen(i, j) && this.ufGridBackWash.connected(0, encode(i, j));
    }
    // Number of open sites.
    public int numberOfOpenSites() {
        return this.openSites;
    }
    //Does the system percolate?
    public boolean percolates() {
        return this.ufGrid.connected(0, N * N + 1);
    }


    // An integer ID (1...N) for site (i, j).
    private int encode(int i, int j) {
        return (i * N) + j + 1;
    }

// Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.println(perc.numberOfOpenSites() + " open sites");
        if (perc.percolates()) {
            StdOut.println("percolates");
        } else {
            StdOut.println("does not percolate");
        }

        // Check if site (i, j) optionally specified on the command line
        // is full.
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(perc.isFull(i, j));
        }
    }
}