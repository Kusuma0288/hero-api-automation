package au.com.woolworths.helpers.common;

import au.com.woolworths.context.ApplicationContext;
import au.com.woolworths.utils.SharedData;
import au.com.woolworths.utils.TestProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class BaseHelper {

  private final static Logger logger = Logger.getLogger("BaseHelper.class");
  protected static List<Header> headerListCommon;
  protected static List<Header> headerListTrader;
  protected static List<Header> headerListRewards;
  protected static List<Header> headerListDigipay;
  protected static List<Header> headerListScanGo;
  protected static List<Header> headerListScanGoKiosk;
  protected static List<Header> headerListFirestoreScanGoTeamMemberbarcode;
  protected static SharedData sharedData;
  protected ObjectMapper mapper = new ObjectMapper();

  public BaseHelper() {
    this.headerListCommon = new LinkedList<>();
    this.headerListTrader = new LinkedList<>();
    this.headerListRewards = new LinkedList<>();
    this.headerListDigipay = new LinkedList<>();
    this.headerListScanGo = new LinkedList<>();
    this.headerListScanGoKiosk = new LinkedList<>();
    this.headerListFirestoreScanGoTeamMemberbarcode = new LinkedList<>();

    this.sharedData = ApplicationContext.getSharedData();
    headerListCommon.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerListCommon.add(new Header("Authorization", "Bearer " + sharedData.accessToken));

    headerListTrader.add(new Header("wowapi-key", TestProperties.get("wowapi-key")));
    headerListTrader.add(new Header("wowapi-auth-token", sharedData.authToken));
    headerListTrader.add(new Header("cache-control", "no-cache"));

    addApiKeyBasedOnClientOs(sharedData.clientOS);

    headerListDigipay.add(new Header("x-api-key", TestProperties.get("digipay-x-api-key")));

    headerListScanGo.add(new Header("x-api-key", TestProperties.get("SCANGO_API_KEY")));
    headerListScanGo.add(new Header("Authorization", "Bearer " + sharedData.accessToken));

    headerListScanGoKiosk.add(new Header("x-api-key", TestProperties.get("KIOSK_API_KEY")));
    headerListScanGoKiosk.add(new Header("deviceid", "SCANNER_KIOSK_" + sharedData.storeID));
    headerListScanGoKiosk.add(new Header("storeid", sharedData.storeID));
    headerListScanGoKiosk.add(new Header("cartbarcode", sharedData.cartID));
    headerListScanGoKiosk.add(new Header("Authorization", "Bearer " + sharedData.kioskAccessToken));

    headerListFirestoreScanGoTeamMemberbarcode.add(new Header("x-api-key", TestProperties.get("SCANGO_API_KEY")));
    headerListFirestoreScanGoTeamMemberbarcode.add(new Header("datapath", TestProperties.get("datapath")));
    headerListFirestoreScanGoTeamMemberbarcode.add(new Header("configidentifier", TestProperties.get("configidentifier")));
    headerListFirestoreScanGoTeamMemberbarcode.add(new Header("appidentifier", TestProperties.get("appidentifier")));

    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
  }

  public void resetHeaderList() {
    headerListCommon.clear();
    headerListCommon.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerListCommon.add(new Header("Authorization", "Bearer " + sharedData.accessToken));
  }

  public void resetTraderHeaderList() {
    headerListTrader.clear();
    headerListTrader.add(new Header("wowapi-key", TestProperties.get("wowapi-key")));
    headerListTrader.add(new Header("wowapi-auth-token", sharedData.authToken));
    headerListTrader.add(new Header("cache-control", "no-cache"));
  }

  public void addApiKeyBasedOnClientOs(String clientOS) {
    headerListRewards.clear();
    headerListRewards.add(new Header("Authorization", "Bearer " + sharedData.accessToken));

    //todo make me better
    if (clientOS != null && clientOS.equalsIgnoreCase("iOS")) {
      headerListRewards.add(new Header("x-api-key", TestProperties.get("rewards-iOS-x-api-key")));
    } else if (clientOS != null && clientOS.equalsIgnoreCase("Android")) {
      headerListRewards.add(new Header("x-api-key", TestProperties.get("rewards-android-x-api-key")));
    }
  }

  public WebDriver initiateWebdriver() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver(options);
    return driver;
  }

}
