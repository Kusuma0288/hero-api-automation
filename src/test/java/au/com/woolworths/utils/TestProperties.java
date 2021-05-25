package au.com.woolworths.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class TestProperties {

  private static final Properties props;
  private final static Logger logger = Logger.getLogger("TestProperties.class");

  static {
    props = new Properties();
    InputStream input = null;
    try {
      String env = System.getProperty("env");
      if ( env == null) {
        System.setProperty("env", "uat");
      } else if ( env.startsWith("@") ) {
        System.setProperty("env", env.substring(1).toLowerCase());
      }
      input = TestProperties.class.getResourceAsStream("/config/" + System.getProperty("env") + ".properties");
      props.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static String get(String propertyName) {
    return props.getProperty(propertyName);
  }

}
