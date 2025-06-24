package com.theconnectedshop;
 
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.*;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.*;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
 
import java.time.Duration;
 
//@TestMethodOrder(MethodOrderer.DisplayName.class)          

public class AddToCartTest {
 
    private static WebDriver driver;

    private static WebDriverWait wait;                   
 
 
 
    @BeforeAll

    static void setUpClass() {

        WebDriverManager.chromedriver().setup();           

        driver = new ChromeDriver();                       // запускаем Chrome

        driver.manage().window().maximize();               // во весь экран

        driver.manage().timeouts()

              .implicitlyWait(Duration.ofSeconds(10));    

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

        WebElement logo = driver.findElement(

                By.cssSelector("img[alt='The Connected Shop Logo White']"));

        Assertions.assertTrue(logo.isDisplayed(), "Логотип не виден");

    }
 
    @Test

    @DisplayName("03. Карточка \"Smart Door Lock\" кликабельна")

    void checkProductCard() {

        WebElement productLink = driver.findElement(

                By.cssSelector("h2.ProductItem__Title a"));

        Assertions.assertEquals("Smart Door Lock",

                productLink.getText(),

                "Название товара отличается");
 
        productLink.click();   // если не падает — ссылка рабочая

        Assertions.assertTrue(driver.getCurrentUrl()

                .contains("/products/smart-door-lock"),

                "Переход по ссылке некорректен");

    }
 
    @Test

    @DisplayName("04. Добавление товара в корзину и проверка в сайдбаре")

    void addProductToCartAndVerify() {

        // 1) Открываем карточку товара

        WebElement productLink = driver.findElement(

                By.cssSelector("h2.ProductItem__Title a"));

        productLink.click();
 
        // 2) Нажимаем Add to cart

        WebElement addToCartBtn = driver.findElement(

                By.cssSelector("button[data-action='add-to-cart']"));

        addToCartBtn.click();
 
        // 3) Ждём появления сайдбара корзины и нашего товара

        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(

                By.cssSelector("h2.CartItem__Title a")));
 
        // 4) Проверяем, что в корзине тот самый товар

        Assertions.assertEquals("Smart Door Lock",

                cartItem.getText(),

                "Название товара в корзине некорректно");
 
        Assertions.assertTrue(cartItem.getAttribute("href")

                .contains("/products/smart-door-lock"),

                "href товара в корзине некорректен");

    }

}

 