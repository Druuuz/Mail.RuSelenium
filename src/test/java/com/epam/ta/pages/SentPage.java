package com.epam.ta.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SentPage extends NavigationPage {
    private String BASEURL = "https://e.mail.ru/messages/sent/";

    @FindBy(xpath = "//div[@data-cache-key = \"500000_undefined_false\"]//div[@class=\"b-datalist b-datalist_letters b-datalist_letters_to\"]//div[@data-bem =\"b-datalist__item\"]")
    private List<WebElement> listOfSentMessages;

    public void openPage() {
        driver.navigate().to(BASEURL);
    }

    public SentPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

        public boolean isMessageInSents(String subject, String target, String message){
            for (WebElement draft: listOfSentMessages) {
                if (draft.getText().contains(subject+message+"\n"+target)){
                    return true;
                }
            }
            return false;
        }

}
