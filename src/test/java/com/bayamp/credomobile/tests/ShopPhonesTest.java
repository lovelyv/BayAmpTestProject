package com.bayamp.credomobile.tests;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bayamp.baseclasses.BaseUITest;
import com.bayamp.credomobile.pages.HomePage;

public class ShopPhonesTest extends BaseUITest {

	@Test
	public void verifyPhoneListLoaded() {
		try {
			HomePage home = new HomePage(driver);
			boolean isPhoneListLoaded = home.isPhoneListLoaded();
			Assert.assertTrue(isPhoneListLoaded);

		} catch (Exception e) {
			Reporter.log(e.getMessage());
		}

	}

	@Test
	public void verifyFifthPhonesValues() {

		HomePage home = new HomePage(driver);
		Map<String, String> details = home.readFifthPhoneDetails();
		SoftAssert sAssert = new SoftAssert();
		ArrayList<String> expectedPhoneNames = new ArrayList<String>(3);
		expectedPhoneNames.add("Apple");
		expectedPhoneNames.add("Samsung");
		expectedPhoneNames.add("Google");
		String phoneName = details.get("Name").split(" ")[0];
		Reporter.log("Fifth phone name contains " + phoneName);

		sAssert.assertTrue(expectedPhoneNames.contains(phoneName));

		String priceValue = details.get("Price");
		sAssert.assertTrue(priceValue.matches("^\\$\\d{3,4}\\.\\d{2}"));

		sAssert.assertAll();
	}

}
