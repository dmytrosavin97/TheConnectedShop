package com.theconnectedshop.pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
public class HomePage {
 
    private WebDriver driver;
 
    private By logo = By.cssSelector("img[alt='The Connected Shop Logo White']");
    private By searchToggle = By.cssSelector("a[data-action='toggle-search']");
    private By searchInput = By.name("q");
 
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
 
    public boolean isLogoDisplayed() {
        return driver.findElement(logo).isDisplayed();
    }
 
    public void openSearch() {
        driver.findElement(searchToggle).click();
    }
 
    public void searchFor(String query) {
        WebElement input = driver.findElement(searchInput);
        input.sendKeys(query);
        input.submit();
    }
}