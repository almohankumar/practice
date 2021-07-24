package com.mohan.practice.pages;



import com.mohan.practice.driver.DriverFactory;
import com.mohan.practice.util.DriverUtil;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Log4j2
public class PracticePage {


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

    @FindBy(xpath ="//ul[contains(@class,'navbar-right')]/li/a[contains(text(),'Alerts')]/following-sibling::ul/li/a[contains(text(),'Download')]" )
    private WebElement fileDownload;

    @FindBy(xpath ="//a[contains(text(),'Follow Twitter & Facebook')]" )
    private WebElement btnFollowFacebookAndTwitter;

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

    @FindBy(id ="from" )
    private WebElement txtFromDate;

    @FindBy(id ="to" )
    private WebElement txtToDate;

    @FindBy(xpath ="//div[@id='ui-datepicker-div']/div/a[@title='Prev']" )
    private WebElement previous;

    @FindBy(xpath ="//div[@id='ui-datepicker-div']/div/a[@title='Next']" )
    private WebElement next;

    @FindBy(xpath ="//div[@id='ui-datepicker-div']//select" )
    private WebElement monthSelect;

    @FindBy(xpath ="//div[@id='ui-datepicker-div']//span[@class='ui-datepicker-year']" )
    private WebElement lblYear;

    @FindBy(id ="cricle-btn" )
    private WebElement btnDownload;

    @FindBy(id ="slider1" )
    private WebElement slider1;

    @FindBy(id ="slider2" )
    private WebElement slider2;

    @FindBy(id ="slider3" )
    private WebElement slider3;

    @FindBy(id ="slider4" )
    private WebElement slider4;

    @FindBy(id ="slider5" )
    private WebElement slider5;

    @FindBy(id ="slider6" )
    private WebElement slider6;





    public void initializeElements(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

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

    public void addDetails(String name, String comment){

        DriverUtil.waitForVisibility(txtName,5);

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

    public void setStartDate(String date){

        DriverUtil.clickWhenReady(txtFromDate);
        setDate(date);

    }

    public void setEndDate(String date){
        DriverUtil.clickWhenReady(txtToDate);
        setDate(date);
    }

    private void setDate(String date){

        List<String> dateParts = Arrays.asList(date.split("-"));
        String dayPart = dateParts.get(0);
        String monthPart = dateParts.get(1);
        String yearPart = dateParts.get(2);

        selectMonthAndYear(monthPart,yearPart);
        selectDay(dayPart);

    }

    public boolean isStartSelectableAfterEnd(String date){

        boolean selectable = false;

        DriverUtil.clickWhenReady(txtFromDate);

        List<String> dateParts = Arrays.asList(date.split("-"));
        String dayPart = dateParts.get(0);
        String monthPart = dateParts.get(1);
        String yearPart = dateParts.get(2);

        selectMonthAndYear(monthPart,yearPart);

        int dateValue = Integer.parseInt(dayPart);

        List<WebElement> selectableDates = DriverFactory.getDriver().findElements(By.xpath("//div[@id='ui-datepicker-div']/table/tbody/tr/td/a"));

        String nextState = next.getAttribute("class");

        if(!nextState.contains("disabled")){
            selectable = true;
        }


        if(selectableDates.size()>dateValue){
            selectable = true;
        }

        return selectable;


    }

    public boolean isEndSelectableBeforeStart(String date){

        boolean selectable = false;

        DriverUtil.clickWhenReady(txtToDate);

        List<String> dateParts = Arrays.asList(date.split("-"));
        String dayPart = dateParts.get(0);
        String monthPart = dateParts.get(1);
        String yearPart = dateParts.get(2);

        selectMonthAndYear(monthPart,yearPart);

        int dateValue = Integer.parseInt(dayPart);

        List<WebElement> selectableDates = DriverFactory.getDriver().findElements(By.xpath("//div[@id='ui-datepicker-div']/table/tbody/tr/td/a"));

        String previousState = previous.getAttribute("class");

        if(!previousState.contains("disabled")){
            selectable = true;
        }

        String firstSelectable = selectableDates.get(0).getText();
        int firstValue = Integer.parseInt(firstSelectable);


        if(firstValue != dateValue){
            selectable = true;
        }

        return selectable;


    }

    private void selectMonthAndYear(String month, String year){

        boolean found = false;

        while(!found){

            if(month.equalsIgnoreCase(getSelectedOption(monthSelect))&& year.equalsIgnoreCase(lblYear.getText())){
                found = true;
            }else{
                int expectedYear = Integer.parseInt(year);
                int actualYear = Integer.parseInt(lblYear.getText());
                if(actualYear>expectedYear){
                    DriverUtil.clickWhenReady(previous);
                }else if(actualYear<expectedYear){
                    DriverUtil.clickWhenReady(next);
                }else{
                    DriverUtil.selectByVisibleText(monthSelect,month);
                }
            }

        }

    }

    private void selectDay(String day){

        List<WebElement> selectableDates = DriverFactory.getDriver().findElements(By.xpath("//div[@id='ui-datepicker-div']/table/tbody/tr/td/a"));

        for(WebElement element:selectableDates){

            if(element.getText().equalsIgnoreCase(day)){
                element.click();
                break;
            }

        }


    }


    private String getSelectedOption(WebElement element){
        Select select = new Select(element);
        WebElement selected = select.getFirstSelectedOption();
        return selected.getText();
    }


    public Map<Integer,Integer> moveSlidersTo(int percent){

        Map<Integer,Integer> sliderPercentMap = new HashMap<>();

        for(int i=1;i<7;i++){

            WebElement slider = DriverFactory.getDriver().findElement(By.id("slider"+i));
            WebElement sliderBar = slider.findElement(By.xpath("div/input"));

            Dimension sliderSize = sliderBar.getSize();
            int sliderWidth = sliderSize.getWidth();

            int pixelsToMove = getPixelsToMove(sliderWidth,percent,100,0);

            Actions builder = new Actions(DriverFactory.getDriver());

            builder.clickAndHold(sliderBar).moveByOffset((-(int)sliderWidth / 2),0)
                    .moveByOffset(pixelsToMove, 0).release().perform();


            String sliderOutput = slider.findElement(By.xpath("div/output")).getText();

            int sliderOutputPercent = Integer.parseInt(sliderOutput);

            sliderPercentMap.put(i,sliderOutputPercent);

        }

        return sliderPercentMap;
    }


    public static int getPixelsToMove(int sliderWidth, int Amount, int SliderMax, int SliderMin)
    {
        int pixels = 0;
        int tempPixels = sliderWidth;
        tempPixels = tempPixels / (SliderMax - SliderMin);
        tempPixels = tempPixels * (Amount - SliderMin);
        pixels = tempPixels;
        return pixels;
    }

}
