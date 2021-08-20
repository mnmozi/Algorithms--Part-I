package week4.Assignment;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private class BoardPlus {
        private final Board board;
        private final int moves;
        private final BoardPlus prev;

        public BoardPlus(Board board, int moves, BoardPlus prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
        }

    }

    private class ManhattanCompare implements Comparator<BoardPlus> {
        public int compare(BoardPlus board1, BoardPlus board2) {
            int board1Manhattan = board1.board.manhattan();
            int board2Manhattan = board2.board.manhattan();
            int priotiryBoard1 = board1Manhattan + board1.moves;
            int priorityBoard2 = board2Manhattan + board2.moves;

            if (priotiryBoard1 < priorityBoard2) {
                return -1;
            }

            if (priotiryBoard1 > priorityBoard2) {
                return 1;
            }

            if (board1Manhattan < board2Manhattan) {
                return -1;
            }

            if (board1Manhattan > board2Manhattan) {
                return 1;
            }

            return 0;
        }
    }

    // find a solution to the initial board (using the A* algorithm)

    private boolean goalBoard;
    private final Deque<Board> steps = new ArrayDeque<Board>();

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("The initial board can't be null!");
        }
        BoardPlus initialBoardPlus = new BoardPlus(initial, 0, null);

        MinPQ<BoardPlus> originalBoards;
        MinPQ<BoardPlus> twinBoards;
        originalBoards = new MinPQ<BoardPlus>(0, manhattanSort());
        twinBoards = new MinPQ<BoardPlus>(0, manhattanSort());

        originalBoards.insert(initialBoardPlus);
        twinBoards.insert(new BoardPlus(initialBoardPlus.board.twin(), 0, null));
        // solve it here
        while (true) {

            BoardPlus oMinBoard = originalBoards.min();
            if (oMinBoard.board.isGoal()) {
                goalBoard = true;
                break;
            } else {
                // originalSeen.add(minBoard.toString());
                originalBoards.delMin();
                for (Board board : oMinBoard.board.neighbors()) {
                    if (oMinBoard.prev != null) {
                        if (!board.equals(oMinBoard.prev.board))
                            originalBoards.insert(new BoardPlus(board, oMinBoard.moves + 1, oMinBoard));
                    } else {
                        originalBoards.insert(new BoardPlus(board, oMinBoard.moves + 1, oMinBoard));
                    }
                }
            }

            // do one step in the original
            // do one step in the twin

            BoardPlus tMinBoard = twinBoards.min();
            if (tMinBoard.board.isGoal()) {
                goalBoard = false;
                break;
            } else {
                twinBoards.delMin();
                for (Board board : tMinBoard.board.neighbors()) {
                    if (tMinBoard.prev != null) {
                        if (!board.equals(tMinBoard.prev.board))
                            twinBoards.insert(new BoardPlus(board, tMinBoard.moves + 1, tMinBoard));
                    } else {
                        twinBoards.insert(new BoardPlus(board, tMinBoard.moves + 1, tMinBoard));
                    }
                }

            }
        }

        if (goalBoard) {
            steps.push(originalBoards.min().board);
            BoardPlus prevBoard = originalBoards.min().prev;
            while (prevBoard != null) {
                steps.push(prevBoard.board);
                prevBoard = prevBoard.prev;
            }
        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return goalBoard;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (goalBoard) {
            return steps.size() - 1;
        } else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (goalBoard)
            return steps;
        else
            return null;
    }

    private Comparator<BoardPlus> manhattanSort() {
        return new ManhattanCompare();
    }

    // private Comparator<BoardPlus> hammingSort() {
    // return new hammingCompare();
    // }

    // private class hammingCompare implements Comparator<BoardPlus> {
    // public int compare(BoardPlus board1, BoardPlus board2) {
    // if (board1.board.hamming() + board1.moves < board2.board.hamming() +
    // board2.moves)
    // return -1;
    // if (board1.board.hamming() + board1.moves > board2.board.hamming() +
    // board2.moves)
    // return 1;
    // else
    // return 0;
    // }
    // }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In("puzzle04.txt");
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}