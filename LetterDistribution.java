import java.nio.file.Files;
import java.nio.file.Paths;

public class LetterCounter {

    public static String readFileAsString(String fileName) throws Exception {
      String str = new String(Files.readAllBytes(Paths.get(fileName)));
      return str;
    }

    public static void main(String[] args) throws Exception {

      String directory = "C:\\Users\\imamo\\Desktop\\para1.txt";
      String str = readFileAsString(directory);
      char[] charArray = str.toCharArray();

      String str1 = "abcdefghijklmnopqrstuvwxyz";
      char[] alphabet = str1.toCharArray();

      int k = 0;

      for (char value : alphabet) {

        for (char c : charArray) {
          if (value == c) {
            k++;
          }
        }
          System.out.println(value + ": " + k);
          k = 0;
      }
    }


  public static void graphDistribution (String[] args) {

    StdDraw.setXscale(-5, 27);
    StdDraw.setYscale(-0.1, 1.1);

    StdDraw.rectangle(11, 0.5, 5, 0.25);
  }
}

