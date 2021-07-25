package com.mohan.practice.pages;

import com.mohan.practice.driver.DriverFactory;
import com.mohan.practice.util.DriverUtil;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Scope("cucumber-glue")
@Log4j2
public class WindowModalPage {

    public WindowModalPage() {
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

    @FindBy(xpath ="//a[contains(text(),'Follow Twitter & Facebook')]" )
    private WebElement btnFollowFacebookAndTwitter;

    public void clickTwitterAndFacebook(){
        DriverUtil.clickWhenReady(btnFollowFacebookAndTwitter);
    }

    public void switchAndPrintTitles(){

        String parentPage = DriverFactory.getDriver().getWindowHandle();

        Set<String> handles = DriverFactory.getDriver().getWindowHandles();

        for(String handle: handles){

            if(!handle.equals(parentPage)){
                DriverFactory.getDriver().switchTo().window(handle);
                log.info("Title: "+DriverFactory.getDriver().getTitle());
            }
        }

        DriverFactory.getDriver().switchTo().window(parentPage);
        log.info("Title: "+DriverFactory.getDriver().getTitle());
    }


}
