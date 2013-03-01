package com.leadtheworld.test.symmetric;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.leadtheworld.security.symmetric.SecureDES;

/**
 * The SecureDESTest class is intended for depicting the usage of SecureDES class.   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013 
 */
public class SecureDESTest
{
	public static void main(String[] args) throws Exception {
		SecureDES des = new SecureDES();
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the text to be encryted: ");
		String original = br.readLine();
		System.out.println("Enter key: ");
		String key = br.readLine();
		byte[] encrypted = des.encrypt(original.getBytes(),key);
		System.out.println("Encrypted text: " + new String(encrypted));
		String decrypted  = new String(des.decrypt(encrypted,key));
		System.out.println("Decrypted text: " + new String(decrypted));
	}
}