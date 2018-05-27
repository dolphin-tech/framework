/*
 * Copyright (c) 2018. Dolphin.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dolphin Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Dolphin.com.
 *
 */
package com.dolphin.framework.cache.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * @author steven.zhou
 */
public final class MD5Util {

	private static final Logger LOG = Logger.getLogger(MD5Util.class);

	private static final char[] hexchar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private MD5Util() { }

	public static final String hexdigest(String str) {
		
		MessageDigest alg = null;
		try {
			alg = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e);
		}
		byte[] digest = alg.digest(str.getBytes());
		return bytesToHex(digest);
		
	}
	
	public static final String hexdigest(byte[] bytes) {
		MessageDigest alg = null;
		try {
			alg = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e);
		}
		
		byte[] digest = alg.digest(bytes);
		return bytesToHex(digest);
	}

	private static final String bytesToHex(byte[] digest) {
		
		StringBuilder sb = new StringBuilder(digest.length * 2);
		
		for (int i = 0, size = digest.length; i < size; i++) {
			sb.append(hexchar[(digest[i] & 0xf0) >>> 4]);
			sb.append(hexchar[digest[i] & 0x0f]);
		}
		
		return sb.toString();
		
	}
	
}
