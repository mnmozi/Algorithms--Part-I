package week5;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
    private final SET<Point2D> points;

    public PointSET() {
        points = new SET<Point2D>();

    } // construct an empty set of points

    public boolean isEmpty() {
        return points.isEmpty();
    } // is the set empty?

    public int size() {
        return points.size();
    } // number of points in the set

    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("Input point Can't be null!");
        points.add(p);
    } // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("Input point Can't be null!");
        return points.contains(p);
    } // does the set contain point p?

    public void draw() {
        for (Point2D point2d : points) {
            point2d.draw();
        }
    } // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException("Input rectangle Can't be null!");
        ArrayList<Point2D> result = new ArrayList<Point2D>();
        for (Point2D point2d : points) {
            if (rect.contains(point2d)) {
                result.add(point2d);
            }
        }
        return result;
    } // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("Input point Can't be null!");
        double min = -1;
        Point2D result = null;
        for (Point2D point2d : points) {
            // if (!point2d.equals(p)) {

            double currDistance = point2d.distanceSquaredTo(p);
            if (min == -1) {
                min = currDistance;
                result = point2d;
            } else {
                if (min > currDistance) {
                    min = currDistance;
                    result = point2d;
                }

            }
            // }
        }
        return result;
    } // a nearest neighbor in the set to point p; null if the set is empty

}
