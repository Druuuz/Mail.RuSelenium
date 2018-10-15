package com.epam.ta.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vitali_Shulha on 20-Oct-16.
 */
public class DriverSingleton {

    private static WebDriver driver;
    private static final Logger logger = LogManager.getRootLogger();
    private static final String WEBDRIVER_CHROMEDRIVER = "webdriver.gecko.driver";
    private static final String CHROMEDRIVER_CHROMEDRIVER_EXE_PATH = ".\\geckodriver\\chromedriver.exe";

    private DriverSingleton(){
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    };


    public static WebDriver getDriver(){
        if (null == driver){
            System.setProperty(WEBDRIVER_CHROMEDRIVER, CHROMEDRIVER_CHROMEDRIVER_EXE_PATH);
            driver = new FirefoxDriver();
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            logger.info("Browser started");
        }

        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
