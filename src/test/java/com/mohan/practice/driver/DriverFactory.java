package com.mohan.practice.driver;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

public final class DriverFactory {

    private static ThreadLocal<RemoteWebDriver> remoteWebDriver = new ThreadLocal<>();

    private static List<RemoteWebDriver> storedDrivers = new ArrayList<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            storedDrivers.stream().forEach(RemoteWebDriver::quit);
            System.out.println("Drivers quit successfully..");
        }));
    }

    private DriverFactory() {

    }

    public static RemoteWebDriver getDriver() {
        return remoteWebDriver.get();
    }

    public static void addDriver(RemoteWebDriver remoteWebDriver) {
        storedDrivers.add(remoteWebDriver);
        DriverFactory.remoteWebDriver.set(remoteWebDriver);
    }

    public static void removeDriver() {
        storedDrivers.remove(remoteWebDriver.get());
        remoteWebDriver.remove();
    }
}
