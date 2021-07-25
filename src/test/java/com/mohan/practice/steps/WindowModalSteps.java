package com.mohan.practice.steps;

import com.mohan.practice.pages.WindowModalPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class WindowModalSteps {

    @Autowired
    WindowModalPage windowModalPage;

    @When("I click on follow twitter and facebook button")
    public void iClickOnFollowTwitterAndFacebookButton() {
        windowModalPage.clickTwitterAndFacebook();
    }

    @Then("I check and print the page titles of social pages and parent page")
    public void iCheckAndPrintThePageTitlesOfSocialPagesAndParentPage() {

        windowModalPage.switchAndPrintTitles();
    }
}
