package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;
import utils.enums.FooterMenuItem;
import utils.enums.HeaderMenuItem;

import static utils.PropertiesReader.getProperty;

public class NavigationTests extends ApplicationManager {

    @Test
    public void iconFacebookNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver())
                .clickIconFooter(FooterMenuItem.ICON_FACEBOOK, "Facebook"));
    }

    @Test
    public void iconTelegramNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver())
                .clickLink(FooterMenuItem.ICON_TELEGRAM, "https://telegram.org/"));
    }

    @Test
    public void iconVkNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver())
                .clickLink(FooterMenuItem.ICON_VK, "https://vk.com/"));
    }

    @Test
    public void iconInstagramNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver())
                .clickLink(FooterMenuItem.ICON_INSTAGRAM, "https://www.instagram.com/"));
    }

    @Test
    public void iconSlackNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver())
                .clickLink(FooterMenuItem.ICON_SLACK, "https://slack.com/"));
    }

    @Test
    public void btnSearchNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver())
                .clickLink(HeaderMenuItem.SEARCH, "https://ilcarro.web.app/search"));
    }

    @Test
    public void btnLetTheCarWorkNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver())
                .clickLink(HeaderMenuItem.LET_THE_CAR_WORK, "https://ilcarro.web.app/let-car-work"));
    }

    @Test
    public void btnTermsOfUseNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver())
                .clickLink(HeaderMenuItem.TERMS_OF_USE, "https://ilcarro.web.app/terms-of-use"));
    }

    @Test
    public void btnSignUpNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver())
                .clickLink(HeaderMenuItem.SIGN_UP, "https://ilcarro.web.app/registration"));
    }

    @Test
    public void btnLogInNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver())
                .clickLink(HeaderMenuItem.LOGIN, "https://ilcarro.web.app/login"));
    }

    @Test
    public void btnLogoutNavigationTest() {
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickButtonHeader(HeaderMenuItem.LOGIN);
        User user = User.builder()
                .email(getProperty("base.properties", "login"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        loginPage.clickBtnOk();
        Assert.assertTrue(new HomePage(getDriver())
                .clickLink(HeaderMenuItem.LOGOUT, "https://ilcarro.web.app/search"));
    }

    @Test
    public void btnDeleteAccountNavigationTest() {
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage.clickButtonHeader(HeaderMenuItem.LOGIN);
        User user = User.builder()
                .email(getProperty("base.properties", "login"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        loginPage.clickBtnOk();
        getDriver().findElement(By.xpath(HeaderMenuItem.DELETE_ACCOUNT.getLocator())).click();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Are you sure that you want delete account? You cant undo this action."));
    }
}
