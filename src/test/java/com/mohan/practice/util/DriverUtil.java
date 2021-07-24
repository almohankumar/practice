package com.mohan.practice.util;

import com.mohan.practice.driver.DriverFactory;
import com.mohan.practice.enums.SelectBy;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
public class DriverUtil extends BaseUtil {

    public static final short DEFAULT_WAIT_TIME = 60;
    public static final short DEFAULT_POLLING_TIME = 500;
    public static final short DEFAULT_TIMEOUT = 5;


    public static void setImplicitTimeout(int timeout) {

        DriverFactory.getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }


    public static boolean isChecked(WebElement checkBox) {

        return checkBox.isSelected();

    }

    public static void waitForAngular(int waitTime, int pollingTime) {

        JavascriptExecutor executor = ((JavascriptExecutor) DriverFactory.getDriver());

        FluentWait wait = new FluentWait(DriverFactory.getDriver()).withTimeout(java.time.Duration.ofSeconds(waitTime))
                .pollingEvery(Duration.ofMillis(pollingTime)).ignoring(JavascriptException.class);

        wait.until((ExpectedCondition<Boolean>) driver -> executor
                .executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable())").toString()
                .equalsIgnoreCase("-1"));
        log.info("Angular wait completed..");
    }


    public static void clickWhenReady(WebElement webElement) {

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), DEFAULT_WAIT_TIME);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
        } catch (Exception e) {

            log.error(e.getMessage());
            Assert.fail("Element " + webElement + " not Clickable in UI");
        }

    }


    public static void waitForUrlToContain(String value) {

        try {
            new WebDriverWait(DriverFactory.getDriver(), DEFAULT_WAIT_TIME).until(ExpectedConditions.urlContains(value));
        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail(
                    "URL doesn't contain " + value + " after waiting for " + DEFAULT_WAIT_TIME + " seconds");
        }

    }


    public static void waitForElementToContainText(WebElement webElement, String value) {

        try {
            new WebDriverWait(DriverFactory.getDriver(), DEFAULT_WAIT_TIME).until(ExpectedConditions.textToBePresentInElement(webElement, value));
        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail(
                    "Element doesn't contain " + value + " after waiting for " + DEFAULT_WAIT_TIME + " seconds");
        }

    }

    public static void waitForUrlToNotContain(String value) {

        try {
            new WebDriverWait(DriverFactory.getDriver(), 60)
                    .until(ExpectedConditions.not(ExpectedConditions.urlContains(value)));
        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail("URL  contains " + value + " after waiting for" + 60 + " seconds");
        }

    }

    public static void waitForInvisibility(WebElement webElement, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeInSeconds);
        try {
            wait.until(ExpectedConditions.invisibilityOf(webElement));

        } catch (Exception e) {

            log.error(e.getMessage());
            Assert.fail("Element visible in UI");

        }
    }

    public static void waitForVisibility(WebElement webElement, int timeInSeconds) {

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeInSeconds);
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));

        } catch (Exception e) {

            log.error(e.getMessage());
            Assert.fail("Element not visible in UI even after " + timeInSeconds + " seconds!");

        }

    }

    public static void clearTextValue(WebElement element) {
        waitForVisibility(element, 10);
        element.clear();
    }

    public static void scrollElementIntoViewTop(WebElement webElement) {

        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(false);", webElement);
    }

    public static void waitForNestedElementVisibility(List<WebElement> webElements, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeInSeconds);
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(webElements));

        } catch (Exception e) {

            log.error(e.getMessage());
            Assert.fail("Element not visible in UI");

        }
    }

    public static void setValue(WebElement webElement, String value) {

        try {
            webElement.clear();
            webElement.sendKeys(value);

        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail("Unable to set value! " + value);
        }

    }

    public static void checkAndSetValue(WebElement element, String value) {

        String elementText = element.getAttribute("value");

        if (value == null) {
            if (elementText != null) {
                clearTextValue(element);
            }
        } else {
            if (elementText != null && !elementText.equalsIgnoreCase(value)) {
                clearTextValue(element);
                setValue(element, value);
            } else {
                setValue(element, value);
            }
        }

    }

    public static void selectByVisibleText(WebElement element, String value) {

        try {
            Thread.sleep(3000);
            Select selectValue = new Select((element));
            selectValue.selectByVisibleText(value);

        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail("Unable to select value!");
        }

    }

    public static void selectByIndex(WebElement element, int index) {

        try {
            Thread.sleep(3000);
            Select selectValue = new Select((element));
            selectValue.selectByIndex(index);

        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail("Unable to select value!");
        }

    }

    public static void selectByValue(WebElement element, String value) {

        try {
            Thread.sleep(3000);
            Select selectValue = new Select((element));
            selectValue.selectByValue(value);

        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail("Unable to set value!");
        }

    }

    public static void checkAndSelectValue(WebElement element, String option) {

        String optionText = element.getAttribute("value");

        if (optionText == null) {
            selectByVisibleText(element, option);
        } else {
            if (!option.equalsIgnoreCase(optionText)) {
                selectByVisibleText(element, option);
            }
        }
    }

    public static void selectFromDropDown(WebElement element, String value, SelectBy selectBy) {

        if (selectBy == SelectBy.VISIBLE_TEXT) {

            selectByVisibleText(element, value);

        } else if (selectBy == SelectBy.VALUE) {

            selectByValue(element, value);

        } else {

            selectByIndex(element, Integer.parseInt(value));

        }

    }

    public static String jsGetText(WebElement element) {
        String text = null;
        try {
            text = (String) ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(" return arguments[0].innerText;", element);
        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail("Unable to click with js");
        }
        return text;
    }

    public static void jsSetValue(WebElement element, String value) {
        try {
            String arg = "arguments[0].value='$'";
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(arg.replace("$", value), element);
        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail("Unable to set value with js");
        }
    }

    public static void jsClick(WebElement element) {
        try {
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail("Unable to click with js");
        }
    }

    public static void waitForElementToBeClickable(WebElement element) {

        try {
            Wait<WebDriver> wait = new WebDriverWait(DriverFactory.getDriver(), 60);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail("Unable to click element! " + element);
        }

    }

    public static void checkOrUncheck(WebElement checkBox, Boolean toBeChecked) {

        if (toBeChecked) {

            if (!isChecked(checkBox)) {
                checkBox.click();
            }
        } else {

            if (isChecked(checkBox)) {
                checkBox.click();
            }
        }

    }


    public static void enterTextByCharacter(WebElement element, String txt) {
        for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i);
            String s = new StringBuilder().append(c).toString();
            element.sendKeys(s);
        }
    }

    public static void enterTxtByCharacterProfilePage(WebElement element, String txt) throws InterruptedException {
        for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i);
            String s = new StringBuilder().append(c).toString();
            element.sendKeys(s);
            Thread.sleep(500);
        }
    }


    public static List<String> getOptionsFromDropDown(WebElement element) {
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        List<String> optionsList = new ArrayList<String>();

        for (WebElement option : options) {
            optionsList.add(option.getText().trim());
        }
        return optionsList;
    }

    public static void selectValueFromTypeAhead(WebElement element, String value) {

        try {
            Thread.sleep(1500);
            element.clear();
            element.sendKeys(value);
            String locator = "";
            locator = locator.replace("$", value.toUpperCase());

            Thread.sleep(2000);
            DriverFactory.getDriver().findElement(By.xpath(locator)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void selectValueFromTypeAheadDropdown(WebElement element, String value) {

        try {
            Thread.sleep(1500);
            element.clear();
            element.sendKeys(value);
            String locator = "";
            locator = locator.replace("$", value.toUpperCase());

            Thread.sleep(2000);
            DriverFactory.getDriver().findElement(By.xpath(locator)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void pollAndWaitUntilLoaderDisappears(int totalTimeInSeconds, int pollingTimeInMilliseconds) {

        if (isSpinnerVisible(10, 100)) {


            waitForAngular(totalTimeInSeconds, pollingTimeInMilliseconds);

            log.info("Waiting for Spinner to disappear..");
            try {
                FluentWait<RemoteWebDriver> wait = new FluentWait<>(DriverFactory.getDriver())
                        .withTimeout(Duration.ofSeconds(totalTimeInSeconds))
                        .pollingEvery(Duration.ofMillis(pollingTimeInMilliseconds))
                        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("")));


            } catch (TimeoutException t) {

                log.info("Spinner doesn't disappear in " + totalTimeInSeconds + " seconds..");
            }

        }

    }

    public static void pollAndWaitUntilAdModalAppears(int totalTimeInSeconds, int pollingTimeInMilliseconds) {

        if (isAdModalVisible(10, 100)) {


            DriverFactory.getDriver().findElement(By.xpath("//div[@id='at-cv-lightbox-button-holder']/a[contains(text(),'No')]")).click();


        }

    }

    private static boolean isAdModalVisible(int totalTimeInSeconds, int pollingTimeInMilliseconds) {

        boolean loaded = true;

        log.info("Waiting for Ad Modal to appear..");

        try {
            FluentWait<RemoteWebDriver> wait = new FluentWait<>(DriverFactory.getDriver())
                    .withTimeout(Duration.ofSeconds(totalTimeInSeconds))
                    .pollingEvery(Duration.ofMillis(pollingTimeInMilliseconds))
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='at-cv-lightbox-header']")));

        } catch (Exception e) {
            loaded = false;
            log.info("Ad Modal doesn't appear in " + totalTimeInSeconds + " seconds, Skipping clicking cancel..");
        }

        return loaded;

    }


    private static boolean isSpinnerVisible(int totalTimeInSeconds, int pollingTimeInMilliseconds) {

        boolean loaded = true;

        log.info("Waiting for Spinner to appear..");

        try {
            FluentWait<RemoteWebDriver> wait = new FluentWait<>(DriverFactory.getDriver())
                    .withTimeout(Duration.ofSeconds(totalTimeInSeconds))
                    .pollingEvery(Duration.ofMillis(pollingTimeInMilliseconds))
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));

        } catch (Exception e) {
            loaded = false;
            log.info("Spinner doesn't appear in " + totalTimeInSeconds + " seconds, Skipping waiting for invisibility of spinner..");
        }

        return loaded;

    }

    public static void scrollToTop() {

        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("scrollTo(0,0)");
    }

    public static void scrollToBottom() {

        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("scrollTo(0,document.body.scrollHeight)");
    }

    public static void scrollElementIntoView(WebElement webElement) {

        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView();", webElement);

    }


    public static boolean loopedClick(WebElement webElement) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                webElement.click();
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

    public static String getSelectedOption(WebElement element) {
        Select select = new Select(element);
        WebElement option = select.getFirstSelectedOption();
        return option.getText().trim();
    }
}
