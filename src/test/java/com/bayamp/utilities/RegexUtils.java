package com.bayamp.utilities;

public class RegexUtils {

	public static boolean checkid(String id) {

		String format = "[a-zA-Z0-9]{24}";

		return id.matches(format);

	}

	public static boolean checkDateFormat(String date) {
		// 2021-09-18T02:16:49.331Z
		String format = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z";
		return date.matches(format);

	}

}
