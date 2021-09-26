package supervillain.herokuapp.com.stepdefinitions.user;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import supervillain.herokuapp.com.helpers.user.UserManipulationHelper;
import supervillain.herokuapp.com.model.user.CreateUserResponse;
import supervillain.herokuapp.com.model.user.UserDetailsResponse;
import supervillain.herokuapp.com.utils.SharedData;

import java.io.IOException;

public class UserManipulation extends UserManipulationHelper {

  @Given("^user wants to verify his token$")
  public void userCallsTokenVerification() throws IOException {
    SharedData.userTokenVerificationResponse = userVerifyToken();
  }

  @Then("^verify the token is not expired$")
  public void verifyToken() {
    Assert.assertEquals(SharedData.statusCode,200,"Unexpected status code");

  }

  @Given("^customer creates a new user with username \"([^\"]*)\" and score \"([^\"]*)\"$")
  public void customerCreatesUser(String username, int score) throws IOException {
       CreateUserResponse createUserResponse= createUser(username,score);
       Assert.assertEquals(SharedData.statusCode,201,"Unexpected status code");
       Assert.assertEquals(createUserResponse.getStatus(),"success", "User is not successfully created");
       Assert.assertEquals(createUserResponse.getMessage(),"User added.", "User is not successfully created");
  }

  @Given("^customer retrieves the user details and verifies the newly created user details$")
  public void customerRetrievesUserDetails() throws IOException {
    UserDetailsResponse userDetailsResponse = userDetailsResponse();
    Assert.assertEquals(SharedData.statusCode,200,"Unexpected status code");
    Assert.assertEquals(userDetailsResponse.getUsername(),SharedData.username,"Username of the new user created matches");
    Assert.assertEquals(userDetailsResponse.getScore(),SharedData.score,"Score of the new user created matches");
  }

  @Given("^customer modifies the created user with new score as \"([^\"]*)\"$")
  public void customerModifiesUserDetails(int newScore) throws IOException {
    modifyUser(newScore);

    Assert.assertEquals(SharedData.statusCode,204,"User details have been modified");
  }

  @Given("^verify the created user is modified$")
  public void verifyCreatedUserModified() throws IOException {
    UserDetailsResponse userDetailsResponse1 = userDetailsResponse();

    Assert.assertEquals(SharedData.statusCode,200,"Unexpected status code");
    Assert.assertEquals(userDetailsResponse1.getScore(),SharedData.score,"Score of the modified user created matches");

  }
}
