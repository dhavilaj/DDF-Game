package com.ddf.commons.codec;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Codec utilities
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class CodecUtils {
	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

	/**
	 * Get the md5 has of a string
	 * 
	 * @param message
	 * @return md5 hash
	 */
	public static String md5(String message) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8"));

			// converting byte array to Hexadecimal String
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digest = sb.toString();
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex);
		}
		return digest;
	}

	/* Getters & Setters */
}
