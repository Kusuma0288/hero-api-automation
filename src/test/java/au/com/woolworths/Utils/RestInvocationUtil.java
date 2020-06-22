package au.com.woolworths.Utils;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class RestInvocationUtil {

  private final static Logger logger = Logger.getLogger("RestInvocationUtil.class");

  private Map<String, String> mapWebServiceResponse = new HashMap<>();
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
    return mapWebServiceResponse;
  }

  public Map<String, String> invokeGetWithHeaders(String endPoint, String authToken, Map<String, ?> queryParams, List<Header> headerList) {
    response = getRestWithDynamicHeaders(endPoint, authToken, queryParams, headerList);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
    requests.add(queryParams.toString());
    responses.add(response);
    return mapWebServiceResponse;
  }

  public Map<String, String> invokeGetWithoutParam(String endPoint, String authToken) {
    response = getResponseWithoutParam(endPoint, authToken);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
    responses.add(response);
    return mapWebServiceResponse;
  }

  public Map<String, String> invokePut(String endPoint, String requestPayload, String authToken) {
    response = putRestWithBody(endPoint, requestPayload, authToken);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
    requests.add(requestPayload);
    responses.add(response);
    return mapWebServiceResponse;
  }

  public Map<String, String> invokeDelete(String endPoint, String authToken, Map<String, ?> queryParams) {
    response = getDeleteResponse(endPoint, authToken, queryParams);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
    requests.add(queryParams.toString());
    responses.add(response);
    return mapWebServiceResponse;
  }

  public Map<String, String> invokePostWithoutBody(String endPoint, String authToken) {
    response = postRestWithoutBody(endPoint, authToken);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
    requests.add("ONLY_POST_NO_BODY");
    responses.add(response);
    return mapWebServiceResponse;
  }

  private Response getRestWithDynamicHeaders(String endPoint, String authToken, Map<String, ?> params, List<Header> dynamicHeaderList) {
    try {
      RestAssured.baseURI = TestProperties.get("BASE_URI");
      Headers headers = new Headers(dynamicHeaderList);
      response = given()
          // .proxy(TestProperties.get("LOCAL_PROXY"))
          .header("Content-Type", "application/json")
          .header("Accept", "application/json")
          .headers(headers)
          .params(params)
          .when()
          .get(endPoint)
          .then()
          .extract().response();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
      Assert.assertTrue(false, "Endpoint::" + endPoint + "Error::" + e.getMessage() + "Stack Trace::" + errors.toString());
    }
    return response;

  }

  private Response putRestWithBody(String endPoint, String requestPayload, String authToken) {
    try {
      RestAssured.baseURI = TestProperties.get("BASE_URI");
      List<Header> headerList = new LinkedList<Header>();
      headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
      headerList.add(new Header("Authorization", "Bearer " + authToken));

      Headers headers = new Headers(headerList);
      response = given()
          // .proxy(TestProperties.get("LOCAL_PROXY"))
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
      Assert.assertTrue(false, "Endpoint::" + endPoint + "Request PayLoad::" + requestPayload + "Error::" + e.getMessage());
    }
    return response;
  }

  private Response postRestWithBodyAndHeaders(String endPoint, String requestPayload, List<Header> dynamicHeaderList) {
    try {
      RestAssured.baseURI = TestProperties.get("BASE_URI");
      Headers headers = new Headers(dynamicHeaderList);
      response = given()
          // .proxy(TestProperties.get("LOCAL_PROXY"))
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
      Assert.assertTrue(false, "Endpoint::" + endPoint + "Request PayLoad::" + requestPayload + "Error::" + e.getMessage());
    }
    return response;
  }

  private Response postRestWithoutBody(String endPoint, String authToken) {
    try {
      RestAssured.baseURI = TestProperties.get("BASE_URI");
      List<Header> headerList = new LinkedList<Header>();
      headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
      headerList.add(new Header("Authorization", "Bearer " + authToken));

      Headers headers = new Headers(headerList);
      response = given()
          //  .proxy(TestProperties.get("LOCAL_PROXY"))
          .header("Content-Type", "application/json")
          .header("Accept", "application/json")
          .headers(headers)
          .when()
          .post(endPoint)
          .then()
          .extract().response();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
      Assert.assertTrue(false, "Endpoint::" + endPoint + "Error::" + e.getMessage() + "Stack Trace::" + errors.toString());

    }
    return response;
  }


  private Response getDeleteResponse(String endPoint, String authToken, Map<String, ?> params) {
    try {
      RestAssured.baseURI = TestProperties.get("BASE_URI");
      List<Header> headerList = new LinkedList<Header>();
      headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
      headerList.add(new Header("Authorization", "Bearer " + authToken));

      Headers headers = new Headers(headerList);
      response = given()
          //  .proxy(TestProperties.get("LOCAL_PROXY"))
          .header("Content-Type", "application/json")
          .header("Accept", "application/json")
          .headers(headers)
          .params(params)
          .when()
          .delete(endPoint)
          .then()
          .extract().response();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
      Assert.assertTrue(false, "Endpoint::" + endPoint + "Error::" + e.getMessage() + "Stack Trace::" + errors.toString());
    }
    return response;
  }

  private Response getResponseWithoutParam(String endPoint, String authToken) {
    try {
      RestAssured.baseURI = TestProperties.get("BASE_URI");
      List<Header> headerList = new LinkedList<Header>();
      headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
      headerList.add(new Header("Authorization", "Bearer " + authToken));
      Headers headers = new Headers(headerList);
      response = given()
          // .proxy(TestProperties.get("LOCAL_PROXY"))
          .header("Content-Type", "application/json")
          .header("Accept", "application/json")
          .headers(headers)
          .when()
          .get(endPoint)
          .then()
          .extract().response();
    } catch (Exception e) {
      e.printStackTrace();
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
      Assert.assertTrue(false, "Endpoint::" + endPoint + "Error::" + e.getMessage() + "Stack Trace::" + errors.toString());
    }
    return response;
  }

}
