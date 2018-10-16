package com.epam.ta.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WriteMessagePage extends AbstractPage {

    private String BASEURL = "https://e.mail.ru/compose/";

    @FindBy(xpath = "//textarea[@data-original-name = \"To\"]")
    private WebElement targetField;

    @FindBy(xpath = "//input[@name=\"Subject\"]")
    private WebElement subjectField;

    @FindBy(id = "tinymce")
    private WebElement messageField;

    @FindBy(xpath = "//div[@data-group=\"save-more\"]")
    private WebElement saveDropDownList;

    @FindBy(xpath = "//a[@data-text=\"Сохранить черновик\"]")
    private WebElement saveDraftButton;

    @FindBy(xpath = "//div[@data-mnemo=\"saveStatus\"]")
    private WebElement saveStatus;

    @FindBy(xpath = "//a[@data-mnemo=\"drafts\"]")
    private WebElement drafts;

    @FindBy(xpath = "//div[@data-name=\"send\"]")
    private WebElement sendButton;

    @FindBy(css = "#compose_to")
    private WebElement filledFieldTarget;


    public void openPage() {
        driver.navigate().to(BASEURL);
    }
    public WriteMessagePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void writeMessage(String subject, String target, String message){
        targetField.sendKeys(target);
        subjectField.sendKeys(subject);
        driver.switchTo().frame(0);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].textContent= arguments[1];", messageField,message);
        driver.switchTo().defaultContent();
    }

    public void saveMessageAsDraft(){
        click(saveDropDownList);
        wait.until(ExpectedConditions.visibilityOf(saveDraftButton));
        click(saveDraftButton);
        wait.until(ExpectedConditions.visibilityOf(saveStatus));
    }

    public void openDrafts(){
        click(drafts);
    }

    public void sendCurrent(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-name=\"send\"]")));
        //wait.until(ExpectedConditions.elementToBeClickable(sendButton));
        click(sendButton);
    }

}
