package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginPage extends AbstractPage {

    @FindBy(id = "mailbox:domain")
    private WebElement domainField;

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passField;

    @FindBy(xpath = "//input[@class='o-control']")
    private WebElement loginButton;


    private String BASE_URL = "https://mail.ru/";
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private void setLogin(String login){
        loginField.sendKeys(login);
    }

    private void setPass(String pass){
        passField.sendKeys(pass);
    }

    private void submitLogin(){
        click(loginButton);
    }

    public void login(String login, String pass, String domain){
        setLogin(login);
        setPass(pass);
        chooseDomain(domain);
        submitLogin();
    }

    public void chooseDomain(String domain){
        Select domains = new Select(domainField);
        domains.selectByVisibleText(domain);
    }

}
