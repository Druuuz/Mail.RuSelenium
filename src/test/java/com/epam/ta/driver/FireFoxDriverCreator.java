package com.epam.ta.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverCreator implements WebDriverCreator {
    private static final String WEBDRIVER_GECKODRIVER = "webdriver.chrome.driver";
    private static final String GECKODRIVER_GECKODRIVER_EXE_PATH = ".\\geckodriver\\chromedriver.exe";
    public WebDriver createWebDriver() {
        System.setProperty(WEBDRIVER_GECKODRIVER, GECKODRIVER_GECKODRIVER_EXE_PATH);
        return new FirefoxDriver();
    }
}
