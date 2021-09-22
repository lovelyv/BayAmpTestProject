package com.bayamp.utilities;

public class StringUtils {		
	public static int getStringLength(String str)
	{
		return str.length();		
	}
	
	public static int getStringLength(String str,boolean noDefaultMethod)
	{
		return	str.toCharArray().length;
		
	}
	
	public static String[] splitString(String str,String splitOn)
	{
		// split, splits the string wherever the matching string or char is found. excluding
		// the matching char or string
		return str.split(splitOn);
	}
	
	public static String replaceAllMatch(String str,String toReplace,String replaceWith) 
	{
		//Replaceall replaces the matching string with the given string and returns a new String object
		return str.replaceAll(toReplace,replaceWith);				
	}
	
	public static char[] convertToCharArray(String str) {
		//converts string to a character array
		return str.toCharArray();				
	}
	
	
	public static char getCharAtIndex(String str, int index)
	{
		// returns the character at the specified index, index begins with 0
		return str.charAt(index);	

	}
	
	public static String getSubstring(String str,int index)
	{
		//substring, retruns the portion of string from the specified index. if only one
		// index is given, it returns the entired string starting from the index.
		return str.substring(0,index);								
	}
	
	public static boolean checkIfStringMatch(String str,String stringToMatch)
	{
		//matches, compares the strings and returns true if its an exact match, regular expression
		return str.matches(stringToMatch);							
	}
	
	public static void getUnicodeComparison()
	{
		// calculates the unicode value of each char. if strings match, it returns 0.
		// if string is no match, returns negative
		// if first string is match and smaller, negative no	
		// if first string is match and greater returns positive no
		System.out.println("This".compareTo("This"));				
		System.out.println("This".compareTo("sihT"));
		System.out.println("This".compareTo("This is bigger string"));				
		System.out.println("This is bigger string".compareTo("This"));	

	}
	
	public static boolean isEmptyString(String str)
	{
		return str.isEmpty();
	}
	
	//reverse using swapping character technique
	public static String reverseString(String str) 
	{	
		char[] charArray = str.toCharArray();
		int maxIndex = str.length() - 1;
		char tempChar;		
		for(int i = 0; i <= maxIndex; i++)
		{			
			tempChar = str.charAt(i);			
			charArray[i] = str.charAt(maxIndex-i); 
			charArray[maxIndex - i] = tempChar;			
		}
		str = new String(charArray);
		return str;		
	}
	
	
	/* Strings are immutable
	String textOne = "1";		
	textOne.replace("1", "3");
	if (textOne.equals(textOne))
	{
		System.out.println("The value of textOne did not change with the replace method, instead it returns a new String object");
	}		
	//textOne still has 1, it returns a new String object and do not change the original one. so its immutable.		
	*/
	
	/* Difference between String and StringBuffer
	 *  String is of fixed length and immutable character sequence
	 *  StringBuffer is writable changeable character or string seq 
	 *  It has extra character spaces alloted 
	 *  StringBuffer length is the actual size of the string length, capacity includes the extra char spaces thats included.
	 *  stringbuffer allocates 16 character space by default and unassigned string is null, else each char 2 bytes.
	  
	StringBuffer s = new StringBuffer();
	System.out.println(s);
	System.out.println(s.length());
	System.out.println(s.capacity());
	
	s = new StringBuffer(20);
	s.append("Buffer String");
	s.append("123456789012345678901");
	System.out.println(s);
	System.out.println(s.length());
	System.out.println(s.capacity());
	
	
	*/
	
	public static boolean checkIfPalindrome(String str)
	{		
		StringBuffer buffer = new StringBuffer(str.toUpperCase());		
		buffer.reverse();
		System.out.println(buffer.toString());
		
		if(str.toUpperCase().equals(buffer.toString()))
		{
			return true;
		}		
		return false;
	}
	
	public static boolean checkIfAnagramStrings(String firstString, String secondString)
	{
		
		if(firstString.length()!= secondString.length())
		{
			return false;
		}
		else
		{
			for(char c : firstString.toCharArray())
			{
				if(secondString.indexOf(c) <0) 
				{
					return false;
				}
				if(secondString.indexOf(c) != secondString.lastIndexOf(c))
				{
					return false;					
				}				
			}
			
		}
		return true;
	}
	
	public static String reverseWordsInSentence(String str) {
		String[] strArray = str.split(" ");
		StringBuffer buffer = new StringBuffer();
		for(int i= strArray.length-1;i>=0;i--)
		{
			buffer.append(strArray[i] + " ");	
		}
		buffer.deleteCharAt(buffer.length()-1);
		
		return buffer.toString();
	}
	
	
	public static String reverseWordsInSentence(String str,int reverseStartIndex) {
		String[] strArray = str.split(" ");
		StringBuffer buffer = new StringBuffer();
		int i = reverseStartIndex-1;
		while(i <= strArray.length-1 )
		{
			System.out.println(strArray[i]);
			buffer.append(strArray[i] + " ");	
			i++;
			if(i==strArray.length)
			{
				//set the index to the beginning of the string
				i =0;
			}
			if(i==reverseStartIndex-1)
			{
				//reversing completed so exit
				break;
			}
		}
		//remove the last empty space		
		buffer.deleteCharAt(buffer.length()-1);		
		return buffer.toString();
	}

}
