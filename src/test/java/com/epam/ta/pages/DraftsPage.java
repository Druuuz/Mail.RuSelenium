package com.epam.ta.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class DraftsPage extends AbstractPage {
    private String BASEURL = "https://e.mail.ru/messages/drafts/";


    @FindBy(xpath = "//*[@id=\"b-letters\"]/div[1]/div[5]/div/div[2]/div")
    private List<WebElement> listOfDrafts;

    @FindBy(xpath = "//a[@data-shortcut=\"g,s\"]")
    private WebElement sentsFolder;

    private int index;

    public void openPage() {
        driver.navigate().to(BASEURL);
    }

    public DraftsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }



    public boolean isMessageInDrafts(String subject, String target, String message){
        int i = 0;
        for (WebElement draft: listOfDrafts) {
            if (draft.getText().contains(subject+message+"\n"+target)){
                index = i;
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean isMessageNotInDrafts(String subject, String target, String message){
        for (WebElement draft: listOfDrafts) {
            if (draft.getText().contains(subject+message+"\n"+target)){
                try{
                    wait.until(ExpectedConditions.invisibilityOf(draft));
                }
                catch (Exception e){
                    return false;
                }
                return true;
            }
        }
        return true;
    }


    public void clickOnDraft(){
        click(listOfDrafts.get(index));
    }

    public void openSentsFolder(){
        click(sentsFolder);
    }
}
