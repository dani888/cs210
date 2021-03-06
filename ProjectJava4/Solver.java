import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedStack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

// A solver based on the A* algorithm for the 8-puzzle and its generalizations.
public class Solver {
    public PriorityQueue<SearchNode> searchNodes = new PriorityQueue<SearchNode>(1, new HammingOrder());
    public List<Board> shortest = new ArrayList<Board>();


    // Helper search node class.
    private class SearchNode {
        Board board;
        int moves;
        SearchNode previous;

        SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }


    }

    // Find a solution to the initial board (using the A* algorithm).
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        if (!initial.isSolvable()) {
            throw new IllegalArgumentException();
        }

        searchNodes.add(new SearchNode(initial, 0, null));

        while (true) {
            SearchNode deq = searchNodes.poll();
            // System.out.println(deq.board);
            // System.out.println(deq.board.isGoal());
            if (deq.board.isGoal()) {
                // System.out.println("Goal!");
                while (deq.previous != null) {
                    // System.out.println("dqu");
                    shortest.add(0, deq.board);
                    deq = deq.previous;
                }
                // System.out.println("done");
                return;
            }
            Iterable<Board> neighbors = deq.board.neighbors();
            for (Board next : neighbors) {
                searchNodes.add(new SearchNode(next, deq.moves++, deq));
            }
        }

        //A* algorythm

    }

    // The minimum number of moves to solve the initial board.
    public int moves() {
        return shortest.size();
    }

    // Sequence of boards in a shortest solution.
    public Iterable<Board> solution() {
        return this.shortest;
    }

    // Helper hamming priority function comparator.
    private static class HammingOrder implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b) {
            if (a.board.hamming() > b.board.hamming()) {
                return 1;
            } else if (a.board.hamming() == b.board.hamming()) {
                return 0;
            }
            return -1;
        }
    }

    // Helper manhattan priority function comparator.
    private static class ManhattanOrder implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b) {
            if (a.board.manhattan() > b.board.manhattan()) {
                return 1;
            } else if (a.board.manhattan() == b.board.manhattan()) {
                return 0;
            }
            return -1;
        }
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board initial = new Board(tiles);
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        } else {
            StdOut.println("Unsolvable puzzle");
        }
    }
}
