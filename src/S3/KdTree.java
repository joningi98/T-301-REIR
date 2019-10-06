package S3;

import edu.princeton.cs.algs4.*;

public class KdTree<P> {

    private int size_of_tree = 0;
    private Node root;

    private static class Node{
        private Point2D p;
        private Node left, right;
        private boolean vertical;

        public Node(Point2D p, Node left, Node right, boolean vertical){
            this.p = p;
            this.left = left;
            this.right = right;
            this.vertical = vertical;
        }
    }

   // public KdTree my_set;

    // construct an empty set of points
    public KdTree() {
       // my_set = new KdTree();
    }

    // is the set empty ?
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
        root = insert_helper(root, p, true);
    }

    private Node insert_helper(Node root, Point2D new_node, boolean vertical){
        if (root == null){
            return new Node(new_node, null, null, !vertical);
        }
        if (vertical){
            if (new_node.x() < root.p.x()){
                root.left =  insert_helper(root.left, new_node, false);
            }
            if (new_node.x() > root.p.x()){
                root.right = insert_helper(root.right, new_node, false);
            }
        }

        if (!vertical){
            if (new_node.y() < root.p.y()){
                root.left = insert_helper(root.left, new_node, true);
            }
            if (new_node.y() > root.p.y()){
                root.right = insert_helper(root.right, new_node, true);
            }
        }

        return root;
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return true;
    }

    // draw all of the points to standard draw
    public void draw() {

    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        return range(rect);
    }

    public static Point2D randomPoint(){
        double x = StdRandom.uniform();
        double y = StdRandom.uniform();
        return new Point2D(x,y);
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        return p;
    }

    public static void main(String[] args){
        KdTree my_tree = new KdTree<>();

        for(int i = 0; i < 4; i++){
            Point2D my_point = new Point2D(StdIn.readDouble(), StdIn.readDouble());
            my_tree.insert(my_point);
        }
    }
}
