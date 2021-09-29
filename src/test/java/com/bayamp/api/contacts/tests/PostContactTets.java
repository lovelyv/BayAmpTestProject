package com.bayamp.api.contacts.tests;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.bayamp.generic.Constants;
import com.bayamp.utilities.ContactServiceUtils;
import com.bayamp.utilities.RegexUtils;

import io.restassured.module.jsv.JsonSchemaValidationException;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;

public class PostContactTets extends BaseApiTest {

	@Test(dataProviderClass = Dataprovider.class, dataProvider = "getContactData")
	public void testCreateContact(String name, String phoneno) {

		File jsonfile = new File(PropertyManager.getProperty(Constants.JSON_POST_CONTACT_SCHEMA_LOCATION));
		response = ContactServiceUtils.createContact(name, phoneno) ;
		Assert.assertEquals(response.getStatusCode(), 201, "Invalid response code");

		String statusMsg = response.getStatusLine();
		Assert.assertEquals(statusMsg.split(" ")[2], "Created", "Invalid response status line");

		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonfile));

		JsonPath path = response.jsonPath();
		String id = path.get("id");
		String cname = path.get("name");
		String phone = path.get("phone");
		String createdDate = path.get("createdAt");

		Assert.assertTrue(RegexUtils.checkid(id), "Invalid Id format " + id);
		Assert.assertEquals(cname, name, "Invalid name");
		Assert.assertEquals(phone, phoneno, "Invalid phoneno");
		Assert.assertTrue(RegexUtils.checkDateFormat(createdDate), "Invalid date format");

	}

}
