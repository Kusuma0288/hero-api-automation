Feature: Checkout API scenarios
Scenario: Verify getting and setting of v3/checkout windows

   Given user continue to connect to apigee with login username as "SHOPPER_USERNAME6"
   When connection from user to apigee endpoint happens
   And I clear the trolley
   Then I search for the pickup stores in the postcode 2204
   Then I set the fulfilmentMethod to "Pickup" for the 1 store
   When I search for the product Milk in pickup mode and store response
   And I add the 2 available products with 1 each from the store to the V3 trolley
   And I make a GET request to checkout api
   And I get the available pickup windows for the logged in user with storeId or addressId
   Then I set the selected available pickup window for the logged in user