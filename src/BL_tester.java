import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class BL_tester {

    public static void main(String[] args){
        int n = 1000;
        double[] a = new double[n];
        for (int i =  0; i < n; i++){
            a[i] = StdRandom.uniform();
        }

        double[] f = new double[n];
        for (int i = 0; i < n; i++){
            f[i] = StdRandom.uniform();
        }

        Arrays.sort(a);
        Stopwatch klukka = new Stopwatch();

        int count = 0;
        for (int i = 0; i < n; i++){
            if (BinarySearch.indexOf(a, f[i]) == -1)  count++;
        }
        StdOut.println("Binary Search time: " + klukka.elapsedTime());
    }

    /*
    public static int rank(int key, int[] a){
        return rank(key, a, 0, a.length - 1);
    }

    private static int rank(int key, int[] a, int lo, int hi){
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid])       return rank(key, a, lo, mid - 1);
        else if (key > a[mid])  return rank(key, a, mid + 1, hi);
        else                    return mid;
    }


    public static void main(String[] args){
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);

        while (!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if (rank(key, whitelist) == -1)
                StdOut.println(key);
        }
    }
     **/
}


