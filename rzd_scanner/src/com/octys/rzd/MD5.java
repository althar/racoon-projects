package com.octys.rzd;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String hex(byte a[]) {
		char HEX[] = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
		StringBuffer hexString = new StringBuffer(); 
		
		for (int i = 0; i < a.length; i++) {
			byte b = a[i];
			hexString.append(HEX[(b >>4) & 0xF]);
			hexString.append(HEX[b & 0xF]);
		}
		return hexString.toString();
		
	}
	
	public static String get(String s) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(s.getBytes());
		return hex(digest.digest());
	}

	public static String get(File f) throws NoSuchAlgorithmException, FileNotFoundException {
		MessageDigest digest = MessageDigest.getInstance("MD5");

		InputStream is = new FileInputStream(f);
		byte[] buffer = new byte[8192];
		int read = 0;
		try {
			while ((read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}
			return hex(digest.digest());
		} catch (IOException e) {
			throw new RuntimeException("Unable to process file for MD5", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException("Unable to close input stream for MD5 calculation", e);
			}
		}
	}

}
