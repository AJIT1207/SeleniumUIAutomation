package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional String browserFromXml) {
        String browser = browserFromXml != null ? browserFromXml : ConfigReader.get("browser");
        String url = ConfigReader.get("url");

        log.info("Starting test on browser: {}", browser);

        switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            default -> throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        String implicitWaitStr = ConfigReader.get("implicitWait");
        long implicitWait = implicitWaitStr != null ? Long.parseLong(implicitWaitStr) : 5;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        log.info("Navigating to URL: {}", url);
        driver.get(url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            log.info("Closing browser");
            driver.quit();
        }
    }

    public String takeScreenshot(String testName) {
        try {
            if (driver == null) return null;
            var src = ((org.openqa.selenium.TakesScreenshot) driver)
                    .getScreenshotAs(org.openqa.selenium.OutputType.FILE);
            String destDir = "test-output/screenshots";
            java.nio.file.Files.createDirectories(java.nio.file.Path.of(destDir));
            String destPath = destDir + "/" + testName + ".png";
            java.nio.file.Files.copy(src.toPath(), java.nio.file.Path.of(destPath),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            return destPath;
        } catch (Exception e) {
            log.error("Failed to capture screenshot", e);
            return null;
        }
    }
}
