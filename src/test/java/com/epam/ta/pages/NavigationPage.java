package com.epam.ta.pages;

import com.epam.ta.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class NavigationPage extends AbstractPage {

    public void openPage() {
    }

    public NavigationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id = "PH_user-email")
    private WebElement userName;

    @FindBy(xpath = "//div[@class=\"b-toolbar__item\"]/a[1]")
    private WebElement createMessageButton;

    @FindBy(id = "PH_logoutLink")
    private WebElement logOutButton;

    @FindBy(xpath = "//a[@data-mnemo=\"drafts\"]")
    private WebElement drafts;

    @FindBy(xpath = "//a[@data-shortcut=\"g,s\"]")
    private WebElement sentsFolder;

    @FindBy(xpath = "//div[@id=\"b-nav_folders\"]//a[@href=\"/messages/trash/\"]/..")
    protected WebElement basketFolder;


    public void clickCreateMessage(){
        createMessageButton.click();
    }

    public String getUserName(){
        wait.until(ExpectedConditions.visibilityOf(userName));
        return userName.getText();
    }

    public void logOut(){
        click(logOutButton);
    }

    public void openDrafts(){
        click(drafts);
    }

    public void openSents(){
        click(sentsFolder);
    }

}
