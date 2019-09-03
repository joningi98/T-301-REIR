package S1;
import edu.princeton.cs.algs4.QuickUnionUF;

public class Percolation {

    private Boolean[][] grid;
    private int top = 0;
    private int bot = 0;
    private QuickUnionUF UF;
    private int gridSize = 0;

    public Percolation(int N){  // create N-by-N grid, with all sites initially blocked
        UF = new QuickUnionUF(N*N+3);
        top = N*N + 2;
        bot = N*N + 2;
        gridSize = N;
        grid = new Boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                grid[i][j] = false;
            }
        }
    }
    public void open(int row, int col){ // open the site (row, col) if it is not open already
        grid[col][row] = true;
        if(row + 1 < gridSize && isOpen(row + 1, col)){ // Right
           UF.union(getGridPoint(row + 1, col), getGridPoint(row, col));
        }
        if(row - 1 >= 0 && isOpen(row - 1, col)){ // Left
            UF.union(getGridPoint(row - 1, col), getGridPoint(row, col));
        }
        if(col - 1 >= 0 && isOpen(row, col - 1)){ // Up
            UF.union(getGridPoint(row, col - 1), getGridPoint(row, col));
        }
        if(col + 1 < gridSize && isOpen(row, col + 1)){ // Down
            UF.union(getGridPoint(row, col + 1), getGridPoint(row, col));
        }
    }
    public boolean isOpen(int row, int col){ // is the site (row, col) open?
        return grid[col][row];
    }
    public boolean isFull(int row, int col){ // is the site (row, col) full?
        if (isOpen(col, row)){
            for(int i = 0; i < gridSize; i++){
                if(UF.connected(getGridPoint(row, col), i)){
                    return true;
                }
            }
        }
        return false;
    }
    public int numberOfOpenSites(){ // number of open sites
        int count = 0;
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid.length; col++){
                if (grid[col][row]){
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

    private int getGridPoint(int row, int col){
        return (col*10) + row;
    }
}
