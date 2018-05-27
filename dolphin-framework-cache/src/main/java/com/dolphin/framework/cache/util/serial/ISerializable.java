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

/**
 * Copyright: Copyright (c) 2013 Asiainfo-Linkage
 * 
 * @className: ISerializable
 * @description: 序列化接口
 * 
 * @version: v1.0.0
 * @author: zhoulin2
 * @date: 2013-2-25
 */
public interface ISerializable {
	
	/**
	 * 将对象编码成byte数组
	 * 
	 * @param obj
	 * @return
	 */
	public byte[] encode(Object obj);
	
	/**
	 * 对字节数组进行压缩
	 * 
	 * @param obj
	 * @return
	 */
	public byte[] encodeGzip(byte[] data);
	
	/**
	 * 将byte数组解码成对象
	 * 
	 * @param bytes
	 * @return
	 */
	public Object decode(byte[] bytes);
	
	/**
	 * byte数组解压
	 * 
	 * @param bytes
	 * @return
	 */
	public byte[] decodeGzip(byte[] bytes);
}
