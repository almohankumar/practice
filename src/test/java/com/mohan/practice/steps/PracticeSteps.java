package com.mohan.practice.steps;

import com.mohan.practice.driver.DriverFactory;
import com.mohan.practice.pages.PracticePage;
import com.mohan.practice.util.DriverUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
public class PracticeSteps {

    @Autowired
    PracticePage practicePage;

    Instant start = null;
    Instant end = null;

    List<Integer> percentList = new ArrayList<>();

    @Given("I navigate to selenium easy test page")
    public void iNavigateToSeleniumEasyTestPage() {

        DriverFactory.getDriver().navigate().to("https://www.seleniumeasy.com/test");
        DriverUtil.pollAndWaitUntilAdModalAppears(10,100);
        practicePage.initializeElements();
    }

    @And("I select ajax page option under input forms menu")
    public void iSelectAjaxPageOptionUnderInputFormsMenu() {

        practicePage.openAjaxForms();

    }


    @And("I enter the name {string} and comment {string}")
    public void iEnterTheNameAndComment(String name, String comment) {
        practicePage.addDetails(name, comment);

    }

    @And("I click on submit button")
    public void iClickOnSubmitButton() {

        practicePage.submitAjaxForm();

    }

    @Then("I verify that the spinner is displayed")
    public void iVerifyThatTheSpinnerIsDisplayed() {

        Assert.assertTrue(practicePage.isLoaderDisplayed());
    }

    @And("I verify that the message {string} is displayed")
    public void iVerifyThatTheMessageIsDisplayed(String expected) {
        String actual = practicePage.getSuccessMessage(expected);
        Assert.assertEquals(actual,expected);
    }

    @And("I select jQuery date picker under date pickers")
    public void iSelectJQueryDatePickerUnderDatePickers() {

        practicePage.openJqueryDatePicker();
    }

    @And("I select the start date {string}")
    public void iSelectTheStartDate(String startDate) {
        practicePage.setStartDate(startDate);
    }

    @And("I select the end date {string}")
    public void iSelectTheEndDate(String endDate) {
        practicePage.setEndDate(endDate);
    }

    @Then("I verify that I cannot select start date after {string}")
    public void iVerifyThatICannotSelectStartDateAfter(String endDate) {

        Assert.assertFalse(practicePage.isStartSelectableAfterEnd(endDate));
    }

    @Then("I verify that I cannot select end date before {string}")
    public void iVerifyThatICannotSelectEndDateBefore(String startDate) {
        Assert.assertFalse(practicePage.isEndSelectableBeforeStart(startDate));
    }

    @And("I select bootstrap progress bar under progress bars")
    public void iSelectBootstrapProgressBarUnderProgressBars() {
        practicePage.openBootstrapProgress();
    }

    @And("I click on the download button")
    public void iClickOnTheDownloadButton() {
        start = practicePage.downloadFile();
    }

    @Then("I log progress until completion")
    public void iLogProgressUntilCompletion() {
        end = practicePage.trackUntilComplete();
    }

    @And("I log the time taken for the download")
    public void iLogTheTimeTakenForTheDownload() {
        Duration elapsed = Duration.between(start,end);
        log.info("Total time to download is "+elapsed.getSeconds()+" seconds.");

    }

    @And("I select drag and drop slider under progress bars")
    public void iSelectDragAndDropSliderUnderProgressBars() {
        practicePage.openDragAndDropSlider();
    }

    @When("I move the sliders to {int} percent")
    public void iMoveTheSlidersToPercent(int percent) {

        Map<Integer,Integer> sliderPercentMap = practicePage.moveSlidersTo(percent);

        percentList.addAll(sliderPercentMap.values());


    }

    @Then("I verify that all the sliders are at {int} percent")
    public void iVerifyThatAllTheSlidersAreAtPercent(int percent) {

        long actual = percentList.stream().filter(p->p.equals(percent)).count();
        long expected = percentList.size();

        Assert.assertEquals(actual,expected);
    }
}
