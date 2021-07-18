package week3.Assignment;

import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> cSegments = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException("The array of points are null");
        }
        checkDublicates(points);

        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);

        for (int i = 0; i < pointsCopy.length - 3; i++) {

            Point[] pointsBySlope = pointsCopy.clone();
            Arrays.sort(pointsBySlope, pointsCopy[i].slopeOrder());

            int j = 1;
            double currSlope = pointsCopy[i].slopeTo(pointsBySlope[j]);
            Point start = pointsBySlope[1];
            int count = 0;
            while (j < pointsBySlope.length) {
                double slope = pointsCopy[i].slopeTo(pointsBySlope[j]);
                if (Double.compare(slope, currSlope) == 0) {
                    count++;
                    j++;
                } else {
                    if (count >= 3 && pointsCopy[i].compareTo(start) < 0) {
                        cSegments.add(new LineSegment(pointsCopy[i], pointsBySlope[j - 1]));
                    }
                    currSlope = pointsCopy[i].slopeTo(pointsBySlope[j]);
                    start = pointsBySlope[j];
                    count = 1;
                    j++;
                }
            }
            if (count >= 3 && pointsCopy[i].compareTo(start) < 0) {
                cSegments.add(new LineSegment(pointsCopy[i], pointsBySlope[j - 1]));
            }

        }

    } // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return cSegments.size();
    }
    // the number of line segments

    public LineSegment[] segments() {
        return cSegments.toArray(new LineSegment[cSegments.size()]);
    } // the lin

    private void checkDublicates(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            Point x = points[i];
            if (x == null) {
                throw new IllegalArgumentException("points can't be null");
            }
            for (int j = i + 1; j < points.length; j++) {
                Point y = points[j];
                if (y == null) {
                    throw new IllegalArgumentException("points can't be null");
                }
                if (y.compareTo(x) == 0) {
                    throw new IllegalArgumentException("there are dublicates points");
                }
            }
        }

    }

    public static void main(String[] args) {

        /// read the n points from a file
        In in = new In("input6.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // // draw the points
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);
        // for (Point p : points) {
        // p.draw();
        // }
        // StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            StdOut.println();

            // segment.draw();
        }
        // StdDraw.show();
    }
}