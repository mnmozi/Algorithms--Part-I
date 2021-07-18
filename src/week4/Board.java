package week4;

import java.util.ArrayList;

public class Board {

    private final int[][] tiles;
    private final int n;
    private int manhattanDis = 0;
    private boolean manhattanSet;

    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
        this.n = tiles.length;
        this.manhattanSet = false;
        // this.manhattanDis = this.manhattan();
    }

    // string representation of this board
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(n + "\n");

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                str.append(tiles[i][j] + " ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int currValue = tiles[i][j];
                if (currValue == 0) {
                    continue;
                }
                int correctValue;
                if (i == n - 1 && j == n - 1) {
                    correctValue = 0;
                } else
                    correctValue = (i * n) + (j + 1);
                if (currValue != correctValue) {
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        if (this.manhattanSet) {
            return this.manhattanDis;
        }
        // move horizontal then vertical
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int currValue = tiles[i][j];
                int yPos;
                int xPos;
                if (currValue == 0) {
                    continue;
                } else {
                    yPos = ((currValue - 1) / n);
                    xPos = (currValue - 1) % n;
                }

                int distance = Math.abs(i - yPos) + Math.abs(j - xPos);
                count += distance;
            }

        }
        // if (manhattanSet && count != manhattanDis) {
        // System.out.println(this.toString() + "..." + count + "..." + manhattanDis);
        // }
        manhattanDis = count;
        manhattanSet = true;
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) {
                    if (tiles[i][j] != 0)
                        return false;
                }

                else if ((i * n) + (j + 1) != tiles[i][j])
                    return false;

            }

        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null)
            return false;

        if (this.tiles == y)
            return true;

        if (y.getClass() != this.getClass())
            return false;

        if (((Board) y).n != this.n) {
            return false;
        }
        if (this.getClass() == y.getClass()) {
            Board that = (Board) y;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (this.tiles[i][j] != that.tiles[i][j])
                        return false;

                }
            }
        }

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<Board>();
        int i = 0;
        int j = 0;
        for (i = 0; i < n; i++) {
            boolean found = false;
            for (j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    found = true;
                    break;
                }
            }
            if (found)
                break;
        }

        // up
        if (i != 0) {
            int[][] newTiles = this.tiles.clone();
            for (int tile = 0; tile < newTiles.length; tile++) {
                newTiles[tile] = this.tiles[tile].clone();
            }
            newTiles[i][j] = newTiles[i - 1][j];
            newTiles[i - 1][j] = 0;
            Board newBoard = new Board(newTiles);
            neighbors.add(newBoard);
        }

        // down

        if (i != n - 1) {
            int[][] newTiles = this.tiles.clone();
            for (int tile = 0; tile < newTiles.length; tile++) {
                newTiles[tile] = this.tiles[tile].clone();
            }

            newTiles[i][j] = newTiles[i + 1][j];
            newTiles[i + 1][j] = 0;
            Board newBoard = new Board(newTiles);

            neighbors.add(newBoard);
        }

        // check if the cell is on one of the edgs
        // right
        if (j != 0) {
            int[][] newTiles = this.tiles.clone();
            for (int tile = 0; tile < newTiles.length; tile++) {
                newTiles[tile] = this.tiles[tile].clone();
            }

            newTiles[i][j] = newTiles[i][j - 1];
            newTiles[i][j - 1] = 0;
            Board newBoard = new Board(newTiles);

            neighbors.add(newBoard);
        }

        // left

        if (j != n - 1) {
            int[][] newTiles = this.tiles.clone();
            for (int tile = 0; tile < newTiles.length; tile++) {
                newTiles[tile] = this.tiles[tile].clone();
            }
            newTiles[i][j] = newTiles[i][j + 1];
            newTiles[i][j + 1] = 0;
            Board newBoard = new Board(newTiles);

            neighbors.add(newBoard);
        }

        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    // here i will make it random to pick a random cell and change it with the next
    // one

    public Board twin() {
        int i = 0;
        int j = 0;
        if (this.tiles[i][j] == 0 || this.tiles[i][j + 1] == 0) {
            i++;
        }

        int[][] newTiles = this.tiles.clone();
        for (int tile = 0; tile < newTiles.length; tile++) {
            newTiles[tile] = this.tiles[tile].clone();
        }
        int tmp = newTiles[i][j];
        newTiles[i][j] = newTiles[i][j + 1];
        newTiles[i][j + 1] = tmp;

        Board newBoard = new Board(newTiles);

        return newBoard;
    }

    public static void main(String[] args) {
        // In in = new In("puzzle04.txt");
        // int n = in.readInt();
        // int[][] tiles = new int[n][n];
        // for (int i = 0; i < n; i++)
        // for (int j = 0; j < n; j++)
        // tiles[i][j] = in.readInt();
        // Board initial = new Board(tiles);
        // System.out.println(initial.manhattan());
        // int i = 0;
        // System.out.println(initial.manhattanDis);
        // for (Board x : initial.neighbors()) {
        // if (i == 1) {

        // // break;
        // }
        // System.out.println(x.manhattanDis);
        // i++;
        // }
        // Board twin1 = initial.twin();
        // Board twin2 = initial.twin();
        // System.out.println(twin1.getClass() + "..." + initial.getClass());
        // System.out.println(twin1.equals(initial));

    }

}
