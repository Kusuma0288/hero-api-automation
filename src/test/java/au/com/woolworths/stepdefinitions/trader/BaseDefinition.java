package au.com.woolworths.stepdefinitions.trader;

import cucumber.api.java.en.When;

import java.util.logging.Logger;

public class BaseDefinition {

  private final static Logger logger = Logger.getLogger("BaseDefinition.class");

  @When("^I add wait for (.*) msecs to make sure the sync is successful$")
  public void iWait(int timeInSecs) throws Throwable {
    Thread.sleep(timeInSecs);
  }

}
