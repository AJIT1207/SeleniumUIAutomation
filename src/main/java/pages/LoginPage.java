package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By txtUsername = By.xpath("//input[@placeholder='Username']");
    private By txtPassword = By.xpath("//input[@placeholder='Password']");
    private By btnLogin = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUsername(String user) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(txtUsername));
        usernameInput.clear();
        usernameInput.sendKeys(user);
    }

    public void enterPassword(String pass) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(txtPassword));
        passwordInput.clear();
        passwordInput.sendKeys(pass);
    }

    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        loginBtn.click();
    }
}