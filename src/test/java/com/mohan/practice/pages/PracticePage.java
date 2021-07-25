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
@Log4j2
@Scope("cucumber-glue")
public class PracticePage {

    public PracticePage(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }


    @FindBy(xpath ="//ul[@class='nav navbar-nav']/li/a[contains(text(),'Input')]" )
    private WebElement inputForms;

    @FindBy(xpath ="//ul[@class='nav navbar-nav']/li/a[contains(text(),'Input')]/following-sibling::ul/li/a[contains(text(),'Ajax Form Submit')]" )
    private WebElement ajaxForm;

    @FindBy(xpath ="//ul[@class='nav navbar-nav']/li/a[contains(text(),'Date pickers')]" )
    private WebElement datePickers;

    @FindBy(xpath ="//ul[@class='nav navbar-nav']/li/a[contains(text(),'Date pickers')]/following-sibling::ul/li/a[contains(text(),'JQuery Date Picker')]" )
    private WebElement jQueryDatePicker;

    @FindBy(xpath ="//ul[contains(@class,'navbar-right')]/li/a[contains(text(),'Progress')]" )
    private WebElement progressBars;

    @FindBy(xpath ="//ul[contains(@class,'navbar-right')]/li/a[contains(text(),'Progress')]/following-sibling::ul/li/a[contains(text(),'Bootstrap')]" )
    private WebElement bootstrapProgressBar;

    @FindBy(xpath ="//ul[contains(@class,'navbar-right')]/li/a[contains(text(),'Progress')]/following-sibling::ul/li/a[contains(text(),'Sliders')]" )
    private WebElement sliders;

    @FindBy(xpath ="//ul[contains(@class,'navbar-right')]/li/a[contains(text(),'Alerts')]" )
    private WebElement alertsAndModals;

    @FindBy(xpath ="//ul[contains(@class,'navbar-right')]/li/a[contains(text(),'Alerts')]/following-sibling::ul/li/a[contains(text(),'Window')]" )
    private WebElement windowPopupModal;

    @FindBy(xpath ="//ul[contains(@class,'navbar-right')]/li/a[contains(text(),'Alerts')]/following-sibling::ul/li/a[contains(text(),'Javascript')]" )
    private WebElement javascriptAlerts;

    @FindBy(xpath ="//ul[contains(@class,'navbar-right')]/li/a[contains(text(),'Alerts')]/following-sibling::ul/li/a[contains(text(),'Download')]" )
    private WebElement fileDownload;



    public void openAjaxForms(){

        DriverUtil.clickWhenReady(inputForms);
        DriverUtil.clickWhenReady(ajaxForm);
    }

    public void openJqueryDatePicker(){

        DriverUtil.clickWhenReady(datePickers);
        DriverUtil.clickWhenReady(jQueryDatePicker);
    }

    public void openBootstrapProgress(){

        DriverUtil.clickWhenReady(progressBars);
        DriverUtil.clickWhenReady(bootstrapProgressBar);
    }

    public void openDragAndDropSlider(){

        DriverUtil.clickWhenReady(progressBars);
        DriverUtil.clickWhenReady(sliders);
    }

    public void openWindowPopupModal(){

        DriverUtil.clickWhenReady(alertsAndModals);
        DriverUtil.clickWhenReady(windowPopupModal);
    }

    public void openJavascriptAlerts(){

        DriverUtil.clickWhenReady(alertsAndModals);
        DriverUtil.clickWhenReady(javascriptAlerts);
    }

    public void openFileDownload(){

        DriverUtil.clickWhenReady(alertsAndModals);
        DriverUtil.clickWhenReady(fileDownload);
    }

}
