@REGRESSION_REWARDS @CLOVER
Feature: View your redeemable fuel vouchers in the card screen

  Scenario: User selects fuel voucher
    Given a user logs in the rewards app with card number "REWARDS_USER_NO_FUEL_VOUCHER_OR_OFFERS"
    When the user selects fuel vouchers
    And the user does not have a fuel voucher
    Then the user should see the terms and conditions link for fuel vouchers
    And the user should see a fuel icon