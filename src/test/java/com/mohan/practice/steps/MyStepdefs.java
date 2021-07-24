package com.mohan.practice.steps;

import com.mohan.practice.driver.DriverFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class MyStepdefs {


    @Given("I navigate to google page")
    public void iNavigateToGooglePage() {
        DriverFactory.getDriver().navigate().to("https://www.google.com");
    }


    @And("I search for {string}")
    public void iSearchFor(String arg0) throws InterruptedException {
        Thread.sleep(5000);
    }
}
