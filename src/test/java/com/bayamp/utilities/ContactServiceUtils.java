package com.bayamp.utilities;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ContactServiceUtils {

	private static RequestSpecification httpreq;
	
	private static void setRestAssured()
	{
		RestAssured.baseURI = "https://bayamp-rest-api-contacts.herokuapp.com";
		if (httpreq == null) {
			httpreq = RestAssured.given();
			httpreq.basePath("/contacts");
			httpreq.header("Content-Type", "application/json");
		}
	}

	// method is synchronized, if tests run in parallel, for thread safety we add
	// synchronize
	public static Response getAllContacts(int page) {
		if(httpreq==null)
		{
			setRestAssured();
		}
		httpreq.queryParam("page", page);
		httpreq.queryParam("limit", "50");
		Response res = httpreq.get();
		httpreq = null;
		return res;
	}

	private static Response createNewContact(String jsonString) {

		if(httpreq==null)
		{
			setRestAssured();
		}
		httpreq.body(jsonString);
		Response res = httpreq.request(Method.POST);
		return res;
	}

	private static Response getContactById(String contactId) {

		if(httpreq==null)
		{
			setRestAssured();
		}
		Response res = httpreq.request(Method.GET, contactId);
		return res;
	}

	private static Response updateContact(String jsonString, String id) {

		if(httpreq==null)
		{
			setRestAssured();
		}
		httpreq.body(jsonString);
		Response res = httpreq.request(Method.PUT, id);
		return res;
	}

	public static Response deleteContact(String contactId) {

		if(httpreq==null)
		{
			setRestAssured();
		}
		Response res = httpreq.request(Method.DELETE,  contactId);
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
