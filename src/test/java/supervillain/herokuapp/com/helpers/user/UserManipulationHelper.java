package supervillain.herokuapp.com.helpers.user;
import supervillain.herokuapp.com.helpers.common.BaseHelper;
import supervillain.herokuapp.com.model.user.CreateUserRequest;
import supervillain.herokuapp.com.model.user.CreateUserResponse;
import supervillain.herokuapp.com.model.user.UserDetailsResponse;
import supervillain.herokuapp.com.model.user.UserTokenVerificationResponse;
import supervillain.herokuapp.com.stepdefinitions.common.ServiceHooks;
import supervillain.herokuapp.com.utils.RestInvocationUtil;
import supervillain.herokuapp.com.utils.SharedData;
import supervillain.herokuapp.com.utils.URLResources;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static supervillain.herokuapp.com.utils.Utilities.generateRandomNumber;

public class UserManipulationHelper extends BaseHelper {

  private final static Logger logger = Logger.getLogger("AddressHelper.class");
  RestInvocationUtil invocationUtil;

  public UserManipulationHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public UserTokenVerificationResponse userVerifyToken() throws IOException {

    String endPoint = URLResources.Verify_Token;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithoutParam(endPoint, headerListCommon);
    String responseStr = mapWebserviceResponse.get("response");
    UserTokenVerificationResponse[] userTokenVerificationResponse = mapper.readValue(responseStr, UserTokenVerificationResponse[].class);
    return userTokenVerificationResponse[0];

  }

  public CreateUserResponse createUser(String username, int score) throws IOException {

    CreateUserRequest createUserRequest = new CreateUserRequest();
    String newUsername = username +generateRandomNumber();
    String endPoint = URLResources.User_Details;
    createUserRequest.setScore(score);
    createUserRequest.setUsername(newUsername);

    SharedData.username = newUsername;
    SharedData.score=score;

    String createUserRequestStr = mapper.writeValueAsString(createUserRequest);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, createUserRequestStr, headerListCommon);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, CreateUserResponse.class);

  }

  public UserDetailsResponse userDetailsResponse() throws IOException {

    String endPoint = URLResources.User_Details;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithoutParam(endPoint, headerListCommon);
    String responseStr = mapWebserviceResponse.get("response");
    UserDetailsResponse[] userDetailsResponse = mapper.readValue(responseStr, UserDetailsResponse[].class);
    return Arrays.stream(userDetailsResponse).filter(it -> it.getUsername().equals(SharedData.username)).findFirst().get();

  }

  public void modifyUser(int newScore) throws IOException {

    CreateUserRequest createUserRequest = new CreateUserRequest();
    String endPoint = URLResources.User_Details;
    createUserRequest.setScore(newScore);
    createUserRequest.setUsername(SharedData.username);

    SharedData.score=newScore;

    String createUserRequestStr = mapper.writeValueAsString(createUserRequest);

    invocationUtil.invokePut(endPoint, createUserRequestStr, headerListCommon);

  }

}
