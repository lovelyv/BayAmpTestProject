package com.bayamp.credomobile.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
	WebDriver driver;
	By shopLocator = By.xpath("//span[text()=\"Shop\"]");
	By phoneLocator = By.cssSelector("div.left-phones-container>ul>li>h3>a");
	By phoneListLocator = By.cssSelector("div.products.wrapper.grid.products-grid>ol li");
	By fifthPhoneNameLocator = By.cssSelector("ol.products.list.items.product-items>li:nth-child(5) a.product-item-link");
	By fifthPhonePriceLocator = By.cssSelector("ol.products.list.items.product-items>li:nth-child(5) span.price-wrapper.special-price>span");
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public boolean isPhoneListLoaded()
	{
		clickPhoneLink();
		List<WebElement> phones = driver.findElements(phoneListLocator);		
		if (phones.size()>0)
		{
			JavascriptExecutor js =(JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", phones.get(phones.size()-1));
			return true;
		}
		return false;
	}
	
	private void clickPhoneLink()
	{
		Actions action = new Actions(driver);
		WebElement shopLink = driver.findElement(shopLocator);
		WebElement phoneLink = driver.findElement(phoneLocator);
		action.moveToElement(shopLink);
		action.moveToElement(phoneLink).click().perform();
		//scroll to the bottom of the page,((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
		
		
	}
	
	public Map<String,String> readFifthPhoneDetails()
	{
		clickPhoneLink();		
		Map<String,String> fifthPhone = new HashMap<String, String>();
		WebElement fiftPhoneName = driver.findElement(fifthPhoneNameLocator);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", fiftPhoneName);
		fifthPhone.put("Name", fiftPhoneName.getText());
		WebElement fiftPhonePrice = driver.findElement(fifthPhonePriceLocator);
		fifthPhone.put("Price", fiftPhonePrice.getText());
		return fifthPhone;
		
	}

}
