package S1;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private boolean[][] grid;
    private int top;
    private int bot;
    private int count;
    private QuickUnionUF UF;
    private int gridSize;
    private boolean perculate = false;
    private int[] info;

    public Percolation(int N){  // create N-by-N grid, with all sites initially blocked
        if (N <= 0){
            throw new java.lang.IllegalArgumentException();
        }
        UF = new QuickUnionUF(N*N+2);
        count = 0;
        top = N*N;
        bot = N*N + 1;
        gridSize = N;
        grid = new boolean[N][N];
        info = new int[N*N+2];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                info[j*N+i] = 0;
            }
        }
        info[top] = 1;
        for(int i = 0; i < N; i++) {
            UF.union(getGridPoint(0,i), top);
            info[getGridPoint(0,i)] = 1;
            info[getGridPoint(N-1, i)] = 2;
        }
    }

    public void open(int row, int col){ // open the site (row, col) if it is not open already
        if (!grid[row][col]){
            count++;
        }
        grid[row][col] = true;

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        for(int i = 0; i < 4; i++) {
            if(0 <= col+dx[i] && col+dx[i] < gridSize && 0 <= row+dy[i] && row+dy[i] < gridSize && isOpen(row+dy[i], col+dx[i])) {
                int new_col = col+dx[i];
                int new_row = row+dy[i];
                int val_1 = info[UF.find(getGridPoint(new_row, new_col))];
                int val_2 = info[UF.find(getGridPoint(row, col))];
                UF.union(getGridPoint(row,col), getGridPoint(new_row, new_col));
                info[UF.find(getGridPoint(row,col))] = val_1|val_2;
                int val_3 = val_1 | val_2;
                if(val_3 == 3) {
                    perculate = true;
                }

            }
        }
    }
    public boolean isOpen(int row, int col){ // is the site (row, col) open?
        return grid[row][col];
    }
    public boolean isFull(int row, int col){ // is the site (row, col) full?
        if (isOpen(row, col)){
            return UF.connected(getGridPoint(row, col), top);
        }
        return false;
    }
    public int numberOfOpenSites(){ // number of open sites
        return count;
    }
    public boolean percolates(){  // does the system percolate?
        return perculate;
    }
    public static void main(String[] args){ // unit testing (required)
        int N = 2;
        Percolation myGrid = new Percolation(N);
        StdOut.println(myGrid.UF.connected(myGrid.getGridPoint(1,1), myGrid.bot));  // Check if bottom row is connected to bot number
        StdOut.println("Percolates?: " + myGrid.percolates());  // See if the grid percolates
        StdOut.println("Closed: " + myGrid.UF.connected(myGrid.top, myGrid.bot));   // Check if bot number and top number are connected (Channel not open)
        myGrid.open(0,1);   // Opening channels
        StdOut.println("Grid[0][1] open: " + myGrid.isOpen(0, 1)); // See if the channel can be opened
        myGrid.open(1,1);   // Opening channels
        StdOut.println("Open: " + myGrid.UF.connected(myGrid.top, myGrid.bot)); // Check if bot number and top number are connected (Channel open)
        StdOut.println("Percolates?: " + myGrid.percolates());  // See if the grid percolates

    }

    public int getGridPoint(int row, int col){
        return (row*gridSize) + col;
    }
}
