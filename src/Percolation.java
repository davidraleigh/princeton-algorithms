/**
 * Created by davidraleigh on 2/3/15.
 *
 Performance requirements.  The constructor should take time proportional to N2; all methods should take constant time plus a constant number of calls to the union-find methods union(), find(), connected(), and count().
 */
public class Percolation {
  private WeightedQuickUnionUF _weightedUnionUF;
  private int _N;
  private boolean _positionStatus[];

  /**
   * create N-by-N grid, with all sites blocked
   * constructor should throw a java.lang.IllegalArgumentException if N ≤ 0.
   * @param N
   */
  public Percolation(int N)
  {
    _N = N;
    _weightedUnionUF = new WeightedQuickUnionUF(N * N);
    // all values initialize to false
    _positionStatus = new boolean[N * N];
  }

  private int _arrayPosition(int i, int j)
  {
    return (i - 1) * _N + (j - 1);
  }

  private void _union(int row1, int col1, int row2, int col2)
  {
    _weightedUnionUF.union(_arrayPosition(row1, col1), _arrayPosition(row2, col2));
  }

  /**
   * open site (row i, column j) if it is not open already
   * Throw a java.lang.IndexOutOfBoundsException if any argument to open() is outside its prescribed range.
   * @param i row
   * @param j column
   */
  public void open(int i, int j)
  {
    if (isOpen(i, j))
      return;

    if (i > _N || j > _N || i < 1 || j < 1)
      throw new IndexOutOfBoundsException();

    // just to help us keep track
    int iAbove = i - 1;
    int iBelow = i + 1;
    int jLeft = j - 1;
    int jRight = j + 1;

    // test above
    if (i > 1 && isOpen(iAbove, j))
      _union(i, j, iAbove, j);

    // test below
    if (i < _N && isOpen(iBelow, j))
      _union(i, j, iBelow, j);

    // test left
    if (j > 1 && isOpen(i, jLeft))
      _union(i, j, i, jLeft);

    // test right
    if (j < _N && isOpen(i, jRight))
      _union(i, j, i, jRight);

    _positionStatus[_arrayPosition(i, j)] = true;
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
    if (i > _N || j > _N || i < 1 || j < 1)
      throw new IndexOutOfBoundsException();

    return _positionStatus[_arrayPosition(i, j)];
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
    if (i > _N || j > _N || i < 1 || j < 1)
      throw new IndexOutOfBoundsException();

    // NOT IMPLEMENTED
    return false;
  }

  /**
   * does the system percolate?
   * @return
   */
  public boolean percolates()
  {
    //int topRow = 0;
    int bottomRow = _N - 1;
    for (int topCol = 0; topCol < _N; topCol++) {
      if (!_positionStatus[topCol])
        continue;
      for (int bottomCol = 0; bottomCol < _N; bottomCol++) {
        if (!_positionStatus[bottomRow * _N + bottomCol])
          continue;
        if (_weightedUnionUF.connected(/*(topRow * _N +)*/topCol, bottomRow * _N + bottomCol)) {
          return true;
        }
      }
    }

    return false;
  }
}
