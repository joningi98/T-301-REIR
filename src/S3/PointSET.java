package S3;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Point2D;

import java.util.Arrays;

public class PointSET {
    private SET<Point2D> my_set;

    // construct an empty set of points
    public PointSET() {
        my_set = new SET<Point2D>();
    }

    // is the set empty ?
    public boolean isEmpty() {
        return my_set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return my_set.size();
    }

    // add the point p to the set (if it is not already in the set )
    public void insert(Point2D p) {
        if (!my_set.contains(p)) {
            my_set.add(p);
        }
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return my_set.contains(p);
    }

    // draw all of the points to standard draw
    public void draw() {
        for (Point2D p : my_set) {
            p.draw();
        }
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> set_inside_rect = new SET<Point2D>();
        for (Point2D p : my_set) {
            if (rect.contains(p)) {
                set_inside_rect.add(p);
            }
        }
        return set_inside_rect;
    }

    public static Point2D randomPoint(){
        double x = StdRandom.uniform();
        double y = StdRandom.uniform();
        return new Point2D(x,y);
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        Point2D nearest_point = null;

        for (Point2D curr_point : my_set) {
            if (nearest_point == null) {
                nearest_point = curr_point;
            }

            if (curr_point.distanceSquaredTo(p) < nearest_point.distanceSquaredTo(p)) {
                nearest_point = curr_point;
            }
        }
        return nearest_point;
    }
}