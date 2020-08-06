package au.com.woolworths.helpers.common;

import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.context.ApplicationContext;
import au.com.woolworths.utils.SharedData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class BaseHelper {

  private final static Logger logger = Logger.getLogger("BaseHelper.class");
  protected SharedData sharedData;
  protected static List<Header> headerList;
  protected ObjectMapper mapper = new ObjectMapper();

  public BaseHelper() {
    this.headerList = new LinkedList<>();
    this.sharedData = ApplicationContext.getSharedData();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + sharedData.accessToken));
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
  }

  public void setHeaderList() {
    headerList.remove(1);
    headerList.add(new Header("Authorization", "Bearer " + sharedData.accessToken));
  }

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
