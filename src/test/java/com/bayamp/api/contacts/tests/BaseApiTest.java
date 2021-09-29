package com.bayamp.api.contacts.tests;

import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;

public class BaseApiTest {

	protected Response response;

	@BeforeClass
	public void init() {
		PropertyManager.setProperties();
	}

}
