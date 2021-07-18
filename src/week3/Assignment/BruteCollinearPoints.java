package week3.Assignment;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private final ArrayList<LineSegment> fourPointsSegments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("The array of points are null");
        }
        checkDublicates(points);
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        for (int first = 0; first < pointsCopy.length - 3; first++) {
            for (int second = first + 1; second < pointsCopy.length - 2; second++) {
                double fsSlope = pointsCopy[first].slopeTo(pointsCopy[second]);
                for (int third = second + 1; third < pointsCopy.length - 1; third++) {
                    double stSlope = pointsCopy[second].slopeTo(pointsCopy[third]);
                    if (fsSlope == stSlope) {
                        for (int fourth = third + 1; fourth < pointsCopy.length; fourth++) {
                            if (pointsCopy[fourth].slopeTo(pointsCopy[third]) == stSlope) {
                                fourPointsSegments.add(new LineSegment(pointsCopy[first], pointsCopy[fourth]));
                            }
                        }
                    }
                }

            }
        }
    } // finds all line segments containing 4 points

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

    public int numberOfSegments() {
        return fourPointsSegments.size();
    }
    // the number of line segments

    public LineSegment[] segments() {
        return fourPointsSegments.toArray(new LineSegment[fourPointsSegments.size()]);
    } // the line segments

}