import org.junit.*;

public class PercolationStatsTest {

  @Before
  public void setUp() throws Exception {

  }

  @After
  public void tearDown() throws Exception {

  }

  @org.junit.Test
  public void test() throws Exception {
    PercolationStats percolationStats = new PercolationStats(20, 10);
    org.junit.Assert.assertTrue(!Double.isInfinite(percolationStats.mean()));
  }
}