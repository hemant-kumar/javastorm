package com.javastorm.helperutility.collections;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is intended for providing helper methods for Collections. 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 21/03/2013
 */
public class CollectionsHelper 
{
	/**
	 * This function filters the input collection and returns a collection of elements starting with specified input.   
	 * @param coll
	 * @param str
	 * @return Filtered Collection<String>
	 */
	public static Collection<String> startsWithString(Collection<String> coll, final String str) {
		Collection<String> list = new ArrayList<String>();
		for(String item : coll) {
			if(item.startsWith(str)) {
				list.add(item);
			}
		}
		return list;
	}

	/**
	 * This function filters the input collection and returns a collection of elements ending with specified input.   
	 * @param coll
	 * @param str
	 * @return Filtered Collection<String>
	 */
	public static Collection<String> endsWithString(Collection<String> coll, final String str) {
		Collection<String> list = new ArrayList<String>();
		for(String item : coll) {
			if(item.endsWith(str)) {
				list.add(item);
			}
		}
		return list;
	}

	/**
	 * This function filters the input collection and returns a collection of elements containing the specified input.   
	 * @param coll
	 * @param str
	 * @return Filtered Collection<String>
	 */
	public static Collection<String> containsString(Collection<String> coll, final String str) {
		Collection<String> list = new ArrayList<String>();
		for(String item : coll) {
			if(item.contains(str)) {
				list.add(item);
			}
		}
		return list;
	}

	
	/**
	 * This function filters the input collection and returns a collection of elements starting with specified input (Ignore Case).
	 * @param coll
	 * @param str
	 * @return Filtered Collection<String>
	 */
	public static Collection<String> startsWithStringIgnoreCase(Collection<String> coll, final String str) {
		Collection<String> list = new ArrayList<String>();
		for(String item : coll) {
			if(item.toLowerCase().startsWith(str.toLowerCase())) {
				list.add(item);
			}
		}
		return list;
	}

	/**
	 * This function filters the input collection and returns a collection of elements ending with specified input (Ignore Case).
	 * @param coll
	 * @param str
	 * @return Filtered Collection<String>
	 */
	public static Collection<String> endsWithStringIgnoreCase(Collection<String> coll, final String str) {
		Collection<String> list = new ArrayList<String>();
		for(String item : coll) {
			if(item.toLowerCase().endsWith(str.toLowerCase())) {
				list.add(item);
			}
		}
		return list;
	}

	/**
	 * This function filters the input collection and returns a collection of elements containing the specified input (Ignore Case).
	 * @param coll
	 * @param str
	 * @return Filtered Collection<String>
	 */
	public static Collection<String> containsStringIgnoreCase(Collection<String> coll, final String str) {
		Collection<String> list = new ArrayList<String>();
		for(String item : coll) {
			if(item.toLowerCase().contains(str.toLowerCase())) {
				list.add(item);
			}
		}
		return list;
	}

}
