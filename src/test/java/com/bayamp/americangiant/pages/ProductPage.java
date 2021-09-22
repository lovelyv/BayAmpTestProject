package com.bayamp.americangiant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	WebDriver driver;
	WebDriverWait wait;
	By productLocator = By.xpath("//div[@class=\"collection__grid-item\"] //p[contains(text(),'classic cotton v-neck t')]/parent::div //span[contains(text(),\"Varsity Blue\")] /parent :: div");
	
	ProductDetailsPage detailPage;
	public ProductPage(WebDriver driver,WebDriverWait wait)
	{
		this.driver = driver;
		this.wait = wait;
	}
	
	public boolean isLoaded()
	{
		try {
			wait.until(ExpectedConditions.urlToBe("https://www.american-giant.com/collections/womens-tops-1"));
			return true;
			
		} catch (Exception e) {
			return false;
		}		
	}
	
	public ProductDetailsPage clickProduct() throws Exception
	{
		try {
			WebElement product = driver.findElement(productLocator);
			product.click();
			detailPage = new ProductDetailsPage(driver,wait);
			return detailPage;
			
		} catch (Exception e) {
			throw new Exception(e.getMessage()) ;
		}
		
	}
}
