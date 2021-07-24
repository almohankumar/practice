package com.mohan.practice.steps;

import com.mohan.practice.config.CommonConfig;
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

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class PracticeSteps {

    @Autowired
    PracticePage practicePage;

    @Autowired
    CommonConfig commonConfig;

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

    @And("I select window popup modal under alerts and modals")
    public void iSelectWindowPopupModalUnderAlertsAndModals() {

        practicePage.openWindowPopupModal();
    }

    @When("I click on follow twitter and facebook button")
    public void iClickOnFollowTwitterAndFacebookButton() {
        practicePage.clickTwitterAndFacebook();
    }

    @Then("I check and print the page titles of social pages and parent page")
    public void iCheckAndPrintThePageTitlesOfSocialPagesAndParentPage() {

        String parentPage = DriverFactory.getDriver().getWindowHandle();

        Set<String> handles = DriverFactory.getDriver().getWindowHandles();

        for(String handle: handles){

            if(!handle.equals(parentPage)){
                DriverFactory.getDriver().switchTo().window(handle);
                log.info("Title: "+DriverFactory.getDriver().getTitle());
            }
        }

        DriverFactory.getDriver().switchTo().window(parentPage);
        log.info("Title: "+DriverFactory.getDriver().getTitle());
    }

    @And("I select javascript alerts under alerts and modals")
    public void iSelectJavascriptAlertsUnderAlertsAndModals() {

        practicePage.openJavascriptAlerts();

    }

    @When("I click on javascript alert box")
    public void iClickOnJavascriptAlertBox() {
        practicePage.clickJavascriptAlert();
    }

    @Then("I verify and accept the alert")
    public void iVerifyAndAcceptTheAlert() {

        String expected = "I am an alert box!";
        String actual =DriverFactory.getDriver().switchTo().alert().getText();

        Assert.assertEquals(expected,actual);

        DriverFactory.getDriver().switchTo().alert().accept();

    }

    @And("I select file download under alerts and modals")
    public void iSelectFileDownloadUnderAlertsAndModals() {
        practicePage.openFileDownload();
    }

    @And("I enter the {string} to download")
    public void iEnterTheToDownload(String text) {
        practicePage.setDownloadText(text);
    }

    @And("I click on generate file button")
    public void iClickOnGenerateFileButton() {
        practicePage.generateFile();
    }

    @When("I download the generated file")
    public void iDownloadTheGeneratedFile() {
        practicePage.downloadGeneratedFile();

        /*try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(5000);

        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }*/

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Then("I read and verify the {string} in the downloaded file")
    public void iReadAndVerifyTheInTheDownloadedFile(String text) {

        String filePath = commonConfig.getBasePath()+commonConfig.getDownloadPath()+"easyinfo.txt";
        System.out.println(filePath);

        Path path = Paths.get(filePath);
        String fileText =null;

        try {
            Stream<String> lines = Files.lines(path);
            fileText = lines.collect(Collectors.joining("\n"));
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(text,fileText);


    }
}
