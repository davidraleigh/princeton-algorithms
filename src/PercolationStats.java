/**
 * Created by davidraleigh on 2/3/15.
 */
public class PercolationStats {
  private double _mean;
  private double _std;
  private double _confidenceLo;
  private double _confidenceHigh;

  /**
   * perform T independent experiments on an N-by-N grid
   * constructor should throw a java.lang.IllegalArgumentException if either N ≤ 0 or T ≤ 0
   * @param N
   * @param T
   */
  public PercolationStats(int N, int T)
  {
    if (N <= 0 || T <= 0)
      throw new IllegalArgumentException();

    int[] attemptCount = new int[T];
    int attemptIndex = 0;
    int meanSum = 0;
    while (T > 0) {
      Percolation percolation = new Percolation(N);

      int count = 0;
      while (!percolation.percolates()) {
        int i = StdRandom.uniform(1, N + 1);
        int j = StdRandom.uniform(1, N + 1);
        percolation.open(i, j);
        count++;
      }
      meanSum += count;
      attemptCount[attemptIndex] = count;
      T--;
    }

    // calculate mean
    _mean = ((double)meanSum) / T;

    // calculate std
    double stdSum = 0;
    for (int i = 0; i < T; i++) {
      stdSum += Math.pow((attemptCount[i] - _mean), 2);
    }
    _std = Math.sqrt(stdSum / T - 1);

    _confidenceLo = _mean - (1.96 * _std)/Math.sqrt(T);
    _confidenceHigh = _mean + (1.96 * _std)/Math.sqrt(T);
  }

  /**
   * sample mean of percolation threshold
   * @return
   */
  public double mean()
  {
    return _mean;
  }

  /**
   * sample standard deviation of percolation threshold
   * @return
   */
  public double stddev()
  {
    return _std;
  }

  /**
   * low  endpoint of 95% confidence interval
   * @return
   */
  public double confidenceLo()
  {
    return _confidenceLo;
  }

  /**
   * high endpoint of 95% confidence interval
   * @return
   */
  public double confidenceHi()
  {
    return _confidenceHigh;
  }

  public static void main(String[] args)
  {
    //PercolationStats 200 100
    int N = Integer.parseInt(args[1]);
    int T = Integer.parseInt(args[2]);

    PercolationStats percolationStats = new PercolationStats(N, T);
//    mean                    = 0.5929934999999997
//    stddev                  = 0.00876990421552567
//    95% confidence interval = 0.5912745987737567, 0.5947124012262428
    StringBuilder sb = new StringBuilder();
    sb.append("mean                    = ");
    sb.append (percolationStats.mean());
    StdOut.println(sb.toString());
    sb.setLength(0);
    sb.append("stddev                  = ");
    sb.append(percolationStats.stddev());
    StdOut.println(sb.toString());
    sb.setLength(0);
    sb.append("95% confidence interval = ");
    sb.append(percolationStats.confidenceLo());
    sb.append(", ");
    sb.append(percolationStats.confidenceHi());
    StdOut.println(sb.toString());
  }
}
