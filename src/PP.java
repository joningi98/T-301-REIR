import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PP {
    public static void main(String[] args){
        int n = StdIn.readInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = StdIn.readInt();
        }

        int min = a[0], max = a[0];
        for (int i = 1; i < n; i++){
            if (a[i] < min) min = a[i];
            if (a[i] > max) max = a[i];
        }
        StdOut.println(max - min);
    }
}
