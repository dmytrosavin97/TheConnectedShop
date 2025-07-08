package com.theconnectedshop.pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
 
public class SearchResultsPage {
 
    private WebDriver driver;
    private WebDriverWait wait;
 
    private By productLink = By.xpath("//h2[contains(@class, 'ProductItem__Title')]//a[starts-with(@href, '/products/smart-door-lock-slim') and contains(text(),'Smart Door Lock Slim')]");
 
    public SearchResultsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
 
    public WebElement getProductLink() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productLink));
    }
 
    public void clickOnProduct() {
        getProductLink().click();
    }
}