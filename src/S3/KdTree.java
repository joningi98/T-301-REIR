package S3;

import edu.princeton.cs.algs4.*;

import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class KdTree {

    private int size_of_tree = 0;
    private Node root;

    private static class Node{
        private Point2D p;
        private Node left, right;
        private boolean vertical;
        private RectHV rect;

        public Node(Point2D p, Node left, Node right, boolean vertical, RectHV rect){
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
            root = new Node(p,null, null, false, rect);
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
                    node.left = new Node(new_node, null, null, false, rect);
                }
                node.left =  insert_helper(node.left, new_node, false);
            }
            if (new_node.x() > node.p.x()){
                if (node.right == null){
                    RectHV rect = get_rect(node, new_node);
                    node.right = new Node(new_node, null, null, false, rect);
                }
                node.right = insert_helper(node.right, new_node, false);
            }
        }
        if (!vertical){
            if (new_node.y() < node.p.y()){
                if (node.left == null){
                    RectHV rect = get_rect(node, new_node);
                    node.left = new Node(new_node, null, null, true, rect);
                }
                node.left = insert_helper(node.left, new_node, true);
            }
            if (new_node.y() > node.p.y()){
                if (node.right == null){
                    RectHV rect = get_rect(node, new_node);
                    node.right = new Node(new_node, null, null, true, rect);
                }
                node.right = insert_helper(node.right, new_node, true);
            }
        }
        return node;
    }

    private RectHV get_rect(Node node, Point2D next_node){
        double xmin = 0.0;
        double ymin = 0.0;
        double xmax = 1.0;
        double ymax = 1.0;
        if (node.vertical){
            if (node.p.x() < next_node.x()){
                xmax = next_node.x();
            }
            else{
                xmin = next_node.x();
            }
        }
        if (!node.vertical){
            if (node.p.y() < next_node.y()){
                ymax = next_node.y();
            }
            else{
                ymin = next_node.y();
            }
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
    /*
    // draw all of the points to standard draw
    public void draw() {
        StdDraw.setPenRadius(0.02);
        draw_helper(node, null, true);
    }

    private void draw_helper(Node node, Point2D last_point, boolean vertical){
        if(node != null){
            draw_helper(node, node.p, !vertical);
            StdDraw.point(node.p.x(), node.p.y());
            if (vertical){ // Vertical line
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(Color.RED);
                if (last_point == null){
                    StdDraw.line(node.p.x(), 0.0, node.p.x(), 1.0);
                }
                else{
                    StdDraw.line(node.p.x(), 0.0, node.p.x(), last_point.y());
                }
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.setPenRadius(0.02);
            }
            if (!vertical){ // Horizontal line
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(Color.BLUE);
                if (last_point == null){
                    StdDraw.line(0.0, node.p.y(), 1.0, node.p.y());
                }
                else{
                    StdDraw.line(, node.p.y(), last_point.x(), node.p.y());
                }
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.setPenRadius(0.02);
            }
            draw_helper(node.right, node.p, !vertical);
        }
    }
     */

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


    public static Point2D randomPoint(){
        double x = StdRandom.uniform(1);
        double y = StdRandom.uniform(1);
        return new Point2D(x,y);
    }

    // a nearest neighbor in the set to p; null if set is empty
    // TODO: Nearest
    public Point2D nearest(Point2D p) {
       return p;
    }
    public static void main(String[] args){
        KdTree my_tree = new KdTree();

        Point2D[] my_points = {new Point2D(0.7, 0.2),
                               new Point2D(0.5, 0.4),
                               new Point2D(0.2, 0.3),
                               new Point2D(0.4, 0.7)};

        for(Point2D p: my_points){
            my_tree.insert(p);
        }
        StdOut.println(my_tree.contains(new Point2D(0.7, 0.2)));
        Iterable<Point2D> my_set = my_tree.range(new RectHV(0.0, 0.0, 1.0, 1.0));
        StdOut.println(my_set);
    }
}
