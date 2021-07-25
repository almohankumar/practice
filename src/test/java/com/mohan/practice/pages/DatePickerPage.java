package com.mohan.practice.pages;

import com.mohan.practice.driver.DriverFactory;
import com.mohan.practice.util.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Scope("cucumber-glue")
public class DatePickerPage {

    public DatePickerPage(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

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
}
