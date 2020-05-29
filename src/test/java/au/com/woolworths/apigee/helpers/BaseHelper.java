package au.com.woolworths.apigee.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class BaseHelper {

  private final static Logger logger = Logger.getLogger("BaseHelper.class");

  public long convertToEpochTime() {
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
