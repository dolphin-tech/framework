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
package com.dolphin.framework.cache.redis.driver.io;

import java.io.IOException;

import com.dolphin.framework.cache.redis.driver.io.SockIOBucket;

/**
 * @author steven.zhou
 */
public interface ISockIO {
	
	/**
	 * 初始化
	 * 
	 * @throws IOException
	 */
	public boolean init() throws IOException;
	
	/**
	 * 写byte
	 * 
	 * @param b
	 * @throws IOException
	 */
	public void write(byte b) throws IOException;
	
	/**
	 * 写byte[]
	 * 
	 * @param bytes
	 * @throws IOException
	 */
	public void write(byte[] bytes) throws IOException;

	/**
	 * 读byte
	 * 
	 * @return
	 * @throws IOException
	 */
	public byte read() throws IOException;
	
	/**
	 * 读指定长度的byte[]
	 * 
	 * @param b
	 * @param off
	 * @param len
	 * @return
	 * @throws IOException
	 */
	public int read(byte[] b, int off, int len) throws IOException;

	/**
	 * 刷新
	 * 
	 * @throws IOException
	 */
	public void flush() throws IOException;
	
	/**
	 * 是否连接
	 * 
	 * @return
	 */
	public boolean isConnected();
	
	/**
	 * 心跳检测
	 * 
	 * @return
	 */
	public boolean isAlive();
	
	/**
	 * 读一行
	 * 
	 * @return
	 * @throws IOException
	 */
	public byte[] readLineBytes() throws IOException;

	public void release();
	
	public SockIOBucket getBucket();
	
	public String getHost();

	public int getPort();
	
	public void close() throws IOException;
	
	/**
	 * 连接版本号
	 * 
	 * @return
	 */
	public int getVersion();
	
	/**
	 * 是否为主地址连接
	 * 
	 * @return
	 */
	public boolean isMaster();
}
