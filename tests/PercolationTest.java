import org.junit.*;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;

public class PercolationTest {

  private ArrayList<Percolation> nonPercolators;
  private ArrayList<Percolation> percolators;

//  @BeforeClass
//  public void beforeClass() throws Exception {
//
//  }

  @Ignore
  public static Percolation readFile(File file) throws java.io.IOException {
    Percolation percolation = null;
    String fileName = file.toString();
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
      String line = br.readLine();
      if (line == null) {
        return percolation;
      }
      percolation = new Percolation(Integer.parseInt(line));
      line = br.readLine();

      while (line != null) {
        String[] pair = line.split("\\s+");
        percolation.open(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]));
        line = br.readLine();
      }
      return percolation;
    } finally {
      br.close();
    }
  }

  @Before
  public void setUp() throws Exception {
    nonPercolators = new ArrayList<Percolation>();
    percolators = new ArrayList<Percolation>();

    System.out.println("Working Directory = " + System.getProperty("user.dir"));

    File dataDirectory = new File(System.getProperty("user.dir"), "data");
    File[] files = new File(dataDirectory.toString()).listFiles();
    for (File file : files) {
      String REGEX = "(-no)";
      Pattern p = Pattern.compile(REGEX);
      Matcher m = p.matcher(file.getName()); // get a matcher object

      if (m.find()) {
        System.out.println("nonPercolators: " + file.getAbsolutePath());
        nonPercolators.add(readFile(file));
      } else {
        System.out.println("percolators: " + file.getAbsolutePath());
        percolators.add(readFile(file));
      }
    }
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void testOpen() throws Exception {

  }

  @Test
  public void testIsOpen() throws Exception {

  }

  @Test
  public void testIsFull() throws Exception {

  }

  @Test
  public void testPercolates() throws Exception {

  }
}