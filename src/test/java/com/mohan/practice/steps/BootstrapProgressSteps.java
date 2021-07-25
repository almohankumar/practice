package com.mohan.practice.steps;

import com.mohan.practice.pages.BootstrapProgressPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.Instant;

@Log4j2
public class BootstrapProgressSteps {

    @Autowired
    BootstrapProgressPage bootstrapProgressPage;

    Instant start = null;
    Instant end = null;

    @And("I click on the download button")
    public void iClickOnTheDownloadButton() {
        start = bootstrapProgressPage.downloadFile();
    }

    @Then("I log progress until completion")
    public void iLogProgressUntilCompletion() {
        end = bootstrapProgressPage.trackUntilComplete();
    }

    @And("I log the time taken for the download")
    public void iLogTheTimeTakenForTheDownload() {
        Duration elapsed = Duration.between(start,end);
        log.info("Total time to download is "+elapsed.getSeconds()+" seconds.");

    }

}
