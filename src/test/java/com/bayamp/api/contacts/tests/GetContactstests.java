package com.bayamp.api.contacts.tests;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.bayamp.generic.Constants;
import com.bayamp.utilities.RegexUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidationException;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetContactstests extends BaseApiTest {

	@Test
	public void getAllContacts() {
		File jsonfile = new File(prop.getProperty(Constants.JSON_GETALLCONTACTS_SCHEMAFILE_LOCATION));
		response = RestAssured.get("/contacts");
		//response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonfile));
		List<Map<String, String>>  allContacts = response.jsonPath().getList("$");
		 for (Map<String, String> c : allContacts) {
			System.out.println(c.get("name"));
			System.out.println(c.get("phone"));			
		}
		System.out.println("cont is " + allContacts.size());
	
		System.out.println(response.jsonPath().getString("name"));
		
		List<String>contacts = response.jsonPath().getList("phone");
		
		// use list of map, for jsonarrays , https://devqa.io/parse-json-response-rest-assured/
		//List<Map<String, String>> companies = response.jsonPath().getList("company");
		System.out.println(contacts.get(0));
		//JSONArray contacts = JSONParser.p
		
	}

	@Test(dataProviderClass = Dataprovider.class, dataProvider = "getContactData")
	public void testCreateContact(String name, String phoneno) {
		try {
			
			File jsonfile = new File(prop.getProperty(Constants.JSON_POST_CONTACT_SCHEMA_LOCATION));
			JSONObject jsonBody = new JSONObject();
			jsonBody.put("name", name);
			jsonBody.put("phone", phoneno);
			httpReq.body(jsonBody.toJSONString());
			httpReq.header("Content-Type", "application/json");

			response = httpReq.request(Method.POST, "/contacts");

			Assert.assertEquals(response.getStatusCode(), 201, "Invalid response code");

			String statusMsg = response.getStatusLine();
			Assert.assertEquals(statusMsg.split(" ")[2], "Created", "Invalid response status line");

			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonfile));

			
			JsonPath path = response.jsonPath();
			String id = path.get("id");
			String cname = path.get("name");
			String phone = path.get("phone");
			String createdDate = path.get("createdAt");

			Assert.assertTrue(RegexUtils.checkid(id),"Invalid Id format " + id);
			Assert.assertEquals(cname, name,"Invalid name");
			Assert.assertEquals(phone, phoneno,"Invalid phoneno");
			Assert.assertTrue(RegexUtils.checkDateFormat(createdDate),"Invalid date format");
		} catch (JsonSchemaValidationException ex) {
			Reporter.log(ex.getMessage());
		}
	}

	@Test
	// verify the formats of date and id are correct
	public void validateContacts() {
		// validate response code, message , header and body

		Response httpRes = httpReq.request(Method.GET, "/contacts");

		int statusCode = httpRes.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		String responseBody = httpRes.getBody().asString();
		// JSONArray jArr =

		String statusLine = httpRes.getStatusLine();
//		httpRes.getHeader(name)
		// JSONParser jsonParser = new JSONParser();

//		try {
//			JSONArray jArr = (JSONArray) jsonParser.parse(responseBody);
//			for (JSONObject ob : jArr) {
//				
//			}
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		System.out.println("Response Body is: " + responseBody);
	}

}
