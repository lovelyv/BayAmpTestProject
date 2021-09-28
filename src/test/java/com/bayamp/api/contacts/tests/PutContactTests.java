package com.bayamp.api.contacts.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bayamp.utilities.RandomUtils;

import io.restassured.path.json.JsonPath;

public class PutContactTests extends BaseApiTest{
	
	@Test(dataProviderClass = Dataprovider.class, dataProvider = "getContactData")
	public void validateContactUpdate(String name, String phone)
	{
 		response = ContactService.createContact(name, phone);
 		String id = response.path("id");
 		String newName = RandomUtils.generateRandomString();
 		String newPhone = RandomUtils.generateRandomPhone();
 		response = ContactService.updateContact(id,newName,newPhone);
 		Assert.assertEquals(response.statusCode(), 200);
 		
 		response = ContactService.getContact(id);

		JsonPath jPath = response.jsonPath();
		Assert.assertEquals(jPath.get("name"),newName);
		Assert.assertEquals(jPath.get("phone"),newPhone);
	}
	
	

}
