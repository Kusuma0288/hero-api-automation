package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.metis.AccountHelper;
import au.com.woolworths.model.metis.account.AccountHomePageResponse;
import au.com.woolworths.model.metis.account.Section;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class AccountDefinition extends AccountHelper {
  private final static Logger logger = Logger.getLogger("AccountDefinition.class");
  private AccountHomePageResponse AccountHomePageResponse = new AccountHomePageResponse();

  @When("^the user selects the account screen")
  public void theUserMakesARequestForTheAccountScreen() throws Throwable {
    InputStream iStream = ActivityDefinition.class.getResourceAsStream("/gqlQueries/metis/queries/rewards/account.graphql");
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, null);
    AccountHomePageResponse = iRetrieveMyAccount(graphqlQuery);
  }

  @Then("^the user should be able to see the account details$")
  public void accountDetailsAreDisplayed() {
    List <Section> sections = AccountHomePageResponse.getData().getRewardsAccountHome().getSections();
    Assert.assertEquals("Manage Your account section title missing", "MANAGE YOUR ACCOUNT", sections.get(0).getTitle());
    Assert.assertEquals("Update Account Details title missing", "Update Account Details", sections.get(0).getOptions().get(0).getTitle());
    Assert.assertEquals("Choose How You're rewarded title missing", "Choose How You're rewarded", sections.get(0).getOptions().get(1).getTitle());
    Assert.assertEquals("Receipt Preferences title missing", "Receipt Preferences", sections.get(0).getOptions().get(2).getTitle());
    Assert.assertEquals("Cards & Accounts title missing", "Cards & Accounts", sections.get(0).getOptions().get(3).getTitle());
    Assert.assertEquals("The page for cards-account should not be empty", "cards-account", sections.get(0).getOptions().get(3).getPage());
    Assert.assertEquals("The page for my details should not be empty", "my-details", sections.get(0).getOptions().get(0).getPage());
    Assert.assertNotNull("The page should not be empty", sections.get(0).getOptions().get(0).getPage());
    Assert.assertNotNull("The id should not be empty", sections.get(0).getOptions().get(0).getId());
    Assert.assertNotNull("The id should not be empty", sections.get(0).getOptions().get(2).getId());
    Assert.assertNotNull("The action should not be empty", sections.get(0).getOptions().get(2).getAction());
    Assert.assertNotNull("The badge value should not be empty", sections.get(0).getOptions().get(1).getShouldShowNewBadge());
    Assert.assertNotNull("The url should not be empty", sections.get(0).getOptions().get(2).getAction().getUrl());
    Assert.assertNotNull("The badge should not be empty", sections.get(0).getOptions().get(2).getShouldShowNewBadge());

    Assert.assertEquals("Redemption settings Deep link url is incorrect", "com.woolworths.rewards://redemptionSettings", sections.get(0).getOptions().get(1).getAction().getUrl());
    Assert.assertEquals("DEEP_LINK for redemption settings missing", "DEEP_LINK", sections.get(0).getOptions().get(1).getAction().getAction());
    Assert.assertEquals("Receipt preference deeplink is incorrect", "com.woolworths.rewards://preferences/receipts", sections.get(0).getOptions().get(2).getAction().getUrl());
    Assert.assertEquals("DEEP_LINK for receipt preference missing", "DEEP_LINK", sections.get(0).getOptions().get(2).getAction().getAction());
    Assert.assertNotNull("The action should not be empty", sections.get(0).getOptions().get(1).getAction());
    Assert.assertNotNull("The id should not be empty", sections.get(0).getOptions().get(1).getId());

    Assert.assertEquals("Communication Preference is missing", "COMMUNICATION PREFERENCES", sections.get(1).getTitle());
    Assert.assertEquals("Push Notifications title missing", "Push Notifications", sections.get(1).getOptions().get(0).getTitle());
    Assert.assertEquals("Email, SMS and Post missing", "Email, SMS and Post", sections.get(1).getOptions().get(1).getTitle());
    Assert.assertEquals("Push notifications Deep link url is incorrect", "com.woolworths.rewards://preferences/pushNotifications", sections.get(1).getOptions().get(0).getAction().getUrl());
    Assert.assertEquals("DEEP_LINK for push notification missing", "DEEP_LINK", sections.get(1).getOptions().get(0).getAction().getAction());

    Assert.assertEquals("Rewards Preferences title missing", "REWARDS", sections.get(2).getTitle());
    Assert.assertEquals("Write a review URL missing", "https://itunes.apple.com/app/id1489403660?action=write-review", sections.get(2).getOptions().get(0).getUrl());
    Assert.assertEquals("How it works url is incorrect", "https://www.woolworthsrewards.com.au/how-it-works.html", sections.get(2).getOptions().get(1).getAction().getUrl());
    Assert.assertEquals("WEB_LINK url Wallet onboarding is incorrect", "com.woolworths.rewards://walletOnboarding", sections.get(2).getOptions().get(2).getAction().getUrl());
    Assert.assertEquals("WEB_LINK for Terms of use is incorrect ", "https://www.woolworthsrewards.com.au/terms/app.html", sections.get(2).getOptions().get(5).getAction().getUrl());
    Assert.assertEquals("WEB_LINK for help and support missing", "WEB_LINK", sections.get(2).getOptions().get(1).getAction().getAction());

    logger.info("The account details were returned successfully");

  }
}






