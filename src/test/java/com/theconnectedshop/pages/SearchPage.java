package com.theconnectedshop.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.List;

public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private By searchToggle = By.cssSelector("a[data-action='toggle-search']");
    private By searchInput = By.name("q");

    // Селектор всех товаров после поиска
    private By productLinks = By.cssSelector("a[href^='/products/']");

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Вводим текст и запускаем поиск
    public void searchFor(String query) {
        driver.findElement(searchToggle).click();
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.sendKeys(query);
        input.sendKeys(Keys.ENTER);
    }

        // Получаем все найденные товары
        public List<WebElement> getResults() {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productLinks));
        }
}