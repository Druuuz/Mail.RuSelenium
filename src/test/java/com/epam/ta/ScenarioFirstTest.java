package com.epam.ta;

import com.epam.ta.AssertionExtension.Verification;
import org.testng.Assert;
import org.testng.annotations.*;

import com.epam.ta.steps.Steps;

public class ScenarioFirstTest {
    private Steps steps;
    private final String USERNAME = "test.poc";
    private final String PASSWORD = "pass666";
    private final String DOMAIN = "@bk.ru";
    private final String SUBJECT = "Theme";
    private final String MESSAGE = "Some text in message";
    private final String TARGET = "target@tartar.com";
    private Verification verification = new Verification();


    @BeforeClass(description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.initBrowser();
        steps.initPages();
    }

    @Test(priority = 1)
    public void CanLogin() {
        steps.login(USERNAME, PASSWORD, DOMAIN);
        Assert.assertEquals(steps.getUserName(), USERNAME + DOMAIN);
    }

    @Test(priority = 2)
    public void createDraft() {
        steps.writeMessage(SUBJECT, TARGET, MESSAGE);
        steps.saveMessageAsDraft();
        steps.openDraftsFolder();
        verification.verifyTrue(steps.isMessageInDraftFolder(SUBJECT, TARGET, MESSAGE));
    }

    @Test(priority = 3)
    public void sendDraft() {
        steps.openDraft();
        steps.sendDraft();
        steps.openDraftsFolder();
        verification.verifyTrue(steps.isMessageNotInDrafts(SUBJECT, TARGET, MESSAGE));
    }

    @Test(priority = 4)
    public void checkSendedFolder() {
        steps.openSentsFolder();
        verification.verifyTrue(steps.isMessageInSentFolder(SUBJECT, TARGET, MESSAGE));
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
