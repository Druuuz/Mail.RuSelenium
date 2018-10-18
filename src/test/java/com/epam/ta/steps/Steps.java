package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Steps {

    private LoginPage loginPage;
    private InboxPage inboxPage;
    private SentPage sentPage;
    private DraftsPage draftsPage;
    private WriteMessagePage writeMessagePage;
    private SpamsPage spamsPage;
    private BasketPage basketPage;
    private WebDriver driver;

    private final Logger logger = LogManager.getRootLogger();

    public void initBrowser() {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver() {
        DriverSingleton.closeDriver();
    }

    public void initPages() {
        basketPage = new BasketPage(driver);
        spamsPage = new SpamsPage(driver);
        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
        writeMessagePage = new WriteMessagePage(driver);
        draftsPage = new DraftsPage(driver);
        sentPage = new SentPage(driver);
    }

    public void login(String username, String password, String domain) {
        loginPage.openPage();
        loginPage.login(username, password, domain);
    }

    public String getUserName() {
        return inboxPage.getUserName();
    }

    public void writeMessage(String subject, String target, String message) {
        inboxPage.clickCreateMessage();
        writeMessagePage.writeMessage(subject, target, message);
    }

    public void saveMessageAsDraft() {
        writeMessagePage.saveMessageAsDraft();
    }

    public void openDraftsFolder() {
        draftsPage.openPage();
    }

    public boolean isMessageInDraftFolder(String subject, String target, String message) {
        return draftsPage.isMessageInDrafts(subject, target, message);
    }

    public void openDraft() {
        draftsPage.clickOnDraft();
    }

    public void sendDraft() {
        writeMessagePage.sendCurrent();
    }

    public void openSentsFolder() {
        sentPage.openPage();
    }

    public boolean isMessageInSentFolder(String subject, String target, String message) {
        return sentPage.isMessageInSents(subject, target, message);
    }

    public void logOut() {
        inboxPage.logOut();
    }

    public boolean isMessageNotInDrafts(String subject, String target, String message) {
        return draftsPage.isMessageNotInDrafts(subject, target, message);
    }

    public void setDraftAsSpam() {
        draftsPage.markDraftAsSpam();
    }

    public void openSpamFolder() {
        spamsPage.openPage();
    }

    public boolean isMessageInSpam(String subject, String target, String message) {
        return spamsPage.isMessageInSpam(subject, target, message);
    }

    public void moveMessageIntoBasket() {
        draftsPage.moveMessageIntoBasket();
    }

    public void openBasket() {
        basketPage.openPage();
    }

    public boolean isMessageInBasket(String subject, String target, String message) {
        return basketPage.isMessageInBasket(subject, target, message);
    }


}
