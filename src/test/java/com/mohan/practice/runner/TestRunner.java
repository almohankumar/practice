package com.mohan.practice.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/", glue = "com/mohan/practice/", tags = {"@booking"},  plugin = {
        "pretty", "html:target/cucumber-html-reports", "json:target/Json_Reports/cucumber.json" }, dryRun = false, monochrome = true)

public class TestRunner extends AbstractTestNGCucumberTests {
/*
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }*/
}

