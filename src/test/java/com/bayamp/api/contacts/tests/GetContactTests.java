package com.bayamp.api.contacts.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;

public class GetContactTests extends BaseApiTest {
	SoftAssert sf = new SoftAssert();

	@Test(dataProviderClass = Dataprovider.class, dataProvider = "getContactData")
	public void validateContact(String name, String phone) {

		response = ContactService.createContact(name, phone);
		String id = response.jsonPath().getString("id");
		
		response = ContactService.getContact(id);
		
		JsonPath jPath = response.jsonPath();
		sf.assertEquals(name, jPath.get("name"));
		sf.assertEquals(phone, jPath.get("phone"));
		sf.assertEquals(id, jPath.get("id"));
		sf.assertAll();
	}

}
