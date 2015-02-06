
/**
 * Created by davidraleigh on 2/3/15.
 *
 Performance requirements.  The constructor should take time proportional to N2; all methods should take constant time plus a constant number of calls to the union-find methods union(), find(), connected(), and count().
 */
public class Percolation {

  /**
   * create N-by-N grid, with all sites blocked
   * constructor should throw a java.lang.IllegalArgumentException if N ≤ 0.
   * @param N
   */
  public Percolation(int N)
  {

  }

  /**
   * open site (row i, column j) if it is not open already
   * Throw a java.lang.IndexOutOfBoundsException if any argument to open() is outside its prescribed range.
   * @param i
   * @param j
   */
  public void open(int i, int j)
  {

  }

  /**
   * is site (row i, column j) open?
   * Throw a java.lang.IndexOutOfBoundsException if any argument to isOpen() is outside its prescribed range.
   * @param i
   * @param j
   * @return
   */
  public boolean isOpen(int i, int j)
  {
    // NOT IMPLEMENTED
    return false;
  }

  /**
   * is site (row i, column j) full?
   * Throw a java.lang.IndexOutOfBoundsException if any argument to isFull() is outside its prescribed range.
   * @param i
   * @param j
   * @return
   */
  public boolean isFull(int i, int j)
  {
    // NOT IMPLEMENTED
    return false;
  }

  /**
   * does the system percolate?
   * @return
   */
  public boolean percolates()
  {
    // NOT IMPLEMENTED
    return false;
  }
}
