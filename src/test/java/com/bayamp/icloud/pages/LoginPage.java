package com.bayamp.icloud.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By emailIdLocator = By.cssSelector("input#account_name_text_field");
	By loginButtonLocator = By.cssSelector("button#sign-in");
	
	public void login()
	{
		/*selenium cannot directly access the frame or iframe, so there are 3 ways
		using Index of the iframe.
		using Name or Id of the iframe
		And, using the Web Element object of the iframe. driver.switchto().frame(int/name/webelement)
		 */
		driver.switchTo().frame("aid-auth-widget");
		WebElement emailId = driver.findElement(emailIdLocator);
		emailId.sendKeys("lovelyvenu@gmail.com");
		driver.findElement(loginButtonLocator).click();
	}

}
