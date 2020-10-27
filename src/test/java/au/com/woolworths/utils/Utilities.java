package au.com.woolworths.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class Utilities {

  public static boolean isNumeric(String strNum) {
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException | NullPointerException nfe) {
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

  public static long convertToEpochTime() {
    Date today = Calendar.getInstance().getTime();

    // Constructs a SimpleDateFormat using the given pattern
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");

    // format() formats a Date into a date/time string.
    String currentDateAndTime = dateFormat.format(today);
    long epochTime;

    try {

      // parse() parses text from the beginning of the given string to produce a date.
      Date date = dateFormat.parse(currentDateAndTime);

      // getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
      epochTime = date.getTime();

    } catch (ParseException e) {
      epochTime = 0;
      e.printStackTrace();
    }

    return epochTime;
  }
}
