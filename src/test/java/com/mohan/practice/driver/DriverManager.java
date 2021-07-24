package com.mohan.practice.driver;

import com.mohan.practice.config.CommonConfig;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;


@Component
@Log4j2
public class DriverManager {

    @Autowired
    CommonConfig commonConfig;


    public void startDriver(){

        RemoteWebDriver remoteWebDriver;

        String driverPath = commonConfig.getDriverPath();
        String fallbackDriverPath = commonConfig.getFallbackDriverPath();

        ChromeDriverService service;
        ChromeOptions options = configureOptionsAndPrefs();

        try{

            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(driverPath))
                    .usingAnyFreePort()
                    .build();

        }catch(IllegalStateException i){

            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(fallbackDriverPath))
                    .usingAnyFreePort()
                    .build();

        }

        remoteWebDriver = new ChromeDriver(service,options);
        DriverFactory.addDriver(remoteWebDriver);

    }



    private String getBrowserMode(){

        String browser;

        browser = System.getProperty("browser");

        if (browser == null) {
            browser = System.getenv("browser");
        }

        return (browser == null) ? "Headless" : "Headed";

    }


    private ChromeOptions configureOptionsAndPrefs(){


        String downloadPath = commonConfig.getDownloadPath();


        Map<String, Object> prefs = new HashMap<String, Object>();

        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("download.default_directory", System.getProperty("user.dir") + downloadPath);
        prefs.put("download.prompt_for_download",false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("traceCategories", "browser,devtools.timeline,devtools"); // comma-separated trace categories

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--window-size=1920,1080");
        options.addArguments("disable-infobars");
        options.addArguments("--ignore-certificate-errors");
        // options.addArguments("--log-level=0");
        options.setExperimentalOption("prefs", prefs);
        options.setCapability(CapabilityType.LOGGING_PREFS,getLoggingPreferences());

        String mode = getBrowserMode();

        log.info("Browser Mode: "+mode);

        if(mode.equalsIgnoreCase("Headless")){
            options.addArguments("--headless");
        }

        return options;

    }

    private LoggingPreferences getLoggingPreferences(){

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);


        return logPrefs;
    }

    public void printLog(String type) {

        List<LogEntry> entries = DriverFactory.getDriver().manage().logs().get(type).getAll();
        System.out.println(entries.size() + " " + type + " log entries found");
        for (LogEntry entry : entries) {
            System.out.println(
                    new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }

    }



}

