import edu.princeton.cs.algs4.*;

class CouponCollectorStats  {
    private CouponCollectorStats(int N, int T){
        double[] numbers = new double[T];
        for (int i = 0; i < T; i++){
            double count = couponCollectorTest(N);
            numbers[i] = count;
        }
        double mean = mean(numbers);
        StdOut.println("N: " + N + ", T: " + T);
        StdOut.println("Mean: " + String.format("%.2f", mean));
        StdOut.println("Standard deviation: " + String.format("%.2f", stddev(numbers)));
    }
    private static double mean(double[] numbers){
        return StdStats.mean(numbers);
    }

    private double stddev(double[] numberArray){
        return StdStats.stddev(numberArray);
    }

    private static int couponCollectorTest(int n){
        boolean[] isCollected = new boolean[n];
        int count = 0;
        int distinct = 0;
        while (distinct < n) {
            int value = (int) (Math.random() * n);
            count++;
            if (!isCollected[value]) {
                distinct++;
                isCollected[value] = true;
            }
        }
        return count;
    }

    public static void main(String[] args){
        int t = StdIn.readInt();
        int n = StdIn.readInt();
        new CouponCollectorStats(n, t);
        int[] tests = {10, 100, 1000, 10000};
        for(int i : tests ){
            new CouponCollectorStats(1000, i);
        }
    }
}
