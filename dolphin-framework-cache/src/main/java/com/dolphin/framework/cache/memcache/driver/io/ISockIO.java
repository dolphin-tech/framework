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
package com.dolphin.framework.cache.memcache.driver.io;

import java.io.IOException;

/**
 * Copyright: Copyright (c) 2013 Asiainfo-Linkage
 * 
 * @className: ISockIO
 * @description: SockIO接口
 * 
 * @version: v1.0.0
 * @author: zhoulin2
 * @date: 2013-3-19
 */
public interface ISockIO {
		
	public boolean init();
	
	public void write(byte[] bytes) throws IOException;

	public int read(byte[] b, int off, int len) throws IOException;

	public void flush() throws IOException;

	public boolean isConnected();

	public boolean isAlive();

	public void close() throws IOException;

	public byte[] readLineBytes() throws IOException;

	public void release();
	
	public SockIOBucket getBucket();
	
	public String getHost();

	public int getPort();
	
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
