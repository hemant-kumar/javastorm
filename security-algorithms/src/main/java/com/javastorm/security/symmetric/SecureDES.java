package com.javastorm.security.symmetric;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * The SecureDES class is intended for performing symmetric encryption & decryption using DES algorithm.   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013 
 */
public class SecureDES
{
	/**
	 * This function take care of encryption process.
	 * @param message
	 * @param symmetricKey
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] message,String symmetricKey) throws Exception {
		byte[] keyBytes = Arrays.copyOf(symmetricKey.getBytes(),24);
		for (int j=0,k=16; j < 8; k++,j++)
			keyBytes[k] = keyBytes[j];
		SecretKey key = new SecretKeySpec(keyBytes,"DESede");
		IvParameterSpec iv = new IvParameterSpec(new byte[8]);
		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE,key,iv);
		return cipher.doFinal(message);
	}

	/**
	 * This function takes care of decryption process.
	 * @param message
	 * @param symmetricKey
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] message,String symmetricKey) throws Exception {
		byte[] keyBytes = Arrays.copyOf(symmetricKey.getBytes(),24);
		for (int j=0,k=16; j<8; j++,k++)
			keyBytes[k] = keyBytes[j];
		SecretKey key = new SecretKeySpec(keyBytes,"DESede");
		IvParameterSpec iv = new IvParameterSpec(new byte[8]);
		Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		decipher.init(Cipher.DECRYPT_MODE,key,iv);
		return decipher.doFinal(message);
	}
}
