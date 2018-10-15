package com.epam.ta.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;

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



    public String getTarget(){
        return filledFieldTarget.getAttribute("value");
    }

    public String getSubject(){
        return driver.findElement(By.xpath("//*[@id=\"themeInner\"]/script[12]")).getText();
    }

    public  String getMessageText(){
        driver.switchTo().frame(0);
        String messsage = messageField.getText();
        driver.switchTo().defaultContent();
        return messsage;
    }


     public boolean isDataOfMessageCorrect(String subject, String target, String message){
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ПРОВЕРКА НА САБДЖ
        if (getTarget().equals(target) && getMessageText().equals(message)){
            return true;
        }
        return false;


     }

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

    public String saveDraft(){
        click(saveDropDownList);
        wait.until(ExpectedConditions.visibilityOf(saveDraftButton));
        click(saveDraftButton);
        wait.until(ExpectedConditions.visibilityOf(saveStatus));
        return saveStatus.getText();
    }

    public void openDrafts(){
        click(drafts);
    }




    public void sendCurrent(){


            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-name=\"send\"]")));
            //wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@data-name=\"send\"]"),2));
            click(sendButton);



            //wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//a[contains(text(),\"письмо\")]"),1));

        /*
        try{
            Thread.sleep(2000);
        } catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        }

*/

    }

}
