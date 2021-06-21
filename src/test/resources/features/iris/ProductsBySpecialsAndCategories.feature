@Wolves
Feature: Specials GraphQL
  # UAT has known issues so focus on PROD
  # Test failing due to bug TW-3462
#  @PROD_APIGEE @IRIS @PROD
#  Scenario: User never sees duplicate products in search results
#    Given I connect to apigee endpoint as a guest user
#    When user searches for online "Milk" products 20 at a time and scrolls to the end of the results
#    Then no duplicate results are returned
#    And the product total count matches the actual number of products returned

#  Taniwha Squad modified search to include a conditional flag to contain a Google Ad Banner
#   "variables": {
#     "searchTerm": "Coke",
#     "productsFeed": true
#   }


  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario Outline: Verifications of Specials for logged in user- My Specials with sort option Trader Relevance
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME1"
    And connection from user to apigee endpoint happens
    Then user visit Specials and applies sort option "TraderRelevance" and "<SpecialsGroup>" specials group

    Examples:
    |SpecialsGroup|
    |    -1       |

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario Outline: Verifications of Specials for logged in user- Online Only Specials with sort option Name Ascending[A-Z]
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME1"
    And connection from user to apigee endpoint happens
    Then user visit Specials and applies sort option "Name" and "<SpecialsGroup>" specials group

    Examples:
      |SpecialsGroup|
      |    1674     |

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario Outline: Verifications of Specials for logged in user- Half Price with sort option Name Descending[Z-A]
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME1"
    And connection from user to apigee endpoint happens
    Then user visit Specials and applies sort option "NameDesc" and "<SpecialsGroup>" specials group

    Examples:
      |SpecialsGroup|
      |    3658     |

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario Outline: Verifications of Specials for logged in user- Buy More Save More with sort option NEW
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME1"
    And connection from user to apigee endpoint happens
    Then user visit Specials and applies sort option "AvailableDateDesc" and "<SpecialsGroup>" specials group

    Examples:
      |SpecialsGroup|
      |    3657     |

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario Outline: Verifications of Specials for guest user- Buy More Save More with Price[Low-High]
    Given I connect to apigee endpoint as a guest user
    Then user visit Specials and applies sort option "PriceAsc" and "<SpecialsGroup>" specials group

    Examples:
      |SpecialsGroup|
      |    3657     |

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario Outline: Verifications of Specials for guest user- Half Price with sort option Price[High-Low]
    Given I connect to apigee endpoint as a guest user
    Then user visit Specials and applies sort option "PriceDesc" and "<SpecialsGroup>" specials group

    Examples:
      |SpecialsGroup|
      |    3658     |

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario Outline: Verifications of Specials for guest user- Online Only Specials with sort option Unit Price[Low-High]
    Given I connect to apigee endpoint as a guest user
    Then user visit Specials and applies sort option "CUPAsc" and "<SpecialsGroup>" specials group

    Examples:
      |SpecialsGroup|
      |    1674     |

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario Outline: Verifications of Specials for guest user- Online Only Specials with sort option Unit Price[High-Low]
    Given I connect to apigee endpoint as a guest user
    Then user visit Specials and applies sort option "CUPDesc" and "<SpecialsGroup>" specials group

    Examples:
      |SpecialsGroup|
      |    1674     |

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario Outline: Verifications of Specials Categories for logged in user with sort option Trader Relevance
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME1"
    And connection from user to apigee endpoint happens
    Then user visit Specials Categories and applies sort option "TraderRelevance" and "<CategoriesGroup>" as a category

    Examples:
      |CategoriesGroup|
      |    1_E872E7C  |

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario Outline: Verifications of Specials Categories for a guest user with sort option Trader Relevance
    Given I connect to apigee endpoint as a guest user
    Then user visit Specials Categories and applies sort option "TraderRelevance" and "<CategoriesGroup>" as a category

    Examples:
      |CategoriesGroup|
      |    1_E872E7C  |




