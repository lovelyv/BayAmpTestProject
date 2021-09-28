package com.bayamp.api.contacts.tests;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//rename as util
public class ContactService {

	private static RequestSpecification httpreq;

	// add loadproperties to propertiesmanager.get
	static {
//		loadPropertiesFile();
		// add to config
		RestAssured.baseURI = "https://bayamp-rest-api-contacts.herokuapp.com";
		if (httpreq == null) {
			httpreq = RestAssured.given();
		}

	}

	// method is synchronized, if tests run in parallel, for thread safety we add
	// synchronize
	public static Response getAllContacts(int page) {
		// RequestSpecification httpreq = RestAssured.given();
		httpreq.queryParam("page", page);
		httpreq.queryParam("limit", "50");
		httpreq.header("Content-Type", "application/json");
		httpreq.basePath("/contacts");
		Response res = httpreq.get();
		return res;
	}

	private static Response createNewContact(String jsonString) {
		// RequestSpecification httpreq = RestAssured.given();
		httpreq.header("Content-Type", "application/json");
		httpreq.body(jsonString);
		Response res = httpreq.request(Method.POST, "/contacts");
		return res;
	}

	private static Response getContactById(String contactId) {
		// RequestSpecification httpreq = RestAssured.given();
		httpreq.basePath("/contacts");
		httpreq.header("Content-Type", "application/json");
		Response res = httpreq.request(Method.GET, contactId);
		return res;
	}

	private static Response updateContact(String jsonString, String id) {
		// RequestSpecification httpreq = RestAssured.given();
		httpreq.header("Content-Type", "application/json");
		httpreq.basePath("/contacts");
		httpreq.body(jsonString);
		Response res = httpreq.request(Method.PUT, id);
		return res;
	}

	public static Response deleteContact(String contactId) {
		// RequestSpecification httpreq = RestAssured.given();
		httpreq.header("Content-Type", "application/json");
		httpreq.basePath("/contacts");
		Response res = httpreq.request(Method.DELETE, "/" + contactId);
		return res;

	}

	public static Response createContact(String name, String phone) {
		JSONObject jBody = new JSONObject();
		jBody.put("name", name);
		jBody.put("phone", phone);
		Response res = createNewContact(jBody.toJSONString());
		return res;

	}

	public static Response updateContact(String id, String name, String phone) {
		JSONObject body = new JSONObject();
		body.put("id", id);
		body.put("name", name);
		body.put("phone", phone);
		Response res = updateContact(body.toJSONString(), id);
		return res;
	}

	public static Response getContact(String id) {
		Response res = getContactById(id);
		return res;

	}

}
