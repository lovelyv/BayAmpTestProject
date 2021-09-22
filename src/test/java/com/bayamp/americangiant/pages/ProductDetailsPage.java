package com.bayamp.americangiant.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {
	WebDriver driver;
	WebDriverWait wait;
	String url = "https://www.american-giant.com/products/womens-classic-cotton-v-neck-t-varsity-blue";
	By addtoCartLocator = By.id("btnAddToBagText");
	By buttonIncrementLocator = By
			.cssSelector("button.increment__btn.increment__btn--cart.increment__add.js-increment-btn");
	By salePriceLocator = By.cssSelector("span.bag-item__sale-price");
	By originalPriceLocator = By.cssSelector("span.bag-item__original-price");
	By subtotalLocator = By.id("bagSubtotal");

	public ProductDetailsPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public boolean isLoaded() {
		wait.until(ExpectedConditions.urlToBe(url));
		return true;
	}

	public void addProductToCart() {
		driver.findElement(addtoCartLocator).click();

	}

	public void incrementItem() {
		driver.findElement(buttonIncrementLocator).click();
	}

	public String getItemPrice() {
		List<WebElement> s = driver.findElements(salePriceLocator);
		if (s.size() > 0) {
			return s.get(0).getText();
		} else {
			return driver.findElement(originalPriceLocator).getText();
		}

	}

	public String getTotalPrice() {
		return driver.findElement(subtotalLocator).getText();
	}
}
