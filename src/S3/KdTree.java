package S3;

import edu.princeton.cs.algs4.*;

import java.awt.*;
import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {

    private int size_of_tree;
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

    public KdTree() {
        size_of_tree = 0;
        root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    // number of points in the set
    public int size() {
        return this.size_of_tree;
    }

    // add the point p to the set (if it is not already in the set )
    public void insert(Point2D p) {
        if (isEmpty()){ // If the tree is empty the we make the root
            RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
            this.size_of_tree ++;
            this.root = new Node(p,null, null, true, rect);
        }
        if (!contains(p)){ // is the tree does not contain the point then we insert it
            this.root = insert_helper(this.root, p, true);
        }
    }

    private Node insert_helper(Node node, Point2D new_node, boolean vertical){
        // Here we go through the tree recursively
        if (vertical) { // If the node is vertical the we look at the X coords
            if (new_node.x() < node.p.x()) {
                if (node.left == null){
                    node.left = create_node(new_node, node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
                }
                else { // We go deeper in the deeper in the
                    insert_helper(node.left, new_node, false);
                }
            }
            if (new_node.x() >= node.p.x()) {
                if (node.right == null){
                    node.right = create_node(new_node, node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
                }
                else{ // We go deeper in the deeper in the
                    insert_helper(node.right, new_node, false);
                }
            }
        }
        else{ // If the node is horizontal the we look at the Y coords
            if (new_node.y() < node.p.y()) {
                if (node.left == null){
                    node.left = create_node(new_node, node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());
                }
                else{ // We go deeper in the deeper in the
                    insert_helper(node.left, new_node, true);
                }
            }
            if (new_node.y() >= node.p.y()) {
                if (node.right == null){
                    node.right = create_node(new_node, node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax());
                }
                else{ // We go deeper in the deeper in the
                    insert_helper(node.right, new_node, true);
                }
            }
        }
        return node;
    }

    private Node create_node(Point2D new_point, double xmin, double ymin, double xmax, double ymax){
        // This function is just for making a node with a rectangle
        RectHV rect = new RectHV(xmin, ymin, xmax, ymax);
        this.size_of_tree ++;
        return new Node(new_point,null, null, true, rect);
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        // Almost the same as insert but we return true is the point is in the tree, else false
        return contains_helper(this.root, p, true);
    }

    private static boolean contains_helper(Node node, Point2D target, boolean vertical){
        if (node == null){
            return false;
        }
        else if (node.p.equals(target)){
            return true;
        }
        else if (vertical){
            if (target.x() < node.p.x()){
                return contains_helper(node.left, target, false);
            }
            else if (target.x() >= node.p.x()){
                return contains_helper(node.right, target, false);
            }
        }
        else {
            if (target.y() < node.p.y()){
                return contains_helper(node.left, target, true);
            }
            else if (target.y() >= node.p.y()){
                return contains_helper(node.right, target, true);
            }
        }
        return false;
    }

    // draw all of the points to standard draw
    public void draw() {
        StdDraw.setPenRadius(0.01);
        draw_helper(this.root,  true);
    }

    private void draw_helper(Node node, boolean vertical) {
        if (node != null) {
            if (vertical) { // Vertical line
                StdDraw.setPenColor(Color.RED);
                StdDraw.line(node.p.x(), node.rect.ymax(), node.p.x(), node.rect.ymin());
            }
            if (!vertical) { // Horizontal line
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
            }
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
        return range_helper(this.root, rect, my_stack);
    }

    private static Stack<Point2D> range_helper(Node node, RectHV rect, Stack<Point2D> my_stack){
        // I use the rect much in this function to see if the nodes are in the rect we are looking for
        if (node == null){
            return my_stack; // return here if the node is null
        }
        if (!rect.intersects(node.rect)){ // there is no point in going further if the rect doest intersect with the node rect
            return my_stack;
        }
        if (rect.contains(node.p)){ // if the node is in the rect we add it to our stack
            my_stack.push(node.p);
        }
        range_helper(node.left, rect, my_stack); // Go down the both subtrees
        range_helper(node.right, rect, my_stack);
        return my_stack;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        return nearest_helper(this.root, p, this.root.p);
    }

    private Point2D nearest_helper(Node node, Point2D target, Point2D nearest_neighbor){
        if (node != null){
            // check if the nearest_neighbour is further away than the new node
            // if so the we change the value of the nearest_neighbour
            if (nearest_neighbor.distanceSquaredTo(target) > node.p.distanceSquaredTo(target)) {
                nearest_neighbor = node.p;
            }
            // We need to check if the node is vertical or horizontal to see which order we
            // go down the tree
            if (node.vertical){
                nearest_neighbor = nearest_helper(node.left, target, nearest_neighbor);
                nearest_neighbor = nearest_helper(node.right, target, nearest_neighbor);
            }
            else{
                nearest_neighbor = nearest_helper(node.right, target, nearest_neighbor);
                nearest_neighbor = nearest_helper(node.left, target, nearest_neighbor);
            }
        }
        return nearest_neighbor;
    }
}
