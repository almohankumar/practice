package com.mohan.practice.steps;

import com.mohan.practice.driver.DriverFactory;
import com.mohan.practice.pages.PracticePage;
import com.mohan.practice.util.DriverUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
public class PracticeSteps {

    @Autowired
    PracticePage practicePage;



    @Given("I navigate to selenium easy test page")
    public void iNavigateToSeleniumEasyTestPage() {

        DriverFactory.getDriver().navigate().to("https://www.seleniumeasy.com/test");
        DriverUtil.pollAndWaitUntilAdModalAppears(10,100);

    }

    @And("I select ajax page option under input forms menu")
    public void iSelectAjaxPageOptionUnderInputFormsMenu() {

        practicePage.openAjaxForms();

    }


    @And("I select jQuery date picker under date pickers")
    public void iSelectJQueryDatePickerUnderDatePickers() {

        practicePage.openJqueryDatePicker();
    }



    @And("I select bootstrap progress bar under progress bars")
    public void iSelectBootstrapProgressBarUnderProgressBars() {
        practicePage.openBootstrapProgress();
    }



    @And("I select drag and drop slider under progress bars")
    public void iSelectDragAndDropSliderUnderProgressBars() {
        practicePage.openDragAndDropSlider();
    }



    @And("I select window popup modal under alerts and modals")
    public void iSelectWindowPopupModalUnderAlertsAndModals() {

        practicePage.openWindowPopupModal();
    }



    @And("I select javascript alerts under alerts and modals")
    public void iSelectJavascriptAlertsUnderAlertsAndModals() {

        practicePage.openJavascriptAlerts();

    }


    @And("I select file download under alerts and modals")
    public void iSelectFileDownloadUnderAlertsAndModals() {
        practicePage.openFileDownload();
    }


}
