package com.javastorm.test.hash;

import java.util.Scanner;

import com.javastorm.security.hash.MD5Hasher;

/**
 * The class is intended for depicting the usage of MD5Hasher.
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013 
 */
public class MD5HasherTest {
    public static void main(String args[]) throws Exception
    {
    	MD5Hasher obj = MD5Hasher.instance;
		System.out.print("Input String : ");
    	Scanner scanner = new Scanner(System.in);
    	String input = scanner.next();
    	String output = new String(obj.hash(input.getBytes()));
        System.out.println("Hash : " + output);
    }
}
