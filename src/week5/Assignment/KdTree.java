package week5.Assignment;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private class Node {
        Point2D point;
        Node left;
        Node right;
        // rect is one of the two rectangles which the prev Node make
        RectHV rect;

        Node(Point2D point, RectHV rect) {
            this.point = point;
            this.rect = rect;
        }

    }

    private Node root;
    private int size;

    public KdTree() {
        size = 0;
    } // construct an empty set of points

    public boolean isEmpty() {
        return root == null;
    } // is the set empty?

    public int size() {
        return size;
    } // number of points in the set

    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("Input point Can't be null!");
        boolean lvl = true;
        root = insertHelper(p, lvl, root, null, false);

    } // add the point to the set (if it is not already in the set)

    private Node insertHelper(Point2D p, boolean lvl, Node currNode, Node prevNode, boolean direction) { // false left
                                                                                                         // true right
        if (currNode == null) {
            Node newNode = new Node(p, null);
            size++;
            if (prevNode == null) {
                newNode.rect = new RectHV(0, 0, 1, 1);
                return newNode;
            }
            // Here we give the node (The one I'm inserting) It's rectangle
            // Note I'm passing !lvl because I want the rectangle of the previous Node not
            // the current
            newNode.rect = rectangleGetter(prevNode, !lvl, direction);
            return newNode;
        }
        // If the point is dublicated we Ignore it
        if (currNode.point.equals(p)) {
            return currNode;
        }
        // lvl variable holds the spliting line
        // true is vertical line So we check the X axes
        // false is horizontal line So we check the Y axes
        if (lvl) {
            if (currNode.point.x() > p.x()) {
                currNode.left = insertHelper(p, !lvl, currNode.left, currNode, false);
            } else {
                currNode.right = insertHelper(p, !lvl, currNode.right, currNode, true);
            }
            return currNode;

        } else {
            if (currNode.point.y() > p.y()) {
                currNode.left = insertHelper(p, !lvl, currNode.left, currNode, false);
            } else {
                currNode.right = insertHelper(p, !lvl, currNode.right, currNode, true);
            }
            return currNode;
        }

    }

    private RectHV rectangleGetter(Node currpoint, boolean lvl, boolean direction) {
        // lvl variable holds the spliting line
        // true is vertical line So we check the X axes
        // false is horizontal line So we check the Y axes
        if (lvl) {
            // check the point is (left/bottom) or (right/up)
            if (!direction)
                return new RectHV(currpoint.rect.xmin(), currpoint.rect.ymin(), currpoint.point.x(),
                        currpoint.rect.ymax());
            else
                return new RectHV(currpoint.point.x(), currpoint.rect.ymin(), currpoint.rect.xmax(),
                        currpoint.rect.ymax());
        } else {

            if (!direction)
                return new RectHV(currpoint.rect.xmin(), currpoint.rect.ymin(), currpoint.rect.xmax(),
                        currpoint.point.y());
            else
                return new RectHV(currpoint.rect.xmin(), currpoint.point.y(), currpoint.rect.xmax(),
                        currpoint.rect.ymax());
        }
    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("Input point Can't be null!");
        Node curr = root;
        boolean lvl = true;
        while (curr != null) {
            if (curr.point.equals(p))
                return true;
            if (lvl) {
                if (curr.point.x() > p.x()) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            } else {
                if (curr.point.y() > p.y()) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
            lvl = !lvl;
        }
        return false;
    } // does the set contain point p?

    // public void draw() {
    // int x = 1;
    // int y = x;

    // } // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException("Input point Can't be null!");
        ArrayList<Point2D> result = new ArrayList<Point2D>();
        boolean lvl = true;
        rangeHelper(rect, result, root, lvl);
        return result;
    } // all points that are inside the rectangle (or on the boundary)

    private void rangeHelper(RectHV rect, ArrayList<Point2D> result, Node currNode, boolean lvl) {
        if (currNode == null)
            return;
        if (rect.contains(currNode.point)) {
            result.add(currNode.point);
        }
        int rectPosition = rectPosition(currNode, rect, lvl);
        if (rectPosition == 0) {
            rangeHelper(rect, result, currNode.left, !lvl);
            rangeHelper(rect, result, currNode.right, !lvl);
        } else if (rectPosition == 1) {
            rangeHelper(rect, result, currNode.right, !lvl);
        } else {
            rangeHelper(rect, result, currNode.left, !lvl);
        }
    }

    private static int rectPosition(Node currNode, RectHV rect, boolean lvl) {
        // (Left/bottom)-> -1
        // middle -> 0
        // (right/up) -> 1
        if (lvl) {
            if (rect.xmin() > currNode.point.x())
                return 1;
            else if (rect.xmax() < currNode.point.x())
                return -1;
            else
                return 0;
        } else {
            if (rect.ymin() > currNode.point.y())
                return 1;
            else if (rect.ymax() < currNode.point.y())
                return -1;
            else
                return 0;
        }

    } // returns the position of the line In respect to the rectangle

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("Input point Can't be null!");
        return nearestHelper(p, root, null, true);
    } // a nearest neighbor in the set to point p; null if the set is empty

    private Point2D nearestHelper(Point2D p, Node currNode, Point2D minPoint, boolean lvl) {
        if (currNode == null) {
            return minPoint;
        }
        // see the distance between the current node and the point
        double currDistance = currNode.point.distanceSquaredTo(p);
        if (minPoint == null)
            minPoint = currNode.point;
        else if (currDistance < minPoint.distanceSquaredTo(p))
            minPoint = currNode.point;

        // get the direction of the point in my current position
        RectHV leftRect = currNode.left != null ? currNode.left.rect : rectangleGetter(currNode, lvl, false);
        RectHV rightRect = currNode.right != null ? currNode.right.rect : rectangleGetter(currNode, lvl, true);

        boolean contains = false;
        boolean containsDir = false;
        // ----- Here we check the rectangle that contains the point -----
        if (leftRect.contains(p)) {
            minPoint = nearestHelper(p, currNode.left, minPoint, !lvl);
            contains = true;
            // If the 2nd rectangle (The one that doesn't contain the point)
            // is nearer than the nearest point in the containg rectangle THEN
            // there may be a change that that rect contains the nearest Point
            if (minPoint.distanceSquaredTo(p) > rightRect.distanceSquaredTo(p)) {
                return nearestHelper(p, currNode.right, minPoint, !lvl);
                // minPoint = nearestHelper(p, currNode.right, minPoint, true);
            }

        } else if (rightRect.contains(p)) {

            minPoint = nearestHelper(p, currNode.right, minPoint, !lvl);
            contains = true;
            containsDir = true;
            if (minPoint.distanceSquaredTo(p) > leftRect.distanceSquaredTo(p)) {
                return nearestHelper(p, currNode.left, minPoint, !lvl);
                // minPoint = nearestHelper(p, currNode.left, minPoint, false);
            }
        }
        // Here I just priortize which rectangle to look in first
        if (leftRect.distanceSquaredTo(p) < rightRect.distanceSquaredTo(p)) {

            // The first boolean check is just to avoid redundant Iterations
            // If we checked (In line 214) for the left rectangle
            // we shouldn't go again to the left one ... just the right one.
            if (!(contains && !containsDir) && minPoint.distanceSquaredTo(p) > leftRect.distanceSquaredTo(p)) {
                minPoint = nearestHelper(p, currNode.left, minPoint, !lvl);
            }
            if (!(contains && containsDir) && minPoint.distanceSquaredTo(p) > rightRect.distanceSquaredTo(p)) {
                minPoint = nearestHelper(p, currNode.right, minPoint, !lvl);
            }
        } else {
            if (!(contains && containsDir) && minPoint.distanceSquaredTo(p) > rightRect.distanceSquaredTo(p)) {
                minPoint = nearestHelper(p, currNode.right, minPoint, !lvl);
            }
            if (!(contains && !containsDir) && minPoint.distanceSquaredTo(p) > leftRect.distanceSquaredTo(p)) {
                minPoint = nearestHelper(p, currNode.left, minPoint, !lvl);
            }

        }

        return minPoint;
    }
}
