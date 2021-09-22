package com.bayamp.americangiant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver ;
	WebDriverWait wait;
	By womenButtonLocator = By.xpath("//button[text()=\"Women\"]");
	By subMenuLocator = By.xpath("//li/a/span[text()=\"T-Shirts & Tanks\"]");
	By mainLocator = By.tagName("main");
	By popupLocatorButton = By.xpath("//div[@data-testid=\"POPUP\"]//button/*[1]");
	
	ProductPage pPage;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
	}
	
	public ProductPage menuClick()
	{
		womensApparelButtonClick();
		pPage = clickSubMenu();
		return pPage;
	}
	
	private void womensApparelButtonClick()
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(popupLocatorButton)).click();		
		Actions action = new Actions(driver);
		WebElement btnWomen = driver.findElement(womenButtonLocator);
		action.moveToElement(btnWomen).click().perform();		
	}
	
	
	
	private ProductPage clickSubMenu()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(subMenuLocator)).click();
		pPage = new ProductPage(driver,wait);
		return pPage;
//		driver.findElement(subMenuLocator).click();
	}
	
	public boolean isLoaded()
	{
		try {
			driver.findElement(mainLocator);
			return true;
			
		} catch (Exception e) {
			return false;
		}		
		
	}

}
