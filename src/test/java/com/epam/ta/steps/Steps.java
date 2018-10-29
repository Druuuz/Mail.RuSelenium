package com.epam.ta.steps;

import com.epam.ta.bo.Message;
import com.epam.ta.bo.User;
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

    public void login(User user) {
        loginPage.openPage();
        loginPage.login(user.getUsername(), user.getPassword(), user.getDomain());
    }

    public String getUserName() {
        return inboxPage.getUserName();
    }

    public void writeMessage(Message message) {
        inboxPage.clickCreateMessage();
        writeMessagePage.writeMessage(message.getSubject(), message.getTarget(), message.getBody());
    }

    public void saveMessageAsDraft() {
        writeMessagePage.saveMessageAsDraft();
    }

    public void openDraftsFolder() {
        draftsPage.openPage();
    }

    public boolean isMessageInDraftFolder(Message message) {
        return draftsPage.isMessageInDrafts(message.getSubject(), message.getTarget(), message.getBody());
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

    public boolean isMessageInSentFolder(Message message) {
        return sentPage.isMessageInSents(message.getSubject(), message.getTarget(), message.getBody());
    }

    public void logOut() {
        inboxPage.logOut();
    }

    public boolean isMessageNotInDrafts(Message message) {
        return draftsPage.isMessageNotInDrafts(message.getSubject(), message.getTarget(), message.getBody());
    }

    public void setDraftAsSpam() {
        draftsPage.markDraftAsSpam();
    }

    public void openSpamFolder() {
        spamsPage.openPage();
    }

    public boolean isMessageInSpam(Message message) {
        return spamsPage.isMessageInSpam(message.getSubject(), message.getTarget(), message.getBody());
    }

    public void moveMessageIntoBasket() {
        draftsPage.moveMessageIntoBasket();
    }

    public void openBasket() {
        basketPage.openPage();
    }

    public boolean isMessageInBasket(Message message) {
        return basketPage.isMessageInBasket(message.getSubject(), message.getTarget(), message.getBody());
    }


}
