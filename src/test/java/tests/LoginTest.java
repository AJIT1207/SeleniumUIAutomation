package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.HomePage;
import org.testng.annotations.Test;
import utils.RetryAnalyzer;

public class LoginTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyLogin() {
        LoginPage login = new LoginPage(driver);
        HomePage home = new HomePage(driver);
        // wait for page to load
        login.enterUsername("Admin");
        login.enterPassword("admin123");
        login.clickLogin();

    }


}
