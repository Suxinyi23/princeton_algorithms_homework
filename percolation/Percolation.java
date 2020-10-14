import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf_top;
    private boolean[][] grid;
    private int n;
    private int open_count;


    public Percolation(int n)
    {
        if(n<=0)throw new IllegalArgumentException();
        this.uf=new WeightedQuickUnionUF(n*n+2);
        this.uf_top= new WeightedQuickUnionUF(n*n+1);
        this.grid=new boolean[n][n];
        this.n=n;
        this.open_count=0;

    }

    private int getIndex(int row, int col)
    {
        return row*n+col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if(row<1||row>n||col<1||col>n)throw new IllegalArgumentException();

        row--;
        col--;

        if(!grid[row][col])
        {
            grid[row][col]=true;
            int ind=getIndex(row,col);
            if(row-1>=0 && grid[row-1][col]){
                uf.union(ind,getIndex(row-1,col));
                uf_top.union(ind,getIndex(row-1,col));
            }
            if(row+1<n && grid[row+1][col]){
                uf.union(ind,getIndex(row+1,col));
                uf_top.union(ind,getIndex(row+1,col));
            }
            if(col-1>=0 && grid[row][col-1]){
                uf.union(ind, getIndex(row,col-1));
                uf_top.union(ind, getIndex(row,col-1));
            }
            if(col+1<n && grid[row][col+1]){
                uf.union(ind, getIndex(row,col+1));
                uf_top.union(ind, getIndex(row,col+1));
            }
            if(row==0){
                uf.union(ind,n*n);
                uf_top.union(ind,n*n);
            }
            if(row==n-1)uf.union(ind,n*n+1);
            open_count++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        if(row<1||row>n||col<1||col>n)throw new IllegalArgumentException();
        row--;
        col--;
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if(row<1||row>n||col<1||col>n)throw new IllegalArgumentException();
        row--;
        col--;
        return uf_top.find(getIndex(row,col))==uf_top.find(n*n);
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return this.open_count;
    }

    // does the system percolate?
    public boolean percolates()
    {
        return uf.find(n*n)==uf.find(n*n+1);
    }

    // test client (optional)
    public static void main(String[] args)
    {

    }
}
