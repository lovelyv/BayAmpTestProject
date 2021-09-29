package com.bayamp.api.contacts.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bayamp.generic.Constants;
import com.bayamp.utilities.ContactServiceUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class GetContactsTests extends BaseApiTest {
	SoftAssert sAssert = new SoftAssert();

	@Test
	public void validateGetAllContactsResponse() {

		File jsonfile = new File(PropertyManager.getProperty(Constants.JSON_GETALLCONTACTS_SCHEMAFILE_LOCATION));
		int page = 1;
		int count = 1;
		List<Map<String, Object>> allContacts = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> pageList = new ArrayList<Map<String, Object>>();
		// loop through all the pages existing for the response
		while (count > 0) {
			response = ContactServiceUtils.getAllContacts(page);
			Assert.assertEquals(response.getStatusCode(), 200);
			// this gets the entire response as a list
			pageList = response.jsonPath().getList("$");
			for (Map<String, Object> row : pageList) {
				String phone = (String) row.get("phone");
				sAssert.assertNotNull(phone, "The Phone no is not present for " + row.get("id"));
				if (phone != null) {

					sAssert.assertTrue(phone.length() == 10, "phone length is " + phone.length()
							+ " which is less than 10 digits for " + row.get("id"));
				}
			}
			
			// ArrayList<String> phoneNo = response.then().extract().path("phone");
			// System.out.println(response.then().extract().headers());
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonfile)).log();
			// response.then().assertThat().statusCode(201);
			sAssert.assertEquals(response.getStatusCode(), 200);
			sAssert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
			
			count = pageList.size();
			allContacts.addAll(pageList);
			page++;
		}
		sAssert.assertAll();

		System.out.println("Total contacts received is " + allContacts.size());

		// json path can be used to read the list of values of one type of key, eg name
		// here
		// System.out.println(response.jsonPath().getString("name"));
		// List<String> contacts = response.jsonPath().getList("phone");
		// use list of map, for jsonarrays ,
		// https://devqa.io/parse-json-response-rest-assured/
		// List<Map<String, String>> companies = response.jsonPath().getList("company");
		// System.out.println(contacts.get(0));

	}

}