package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertiesReader;
import utils.enums.FooterMenuItem;

import java.time.Duration;
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

    public void sendTabToInputCity() {
        inputCity.sendKeys(Keys.TAB);
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

    private String createMonth(String month) {
        StringBuilder res = new StringBuilder();
        return res.append(month.substring(0, 1).toUpperCase())
                .append(month.substring(1).toLowerCase()).toString();
    }

    public void typeCalendar(LocalDate date) {
        btnYearCalendar.click();
        String year = Integer.toString(date.getYear());
//        StringBuilder month = new StringBuilder(date.getMonth().toString());
//        month.replace(1, month.length(), month.substring(1).toLowerCase());
//        String day = Integer.toString(date.getDayOfMonth());
        WebElement btnYear = driver.findElement(By.
                xpath("//td[@aria-label='" + year + "']"));
        btnYear.click();
        String month = createMonth(date.getMonth().toString());
        WebElement btnMonth = driver.findElement(By.
                xpath("//td[@aria-label='" + month + " " + year + "']"));
        btnMonth.click();
        String day = String.valueOf(date.getDayOfMonth());
        WebElement btnDay = driver.findElement(By.
                xpath("//td[@aria-label='" + month + " " + day + ", " + year + "']"));
        btnDay.click();
    }

    public void typeSearchFormWithCalendar(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.click();
        typeCalendar(startDate);
        typeCalendar(endDate);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\").removeAttribute(\"disabled\")");
    }

    public boolean clickIconFooter(FooterMenuItem item, String title) {
        driver.findElement(By.xpath(item.getLocator())).click();
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.titleContains(title));
    }
}
