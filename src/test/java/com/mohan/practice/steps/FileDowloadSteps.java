package com.mohan.practice.steps;

import com.mohan.practice.config.CommonConfig;
import com.mohan.practice.pages.FileDownloadPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDowloadSteps {

    @Autowired
    FileDownloadPage fileDownloadPage;
    @Autowired
    CommonConfig commonConfig;

    @And("I enter the {string} to download")
    public void iEnterTheToDownload(String text) {
        fileDownloadPage.setDownloadText(text);
    }

    @And("I click on generate file button")
    public void iClickOnGenerateFileButton() {
        fileDownloadPage.generateFile();
    }

    @When("I download the generated file")
    public void iDownloadTheGeneratedFile() {
        fileDownloadPage.downloadGeneratedFile();

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
