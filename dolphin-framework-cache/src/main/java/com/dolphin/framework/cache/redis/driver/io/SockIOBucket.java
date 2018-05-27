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
public abstract class SockIOBucket implements Comparable<SockIOBucket> {

	/**
	 * 桶的状态码
	 */
	public static final int STATE_ERER = 0x00; // 主备地址都不可用
	public static final int STATE_OKER = 0x01; // 主地址可用，备地址不可用
	public static final int STATE_EROK = 0x02; // 主地址不可用，备地址可用
	public static final int STATE_OKOK = 0x03; // 主备地址都可用
	
	public static final int STATE_ER   = 0x04; // 主地址不可用，无备地址
	public static final int STATE_OK   = 0x05; // 主地址可用，无备地址
	
	public static final int STATE_RAW  = -0x01; // 未初始化
	
	public static final String[] STATES = {"STATE_ERER", "STATE_OKER", "STATE_EROK", "STATE_OKOK", "STATE_ER", "STATE_OK"};
	
	/**
	 * 桶的初始化
	 * 
	 * @return
	 */
	public abstract boolean init() throws IOException;
	
	/**
	 * 桶的销毁，释放资源
	 */
	public abstract void close();
	
	public abstract ISockIO borrowSockIO();
	public abstract ISockIO borrowSockIO(long timeout);
	public abstract void returnSockIO(ISockIO sock);
	public abstract boolean delSock(ISockIO sock);
	public abstract String getAddress();
		
	/**
	 * 健康检测函数，返回桶的状态码
	 * 
	 * @return
	 */
	public abstract int healthCheck() throws IOException, InterruptedException;
	
	public abstract int getStateCode();
	
	/**
	 * 设置桶状态
	 * 
	 * @param stateCode
	 */
	public abstract void setStateCode(int stateCode);
	
	public abstract int compareTo(SockIOBucket o);
}
