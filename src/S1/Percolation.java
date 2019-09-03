package S1;
import edu.princeton.cs.algs4.QuickUnionUF;

public class Percolation {

    private Boolean[][] grid;
    private int top = 0;
    private int bot = 0;
    private QuickUnionUF UF;

    public Percolation(int N){  // create N-by-N grid, with all sites initially blocked
        UF = new QuickUnionUF(N*N+2);
        top = N*N + 2;
        bot = N*N + 2;
        grid = new Boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                grid[i][j] = false;
            }
        }
    }
    public void open(int row, int col){ // open the site (row, col) if it is not open already
        grid[row][col] = true;
    }
    public boolean isOpen(int row, int col){ // is the site (row, col) open?
        return grid[row][col];
    }
    public boolean isFull(int row, int col){ // is the site (row, col) full?
        return UF.connected()

    }
    public int numberOfOpenSites(){ // number of open sites
        int count = 0;
        for(int row = 0; row < grid.length - 1; row++){
            for(int col = 0; col < grid.length - 1; col++){
                if (grid[row][col]){
                    count++;
                }
            }
        }
        return count;
    }
    public boolean percolates(){  // does the system percolate?
        return false;
    }
    public static void main(String[] args){ // unit testing (required)

    }
}
