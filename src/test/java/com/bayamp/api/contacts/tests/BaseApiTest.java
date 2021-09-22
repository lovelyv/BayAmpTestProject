package com.bayamp.api.contacts.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.bayamp.generic.Constants;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseApiTest {
	protected Properties prop;
	protected String token;
	protected RequestSpecification httpReq;
	Response response;

	@BeforeTest
	public void init() {
		loadPropertiesFile();
		RestAssured.baseURI = "https://bayamp-rest-api-contacts.herokuapp.com";
		httpReq = RestAssured.given();

	}

	@BeforeClass
	public void getToken() {

	}

	private void loadPropertiesFile() {
		try {
			InputStream iStream = new FileInputStream(Constants.PROPERTIES_FILE_PATH);
			prop = new Properties();
			prop.load(iStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
