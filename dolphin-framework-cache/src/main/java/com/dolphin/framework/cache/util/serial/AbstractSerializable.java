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
package com.dolphin.framework.cache.util.serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;

/**
 * @author steven.zhou
 */
public abstract class AbstractSerializable implements ISerializable {

	private static final Logger log = Logger.getLogger(AbstractSerializable.class);
	
	/**
	 * 对字节数组进行压缩
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public byte[] encodeGzip(byte[] bytes) {
	
		ByteArrayOutputStream bos = null;
		GZIPOutputStream gos = null;
		
		try {
			bos = new ByteArrayOutputStream(bytes.length);
	        gos = new GZIPOutputStream(bos);
	        gos.write(bytes, 0, bytes.length);
	        gos.finish();
	        return bos.toByteArray();
		} catch (IOException e) {
			
		} finally {
			if (null != gos) {
				try {
					gos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
        return null;
	}

	/**
	 * byte数组解压
	 * 
	 * @param bytes
	 * @return
	 */
	@Override
	public byte[] decodeGzip(byte[] datas) {
		
		GZIPInputStream gis = null;
		ByteArrayOutputStream baos = null;
		
		try {
			gis = new GZIPInputStream(new ByteArrayInputStream(datas));
			baos = new ByteArrayOutputStream(datas.length);

			int cnt = 0;
			byte[] tmp = new byte[datas.length];
			while ((cnt = gis.read(tmp)) != -1) {
				baos.write(tmp, 0, cnt);
			}

			return baos.toByteArray();
			
		} catch (IOException e) {
			log.error(e);
		} finally {
			if (null != gis) {
				try {
					gis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (null != baos) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
}
