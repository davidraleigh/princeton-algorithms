import org.junit.*;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PercolationTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Ignore
  public static Percolation readFile(File file) throws java.io.IOException {
    Percolation percolation = null;
    String fileName = file.toString();
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
      // first line is the number of rows/columns of the square matrix
      String line = br.readLine();
      if (line == null) {
        return percolation;
      }
      line = line.trim();
      percolation = new Percolation(Integer.parseInt(line));

      // prep the next line
      line = br.readLine();
      while (line != null) {
        line = line.trim();
        if (line.length() == 0)
          break;

        String[] pair = line.split("\\s+");
        // open at position
        percolation.open(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]));
        line = br.readLine();
      }
      return percolation;
    } finally {
      br.close();
    }
  }

  @Test
  public void testPercolates() throws Exception {
    ArrayList<Percolation> percolators = new ArrayList<Percolation>();

    System.out.println("Working Directory = " + System.getProperty("user.dir"));
    File dataDirectory = new File(System.getProperty("user.dir"), "data");
    File[] files = new File(dataDirectory.toString()).listFiles();
    for (File file : files) {
      // files with '-no' in the name will fail to percolate
      String REGEX = "(-no)";
      Pattern p = Pattern.compile(REGEX);
      Matcher m = p.matcher(file.getName()); // get a matcher object

      if (!m.find()) {
        System.out.println("percolators: " + file.getAbsolutePath());
        percolators.add(readFile(file));
      }
    }

    for (Percolation percolation : percolators) {
      Assert.assertTrue(percolation.percolates());
    }
  }

  @Test
  public void testFailsPercolates() throws Exception {
    ArrayList<Percolation> nonPercolators = new ArrayList<Percolation>();

    System.out.println("Working Directory = " + System.getProperty("user.dir"));
    File dataDirectory = new File(System.getProperty("user.dir"), "data");
    File[] files = new File(dataDirectory.toString()).listFiles();
    for (File file : files) {
      // files with '-no' in the name will fail to percolate
      String REGEX = "(-no)";
      Pattern p = Pattern.compile(REGEX);
      Matcher m = p.matcher(file.getName()); // get a matcher object

      if (m.find()) {
        System.out.println("nonPercolators: " + file.getAbsolutePath());
        nonPercolators.add(readFile(file));
      }
    }

    for (Percolation percolation : nonPercolators) {
      Assert.assertFalse(percolation.percolates());
    }
  }

  @Test
  public void testOpenIndexOutOfBoundsException1() {
    Percolation percolation = new Percolation(2);
    exception.expect(IndexOutOfBoundsException.class);
    percolation.open(3, 1);
  }

  @Test
  public void testOpenIndexOutOfBoundsException2() {
    Percolation percolation = new Percolation(2);
    exception.expect(IndexOutOfBoundsException.class);
    percolation.open(1, 3);
  }

  @Test
  public void testOpenIndexOutOfBoundsException3() {
    Percolation percolation = new Percolation(2);
    exception.expect(IndexOutOfBoundsException.class);
    percolation.open(0, 1);
  }

  @Test
  public void testOpenIndexOutOfBoundsException4() {
    Percolation percolation = new Percolation(2);
    exception.expect(IndexOutOfBoundsException.class);
    percolation.open(1, 0);
  }

  @Test
  public void testIsOpenIndexOutOfBoundsException1() {
    Percolation percolation = new Percolation(2);
    exception.expect(IndexOutOfBoundsException.class);
    percolation.isOpen(3, 1);
  }

  @Test
  public void testIsOpenIndexOutOfBoundsException2() {
    Percolation percolation = new Percolation(2);
    exception.expect(IndexOutOfBoundsException.class);
    percolation.isOpen(1, 3);
  }

  @Test
  public void testIsOpenIndexOutOfBoundsException3() {
    Percolation percolation = new Percolation(2);
    exception.expect(IndexOutOfBoundsException.class);
    percolation.isOpen(0, 1);
  }

  @Test
  public void testIsOpenIndexOutOfBoundsException4() {
    Percolation percolation = new Percolation(2);
    exception.expect(IndexOutOfBoundsException.class);
    percolation.isOpen(1, 0);
  }

  @Test
  public void testIsFullIndexOutOfBoundsException1() {
    Percolation percolation = new Percolation(2);
    exception.expect(IndexOutOfBoundsException.class);
    percolation.isFull(3, 1);
  }

  @Test
  public void testIsFullIndexOutOfBoundsException2() {
    Percolation percolation = new Percolation(2);
    exception.expect(IndexOutOfBoundsException.class);
    percolation.isFull(1, 3);
  }

  @Test
  public void testIsFullIndexOutOfBoundsException3() {
    Percolation percolation = new Percolation(2);
    exception.expect(IndexOutOfBoundsException.class);
    percolation.isFull(0, 1);
  }

  @Test
  public void testIsFullIndexOutOfBoundsException4() {
    Percolation percolation = new Percolation(2);
    exception.expect(IndexOutOfBoundsException.class);
    percolation.isFull(1, 0);
  }
}