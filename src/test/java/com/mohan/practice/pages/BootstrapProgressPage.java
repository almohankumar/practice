package com.mohan.practice.pages;

import com.mohan.practice.driver.DriverFactory;
import com.mohan.practice.util.DriverUtil;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Scope("cucumber-glue")
@Log4j2
public class BootstrapProgressPage {

    public BootstrapProgressPage() {
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

    @FindBy(id ="cricle-btn" )
    private WebElement btnDownload;


    public Instant downloadFile(){

        DriverUtil.clickWhenReady(btnDownload);
        return Instant.now();
    }

    public Instant trackUntilComplete(){

        boolean complete = false;

        String completed = "0%";

        while(!complete){

            String actual = DriverFactory.getDriver().findElement(By.xpath("//div[@class='prog-circle']/div[@class='percenttext']")).getText();

            if(!actual.equalsIgnoreCase(completed)){
                log.info(actual+ "completed");
                completed = actual;
            }

            if(actual.equalsIgnoreCase("100%")){
                complete = true;
            }
        }
        return Instant.now();
    }
}
