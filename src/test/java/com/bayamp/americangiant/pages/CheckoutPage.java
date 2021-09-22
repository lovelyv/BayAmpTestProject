package com.bayamp.americangiant.pages;

import org.openqa.selenium.WebDriver;

public class CheckoutPage {
	WebDriver driver ;
	public CheckoutPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void checkItemPrice()
	{
		HomePage home = new HomePage(driver);
	}

}
