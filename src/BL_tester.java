import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class BL_tester{

    private BL_tester() {}

    private static int linear_search(int[] a, int key){
        for (int i = 0; i < a.length - 1; i++){
            if (a[i] == key) return a[i];
        }
        return -1;
    }

    private static int indexOf(int[] a, int key){
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

    private static int[] my_array(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = StdRandom.uniform(n);
        }
        return arr;
    }

    private static Stopwatch time(){
        return new Stopwatch();
    }

    public static void main(String[] args){

        int n = StdIn.readInt();
        int[] my_arr = my_array(n);
        int[] search_numbers = my_array(n);

        Arrays.sort(my_arr);

        Stopwatch stopwatch = time();

        for (int i = 0; i < search_numbers.length - 1; i++){
            BL_tester.indexOf(my_arr, search_numbers[i]);
        }
        double time_1 = stopwatch.elapsedTime();


        Stopwatch stopwatch1 = time();

        for (int i = 0; i < search_numbers.length - 1; i++){
            linear_search(my_arr, search_numbers[i]);
        }

        double time_2 = stopwatch1.elapsedTime();

        StdOut.println("Numbers of searches: " + n);
        StdOut.println("Binary search time: " + time_1);
        StdOut.println("Linear search: " + time_2);

    }

}