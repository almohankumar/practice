@api @booking
Feature: To verify the booking API

  Scenario Outline: To verify that all rest actions of the booking API are working as expected.

    Given I make a post call to the "<resource>"
    Then I verify that the response code is 200
    And I verify that the booking is created
    When I make a get call to the "<resource>"
    Then I verify that the response code is 200
    And I verify the retrieved booking
    When I make a post call to "auth" as "admin"
    Then I verify that the response code is 200
    When I make a put call to the "<resource>"
    Then I verify that the response code is 200
    And I verify that the booking is updated
    When I make a delete call to the "<resource>"
    Then I verify that the response code is 201

    Examples:
      | resource |
      | booking  |


  Scenario Outline: To verify that the delete API prevents unauthorized access.

    Given I make a post call to the "<resource>"
    Then I verify that the response code is 200
    And I verify that the booking is created
    When I make a post call to "auth" as "admin"
    Then I verify that the response code is 200
    When I make a put call to the "<resource>" using an invalid token
    Then I verify that the response code is 403
    Examples:
      | resource |
      | booking  |

