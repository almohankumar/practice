package com.mohan.practice.steps;

import com.mohan.practice.pages.AjaxFormPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

public class AjaxFormSteps {

    @Autowired
    AjaxFormPage ajaxFormPage;


    @And("I enter the name {string} and comment {string}")
    public void iEnterTheNameAndComment(String name, String comment) {
        ajaxFormPage.addDetails(name, comment);

    }

    @And("I click on submit button")
    public void iClickOnSubmitButton() {

        ajaxFormPage.submitAjaxForm();

    }

    @Then("I verify that the spinner is displayed")
    public void iVerifyThatTheSpinnerIsDisplayed() {

        Assert.assertTrue(ajaxFormPage.isLoaderDisplayed());
    }

    @And("I verify that the message {string} is displayed")
    public void iVerifyThatTheMessageIsDisplayed(String expected) {
        String actual = ajaxFormPage.getSuccessMessage(expected);
        Assert.assertEquals(actual,expected);
    }

}
