package com.epam.ta;

import org.testng.Assert;
import org.testng.annotations.*;

import com.epam.ta.steps.Steps;

public class GitHubAutomationTest
{
	private Steps steps;
	private final String USERNAME = "test.poc";
	private final String PASSWORD = "pass666";
	private final String DOMAIN = "@bk.ru";


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
		steps.writeMessage("subject", "target@mail.com", "SomeText!!1");
		Assert.assertTrue(steps.isDraftSaved());
		steps.openDrafts();
		Assert.assertTrue(steps.isMessageInDraftFolder("subject", "target@mail.com", "SomeText!!1"));
	}

	@Test(priority = 3)
	public void sendingMessageAndMessageNotInDrafts(){
		steps.openDraft();
		steps.sendDraft();
		steps.openDrafts();
		Assert.assertTrue(steps.isMessageNotInDrafts("subject", "target@mail.com", "SomeText!!1"),"33333333");
	}

	@Test(priority = 4)
	public void messageAppearInSents(){
		steps.openSentsFoldder();
		Assert.assertTrue(steps.isMessageInSentFolder("subject", "target@mail.com", "SomeText!!1"),"22222222");
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
