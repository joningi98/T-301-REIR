package S1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    public PercolationStats(int N, int T) { // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) throw new java.lang.IllegalArgumentException("Invalid input");
        double[] tests = new double[T];
        for(int i = 0; i < T; i++){
            Percolation percolation = new Percolation(N);
            while (true) {
                if (percolation.percolates()){
                    break;
                }
                else{
                    int row = StdRandom.uniform(N);
                    int col = StdRandom.uniform(N);
                    if (row >= 0 && row < N && col >= 0 && col < N){
                        percolation.open(row, col);
                    }
                }
            }
            int number_of_open = percolation.numberOfOpenSites();
            tests[i] = (double) (number_of_open) / (double) (N*N);
        }
        StdOut.printf("mean() = %f\n" , mean(tests));
        StdOut.printf("stddev() = %f\n", stddev(tests));
        StdOut.printf("confidenceLow() = %f\n", confidenceLow(tests, T));
        StdOut.printf("confidenceHigh() = %f\n", confidenceHigh(tests, T));
    }

    public double mean(double[] tests) { // sample mean of percolation threshold
        return StdStats.mean(tests);
    }
    public double stddev(double[] test) { // sample standard deviation of percolation threshold
        return StdStats.stddev(test);
    }
    public double confidenceLow(double[] tests, int T) { // low endpoint of 95% confidence interval
        double mean = StdStats.mean(tests);
        double stddev = StdStats.stddev(tests);
        return mean - (1.96*stddev/Math.sqrt(T));
    }

    public double confidenceHigh(double[] tests, int T) { // high endpoint of 95% confidence interval
        double mean = StdStats.mean(tests);
        double stddev = StdStats.stddev(tests);
        return mean + (1.96*stddev/Math.sqrt(T));
    }

    public static void main(String[] args){
        new PercolationStats(200, 100);


    }
}
