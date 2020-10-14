import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class PercolationStats {

    // perform independent trials on an n-by-n grid
    private double[] arr;
    private int trials;
    public PercolationStats(int n, int trials)
    {
        this.trials=trials;
        if(n<=0||trials<=0)throw new IllegalArgumentException();
        arr=new double[trials];
        for(int i=0;i<trials;i++)
        {
            Percolation p=new Percolation(n);
            while(!p.percolates())
            {
                int row=(int)Math.floor(n*StdRandom.uniform())+1;
                int col=(int)Math.floor(n*StdRandom.uniform())+1;


                if(!p.isOpen(row,col))
                {
                    p.open(row,col);
                }
            }
            arr[i]=(double)p.numberOfOpenSites()/(n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(arr);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(arr);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {

        return mean() - 1.96 * stddev()/Math.sqrt(trials);

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + 1.96 * stddev()/Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args)
    {
        PercolationStats p=new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        System.out.println("mean                    = "+p.mean());
        System.out.println("stddev                  = "+p.stddev());
        System.out.println("95% confidence interval = ["+p.confidenceLo()+", "+p.confidenceHi()+"]");

    }


}