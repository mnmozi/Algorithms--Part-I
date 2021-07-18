package week1.Assignment;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCEVALUE = 1.96;
    private final int t;
    private final double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException("N and tirals need to be > 0");
        }
        this.t = trials;
        results = new double[t];
        for (int i = 0; i < trials; i++) {

            Percolation x = new Percolation(n);
            int openSites = 0;
            while (!x.percolates()) {

                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                while (x.isOpen(row, col)) {
                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);
                }
                x.open(row, col);
                openSites++;
            }
            double result = (double) openSites / (n * n);
            results[i] = result;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        double mean = StdStats.mean(results);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double mean = StdStats.stddev(results);
        return mean;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - ((CONFIDENCEVALUE * stddev()) / Math.sqrt(t)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + ((CONFIDENCEVALUE * stddev()) / Math.sqrt(t)));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = -42;
        int t = 0;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            t = Integer.parseInt(args[1]);
        }
        PercolationStats x = new PercolationStats(n, t);
        System.out.println("mean                    = " + x.mean());
        System.out.println("stddev                  = " + x.stddev());
        System.out.println("95% confidence interval = [" + x.confidenceLo() + ", " + x.confidenceHi() + "]");

    }

}
