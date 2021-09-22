package com.bayaamp.icloud.tests;

import org.testng.annotations.Test;

import com.bayamp.baseclasses.BaseUITest;
import com.bayamp.icloud.pages.LoginPage;

public class LoginTest extends BaseUITest {
	
	@Test
	public void loginTest()
	{
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login();
		
	}

}
