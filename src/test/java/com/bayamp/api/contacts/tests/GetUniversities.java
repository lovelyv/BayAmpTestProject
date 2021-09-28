package com.bayamp.api.contacts.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUniversities {
	// another sample for getUniversities
	SoftAssert s = new SoftAssert();

	@Test
	public void getUniversities() {

		RestAssured.baseURI = "http://universities.hipolabs.com";
		RestAssured.basePath = "/search";
		Response res = RestAssured.given().queryParam("country", "United States").queryParam("limit", "5")
				.queryParam("page", "1").header("Content-Type", "application/json").get();
//		res.then().log().all(); this will log the result in console
		List<Map<String, Object>> results = res.jsonPath().getList("$");

		for (int i = 0; i < results.size(); i++) {
			Map<String, Object> j = (Map<String, Object>) results.get(i);
			s.assertEquals(j.get("country"), "United States");
			// since the domain is of type array, we cast into arraylist, if it was a json object, we will cast into map<string,object>
			ArrayList<String> d = (ArrayList<String>) j.get("domains");
			s.assertTrue(d.size()>0);
		}
		s.assertAll();
	}
}
