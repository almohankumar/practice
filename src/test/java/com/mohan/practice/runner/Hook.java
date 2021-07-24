package com.mohan.practice.runner;

import com.mohan.practice.config.AppConfig;
import com.mohan.practice.config.TestConfig;
import com.mohan.practice.service.RestAssuredService;
import com.mohan.practice.service.RestAssuredFactory;
import com.mohan.practice.driver.DriverFactory;
import com.mohan.practice.driver.DriverManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.logging.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestConfig.class)
@Log4j2
public class Hook  {

    @Autowired
    DriverManager driverManager;

    @Autowired
    RestAssuredService restAssuredService;

    @Autowired
    AppConfig appConfig;




    @Before(value = "@api")
    public void configureBaseUri() {

        log.info("In '@Before' hook");
        log.info("Configuring Base URI: " + appConfig.getServiceUrl());
        restAssuredService.configureBaseUri(appConfig.getServiceUrl());

    }

    @Before(value = "not @api")
    public void setUp() {

        log.info("In '@Before' hook");
        if (DriverFactory.getDriver() == null) {
            driverManager.startDriver();
        }else{
            DriverFactory.getDriver().manage().deleteAllCookies();
            log.info("Clearing cookies..");
        }

    }

    @After(order=3,value = "not @api")
    public void tearDown(Scenario scenario) {

        log.info("In '@After' hook");

        if (scenario.isFailed()) {
            log.error("Scenario Failed - Attempting Screenshot");
            try {
                scenario.write("Current Page URL is " + DriverFactory.getDriver().getCurrentUrl());
                byte[] screenshot = DriverFactory.getDriver().getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
                log.info("Screenshot embedded in Failed Scenario");
                driverManager.printLog(LogType.BROWSER);
              //  driverManager.printLog(LogType.PERFORMANCE);
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }

        }

    }


    @After(value = "@api")
    public void clearSpecification(){

        if(RestAssuredFactory.getSpecification()!=null){
            RestAssuredFactory.removeSpecification();
            log.info("Rest specification removed..");
        }

    }




}
