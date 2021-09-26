package supervillain.herokuapp.com.utils;

import java.util.Random;

public class Utilities {

  public static String generateRandomNumber() {
    Random rnd = new Random();
    int n = rnd.nextInt(900);
    return String.valueOf(n);
  }

}
