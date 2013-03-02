package com.javastorm.security.asymmetric;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * The SecureRSA class is intended for performing asymmetric encryption & decryption using RSA algorithm..   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 02/03/2013
 */
public class SecureRSA 
{
	private KeyFactory keyFactory;
	private Cipher cipher;
	
	public SecureRSA() throws Exception {
		Security.addProvider(Security.getProvider("SunRsaSign"));
		keyFactory = KeyFactory.getInstance("RSA");
		cipher = Cipher.getInstance("RSA");
	}
	
	/**
	 * This function is responsibly for generating a key pair(public & private) at the specified directory path  
	 * @param dirPath
	 * @throws Exception
	 */
	public void generateKeyPair(String dirPath) throws Exception {
		new File(dirPath).mkdirs();
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(512);
		KeyPair keyPair = kpg.generateKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		
		// Store Public Key.
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
		FileOutputStream fos = new FileOutputStream(dirPath + "/public.key");
		fos.write(x509EncodedKeySpec.getEncoded());
		fos.close();
		
		// Store Private Key.
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
		fos = new FileOutputStream(dirPath + "/private.key");
		fos.write(pkcs8EncodedKeySpec.getEncoded());
		fos.close();
	}

	/**
	 * This function is responsible for retrieving a public key from the specified public key file path
	 * @param keyFilePath
	 * @return
	 * @throws Exception
	 */
	public PublicKey getPublicKey(String keyFilePath) throws Exception {
		FileInputStream fis = new FileInputStream(keyFilePath);
		byte[] encodedKey = new byte[fis.available()];
		fis.read(encodedKey);
		fis.close();
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedKey);
		return keyFactory.generatePublic(publicKeySpec);
	}

	/**
	 * This function is responsible for retrieving a private key from the specified private key file path
	 * @param keyFilePath
	 * @return
	 * @throws Exception
	 */
	public PrivateKey getPrivateKey(String keyFilePath) throws Exception {
		FileInputStream fis = new FileInputStream(keyFilePath);
		byte[] encodedKey = new byte[fis.available()];
		fis.read(encodedKey);
		fis.close();
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedKey);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
		return privateKey;
	}

	/**
	 * This function is responsible for retrieving a key pair from the specified public key & private key file path
	 * @param keyFilePath
	 * @return
	 * @throws Exception
	 */
	public KeyPair getKeyPair(String privateKeyFilePath, String publicKeyFilePath) throws Exception {
		return new KeyPair(getPublicKey(publicKeyFilePath), getPrivateKey(privateKeyFilePath));
	}

	/**
	 * This function is responsible for encrypting the input
	 * @param input
	 * @param pubKey
	 * @return
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] input, Key pubKey) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		return cipher.doFinal(input);
	}

	/**
	 * This function is responsible for decrypting the input
	 * @param input
	 * @param privKey
	 * @return
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] input, Key privKey) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, privKey);
		return cipher.doFinal(input);	
	}

	/**
	 * This function is responsible for encrypting the input
	 * @param input
	 * @param publicKeyFilePath
	 * @return
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] input, String publicKeyFilePath) throws Exception {
		return encrypt(input, getPublicKey(publicKeyFilePath));
	}

	/**
	 * This function is responsible for decrypting the input
	 * @param input
	 * @param privateKeyFilePath
	 * @return
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] input, String privateKeyFilePath) throws Exception {
		return decrypt(input, getPrivateKey(privateKeyFilePath));	
	}

	/**
	 * This function is responsible for encrypting the input
	 * @param input
	 * @param keyPair
	 * @return
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] input, KeyPair keyPair) throws Exception {
		return encrypt(input, keyPair.getPublic());
	}

	/**
	 * This function is responsible for decrypting the input
	 * @param input
	 * @param keyPair
	 * @return
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] input, KeyPair keyPair) throws Exception {
		return decrypt(input, keyPair.getPrivate());	
	}
}
