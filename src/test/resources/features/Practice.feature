@practice
Feature: To navigate to selenium easy and perform the practice.
  @uc1
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
  Scenario: Use case 4 - Slider
    Given I navigate to selenium easy test page
    And I select drag and drop slider under progress bars
    When I move the sliders to 50 percent
    Then I verify that all the sliders are at 50 percent

  @uc5
  Scenario: Use case 5 - Windows
    Given I navigate to selenium easy test page
    And I select window popup modal under alerts and modals
    When I click on follow twitter and facebook button
    Then I check and print the page titles of social pages and parent page

  @uc6
  Scenario: Use case 6 - Javascript Alert
    Given I navigate to selenium easy test page
    And I select javascript alerts under alerts and modals
    When I click on javascript alert box
    Then I verify and accept the alert

  @uc7
  Scenario Outline: Use case 6 - File Download
    Given I navigate to selenium easy test page
    And I select file download under alerts and modals
    And I enter the "<text>" to download
    And I click on generate file button
    When I download the generated file
    Then I read and verify the "<text>" in the downloaded file
    Examples:
      | text                       |
      | Lorem ipsum dolor sit amet |


