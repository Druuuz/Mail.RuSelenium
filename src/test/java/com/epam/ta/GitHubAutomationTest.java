package com.epam.ta;

import com.epam.ta.AssertionExtension.Verification;
import org.testng.Assert;
import org.testng.annotations.*;

import com.epam.ta.steps.Steps;

public class GitHubAutomationTest
{
	private Steps steps;
	private final String USERNAME = "test.poc";
	private final String PASSWORD = "pass666";
	private final String DOMAIN = "@bk.ru";
	private final String SUBJECT = "Theme";
	private final String MESSAGE = "Some text in message";
	private final String TARGET = "target@tartar.com";


	@BeforeClass(description = "Init browser")
	public void setUp()
	{
		steps = new Steps();
		steps.initBrowser();
	}

	@Test(priority = 1)
	public void CanLogin()
	{
		steps.login(USERNAME, PASSWORD, DOMAIN);
		Assert.assertEquals(steps.getUserName(), USERNAME + DOMAIN);
	}

	@Test(priority = 2)
	public void creatingMessageAndSaveDrafts(){
		Verification verification = new Verification();
		steps.writeMessage(SUBJECT, TARGET, MESSAGE);
		steps.saveMessageAsDrafts();
		steps.openDraftsFolder();
		verification.verifyTrue(steps.isMessageInDraftFolder(SUBJECT, TARGET, MESSAGE));
		//Assert.assertTrue(steps.isMessageInDraftFolder("subject", "target@mail.com", "SomeText!!1"));
	}

	@Test(priority = 3)
	public void sendingMessageAndMessageNotInDrafts(){
		Verification verification = new Verification();
		steps.openDraft();
		steps.sendDraft();
		steps.openDraftsFolder();
		verification.verifyTrue(steps.isMessageNotInDrafts(SUBJECT, TARGET, MESSAGE));
		//Assert.assertTrue(steps.isMessageNotInDrafts("subject", "target@mail.com", "SomeText!!1"),"33333333");
	}

	@Test(priority = 4)
	public void messageAppearInSents(){
		Verification verification = new Verification();
		steps.openSentsFolder();
		verification.verifyTrue(steps.isMessageInSentFolder(SUBJECT, TARGET, MESSAGE));
		//Assert.assertTrue(steps.isMessageInSentFolder(SUBJECT, TARGET, MESSAGE),"22222222");
	}

	@Test(priority = 5)
	public void canLogOf(){
		steps.logOut();
	}

	@AfterClass(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}


}
