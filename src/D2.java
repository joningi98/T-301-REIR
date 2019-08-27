import edu.princeton.cs.algs4.*;

import java.util.Arrays;
import java.util.stream.IntStream;

class CouponCollectorStats  {
    private CouponCollectorStats(int N, int T){
        int count = 0;
        for (int i = 0; i < T; i++){
            count += couponCollectorTest(N);
        }
        double mean = mean(count, T);
        StdOut.println("Mean: " + mean);
        StdOut.println("Standard deviation: " + stddev(count, mean, N));
    }
    private static double mean(int max, int t){
        return (float)(max / t);
    }

    private double stddev(int max, double mean, int N){
        return Math.sqrt((Math.pow(max-mean, 2)/ N));
    }
    private static boolean contains(int key, int[] list){
        for (int i = 0; i < list.length - 1; i++){
            if (list[i] == key){
                return true;
            }
        }
        return false;
    }

    private static int[] cards(int n){
        int[] cards = new int[n];
        for (int i = 0; i < n; i ++){
            cards[i] = i;
        }
        return cards;
    }

    private static int couponCollectorTest(int n){
        int[] cards = cards(n);
        int countCards = 0;
        int [] foundCards = new int[cards.length];
        int count = 0;
        while (true){
            count++;
            int randomCard = StdRandom.uniform(n);
            if (!contains(randomCard, foundCards)){
                foundCards[randomCard] = randomCard;
                countCards++;
            }
            else if (countCards == cards.length - 1){
                break;
            }
        }
        return count;
    }

    public static void main(String[] args){
        int t = StdIn.readInt();
        int n = StdIn.readInt();
        new CouponCollectorStats(n, t);

    }
}
