package ui_tests;

import manager.AppManager;
import org.testng.annotations.Test;
import pages.HomePage;

import static java.sql.DriverManager.getDriver;

public class LoginTests extends AppManager {

    @Test
    public void loginPositiveTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
    }

}
