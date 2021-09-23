package com.bayamp.api.contacts.tests;
import static io.restassured.RestAssured.given;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ContactService {
	static RequestSpecification httpreq = given();
	
	public static Response getAllContacts()
	{ 	
		Response res = httpreq.request(Method.GET,"/contacts");
		return res;
	}
	
	public static Response createNewContact(String jsonString)
	{
		
		httpreq.header("Content-Type", "application/json");
		httpreq.body(jsonString);
		Response res = httpreq.request(Method.POST,"/contacts");
		return res;		
	}
	
	public static Response getContactById(String contactId)
	{
		httpreq.header("Content-Type","application/json");
		Response res = httpreq.request(Method.GET,"/" + contactId);
		return res;
	}
	
	public static Response updateContact(String contactId,String jsonString)
	{
		httpreq.header("Content-Type","application/json");
		httpreq.body(jsonString);
		Response res = httpreq.request(Method.PUT,"/" + contactId);
		return res;
	}
	
	public static Response updateContact(String jsonString)
	{
		httpreq.header("Content-Type","application/json");
		httpreq.body(jsonString);
		Response res = httpreq.request(Method.PUT);
		return res;
	}
	
	public static Response deleteContact(String contactId)
	{
		httpreq.header("Content-Type","application/json");		
		Response res = httpreq.request(Method.DELETE,"/" + contactId);
		return res;
		
	}

}
