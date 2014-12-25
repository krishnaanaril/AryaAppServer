package com.example.aaryaApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Common Utility function
 * @author Krishna Mohan, A M
 *
 */
public class CommonUtilities {

	/**
	 * Utility function to convert given List to a string appended with semi colon
	 * @param toList
	 * @return
	 */
	public static String ListToString(List<String> toList) {
		String toString="";
		for (String email : toList) {
			toString.concat(email+";");
		}
		return toString;
	}
	
	/**
	 * Utility function to convert given String to a List
	 * @param toString
	 * @return
	 */
	public static List<String> StringToList(String toString) {
		List<String> toList= new ArrayList<String>();
		toList= Arrays.asList(toString.split(";"));
		return toList;
	}
}
