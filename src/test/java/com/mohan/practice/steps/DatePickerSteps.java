package com.mohan.practice.steps;

import com.mohan.practice.pages.DatePickerPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

public class DatePickerSteps {

    @Autowired
    DatePickerPage datePickerPage;

    @And("I select the start date {string}")
    public void iSelectTheStartDate(String startDate) {
        datePickerPage.setStartDate(startDate);
    }

    @And("I select the end date {string}")
    public void iSelectTheEndDate(String endDate) {
        datePickerPage.setEndDate(endDate);
    }

    @Then("I verify that I cannot select start date after {string}")
    public void iVerifyThatICannotSelectStartDateAfter(String endDate) {

        Assert.assertFalse(datePickerPage.isStartSelectableAfterEnd(endDate));
    }

    @Then("I verify that I cannot select end date before {string}")
    public void iVerifyThatICannotSelectEndDateBefore(String startDate) {
        Assert.assertFalse(datePickerPage.isEndSelectableBeforeStart(startDate));
    }
}
