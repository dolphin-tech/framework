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
package com.dolphin.framework.cache.memcache;

/**
 * Copyright: Copyright (c) 2014 Asiainfo
 * 
 * @className: MemCacheAddress
 * @description: MemCache地址对象
 * 
 * @version: v1.0.0
 * @author: zhoulin2
 * @date: 2014-7-14
 */
public class MemCacheAddress implements Comparable<MemCacheAddress> {

	/**
	 * 主地址
	 */
	private String master;
	
	/**
	 * 备地址
	 */
	private String slave;
	
	public String getMaster() {
		return master;
	}
	
	public void setMaster(String master) {
		this.master = master;
	}
	
	public String getSlave() {
		return slave;
	}
	
	public void setSlave(String slave) {
		this.slave = slave;
	}

	/**
	 * 仅按master排序
	 * 
	 * @param anotherRedisAddress
	 * @return
	 */
	@Override
	public int compareTo(MemCacheAddress o) {
		String anotherMaster = o.getMaster();
		return this.master.compareTo(anotherMaster);
	}

	@Override
	public String toString() {
		return this.master + " -> " + this.slave;
	}

}
