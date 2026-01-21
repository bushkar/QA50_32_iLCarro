package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;

import java.util.Random;

public class RegistrationTests extends ApplicationManager {

    @Test
    public void registrationPositiveTest() {
        User user = User.builder()
                .firstName("FirstName")
                .lastName("LastName")
                .email("qa" + new Random().nextInt(1000) + "@gmail.com")
                .password("Password$123")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnRegistration();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.isRegisteredDisplayed());
    }
}
