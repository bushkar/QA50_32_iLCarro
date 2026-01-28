package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[@ng-reflect-router-link='login']")
    WebElement btnLogin;
    //    @FindBy(xpath = "//a[@ng-reflect-router-link='registration']")
//    WebElement btnRegistration;
    @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement btnSignUp;
    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    WebElement btnLogOut;

    public void clickBtnLogin() {
        btnLogin.click();
    }

//    public void clickBtnRegistration() {
//        btnRegistration.click();
//    }

    public void clickBtnSignUp() {
        btnSignUp.click();
    }

    public void clickBtnLogOut() {
        btnLogOut.click();
    }
}
