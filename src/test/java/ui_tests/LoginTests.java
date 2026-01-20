package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static java.sql.DriverManager.getDriver;

public class LoginTests extends ApplicationManager {

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
        Assert.assertTrue(loginPage.isLoggedInDisplayed());
    }

}
