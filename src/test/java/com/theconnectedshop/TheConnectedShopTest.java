package com.theconnectedshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TheConnectedShopTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
       
        WebDriverManager.chromedriver().setup();
        
        driver = new ChromeDriver();
    }

    @Test
    public void testOpenTheConnectedShop() {
       
        driver.get("https://theconnectedshop.com");

       //Провверка Title//
        String expectedTitle = "The Connected Shop - Smart Locks, Smart Sensors, Smart Home & Office";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle, "Заголовок страницы не совпадает с ожидаемым");
        
        //Провверка URL//

        String expectedURL = "https://theconnectedshop.com/";
        String actualURL = driver.getCurrentUrl();
        assertEquals(expectedURL, actualURL, "URL страницы не совпадает с ожидаемым");

        //Провверка Logo//
        //Пррописываем локатор. Локатор берем из DevTools//

        WebElement logo = driver.findElement(By.xpath("//img[@alt='The Connected Shop Logo White']"));

        if(logo.isDisplayed()) {

         System.out.println("Логотип отображается на странице.");
        } else {
            System.out.println("Логотип найден, но не отображается.");
        
        }

        //Проверка что логотип имеет атрибуты//

        String src = logo.getAttribute("src");
            String alt = logo.getAttribute("alt");
            String width = logo.getAttribute("width");
            String height = logo.getAttribute("height");
 
            // Вывод значений.
            System.out.println("SRC: " + src);
            System.out.println("ALT: " + alt);
            System.out.println("WIDTH: " + width);
            System.out.println("HEIGHT: " + height);
 
            // Проверка условий
            if (src != null && !src.isEmpty()
                && alt.equals("The Connected Shop Logo White")
                && width.equals("250")
                && height.equals("75px")) {
                System.out.println("Все атрибуты соответствуют ожиданиям.");
            } else {
                System.out.println("Один или несколько атрибутов некорректны.");
            }

            WebElement logoPrimary = driver.findElement(By.xpath("//img[@alt='The Connected Shop Logo']"));
            if(logo.isDisplayed()) {
                System.out.println("Логотип отображается на странице.");
            } else {
                System.out.println("Логотп найден, но не отображается");

                //Проверка атрибутов Primary

                String srcPrimary = logoPrimary.getAttribute("src");
            String altPrimary = logoPrimary.getAttribute("alt");
            String widthPrimary = logoPrimary.getAttribute("widthy");
            String heightPrimary = logoPrimary.getAttribute("height");

            // Вывод значений Primary
            System.out.println("SRC: " + srcPrimary);
            System.out.println("ALT: " + altPrimary);
            System.out.println("WIDTH: " + widthPrimary);
            System.out.println("HEIGHT: " + heightPrimary);

            // Проверка условий Primary
            if (src != null && !src.isEmpty()
                && altPrimary.equals("The Connected Shop Logo Primary")
                && widthPrimary.equals("250")
                && heightPrimary.equals("75px")) {
                System.out.println("Все атрибуты соответствуют ожиданиям.");
            } else {
                System.out.println("Один или несколько атрибутов некорректны.");
            }


            }
            WebElement account = driver.findElement(By.xpath("(//a[text()='Account'])[1]"));
            if(account.isDisplayed()) {
                System.out.println("Аккаунт отображается на странице.");
            } else {
                System.out.println("Аккаунт найден, но не отображается");

                //Проверка атрибутов Account

                String href = account.getAttribute("href");

                // Вывод значений Account
            System.out.println("Href: " + href);

            // Проверка условий Acoount
            if (href != null && !href.isEmpty()

            && href.equals("/account")){

                System.out.println("Все атрибуты соответствуют ожиданиям.");
                
                } else {
                System.out.println("Один или несколько атрибутов некорректны.");
                }}
            
            //Search
            WebElement search = driver.findElement(By.xpath("//*[@id=\"section-header\"]/div/div[3]/nav/ul/li[2]/a"));
            if(search.isDisplayed()) {
                System.out.println("Search отображается на странице.");
            } else {
                System.out.println("Search найден, но не отображается");

                //Проверка атрибутов Search

                String href = search.getAttribute("href");
                String dataAction = search.getAttribute("data-action");

                // Вывод значений Search
            System.out.println("Href: " + href);
            System.out.println("Data-Action: " + dataAction);

            // Проверка условий Search
            if (href != null && !href.isEmpty()

             && href.equals("/search")
             && dataAction.equals("toggle-search")){

                System.out.println("Все атрибуты соответствуют ожиданиям.");
                
                } else {
                System.out.println("Один или несколько атрибутов некорректны.");
                }}
            
            //Cart

             WebElement cart = driver.findElement(By.xpath("//*[@id=\"section-header\"]/div/div[3]/nav/ul/li[3]/a"));
            if(cart.isDisplayed()) {
                System.out.println("Search отображается на странице.");
            } else {
                System.out.println("Search найден, но не отображается");

                //Проверка атрибутов Cart

                String href = cart.getAttribute("href");
                String dataAction = cart.getAttribute("data-action");
                String dataDrawerId = cart.getAttribute("data-drawer-id");
                String ariaLabel = cart.getAttribute("aria-label");

                // Вывод значений Cart
            System.out.println("Href: " + href);
            System.out.println("Data-Action: " + dataAction);
            System.out.println("Data-Drawer-Id: " + dataDrawerId);
            System.out.println("Aria-Label: " + ariaLabel);

            // Проверка условий Cart
            if (href != null && !href.isEmpty()

             && href.equals("/cart")
             && dataAction.equals("open-drawer")
             && dataDrawerId.equals("siddebar-cart")
             && ariaLabel.equals("Open cart")){

                System.out.println("Все атрибуты соответствуют ожиданиям.");
                
                } else {
                System.out.println("Один или несколько атрибутов некорректны.");
                }}



        

    }

    @AfterEach
    public void tearDown() {
        // Закрытие браузера после теста
        if (driver != null) {
            driver.quit();
        }
    }
}