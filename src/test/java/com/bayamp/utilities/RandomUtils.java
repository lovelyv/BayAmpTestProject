package com.bayamp.utilities;

import java.util.Random;

public class RandomUtils {

	public static String generateRandomString() {

		Random r = new Random();
		String lower_case = "abcdefghijklmnopqrstuvwxyz";
		String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String ALL_CHARS = upper_case + " " + lower_case;

		int len = r.nextInt(20);
		StringBuilder sbulid = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			int index = r.nextInt(ALL_CHARS.length());
			sbulid.append(ALL_CHARS.charAt(index));
		}
		return sbulid.toString();
	}
	
	public static String generateRandomPhone()
	{			
		Random r = new Random();
		String no = (String.valueOf(r.nextInt(999)) +String.valueOf(r.nextInt(999)) + String.valueOf(r.nextInt(9999)));
		return no;		
	}
	
	public static int getRandomInt(int n)
	{
		Random r = new Random();
		return r.nextInt(n);
	}
	
	public static long generateRandomId()
	{
		Random r = new Random();
		return r.nextLong();

	}

}
