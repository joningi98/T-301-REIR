public class Percolation {

    private Boolean[][] grid;

    public Percolation(int N){  // create N-by-N grid, with all sites initially blocked
        grid = new Boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                grid[i][j] = false;
            }
        }

    }
    public void open(int row, int col){ // open the site (row, col) if it is not open already
        grid[row][col] = true;

        if (isOpen(row, col + 1)){ // left
            
        }

        if (isOpen(row, col - 1)){ // Right

        }
        if (isOpen(row + 1, col)){ // Up

        }

        if (isOpen(row - 1, col + 1)){ // Down

        }


    }
    public boolean isOpen(int row, int col){ // is the site (row, col) open?
        return grid[row][col];
    }
    public boolean isFull(int row, int col){ // is the site (row, col) full?

    }
    public int numberOfOpenSites(){ // number of open sites

    }
    public boolean percolates(){  // does the system percolate?

    }
    public static void main(String[] args){ // unit testing (required)
    }
}
