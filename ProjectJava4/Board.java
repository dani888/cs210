import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

// Models a board in the 8-puzzle game or its generalization.
public class Board {
    int[][] board;
    int[][] goalBoard;
    int N;
    int[][] manhattanDistances;

    // Construct a board from an N-by-N array of tiles, where
    // tiles[i][j] = tile at row i and column j, and 0 represents the blank
    // square.
    public Board(int[][] tiles) {
        this.board = tiles;
        this.N = tiles.length;
        this.goalBoard = new int[this.N][this.N];
        this.manhattanDistances = new int[this.N][this.N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.goalBoard[i][j] = (i * this.N) + j + 1;
                this.manhattanDistances[i][j] = manhattanDistance(i, j);
            }
        }
        this.goalBoard[this.N - 1][this.N - 1] = 0;
    }

    // Tile at row i and column j.
    public int tileAt(int i, int j) {
        return this.board[i][j];
    }

    // Size of this board.
    public int size() {
        return this.N;
    }

    // Number of tiles out of place.
    public int hamming() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.board[i][j] != 0 && this.board[i][j] != this.goalBoard[i][j]) {
                    // System.out.println("this.board[" + i + "][" + j + "] != this.goalBoard[" + i + "][" + j + "] = " + this.board[i][j] + "!=" + this.goalBoard[i][j]);
                    count++;
                }
            }
        }
        return count;
    }

    // Sum of Manhattan distances between tiles and goal.
    public int manhattan() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                total += manhattanDistances[i][j];
            }
        }
        return total;
    }

    public int manhattanDistance(int row, int col) {
        // System.out.println("row:" + row + ", col:" + col);
        if (this.board[row][col] == 0) {
            return 0;
        }
        int goalRow = ((this.board[row][col] - 1) / this.N);
        int goalCol = ((this.board[row][col] - 1) % this.N);
        // System.out.println("Math.abs(" + row + "-" + goalRow + ") + Math.abs(" + col + "-" + goalCol + ") = " + (Math.abs(row-goalRow) + Math.abs(col-goalCol)));
        return Math.abs(row - goalRow) + Math.abs(col - goalCol);
    }

    // Is this board the goal board?
    public boolean isGoal() {
        // System.out.println(goalBoardToString());
        return equalss(new Board(this.goalBoard));
    }

    // Is this board solvable?
    public boolean isSolvable() {
        if (this.N % 2 == 0) {
            //even
            // System.out.println("Even board N=" + this.N);
            if (((inversions() + blankPos()) % 2) == 0) {
                //even
                // System.out.println("Even inversions + blankPos inversions=" + inversions() + ", blankPos=" + blankPos());
                return false;
            }
            //odd
            // System.out.println("Odd inversions + blankPos inversions=" + inversions() + ", blankPos=" + blankPos());
            return true;
        } else {
            //odd
            // System.out.println("Odd board N=" + this.N);
            if (inversions() % 2 == 0) {
                //inversions even
                // System.out.println("Even inversions inversions=" + inversions());
                return true;
            }
            //inversions odd
            // System.out.println("Odd inversions inversions=" + inversions());
            return false;
        }
    }

    // Does this board equal that?
    public boolean equalss(Board that) {
        // System.out.println("in equalss");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.board[i][j] != that.board[i][j]) {
                    // System.out.println("i:" + i + "j:" + j + ", " + this.board[i][j] + "!=" +  that.board[i][j]);
                    return false;
                }
            }
        }
        // System.out.println("equals = ture");
        return true;
    }

    // All neighboring boards.
    public Iterable<Board> neighbors() {
        List<Board> boardList = new ArrayList<Board>();
        int zeroRow = 0;
        int zeroCol = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.board[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }
        if (zeroRow > 0) {
            //top
            Board newBoard = new Board(cloneTiles());
            newBoard.board[zeroRow][zeroCol] = tileAt(zeroRow - 1, zeroCol);
            newBoard.board[zeroRow - 1][zeroCol] = 0;
            newBoard.manhattanDistances[zeroRow][zeroCol] = newBoard.manhattanDistance(zeroRow, zeroCol);
            newBoard.manhattanDistances[zeroRow - 1][zeroCol] = 0;
            boardList.add(newBoard);
        }
        if (zeroCol < N - 1) {
            //right
            Board newBoard = new Board(cloneTiles());
            newBoard.board[zeroRow][zeroCol] = tileAt(zeroRow, zeroCol + 1);
            newBoard.board[zeroRow][zeroCol + 1] = 0;
            newBoard.manhattanDistances[zeroRow][zeroCol] = newBoard.manhattanDistance(zeroRow, zeroCol);
            newBoard.manhattanDistances[zeroRow][zeroCol + 1] = 0;
            boardList.add(newBoard);
        }
        if (zeroRow < N - 1) {
            //bottom
            Board newBoard = new Board(cloneTiles());
            newBoard.board[zeroRow][zeroCol] = tileAt(zeroRow + 1, zeroCol);
            newBoard.board[zeroRow + 1][zeroCol] = 0;
            newBoard.manhattanDistances[zeroRow][zeroCol] = newBoard.manhattanDistance(zeroRow, zeroCol);
            newBoard.manhattanDistances[zeroRow + 1][zeroCol] = 0;
            boardList.add(newBoard);
        }
        if (zeroCol > 0) {
            //left
            Board newBoard = new Board(cloneTiles());
            newBoard.board[zeroRow][zeroCol] = tileAt(zeroRow, zeroCol - 1);
            newBoard.board[zeroRow][zeroCol - 1] = 0;
            newBoard.manhattanDistances[zeroRow][zeroCol] = newBoard.manhattanDistance(zeroRow, zeroCol);
            newBoard.manhattanDistances[zeroRow][zeroCol - 1] = 0;
            boardList.add(newBoard);
        }
        return boardList;
    }

    // String representation of this board.
    public String toString() {
        String str = this.N + "\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j == 0 && this.board[i][j] > 9) {
                    str += this.board[i][j];
                } else if (j == 0 || this.board[i][j] > 9) {
                    str += " " + this.board[i][j];
                } else {
                    str += "  " + this.board[i][j];
                }
            }
            if (i != N - 1) {
                str += "\n";
            }

        }
        return str;

    }

    // String representation of this board.
    public String manhattanDistancesToString() {
        String str = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                str += " " + this.manhattanDistances[i][j];
            }
            str += "\n";
        }
        return str;

    }


    // String representation of this board.
    public String goalBoardToString() {
        String str = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                str += " " + this.goalBoard[i][j];
            }
            str += "\n";
        }
        return str;

    }

    // Helper method that returns the position (in row-major order) of the
    // blank (zero) tile.
    private int blankPos() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.board[i][j] == 0) {
                    return i;
                }
            }
        }
        return 0;
    }

    // Helper method that returns the number of inversions.
    private int inversions() {
        int inversions = 0;
        int[] flatBoard = new int[this.N * this.N];
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length ; j++ ) {
                int flatIndex = (i * this.N) + (j % this.N);
                // System.out.println(flatIndex);
                flatBoard[flatIndex] = this.board[i][j];
            }
        }
        // System.out.println(Arrays.toString(flatBoard));
        for (int i = 0; i < flatBoard.length; i++) {
            for (int j = i + 1; j < flatBoard.length ; j++ ) {
                if (flatBoard[i] > flatBoard[j] && flatBoard[j] != 0) {
                    // System.out.println("flatBoard[" + i + "] > flatBoard[" + j + "] = " + flatBoard[i] + " > " + flatBoard[j]);
                    inversions++;
                }
            }
        }
        return inversions;
    }

    // Helper method that clones the tiles[][] array in this board and
    // returns it.
    private int[][] cloneTiles() {
        int[][] clone = new int[this.N][this.N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                clone[i][j] = board[i][j];
            }
        }
        return clone;
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
        Board board = new Board(tiles);
        // StdOut.println(board);
        // StdOut.println(board.goalBoardToString());
        StdOut.println(board.hamming());
        StdOut.println(board.manhattan());
        // StdOut.println(board.manhattanDistancesToString());
        StdOut.println(board.isGoal());
        StdOut.println(board.isSolvable());
        for (Board neighbor : board.neighbors()) {
            StdOut.println(neighbor);
        }
    }
}
