package com.mohan.practice.pages;

import com.mohan.practice.driver.DriverFactory;
import com.mohan.practice.util.DriverUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("cucumber-glue")
public class AlertsPage {

    public AlertsPage() {
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

    @FindBys({@FindBy(xpath = "//div[contains(text(),'Alert Box')]/following::div/button")})
    private List<WebElement> javascriptSubmitButtons;

    public void clickJavascriptAlert(){
        DriverUtil.clickWhenReady(javascriptSubmitButtons.get(0));
    }

    public String getAlertText(){

        return DriverFactory.getDriver().switchTo().alert().getText();
    }

    public void acceptAlert(){

        DriverFactory.getDriver().switchTo().alert().accept();
    }
}
