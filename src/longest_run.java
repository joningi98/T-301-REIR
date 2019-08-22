import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class longest_run {

    public static void main(String[] args){
        int number_of_runs = StdIn.readInt();
        int[] runs = new int[number_of_runs];

        for (int i = 0; i < number_of_runs; i++){
            runs[i] = StdIn.readInt();
        }

        int last_run = runs[0];
        int next_run = 0;
        int longest_run = 0;

        for (int i = 0; i < runs.length - 1; i++){
            if (runs[i] < last_run){
                int temp = 0;
                for (int j = next_run; j < i; j ++){
                    temp += 1;
                }
                if (temp > longest_run){
                    longest_run = temp;
                }
                next_run = runs[i];
            }
            else {
                last_run = runs[i];
            }
        }
        int temp_last = runs.length - last_run - 1;

        if (temp_last > longest_run){
            longest_run = temp_last;
        }

        StdOut.println(longest_run);
    }
}
