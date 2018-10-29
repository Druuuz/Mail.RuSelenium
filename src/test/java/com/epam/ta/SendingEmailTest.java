package com.epam.ta;

import com.epam.ta.AssertionExtension.Verification;
import com.epam.ta.bo.Message;
import com.epam.ta.bo.User;
import org.testng.Assert;
import org.testng.annotations.*;

import com.epam.ta.steps.Steps;

public class SendingEmailTest {
    private Steps steps;
    private final String USERNAME = "test.poc";
    private final String PASSWORD = "pass666";
    private final String DOMAIN = "@bk.ru";
    private final String SUBJECT = "Theme";
    private final String MESSAGE = "Some text in message";
    private final String TARGET = "target@tartar.com";
    private Verification verification = new Verification();

    private User user = new User("test.poc", "pass666", "@bk.ru");
    private Message message = new Message("target@tartar.com", "Theme", "Some text in message");


    @BeforeClass(description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.initBrowser();
        steps.initPages();
    }

    @Test(priority = 1)
    public void CanLogin() {
        steps.login(user);
        Assert.assertEquals(steps.getUserName(), user.getUsername() + user.getDomain());
    }

    @Test(priority = 2)
    public void createDraft() {
        steps.writeMessage(message);
        steps.saveMessageAsDraft();
        steps.openDraftsFolder();
        verification.verifyTrue(steps.isMessageInDraftFolder(message));
    }

    @Test(priority = 3)
    public void sendDraft() {
        steps.openDraft();
        steps.sendDraft();
        steps.openDraftsFolder();
        verification.verifyTrue(steps.isMessageNotInDrafts(message));
    }

    @Test(priority = 4)
    public void checkSendedFolder() {
        steps.openSentsFolder();
        verification.verifyTrue(steps.isMessageInSentFolder(message));
    }

    @Test(priority = 5)
    public void canLogOf() {
        steps.logOut();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        steps.closeDriver();
    }


}
