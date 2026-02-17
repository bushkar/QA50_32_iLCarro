package pages;

import dto.Search;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.PropertiesReader;

import java.time.LocalDate;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
//        driver.get("https://ilcarro.web.app/search");
        driver.get(PropertiesReader.getProperty("base.properties", "baseUrl"));
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
    @FindBy(id = "city")
//    WebElement fieldCity;
    WebElement inputCity;
    @FindBy(id = "dates")
//    WebElement fieldDates;
    WebElement inputDates;
    //    @FindBy(css = "button[type='submit']")
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnYearCalendar;

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

    //    public void typeSearchForm(Search search) {
//        fieldCity.sendKeys(search.getCity());
//        fieldDates.sendKeys(search.getDates());
//        btnYalla.click();
//    }
    public void typeSearchForm(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        String dates = startDate.getMonthValue() + "/"
                + startDate.getDayOfMonth() + "/"
                + startDate.getYear() + " - "
                + endDate.getMonthValue() + "/"
                + endDate.getDayOfMonth() + "/"
                + endDate.getYear();
        inputDates.sendKeys(dates);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\").removeAttribute(\"disabled\")");
//        btnYalla.click();
//        clickWait(btnYalla, 3);
    }

    public void clickBtnYalla() {
        clickWait(btnYalla, 3);
    }

    public void typeSearchFormWOJS(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        String dates = startDate.getMonthValue() + "/"
                + startDate.getDayOfMonth() + "/"
                + startDate.getYear() + " - "
                + endDate.getMonthValue() + "/"
                + endDate.getDayOfMonth() + "/"
                + endDate.getYear();
        inputDates.sendKeys(dates);
//        clickWait(btnYalla, 3);
    }

    public void typeCalendar(LocalDate date) {
        btnYearCalendar.click();
        String year = Integer.toString(date.getYear());
        WebElement btnYear = driver.findElement(By.
                xpath("//td[@aria-label='" + year + "']"));
        btnYear.click();
    }

    public void typeSearchFormWithCalendar(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.click();
        typeCalendar(startDate);
    }
}
