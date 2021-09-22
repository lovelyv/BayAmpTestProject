package com.bayamp.api.contacts.tests;

import org.testng.annotations.DataProvider;

public class Dataprovider {
	@DataProvider
	public String[][] getContactData()
	{
		String[][] contactDetails = new String[3][2];
		contactDetails[0][0] = "name4";
		contactDetails[0][1] = "111111111";
		
		contactDetails[1][0] = "name5";
		contactDetails[1][1] = "222222222";
		
		contactDetails[2][0] = "name6";
		contactDetails[2][1] = "333333333";
		
		return contactDetails;
	}

}
