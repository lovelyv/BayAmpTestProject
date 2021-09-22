package com.bayamp.americangiant.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bayamp.americangiant.pages.HomePage;
import com.bayamp.americangiant.pages.ProductDetailsPage;
import com.bayamp.americangiant.pages.ProductPage;
import com.bayamp.baseclasses.BaseUITest;

public class CheckoutTest extends BaseUITest {
	HomePage home;
	ProductPage pPage;
	ProductDetailsPage detailsPage;

	@Test
	public void verifyPriceCalculation() {
		try {
			goToHomePage();
			goToProductPage();
			goToProductDetailsPage();
			addItemToCart();
		}
		catch(AssertionError e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {			
			Assert.fail(e.getMessage());
		}

	}

	private void goToHomePage() {
		home = new HomePage(driver);
		Assert.assertTrue(home.isLoaded());
	}

	private void goToProductPage() {
		pPage = home.menuClick();
		Assert.assertTrue(pPage.isLoaded());
	}

	private void goToProductDetailsPage() throws Exception {

		detailsPage = pPage.clickProduct();
		Assert.assertTrue(detailsPage.isLoaded());

	}

	private void addItemToCart()
	{
		detailsPage.addProductToCart();
		detailsPage.incrementItem();
		detailsPage.incrementItem();
		String individualPrice = detailsPage.getItemPrice();
		double expectedPrice = Float.valueOf(individualPrice.substring(1,individualPrice.length())) * 3;
		expectedPrice = Math.round(expectedPrice*100.0)/100.0;
		String priceVal = detailsPage.getTotalPrice();		
		double price =   Double.valueOf(priceVal.substring(1,priceVal.length())) ;		
		Assert.assertEquals(price, expectedPrice);
		System.out.println("Final price is " + price);
	}
}
