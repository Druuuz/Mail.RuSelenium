package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.pages.*;
import com.sun.xml.internal.ws.server.DefaultResourceInjector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Steps {
    private WebDriver driver;

    private final Logger logger = LogManager.getRootLogger();

    public void initBrowser() {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver() {
        DriverSingleton.closeDriver();
    }

    public void login(String username, String password, String domain) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password, domain);
    }

    public String getUserName(){
        AuthorizedPage authorizedPage = new AuthorizedPage(driver);
        return authorizedPage.getUserName();
    }

    public void writeMessage(String subject, String target, String message){
        AuthorizedPage authorizedPage = new AuthorizedPage(driver);
        authorizedPage.clickCreateMessage();
        WriteMessagePage writeMessagePage = new WriteMessagePage(driver);
        writeMessagePage.writeMessage(subject, target, message);
    }

    public boolean isDraftSaved(){
        WriteMessagePage writeMessagePage = new WriteMessagePage(driver);
        if (writeMessagePage.saveDraft().contains("Сохранено в")){
            return true;
        }
        return false;
    }
    public void openDrafts(){
        WriteMessagePage writeMessagePage = new WriteMessagePage(driver);
        writeMessagePage.openDrafts();
    }
    public boolean isMessageInDraftFolder(String subject, String target, String message){
        DraftsPage draftsPage = new DraftsPage(driver);
        return draftsPage.isMessageInDrafts(subject, target, message);
    }


    public void openDraft(){
        DraftsPage draftsPage = new DraftsPage(driver);
        draftsPage.clickOnDraft();
    }


    public void sendDraft(){
        WriteMessagePage writeMessagePage = new WriteMessagePage(driver);
        writeMessagePage.sendCurrent();

    }
    public void openSentsFoldder(){
       SentPage sentPage = new SentPage(driver);
       sentPage.openPage();
    }

    public boolean isMessageInSentFolder(String subject, String target, String message){
        SentPage sentPage = new SentPage(driver);
        return sentPage.isMessageInSents(subject, target, message);
    }

    public void logOut(){
        AuthorizedPage authorizedPage = new AuthorizedPage(driver);
        authorizedPage.logOut();
    }

    public boolean isMessageNotInDrafts(String subject, String target, String message){
        DraftsPage draftsPage = new DraftsPage(driver);
        return draftsPage.isMessageNotInDrafts(subject, target, message);
    }


}
