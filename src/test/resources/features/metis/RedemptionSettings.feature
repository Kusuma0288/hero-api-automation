@REGRESSION @REDEMPTION_SETTINGS
Feature: Redemption settings provides the option to choose how to be rewarded

  Scenario: User selects redemption settings
    Given a user has a Link session token
    And the user logs in with their authcode
    And the user should be logged into the Rewards App
    When the user makes a request for the redemption settings
    Then the redemption setting should return "3" reward options
    And  the step up url should be returned
    And item "1" returns the title "Automatic savings"
    And item "1" should return the body "Get $10 off your next shop, whenever you reach 2000 points"
    And item "1" should return the icon "dollars_off"
    And item "1" should return title "Switch to Woolworths Dollars?" for confirmation message "1"
    And item "1" should return button label "Switch" for confirmation message "1"
    And item "1" should not be destructive for confirmation message "1" title
    And item "2" returns the title "Save for Christmas"
    And item "2" should return the body "Get all your money off at Christmas, available from 01/12/20"
    And item "2" should return the icon "xmas_stocking"
    And item "2" should return title "Switch to Bank for Christmas?" for confirmation message "1"
    And item "2" should return button label "Switch" for confirmation message "1"
    And item "2" should not be destructive for confirmation message "1" title
    And item "3" returns the title "Qantas Points"
    And item "3" should return the body "Converting to Qantas Points. 2000 Woolworths Points = 1000 Qantas Points"
    And item "3" should return the icon "airplane"
    And item "3" should return title "Switch to Qantas Points" for confirmation message "1"
    And item "3" should return button label "Switch" for confirmation message "1"
    And item "3" should be destructive for confirmation message "1" title