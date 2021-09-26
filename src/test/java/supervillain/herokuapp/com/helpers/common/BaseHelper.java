package supervillain.herokuapp.com.helpers.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import org.testng.asserts.SoftAssert;
import supervillain.herokuapp.com.utils.TestProperties;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class BaseHelper {

  private final static Logger logger = Logger.getLogger("BaseHelper.class");
  protected static List<Header> headerListCommon;
  protected ObjectMapper mapper = new ObjectMapper();
  public static SoftAssert softAssert;

  public BaseHelper() {
    this.headerListCommon = new LinkedList<>();
    headerListCommon.add(new Header("Authorization", TestProperties.get("Authorization")));
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
  }


}

