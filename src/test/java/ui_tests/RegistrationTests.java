package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;

import java.util.Random;
import static utils.UserFactory.*;

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
//        registrationPage.clickCheckBox();
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
//        Assert.assertTrue(registrationPage.isRegisteredDisplayed());
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationPositiveTest_WithFaker() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("You are logged in success"));
    }
}
