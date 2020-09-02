package au.com.woolworths.utils;

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
    return mapWebServiceResponse;
  }

  public Map<String, String> invokeGetWithHeaders(String endPoint, Map<String, ?> queryParams, List<Header> headerList) {
    response = getRestWithDynamicHeaders(endPoint, queryParams, headerList);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
    requests.add(queryParams.toString());
    responses.add(response);
    return mapWebServiceResponse;
  }

  public Map<String, String> invokeWithAPIKey(String endPoint, String requestPayload, String apiKey) {
    response = postRestWithBodyAndAPIkey(endPoint, requestPayload, apiKey);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
    requests.add(requestPayload);
    responses.add(response);
    return mapWebServiceResponse;
  }

  public Map<String, String> invokeGetWithoutParam(String endPoint, List<Header> headerList) {
    response = getResponseWithoutParam(endPoint, headerList);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
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
    responses.add(response);
    return mapWebServiceResponse;
  }

  public Map<String, String> invokeDelete(String endPoint, Map<String, ?> queryParams, List<Header> dynamicHeaderList) {
    response = getDeleteResponse(endPoint, queryParams, dynamicHeaderList);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
    requests.add(queryParams.toString());
    responses.add(response);
    return mapWebServiceResponse;
  }

  public Map<String, String> invokePostWithoutBody(String endPoint, List<Header> dynamicHeaderList) {
    response = postRestWithoutBody(endPoint, dynamicHeaderList);
    String strResponse = response.getBody().asString();
    mapWebServiceResponse.put("response", strResponse);
    mapWebServiceResponse.put("statusCode", Integer.toString(response.getStatusCode()));
    mapWebServiceResponse.put("contentType", (response.contentType()));
    endPoints.add(endPoint);
    requests.add("ONLY_POST_NO_BODY");
    responses.add(response);
    return mapWebServiceResponse;
  }

  private Response getRestWithDynamicHeaders(String endPoint, Map<String, ?> params, List<Header> dynamicHeaderList) {
    try {
      RestAssured.baseURI = getBaseURL(endPoint);

      Headers headers = new Headers(dynamicHeaderList);
      response = given()
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
      Assert.fail("Endpoint::" + endPoint + "Error::" + e.getMessage() + "Stack Trace::" + errors.toString());
    }
    return response;

  }

  private Response putRestWithBody(String endPoint, String requestPayload, List<Header> headerList) {
    try {
      RestAssured.baseURI = getBaseURL(endPoint);
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
      RestAssured.baseURI = getBaseURL(endPoint);
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

  private Response postRestWithoutBody(String endPoint, List<Header> headerList) {
    try {
      RestAssured.baseURI = getBaseURL(endPoint);
      Headers headers = new Headers(headerList);
      response = given()
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
      Assert.fail("Endpoint::" + endPoint + "Error::" + e.getMessage() + "Stack Trace::" + errors.toString());

    }
    return response;
  }


  private Response getDeleteResponse(String endPoint, Map<String, ?> params, List<Header> headerList) {
    try {
      RestAssured.baseURI = getBaseURL(endPoint);
      Headers headers = new Headers(headerList);
      response = given()
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
      Assert.fail("Endpoint::" + endPoint + "Error::" + e.getMessage() + "Stack Trace::" + errors.toString());
    }
    return response;
  }

  private Response getResponseWithoutParam(String endPoint, List<Header> headerList) {
    try {
      RestAssured.baseURI = getBaseURL(endPoint);
      Headers headers = new Headers(headerList);
      response = given()
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
      Assert.fail("Endpoint::" + endPoint + "Error::" + e.getMessage() + "Stack Trace::" + errors.toString());
    }
    return response;
  }

  private Response postRestWithBodyAndAPIkey(String endPoint, String requestPayload, String apiKey) {
    try {
      RestAssured.baseURI = getBaseURL(endPoint);
      List<Header> headerList = new LinkedList<>();
      headerList.add(new Header("wowapi-key", apiKey));
      headerList.add(new Header("cache-control", "no-cache"));
      Headers headers = new Headers(headerList);

      response = given()
          .header("Content-Type", "application/json")
          .header("Accept", "application/json")
          .headers(headers)
          .body(requestPayload)
          .when()
          .post(endPoint).then()
          .extract().response();

    } catch (Exception e) {
      e.printStackTrace();
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
      Assert.fail("Endpoint::" + endPoint + "Request PayLoad::" + requestPayload + "Error::" + e.getMessage() + "Stack Trace::" + errors.toString());
    }
    return response;
  }


  private String getBaseURL(String endPoint) {
    if (endPoint.startsWith("/api") || endPoint.startsWith("api") || endPoint.startsWith("/Auth"))
      return TestProperties.get("BASE_URI_TRADER");
    else
      return TestProperties.get("BASE_URI_APIGEE");
  }

}
