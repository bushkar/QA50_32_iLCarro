package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import javax.swing.*;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "name")
//    WebElement inputFirstName;
    WebElement fieldFirstName;
    @FindBy(id = "lastName")
//    WebElement inputLastName;
    WebElement fieldLastName;
    @FindBy(id = "email")
//    WebElement inputEmail;
    WebElement fieldEmail;
    @FindBy(id = "password")
//    WebElement inputPassword;
    WebElement fieldPassword;
    //    @FindBy(xpath = "//*[@class='checkbox-container']")
    @FindBy(xpath = "//label[@for='terms-of-use']")
//    WebElement checkboxIAgree;
            WebElement checkBoxAgree;
    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;
    @FindBy(xpath = "//h2[text()='You are logged in success']")
    WebElement popUpSuccessfulRegistration;

    public void typeRegistrationForm(User user) {
//        inputFirstName.sendKeys(user.getFirstName());
        fieldFirstName.sendKeys(user.getFirstName());
//        inputLastName.sendKeys(user.getLastName());
        fieldLastName.sendKeys(user.getLastName());
//        inputEmail.sendKeys(user.getEmail());
        fieldEmail.sendKeys(user.getEmail());
//        inputPassword.sendKeys(user.getPassword());
        fieldPassword.sendKeys(user.getPassword());
//        if (!checkboxIAgree.isSelected())
//            checkboxIAgree.click();
    }

    public void clickCheckBox() {

    }

    public void clickCheckBoxWithActions() {
        int y = checkBoxAgree.getSize().getHeight();
        int x = checkBoxAgree.getSize().getWidth();
        System.out.println(x + "x" + y);

        Actions actions = new Actions(driver);
        actions.moveToElement().click().perform();
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public boolean isRegisteredDisplayed() {
        return isElementDisplayed(popUpSuccessfulRegistration);
    }
}
