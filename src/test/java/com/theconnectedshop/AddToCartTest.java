package com.theconnectedshop;
 
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.*;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.*;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
 
import java.time.Duration;
 
@TestMethodOrder(MethodOrderer.DisplayName.class)          

public class AddToCartTest {
 
    private static WebDriver driver;

    private static WebDriverWait wait;                   
 
 
 
    @BeforeAll

    static void setUpClass() {

        WebDriverManager.chromedriver().setup();           

        driver = new ChromeDriver();                       // запускаем Chrome

        driver.manage().window().maximize();               // во весь экран

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));    

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://theconnectedshop.com/"); // Открываем сайт один раз

    }
 
    @AfterAll

    static void tearDownClass() {

        if (driver != null) driver.quit();

    }
 
        @BeforeAll

    static void openHomePage() {

        driver.get("https://theconnectedshop.com/");

    }
 
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

        WebElement logo = driver.findElement(By.cssSelector("img[alt='The Connected Shop Logo White']"));

        Assertions.assertTrue(logo.isDisplayed(), "Логотип не виден");

    }

    

@Test

    @DisplayName("03.Search check")

    void searchClick() {
WebElement searchClick = driver.findElement(By.cssSelector("a[data-action='toggle-search']"));

searchClick.click();  
}

    void checkSearch() {
WebElement searchInput = driver.findElement(By.name("q"));
searchInput.sendKeys("smart door lock ");
searchInput.submit();


    }
 
    @Test

    @DisplayName("03. Карточка \"Smart Door Lock Slim\" кликабельна")

    void checkProductCard() {

        WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/products/smart-door-lock-slim' and text()='Smart Door Lock Slim']")));
        Assertions.assertEquals("smart door lock slim",
        
               productLink.getText().toLowerCase(),
        
              "Название товара отличается");
 
        productLink.click();   // если не падает — ссылка рабочая

        Assertions.assertTrue(driver.getCurrentUrl()

                .contains("/products/smart-door-lock-slim"),

                "Переход по ссылке некорректен");

    }
 
    @Test

    @DisplayName("04. Добавление товара в корзину и проверка в сайдбаре")

    void addProductToCartAndVerify() {

        // 1) Открываем карточку товара

        WebElement productLink = driver.findElement(By.xpath("//a[@href='/products/smart-door-lock-slim' and text()='Smart Door Lock Slim']"));

        productLink.click();
 
        // 2) Нажимаем Add to cart

        WebElement addToCartBtn = driver.findElement(

                By.cssSelector("button[data-action='add-to-cart']"));

        addToCartBtn.click();
 
        // 3) Ждём появления сайдбара корзины и нашего товара

        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(

                By.cssSelector("h2.CartItem__Title a")));
 
        // 4) Проверяем, что в корзине тот самый товар

        Assertions.assertEquals("smart door lock slim",
         cartItem.getText().toLowerCase(), "Название товара в корзине некорректно");
 
        Assertions.assertTrue(cartItem.getAttribute("href")

                .contains("/products/smart-door-lock-slim"),

                "href товара в корзине некорректен");

    }

}

 