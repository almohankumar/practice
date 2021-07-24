@practice
Feature: To navigate to selenium easy and perform the practice.

  Scenario: Use case 1 - Form Submission
    Given I navigate to selenium easy test page
    And I select ajax page option under input forms menu
    And I enter the name "Mohan" and comment "Testing"
    And I click on submit button
    Then I verify that the spinner is displayed
    And I verify that the message "Form submited Successfully!" is displayed

  @uc2
  Scenario: Use case 2 - Date Picker
    Given I navigate to selenium easy test page
    And I select jQuery date picker under date pickers
    And I select the start date "1-Oct-2020"
    And I select the end date "1-Nov-2020"
    Then I verify that I cannot select start date after "1-Nov-2020"
    Then I verify that I cannot select end date before "1-Oct-2020"

  @uc3
  Scenario: Use case 3 - Download Progress
    Given I navigate to selenium easy test page
    And I select bootstrap progress bar under progress bars
    And I click on the download button
    Then I log progress until completion
    And I log the time taken for the download

  @uc4
  Scenario: Use case 3 - Slider
    Given I navigate to selenium easy test page
    And I select drag and drop slider under progress bars
    When I move the sliders to 50 percent
    Then I verify that all the sliders are at 50 percent


