package supervillain.herokuapp.com.utils;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class RestInvocationUtil {

  private final static Logger logger = Logger.getLogger("RestInvocationUtil.class");

  private final Map<String, String> mapWebServiceResponse = new HashMap<>();
  public Response response;
  public ArrayList<String> endPoints = new ArrayList<>();
  public ArrayList<String> requests = new ArrayList<>();
  public ArrayList<Response> responses = new ArrayList<>();

  public Map<String, String> invokePostWithHeaders(String endPoint, String requestPayload, List<Header> headerList) {
    response = postRestWithBodyAndHeaders(endPoint, requestPayload, headerList);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
    requests.add(requestPayload);
    responses.add(response);
    SharedData.statusCode = response.getStatusCode();
    return mapWebServiceResponse;
  }

  public Map<String, String> invokeGetWithoutParam(String endPoint, List<Header> headerList) {
    response = getResponseWithoutParam(endPoint, headerList);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
    SharedData.statusCode = response.getStatusCode();
    responses.add(response);
    return mapWebServiceResponse;
  }

  public Map<String, String> invokePut(String endPoint, String requestPayload, List<Header> dynamicHeaderList) {
    response = putRestWithBody(endPoint, requestPayload, dynamicHeaderList);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
    requests.add(requestPayload);
    SharedData.statusCode = response.getStatusCode();
    responses.add(response);
    return mapWebServiceResponse;
  }


  private Response putRestWithBody(String endPoint, String requestPayload, List<Header> headerList) {

    try {
      baseURI = getBaseURL(endPoint);
      Headers headers = new Headers(headerList);
      response = given()
          .header("Content-Type", "application/json")
          .header("Accept", "application/json")
          .headers(headers)
          .body(requestPayload)
          .when()
          .put(endPoint)
          .then()
          .extract().response();
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Endpoint::" + endPoint + "Request PayLoad::" + requestPayload + "Error::" + e.getMessage());
    }
    return response;
  }

  private Response postRestWithBodyAndHeaders(String endPoint, String requestPayload, List<Header> dynamicHeaderList) {
    try {
      baseURI = getBaseURL(endPoint);
      Headers headers = new Headers(dynamicHeaderList);
      response = given()
          .header("Content-Type", "application/json")
          .header("Accept", "application/json")
          .headers(headers)
          .body(requestPayload)
          .when()
          .post(endPoint)
          .then()
          .extract().response();
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Endpoint::" + endPoint + "Request PayLoad::" + requestPayload + "Error::" + e.getMessage());
    }
    return response;
  }

  private Response getResponseWithoutParam(String endPoint, List<Header> headerList) {
    try {
      baseURI = getBaseURL(endPoint);
      Headers headers = new Headers(headerList);
      response = given()
          .header("Content-Type", "application/json")
          .header("Accept", "application/json")
          .headers(headers)
          .when()
          .get(endPoint)
          .then()
          .extract().response();
      response.getStatusCode();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
      Assert.fail("Endpoint::" + endPoint + "Error::" + e.getMessage() + "Stack Trace::" + errors.toString());
    }
    return response;
  }

  private String getBaseURL(String endPoint) {

    return TestProperties.get("BASE_URI");
  }
}
