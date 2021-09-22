package com.bayamp.baseclasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.bayamp.generic.Constants;
import com.bayamp.utilities.StringUtils;

public class BaseUITest {
	Properties prop;
	protected WebDriver driver;
	String applicationUrl ;

	@BeforeClass
	public void init() {
		loadPropertiesFile();
		createWebDriver();
		launchApplication();
	}

	private void loadPropertiesFile() {
		try {
			InputStream iStream = new FileInputStream(Constants.PROPERTIES_FILE_PATH);
			prop = new Properties();
			prop.load(iStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createWebDriver() 
	{
		applicationUrl = prop.getProperty(Constants.APPLICATION_URL);
		if (StringUtils.isEmptyString(applicationUrl)) 
		{
			throw new RuntimeException("Application url is empty in config.properties file");
		}

		BrowserTypes browserType = BrowserTypes.valueOf(prop.getProperty(Constants.BROWSER_TYPE).toUpperCase());
		String driverLocation = prop.getProperty(Constants.DRIVER_LOCATION);

		switch (browserType) 
		{

		case CHROME:
			System.setProperty("webdriver.chrome.driver", driverLocation);
			driver = new ChromeDriver();			
			break;
			
		case FIREFOX:			
			System.setProperty("webdriver.gecko.driver", driverLocation);
			driver = new FirefoxDriver();
			break;
			
		case INTERNETEXPLORER:
			System.setProperty("webdriver.internetexplorer.driver", driverLocation);
			driver = new InternetExplorerDriver();
			break;
			
		case EDGE:			
			System.setProperty("webdriver.edge.driver", driverLocation);
			driver = new EdgeDriver();
			break;
			
		case SAFARI:			
			System.setProperty("webdriver.safari.driver", driverLocation);
			driver = new SafariDriver();
			break;
		}		
	}

	private void launchApplication()
	{
		driver.get(applicationUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

	protected WebDriver getWebDriver()
	{
		return driver;
	}
}