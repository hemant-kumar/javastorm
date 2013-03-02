package com.javastorm.test.asymmetric;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.javastorm.security.asymmetric.SecureRSA;

/**
 * The class is intended for depicting the usage of SecureRSA class.   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 02/03/2013
 */
public class SecureRSATest
{
	public static void main(String[] args) throws Exception {
		SecureRSA rsa = new SecureRSA();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter dir path for key generation : ");
		String dirPath = br.readLine();
		rsa.generateKeyPair(dirPath);
		System.out.print("Enter text to encrypted : ");
		String original = br.readLine();
		byte[] encrypted = rsa.encrypt(original.getBytes(), dirPath + "/public.key");
		System.out.println("Encrypted text: " + new String(encrypted));
		byte[] decrypted = rsa.decrypt(encrypted, dirPath + "private.key");
		System.out.println("Decrypted text: " + new String(decrypted));
	}
}