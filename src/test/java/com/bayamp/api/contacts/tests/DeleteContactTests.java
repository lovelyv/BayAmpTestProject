package com.bayamp.api.contacts.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bayamp.utilities.ContactServiceUtils;
import com.bayamp.utilities.RandomUtils;

public class DeleteContactTests extends BaseApiTest {

	@Test(dataProviderClass = Dataprovider.class, dataProvider = "getContactData")
	public void validateDeleteContact(String name, String phone) {

		response = ContactServiceUtils.createContact(name, phone);
		String id = response.path("id");

		response = ContactServiceUtils.deleteContact(id);
		Assert.assertEquals(response.statusCode(), 204);

		response = ContactServiceUtils.getContact(id);
		Assert.assertEquals(response.statusCode(), 404);
	}

	@Test
	public void validateInvalidDeleteContact() {
		
		String id = String.valueOf(RandomUtils.generateRandomId());
		response = ContactServiceUtils.deleteContact(id);
		// should it give 500 error? if invalid id is sent in delete
		Assert.assertEquals(response.statusCode(), 500);

	}

}
