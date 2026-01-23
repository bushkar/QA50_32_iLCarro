package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;

import java.util.Random;

public class RegistrationTests extends ApplicationManager {
    RegistrationPage registrationPage;

    @BeforeMethod
    public void goToRegistrationPage() {
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        User user = User.builder()
                .firstName("FirstName")
                .lastName("LastName")
//                .email("qa" + new Random().nextInt(1000) + "@gmail.com")
                .email("qa" + i + "@gmail.com")
                .password("Password$123")
                .build();
//        HomePage homePage = new HomePage(getDriver());
//        homePage.clickBtnRegistration();
//        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBox();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.isRegisteredDisplayed());
    }
}
