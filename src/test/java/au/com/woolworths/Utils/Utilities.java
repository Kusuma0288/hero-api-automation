package au.com.woolworths.Utils;

import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class Utilities {

  public static boolean isNumeric(String strNum) {
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    } catch (NullPointerException npe) {
      return false;
    }
    return true;
  }

  public static String getSaltString() {
    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < 5) { // length of the random string.
      int index = (int) (rnd.nextFloat() * SALTCHARS.length());
      salt.append(SALTCHARS.charAt(index));
    }
    String saltStr = salt.toString();
    return saltStr;

  }

  public static String generateRandomUUIDString() {
    UUID uuid = UUID.randomUUID();
    String randomUUIDString = uuid.toString();
    return randomUUIDString;
  }

  public static String getRandomEmailAddress() {
    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < 18) { // length of the random string.
      int index = (int) (rnd.nextFloat() * SALTCHARS.length());
      salt.append(SALTCHARS.charAt(index));
    }
    String saltStr = salt.toString();
    return saltStr + "@shopApp.com";
  }

  public static String replaceMultipleandTrimSpaces(String original) {
    return original.trim().replaceAll(" +", " ");
  }

  public static boolean isSorted(double[] array) {
    return IntStream.range(0, array.length - 1).noneMatch(i -> array[i] > array[i + 1]);
  }
}
