@REGRESSION_TRADER @DELETE_ADDRESS @PROD_TRADER @SMOKE_TRADER @Wolves
Feature: Delete address for logged in user

  Scenario: Delete the previous delivery addresses for a logged in user

    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME1 and password
    And I search for the address 1 Darcy Road, PORT KEMBLA NSW 2505
    And I select the "1" address as checkout address from matching addresses
    And I search for the address 1 Cornellia Road, TOONGABBIE NSW 2146
    And I select the "1" address as checkout address from matching addresses
    And I search for the address 6 Darcy Road, PORT KEMBLA NSW 2505
    And I select the "1" address as checkout address from matching addresses
    And delete the previous addresses and verify user has only 1 current address
    And I search for the address 407 Pitt Street, HAYMARKET NSW 2000
    And I select the "1" address as checkout address from matching addresses
    And delete the previous addresses and verify user has only 1 current address