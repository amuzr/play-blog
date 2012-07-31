package com.amuzr.play.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AmuzrUtil {
	private AmuzrUtil() {}
	
	public static String Md5String(int i) {
		return Md5String(String.valueOf(i));
	}
	
	public static String Md5String(long l) {
		return Md5String(String.valueOf(l));
	}

	public static String Md5String(String str) {
		StringBuffer sb = new StringBuffer();
		MessageDigest di;
		try {
			di = MessageDigest.getInstance("MD5");
			byte[] md5Code = di.digest(str.getBytes());
			for(int i=0;i<md5Code.length;i++) {
				String md5Char = String.format("%02x", 0xff & (char)md5Code[i]);
				sb.append(md5Char);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
