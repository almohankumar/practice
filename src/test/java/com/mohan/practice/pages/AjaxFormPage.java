package com.mohan.practice.pages;

import com.mohan.practice.driver.DriverFactory;
import com.mohan.practice.util.DriverUtil;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
@Log4j2
public class AjaxFormPage {

    public AjaxFormPage(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

    @FindBy(id ="title" )
    private WebElement txtName;

    @FindBy(id ="description" )
    private WebElement txtComment;

    @FindBy(id ="btn-submit" )
    private WebElement btnSubmit;

    @FindBy(xpath ="//div[@id='submit-control']/img" )
    private WebElement imgLoader;

    @FindBy(id ="submit-control" )
    private WebElement submitText;


    public void addDetails(String name, String comment){

        DriverUtil.waitForUrlToContain("ajax-form-submit-demo");
        DriverUtil.waitForVisibility(txtName,15);

        DriverUtil.setValue(txtName,name);
        DriverUtil.setValue(txtComment,comment);
    }

    public void submitAjaxForm(){

        DriverUtil.clickWhenReady(btnSubmit);
    }

    public boolean isLoaderDisplayed(){

        return imgLoader.isDisplayed();
    }

    public String getSuccessMessage(String expected){

        DriverUtil.waitForElementToContainText(submitText,expected);

        return submitText.getText();
    }

}
