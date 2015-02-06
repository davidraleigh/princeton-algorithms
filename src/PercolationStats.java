/**
 * Created by davidraleigh on 2/3/15.
 */
public class PercolationStats {
  private double mMean;
  private double mStd;
  private double mConfidenceLo;
  private double mConfidenceHigh;

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
    int meanSum = 0;
    for (int i = 0; i < T; i++) {
      Percolation percolation = new Percolation(N);

      int count = 0;
      while (!percolation.percolates()) {
        int row = StdRandom.uniform(1, N + 1);
        int col = StdRandom.uniform(1, N + 1);
        percolation.open(row, col);
        count++;
      }
      meanSum += count;
      attemptCount[i] = count;
    }

    // calculate mean
    mMean = (double)meanSum / T;

    // calculate std
    double stdSum = 0;
    for (int i = 0; i < T; i++) {
      stdSum += (attemptCount[i] - mMean) * (attemptCount[i] - mMean);
    }
    mStd = Math.sqrt(stdSum / (T - 1));

    mConfidenceLo = mMean - (1.96 * mStd) / Math.sqrt(T);
    mConfidenceHigh = mMean + (1.96 * mStd) / Math.sqrt(T);
  }

  /**
   * sample mean of percolation threshold
   * @return
   */
  public double mean()
  {
    return mMean;
  }

  /**
   * sample standard deviation of percolation threshold
   * @return
   */
  public double stddev()
  {
    return mStd;
  }

  /**
   * low  endpoint of 95% confidence interval
   * @return
   */
  public double confidenceLo()
  {
    return mConfidenceLo;
  }

  /**
   * high endpoint of 95% confidence interval
   * @return
   */
  public double confidenceHi()
  {
    return mConfidenceHigh;
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
    sb.append(percolationStats.mean());
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
