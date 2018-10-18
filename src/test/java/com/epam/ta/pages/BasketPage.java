package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasketPage extends NavigationPage {

    private String BASEURL = "https://e.mail.ru/messages/trash/";

    @FindBy(xpath = "//div[@class=\"b-datalist__body\"]/div")
    private List<WebElement> listOfTrash;

    public void openPage() {
        driver.navigate().to(BASEURL);
    }

    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean isMessageInBasket(String subject, String target, String message) {

        for (WebElement trash : listOfTrash) {
            if (trash.getText().contains(subject + message)) {
                return true;
            }
        }
        return false;
    }
}
