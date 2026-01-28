package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;

import java.util.Random;

import static utils.UserFactory.*;

public class RegistrationTests extends ApplicationManager {
    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

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

    @Test
    public void registrationNegativeTest_WrongFirstName_Empty_WrongEmail_AlreadyExists_WithFaker() {
        PopUpPage popUpPage = new PopUpPage(getDriver());

        User user = positiveUser();
        String firstName = user.getFirstName();
        user.setFirstName("");
        registrationPage.typeRegistrationForm(user);
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Name is required"),
                "validate field Name");

        registrationPage.clearRegistrationForm();
        user.setFirstName(firstName);
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        boolean registrationSuccessful = popUpPage.isTextInPopUpMessagePresent("You are logged in success");
        softAssert.assertTrue(registrationSuccessful,
                "validate registration positive");
        if (!registrationSuccessful)
            softAssert.assertAll();
        popUpPage.clickBtnOk();
        new HomePage(getDriver()).clickBtnLogOut();

        new HomePage(getDriver()).clickBtnSignUp();
        User user2 = positiveUser();
        user2.setEmail(user.getEmail());
        registrationPage.typeRegistrationForm(user2);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(popUpPage.isTextInPopUpMessagePresent("User already exists"),
                "validate field Email");
        softAssert.assertAll();
    }
}
