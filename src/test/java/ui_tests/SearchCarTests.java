package ui_tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestNGListener;

import java.time.LocalDate;
@Listeners(TestNGListener.class)

public class SearchCarTests extends ApplicationManager {
    HomePage homePage;

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchCarPositiveTest() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2026, 3, 12);
        LocalDate endDate = LocalDate.of(2026, 3, 22);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.urlContains("results", 5));
    }

    @Test
    public void searchCarPositiveTestWithCalendar() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2026, 3, 12);
        LocalDate endDate = LocalDate.of(2026, 3, 22);
        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.urlContains("results", 5));
    }

    @Test(expectedExceptions = org.openqa.selenium.TimeoutException.class)
    public void searchCarNegativeTest_EmptyFieldCity() {
        String city = "";
        LocalDate startDate = LocalDate.of(2026, 3, 12);
        LocalDate endDate = LocalDate.of(2026, 3, 22);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        homePage.clickBtnYalla();
    }

    @Test
    public void searchCarNegativeTest_EmptyFieldCityValidateError() {
        String city = "";
        LocalDate startDate = LocalDate.of(2026, 3, 12);
        LocalDate endDate = LocalDate.of(2026, 3, 22);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        Assert.assertTrue(homePage.isTextInErrorPresent("City is required"));
    }

    @Test
//    public void searchCarNegativeTest_DateBeforeToday() {
    public void searchCarNegativeTest_StartDate_BeforeToday() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now();
//        homePage.typeSearchFormWOJS(city, startDate, endDate);
//        homePage.sendTabToInputCity();
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't pick date before today"));
    }

    @Test
//    public void searchCarNegativeTest_BookingLessDay() {
    public void searchCarNegativeTest_StartDateSameEndDate() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
//        homePage.typeSearchFormWOJS(city, startDate, endDate);
//        homePage.sendTabToInputCity();
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't book car for less than a day"));
    }

    @Test
//    public void searchCarNegativeTest_StartDateAfterEndDate() {
    public void searchCarNegativeTest_StartDate_AfterEndDate() {
        String city = "Rehovot";
//        LocalDate startDate = LocalDate.now().plusDays(1);
//        LocalDate endDate = LocalDate.now();
//        homePage.typeSearchFormWOJS(city, startDate, endDate);
//        homePage.sendTabToInputCity();
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("Second date must be after first date"));
    }

    @Test(expectedExceptions = java.time.DateTimeException.class)
    public void searchCarNegativeTest_DateNotValid() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2026, 2, 30);
        LocalDate endDate = LocalDate.of(2026, 3, 22);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
    }
}
