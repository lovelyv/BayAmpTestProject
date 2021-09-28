package com.bayamp.api.contacts.tests;

import org.testng.annotations.DataProvider;

import com.bayamp.utilities.RandomUtils;

public class Dataprovider {
	@DataProvider
	public String[][] getContactData()
	{
		int noOfRecords = RandomUtils.getRandomInt(10);
		System.out.println("Creating " + noOfRecords + " test Contacts!!");
		String[][] contactDetails = new String[noOfRecords][2];
		for (String[] contact : contactDetails) {
			contact[0] = RandomUtils.generateRandomString();
			contact[1] = String.valueOf(RandomUtils.generateRandomPhone());
		}		
		return contactDetails;
	}
	
	

}
