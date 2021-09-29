package com.bayamp.api.contacts.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bayamp.utilities.ContactServiceUtils;
import com.bayamp.utilities.RandomUtils;

import io.restassured.path.json.JsonPath;

public class PutContactTests extends BaseApiTest{
	
	@Test(dataProviderClass = Dataprovider.class, dataProvider = "getContactData")
	public void validateContactUpdate(String name, String phone)
	{
 		response = ContactServiceUtils.createContact(name, phone);
 		Assert.assertEquals(response.statusCode(), 201);
 		
 		String id = response.jsonPath().get("id");
 		
 		String newName = RandomUtils.generateRandomString();
 		String newPhone = RandomUtils.generateRandomPhone();
 		
 		response = ContactServiceUtils.updateContact(id,newName,newPhone);
 		Assert.assertEquals(response.statusCode(), 200);
 		
 		response = ContactServiceUtils.getContact(id);
 		Assert.assertEquals(response.statusCode(), 200);

		JsonPath jPath = response.jsonPath();
		Assert.assertEquals(jPath.get("name"),newName);
		Assert.assertEquals(jPath.get("phone"),newPhone);
	}
	
	

}
