package com.leadtheworld.security.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The MD5Hasher class is intended for calculating the MD5 hash value.   
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013 
 */
public class MD5Hasher
{
	public static MD5Hasher instance;

	static {
		if(instance==null)
			instance = new MD5Hasher();
	}

	private MD5Hasher() {}
	
	/**
	 * This function is responsible for calculating the hash.
	 * @param input byte[]
	 * @return byte[]
	 * @throws NoSuchAlgorithmException
	 */
    public byte[] hash(byte[] input) throws NoSuchAlgorithmException {
        String output = "";
        char[] hexChars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input, 0, input.length);
        byte[] b = md.digest();
        int msb;
        int lsb=0;
        for(int i=0;i<b.length;i++)
        {
            msb=((int)b[i]&0X000000FF)/16;
            lsb=((int)b[i]&0X000000FF)%16;
            output = output + hexChars[msb] + hexChars[lsb];
        }
        return output.getBytes();
    }
}
