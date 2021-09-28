package com.bayamp.api.contacts.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.bayamp.generic.Constants;

public class Propertymanager {
	private static Properties prop;

	public static void setProperties() {
		if (prop == null) {
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

	public static String getProperty(String property) {
		return prop.getProperty(property);
	}

}
