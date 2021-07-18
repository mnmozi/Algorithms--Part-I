package week1.Assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] openStatus;
    private final WeightedQuickUnionUF connectionGrid;
    private final WeightedQuickUnionUF fullGrid; // to handle the case of two paths (one percolates and full the other
                                                 // only connected to the bottom)
    private final int n;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Can't make a grid with 0 or less size");
        }
        this.n = n;
        this.openSites = 0;
        openStatus = new boolean[n][n];
        // add two points top and bottom at 0 and n+1
        connectionGrid = new WeightedQuickUnionUF((n * n) + 2);
        fullGrid = new WeightedQuickUnionUF((n * n) + 1);

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        pointValidity(row, col);
        if (isOpen(row, col)) {
            return;
        }
        openSites += 1;

        // open it in openStatus
        int zIndexRow = row - 1;
        int zIndexCol = col - 1;
        openStatus[zIndexRow][zIndexCol] = true;
        // if in the 1st row we union to the topPoint
        if (row == 1) {
            // we add one as the 1st point in the array is the top Point
            connectionGrid.union(0, arrayflatten(row, col) + 1);
            fullGrid.union(0, arrayflatten(row, col) + 1);
        }

        // if in the nth row we union to the bottomPoint
        if (row == n) {
            connectionGrid.union((n * n) + 1, arrayflatten(row, col) + 1);
        }
        // check the neighboors

        // right
        if (pointExistance(row, col + 1) && isOpen(row, col + 1)) {
            connectionGrid.union(arrayflatten(row, col + 1) + 1, arrayflatten(row, col) + 1);
            fullGrid.union(arrayflatten(row, col + 1) + 1, arrayflatten(row, col) + 1);
        }
        // left
        if (pointExistance(row, col - 1) && isOpen(row, col - 1)) {
            connectionGrid.union(arrayflatten(row, col - 1) + 1, arrayflatten(row, col) + 1);
            fullGrid.union(arrayflatten(row, col - 1) + 1, arrayflatten(row, col) + 1);
        }
        // top
        if (pointExistance(row + 1, col) && isOpen(row + 1, col)) {
            connectionGrid.union(arrayflatten(row + 1, col) + 1, arrayflatten(row, col) + 1);
            fullGrid.union(arrayflatten(row + 1, col) + 1, arrayflatten(row, col) + 1);
        }
        // bottom
        if (pointExistance(row - 1, col) && isOpen(row - 1, col)) {
            connectionGrid.union(arrayflatten(row - 1, col) + 1, arrayflatten(row, col) + 1);
            fullGrid.union(arrayflatten(row - 1, col) + 1, arrayflatten(row, col) + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        pointValidity(row, col);
        return openStatus[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        pointValidity(row, col);
        return fullGrid.find(0) == fullGrid.find(arrayflatten(row, col) + 1);

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return connectionGrid.find(0) == connectionGrid.find((n * n) + 1);
    }

    // ----- My methods -----
    private int arrayflatten(int row, int col) {
        int position = ((row - 1) * n) + (col - 1);
        return position;
    }

    // if the point valid on the grid
    private boolean pointValidity(int row, int col) {
        int zIndexRow = row - 1;
        int zIndexCol = col - 1;
        if (zIndexRow >= 0 && zIndexRow < n && zIndexCol >= 0 && zIndexCol < n) {
            return true;
        }
        throw new IllegalArgumentException("the points are not correct");
    }

    private boolean pointExistance(int row, int col) {
        int zIndexRow = row - 1;
        int zIndexCol = col - 1;
        if (zIndexRow >= 0 && zIndexRow < n && zIndexCol >= 0 && zIndexCol < n) {
            return true;
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation x = new Percolation(4);
        x.open(1, 1);
        x.open(2, 1);
        x.open(2, 2);
        x.open(3, 2);
        x.open(4, 2);

        System.out.println(x.isFull(2, 1));
        System.out.println(x.percolates());
    }
}
