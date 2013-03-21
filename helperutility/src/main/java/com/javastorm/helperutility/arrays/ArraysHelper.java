package com.javastorm.helperutility.arrays;

import java.util.Arrays;
import java.util.Collection;

import com.javastorm.helperutility.collections.CollectionsHelper;

/**
 * This class is intended for providing helper methods for Arrays. 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class ArraysHelper 
{
	/**
	 * This function filters the input array and returns an array of elements starting with specified input.   
	 * @param arr
	 * @param str
	 * @return Filtered String[]
	 */
	public static String[] startsWithString(String[] arr, String str) {
		return toStringArray(CollectionsHelper.startsWithString(Arrays.asList(arr), str));
	}

	/**
	 * This function filters the input array and returns an array of elements ending with specified input.   
	 * @param arr
	 * @param str
	 * @return Filtered String[]
	 */
	public static String[] endsWithString(String[] arr, String str) {
		return toStringArray(CollectionsHelper.endsWithString(Arrays.asList(arr), str));
	}

	/**
	 * This function filters the input array and returns an array of elements containing with specified input.   
	 * @param arr
	 * @param str
	 * @return Filtered String[]
	 */
	public static String[] containsString(String[] arr, String str) {
		return toStringArray(CollectionsHelper.containsString(Arrays.asList(arr), str));
	}
	
	/**
	 * This function converts Collection<String> to String[].
	 * @param coll
	 * @return String[]
	 */
	public static String[] toStringArray(Collection<String> coll) {
		String[] array = new String[coll.size()];
		array = coll.toArray(array);;
		return array;
	}

	/**
	 * This function filters the input array and returns an array of elements starting with specified input (Ignore Case).   
	 * @param arr
	 * @param str
	 * @return Filtered String[]
	 */
	public static String[] startsWithStringIgnoreCase(String[] arr, String str) {
		return toStringArray(CollectionsHelper.startsWithStringIgnoreCase(Arrays.asList(arr), str));
	}

	/**
	 * This function filters the input array and returns an array of elements ending with specified input (Ignore Case).
	 * @param arr
	 * @param str
	 * @return Filtered String[]
	 */
	public static String[] endsWithStringIgnoreCase(String[] arr, String str) {
		return toStringArray(CollectionsHelper.endsWithStringIgnoreCase(Arrays.asList(arr), str));
	}

	/**
	 * This function filters the input array and returns an array of elements containing with specified input (Ignore Case).
	 * @param arr
	 * @param str
	 * @return Filtered String[]
	 */
	public static String[] containsStringIgnoreCase(String[] arr, String str) {
		return toStringArray(CollectionsHelper.containsStringIgnoreCase(Arrays.asList(arr), str));
	}
	
}
