package com.epam.ta.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ChromeDriverCreator implements WebDriverCreator{
    private static final String WEBDRIVER_CHROMEDRIVER = "webdriver.chrome.driver";
    private static final String CHROMEDRIVER_CHROMEDRIVER_EXE_PATH = ".\\geckodriver\\chromedriver.exe";
    public WebDriver createWebDriver() {
        System.setProperty(WEBDRIVER_CHROMEDRIVER, CHROMEDRIVER_CHROMEDRIVER_EXE_PATH);
        return new ChromeDriver();
    }
}
