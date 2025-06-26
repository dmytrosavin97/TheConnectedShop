package com.theconnectedshop;
 
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import java.time.Duration;
 
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class AddToCartTest {
 
    private static WebDriver driver;
    private static WebDriverWait wait;
 
    /* ===== Локаторы вынесены в константы ===== */
    private static final By LOGO              = By.cssSelector("img[alt='The Connected Shop Logo White']");
    private static final By SEARCH_TOGGLE     = By.cssSelector("a[data-action='toggle-search']");
    private static final By SEARCH_INPUT      = By.name("q");
    private static final By PRODUCT_LINK      = By.xpath("//a[@href='/products/smart-door-lock-slim' and text()='Smart Door Lock Slim']");
    private static final By ADD_TO_CART_BTN   = By.cssSelector("button[data-action='add-to-cart']");
    private static final By CART_ITEM_TITLE   = By.cssSelector("h2.CartItem__Title a");
 
    /* ===== Инициализация/завершение сессии ===== */
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
 
    /* ===== Открываем главную страницу перед каждым тестом ===== */
    @BeforeEach
    void openHomePage() {
        driver.get("https://theconnectedshop.com/");
    }
 
    /* ===== Тесты ===== */
 
    @Test
    @DisplayName("01. Проверяем title и URL главной страницы")
    void checkHomeTitleAndUrl() {
        Assertions.assertEquals(
                "The Connected Shop - Smart Locks, Smart Sensors, Smart Home & Office",
                driver.getTitle(),
                "Заголовок страницы некорректен");
 
        Assertions.assertEquals(
                "https://theconnectedshop.com/",
                driver.getCurrentUrl(),
                "URL страницы некорректен");
    }
 
    @Test
    @DisplayName("02. Логотип в шапке отображается")
    void checkHeaderLogo() {
        Assertions.assertTrue(driver.findElement(LOGO).isDisplayed(), "Логотип не виден");
    }
 
    @Test
    @DisplayName("03. Поиск товара «smart door lock» работает")
    void searchSmartDoorLock() {
        driver.findElement(SEARCH_TOGGLE).click();
 
        WebElement input = driver.findElement(SEARCH_INPUT);
        input.sendKeys("smart door lock");
        input.submit();
 
        Assertions.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LINK)).isDisplayed(),
                "Результаты поиска не появились");
    }
 
    
 
    @Test
    @DisplayName("05. Добавление товара в корзину и проверка в сайдбаре")
    void addProductToCartAndVerify() {
        driver.findElement(PRODUCT_LINK).click();
        driver.findElement(ADD_TO_CART_BTN).click();
 
        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(CART_ITEM_TITLE));
 
        Assertions.assertEquals(
                "smart door lock slim",
                cartItem.getText().toLowerCase(),
                "Название товара в корзине некорректно");
 
        Assertions.assertTrue(
                cartItem.getAttribute("href").contains("/products/smart-door-lock-slim"),
                "href товара в корзине некорректен");
        void checkProductCard() {
        WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LINK));
 
        Assertions.assertEquals(
                "smart door lock slim",
                productLink.getText().toLowerCase(),
                "Название товара отличается");
 
        productLink.click();
 
        Assertions.assertTrue(
                driver.getCurrentUrl().contains("/products/smart-door-lock-slim"),
                "Переход по ссылке некорректен");
    }
}