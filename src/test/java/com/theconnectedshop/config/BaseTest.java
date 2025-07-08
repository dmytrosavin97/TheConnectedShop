package com.theconnectedshop.config;
 
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import java.time.Duration;
 
public abstract class BaseTest {
 
    protected static WebDriver driver;
    protected static WebDriverWait wait;
 
    @BeforeAll
    static void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
 
    @AfterAll
    static void tearDownClass() {
        if (driver != null) driver.quit();
    }
 
    @BeforeEach
    void openHomePage() {
        driver.get("https://theconnectedshop.com/");
    }
}