package com.epam.ta.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DraftsPage extends NavigationPage {

    @FindBy(xpath = "//div[@class=\"b-datalist b-datalist_letters b-datalist_letters_to\"]//div[@data-bem =\"b-datalist__item\"]")
    private List<WebElement> listOfDrafts;

    @FindBy(xpath = "//div[@class=\"b-sticky\"]//div[@data-cache-key=\"500001_undefined_false\"]//div[@data-shortcut-title=\"J\"]")
    private WebElement spamButton;

    private String BASEURL = "https://e.mail.ru/messages/drafts/";
    private int index;

    public void openPage() {
        driver.navigate().to(BASEURL);
    }

    public DraftsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void markDraftAsSpam() {
        listOfDrafts.get(index).findElement(By.xpath("//div[@class=\"js-item-checkbox b-datalist__item__cbx\"]")).click();
        Actions action = new Actions(driver);
        action.sendKeys("j").build().perform();
    }

    public boolean isMessageInDrafts(String subject, String target, String message) {
        int i = 0;
        for (WebElement draft : listOfDrafts) {
            if (draft.getText().contains(subject + message + "\n" + target)) {
                index = i;
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean isMessageNotInDrafts(String subject, String target, String message) {
        for (WebElement draft : listOfDrafts) {
            if (draft.getText().contains(subject + message + "\n" + target)) {
                try {
                    wait.until(ExpectedConditions.invisibilityOf(draft));
                } catch (Exception e) {
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    public void clickOnDraft() {
        click(listOfDrafts.get(index));
    }

    public void moveMessageIntoBasket() {
        Actions action = new Actions(driver);
        action.dragAndDrop(listOfDrafts.get(index), basketFolder).build().perform();
    }
}
