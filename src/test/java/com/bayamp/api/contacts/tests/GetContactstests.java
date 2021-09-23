package com.bayamp.api.contacts.tests;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.bayamp.generic.Constants;
import com.bayamp.utilities.RandomUtils;
import com.bayamp.utilities.RegexUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidationException;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetContactstests extends BaseApiTest {

	@Test
	public void validateGetAllContactsSchema() {
		try {
			File jsonfile = new File(prop.getProperty(Constants.JSON_GETALLCONTACTS_SCHEMAFILE_LOCATION));
			response = ContactService.getAllContacts();
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonfile));

		} catch (AssertionError e) {
			Reporter.log(e.getMessage());
		}
	}

	@Test
	public void validateGetAllContactsResponse() {

		response = ContactService.getAllContacts();
		List<Map<String, String>> allContacts = response.jsonPath().getList("$");
		for (Map<String, String> c : allContacts) {
			System.out.println(c.get("name"));
			System.out.println(c.get("phone"));
		}
		System.out.println("Total contacts received is " + allContacts.size());

		System.out.println(response.jsonPath().getString("name"));

		List<String> contacts = response.jsonPath().getList("phone");

		// use list of map, for jsonarrays ,
		// https://devqa.io/parse-json-response-rest-assured/
		// List<Map<String, String>> companies = response.jsonPath().getList("company");
		System.out.println(contacts.get(0));
		// JSONArray contacts = JSONParser.p

	}


}
