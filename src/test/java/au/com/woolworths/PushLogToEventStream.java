package au.com.woolworths;

import com.microsoft.azure.eventhubs.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

public class PushLogToEventStream {
  private final static Logger logger = Logger.getLogger("PushLogToEventStream.class");

  public static void main(String arg[]) throws IOException, EventHubException, ParseException {
/*            final String  namespaceName = "wowdevanalyticsplatformeventstream";
            final String eventHubName = "eventstream";
            final String sasKeyName = "eventstream_publisher";
            final String sasKey = "snCNecsNe9nUpJ666UjZFGtc3JTLcjCpKLsv2gItodI=";*/

    final String namespaceName = "wlxuatanalyticsplatformeventstream";
    final String eventHubName = "eventstream";
    final String sasKeyName = "eventstream_publisher";
    final String sasKey = "LZ/c5BNqOm7aVwtlrLzMbkygtCAwGxwkxqOtul43RJ8=";
    final TransportType transportType = TransportType.AMQP_WEB_SOCKETS;

    final ConnectionStringBuilder connStr = new ConnectionStringBuilder()
        .setNamespaceName(namespaceName)
        .setEventHubName(eventHubName)
        .setSasKeyName(sasKeyName)
        .setSasKey(sasKey)
        .setTransportType(transportType);

    final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    final EventHubClient ehClient = EventHubClient.createSync(connStr.toString(), executorService);

    ObjectMapper mapper = new ObjectMapper();
    ObjectNode[] jsonNodes = mapper.readValue(new File("target/cucumber-reports/CucumberTestReport.json"), ObjectNode[].class);
    jsonNodes[0].put("namespace", "testresults");
    jsonNodes[0].put("platform", "testresults");
    jsonNodes[0].put("application", "apigeeapitest");
    /* jsonNodes[0].put("message", "TestResults");*/

    mapper.writer().writeValue(new File("target/UpdatedCucumberTestReport.json"), jsonNodes);

/*        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("target/UpdatedCucumberTestReport.json"));
        JSONArray json = (JSONArray) obj;
        JSONObject elem = (JSONObject)json.get(0);*/

    byte[] cucumberReport = jsonNodes[0].toString().getBytes("utf-8");
    EventData sendEvent = EventData.create(cucumberReport);
    ehClient.sendSync(sendEvent);
    ehClient.closeSync();
    executorService.shutdown();
    logger.info("Massage sent to Hub");
  }

}