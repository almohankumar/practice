package com.mohan.practice.steps;

import com.mohan.practice.pages.AlertsPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

public class AlertsSteps {

    @Autowired
    AlertsPage alertsPage;

    @When("I click on javascript alert box")
    public void iClickOnJavascriptAlertBox() {
        alertsPage.clickJavascriptAlert();
    }

    @Then("I verify and accept the alert")
    public void iVerifyAndAcceptTheAlert() {

        String expected = "I am an alert box!";
        String actual = alertsPage.getAlertText();

        Assert.assertEquals(expected,actual);

        alertsPage.acceptAlert();

    }
}
