package com.mohan.practice.pages;

import com.mohan.practice.driver.DriverFactory;
import com.mohan.practice.util.DriverUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class FileDownloadPage {

    public FileDownloadPage() {
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

    @FindBy(id ="textbox" )
    private WebElement textForDownload;

    @FindBy(id ="create" )
    private WebElement btnGenerateFile;

    @FindBy(id ="link-to-download" )
    private WebElement btnDownloadFile;


    public void setDownloadText(String text){
        DriverUtil.setValue(textForDownload,text);
    }

    public void generateFile(){
        DriverUtil.clickWhenReady(btnGenerateFile);
    }

    public void downloadGeneratedFile(){
        DriverUtil.waitForVisibility(btnDownloadFile,10);
        DriverUtil.clickWhenReady(btnDownloadFile);
    }
}
