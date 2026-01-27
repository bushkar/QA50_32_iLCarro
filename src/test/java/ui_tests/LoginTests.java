package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;

public class LoginTests extends ApplicationManager {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void loginPositiveTest() {
        User user = User.builder()
                .email("qa32@gmail.com")
                .password("Password$123")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
//        Assert.assertTrue(loginPage.isLoggedInDisplayed());
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("Logged in success"));
    }

    @Test
    public void loginNegativeTest_WrongPassword_WOSpecSymbol() {
        User user = User.builder()
                .email("qa32@gmail.com")
                .password("Password123")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_WrongEmail_Empty() {
        User user = User.builder()
                .email("qa32gmail.com")
                .password("")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
//        Assert.assertTrue(loginPage.isTextInErrorPresent("It'snot look like email"));
        softAssert.assertTrue(loginPage.isTextInErrorPresent("It'snot look like email"), "validate field email");
        System.out.println("wrong text!");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"), "validate field password");
        System.out.println("right text!");
        softAssert.assertAll();
    }
}
