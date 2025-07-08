package com.theconnectedshop.pages;
 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
 
 
public class ProductPage {
 
    private WebDriver driver;

    private WebDriverWait wait;
 
    private By addToCartBtn = By.cssSelector("button[data-action='add-to-cart']");

    private By cartItemTitle = By.cssSelector("h2.CartItem__Title a");
 
    public ProductPage(WebDriver driver, WebDriverWait wait) {

        this.driver = driver;

        this.wait = wait;

    }
 
    public void addToCart() {

        driver.findElement(addToCartBtn).click();

    }
 
    public WebElement getCartItem() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemTitle));

    }

}

 
