import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class BL_tester{

    private BL_tester() {}

    public static int indexOf(int[] a, int key){
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static int rank(int key, int[] a){
        return indexOf(a, key);
    }

    private static int[] my_array(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = StdRandom.uniform(n);
        }
        return arr;
    }

    private static int[] search_nums(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = StdRandom.uniform(n);
        }
        return arr;
    }

    public static void main(String[] args){

        int N = StdIn.readInt();
        int[] my_arr = my_array(N);
        int[] search_numbers = search_nums(N);

        Arrays.sort(my_arr);

        for(int i = 0; i < search_numbers.length - 1; i++){

        }
    }

}