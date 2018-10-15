package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthorizedPage extends AbstractPage {
    private String BASE_URL = "https://mail.ru/";

    @FindBy(id = "PH_user-email")
    private WebElement userName;

    @FindBy(xpath = "//div[@class=\"b-toolbar__item\"]/a[1]")
    private WebElement createMessageButton;

    @FindBy(id = "PH_logoutLink")
    private WebElement logOutButton;

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public AuthorizedPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getUserName(){
        wait.until(ExpectedConditions.visibilityOf(userName));
        return userName.getText();
    }

    public void logOut(){
        click(logOutButton);
    }

    public void clickCreateMessage(){
        createMessageButton.click();
    }
}
