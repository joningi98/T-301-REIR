package S3;

import edu.princeton.cs.algs4.*;

import java.awt.*;
import java.util.Stack;

public class KdTree {

    private int size_of_tree = 0;
    private Node root;

    private static class Node{
        private Point2D p;
        private Node left, right;
        private boolean vertical;
        private RectHV rect;

        Node(Point2D p, Node left, Node right, boolean vertical, RectHV rect){
            this.p = p;
            this.left = left;
            this.right = right;
            this.vertical = vertical;
            this.rect = rect;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return size_of_tree;
    }

    // add the point p to the set (if it is not already in the set )
    public void insert(Point2D p) {
        size_of_tree++;
        if (isEmpty()){
            RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
            root = new Node(p,null, null, true, rect);
        }
        else{
            root = insert_helper(root, p, true);
        }
    }

    private Node insert_helper(Node node, Point2D new_node, boolean vertical){
        if (vertical){
            if (new_node.x() < node.p.x()){
                if (node.left == null){
                    RectHV rect = get_rect(node, new_node);
                    node.left = new Node(new_node, null, null, !vertical, rect);
                }
                node.left = insert_helper(node.left, new_node, !vertical);
            }
            if (new_node.x() > node.p.x()){
                if (node.right == null){
                    RectHV rect = get_rect(node, new_node);
                    node.right = new Node(new_node, null, null, !vertical, rect);
                }
                node.right = insert_helper(node.right, new_node, !vertical);
            }
        }
        if (!vertical){
            if (new_node.y() < node.p.y()){
                if (node.left == null){
                    RectHV rect = get_rect(node, new_node);
                    node.left = new Node(new_node, null, null, !vertical, rect);
                }
                node.left = insert_helper(node.left, new_node, !vertical);
            }
            if (new_node.y() > node.p.y()){
                if (node.right == null){
                    RectHV rect = get_rect(node, new_node);
                    node.right = new Node(new_node, null, null, !vertical, rect);
                }
                node.right = insert_helper(node.right, new_node, !vertical);
            }
        }
        return node;
    }

    private RectHV get_rect(Node old_node, Point2D next_node){
        double xmin = 0.0;
        double ymin = 0.0;
        double xmax = 1.0;
        double ymax = 1.0;
        if (old_node.vertical){
            if (old_node.p.x() <= next_node.x()){
                xmin = old_node.p.x();
                xmax = old_node.rect.xmax();
            }
            else{
                xmax = old_node.p.x();
                xmin = old_node.rect.xmin();
            }
            ymax = old_node.rect.ymax();
            ymin = old_node.rect.ymin();
        }
        if (!old_node.vertical){
            if (old_node.p.y() <= next_node.y()){
                ymin = old_node.p.y();
                ymax = old_node.rect.ymax();
            }
            else{
                ymax = old_node.p.y();
                ymin = old_node.rect.ymin();
            }
            xmax = old_node.rect.xmax();
            xmin = old_node.rect.xmin();
        }
        return new RectHV(xmin, ymin, xmax, ymax);
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return contains_helper(root, p, true);
    }

    private static boolean contains_helper(Node node, Point2D target, boolean vertical){
        if (node == null){
            return false;
        }
        if (node.p.x() == target.x() && node.p.y() == target.y()){
            return true;
        }
        if (vertical){
            if (target.x() < node.p.y()){
                return contains_helper(node, target, false);
            }
            if (target.x() > node.p.x()){
                return contains_helper(node.right, target, false);
            }
        }
        if (!vertical){
            if (target.y() < node.p.y()){
                return contains_helper(node, target, true);
            }
            if (target.y() > node.p.y()){
                return contains_helper(node.right, target, true);
            }
        }
        return false;
    }

    // draw all of the points to standard draw
    public void draw() {
        StdDraw.setPenRadius(0.02);
        draw_helper(root,  true);
    }

    private void draw_helper(Node node, boolean vertical) {
        if (node != null) {
            if (vertical) { // Vertical line
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(Color.RED);
                StdDraw.line(node.p.x(), node.rect.ymax(), node.p.x(), node.rect.ymin());
            }
            if (!vertical) { // Horizontal line
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
            }
            StdDraw.setPenRadius(0.02);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(node.p.x(), node.p.y());
            draw_helper(node.left, !vertical);
            draw_helper(node.right, !vertical);
        }
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> my_stack;
        my_stack = new Stack<>();
        return range_helper(root, rect, my_stack);
    }

    private static Stack<Point2D> range_helper(Node node, RectHV rect, Stack<Point2D> my_stack){
        if (rect.intersects(node.rect)){
            if (rect.contains(node.p)){
                my_stack.push(node.p);
            }
        }
        if (node.left != null){
            range_helper(node.left, rect, my_stack);
        }
        if (node.right != null){
            range_helper(node.right, rect, my_stack);
        }
        return my_stack;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        return nearest_helper(root, p, root.p);
    }

    private Point2D nearest_helper(Node node, Point2D target, Point2D nearest_neighbor){
        if (node != null){
            if (node.rect.contains(target)){
                if (node.p.distanceSquaredTo(target) < nearest_neighbor.distanceSquaredTo(target)){
                    nearest_neighbor = node.p;
                    if (node.left.rect.contains(target)){
                        nearest_neighbor = nearest_helper(node.left, target, nearest_neighbor);
                    }
                    if (node.right.rect.contains(target)){
                        nearest_neighbor = nearest_helper(node.right, target, nearest_neighbor);
                    }
                }
            }
        }
        return nearest_neighbor;
    }

    public static Point2D randomPoint(){
        double x = StdRandom.uniform(1);
        double y = StdRandom.uniform(1);
        return new Point2D(x,y);
    }

    public static void main(String[] args){
        KdTree my_tree = new KdTree();

        Point2D[] my_points = {new Point2D(0.7, 0.2),
                               new Point2D(0.5, 0.4),
                               new Point2D(0.2, 0.3),
                               new Point2D(0.4, 0.7),
                               new Point2D(0.9, 0.6)};

        for(Point2D p: my_points){
            my_tree.insert(p);
        }
        StdOut.println(my_tree.contains(new Point2D(0.7, 0.2)));
        Iterable<Point2D> my_set = my_tree.range(new RectHV(0.0, 0.0, 1.0, 1.0));
        Point2D nearest_point = my_tree.nearest(new Point2D(0.6, 0.2));
        StdOut.println(nearest_point);
        StdOut.println(my_set);
        my_tree.draw();
    }
}
