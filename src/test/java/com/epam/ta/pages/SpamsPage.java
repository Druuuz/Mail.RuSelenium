package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SpamsPage extends NavigationPage {
    private String BASEURL = "https://e.mail.ru/messages/spam/";
    @FindBy(xpath = "//div[@class=\"b-datalist__body\"]/div")
    private List<WebElement> listOfSpamMessages;
    public void openPage() {
        driver.navigate().to(BASEURL);
    }

    public SpamsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean isMessageInSpam(String subject, String target, String message){
        for (WebElement draft: listOfSpamMessages) {
            if (draft.getText().contains(subject+message)){
                return true;
            }
        }
        return false;
    }
}
