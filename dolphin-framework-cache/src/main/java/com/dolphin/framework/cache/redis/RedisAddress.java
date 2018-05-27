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
package com.dolphin.framework.cache.redis;

/**
 * @author steven.zhou
 */
public class RedisAddress implements Comparable<RedisAddress> {
	
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
	public int compareTo(RedisAddress o) {
		String anotherMaster = o.getMaster();
		return this.master.compareTo(anotherMaster);
	}

	@Override
	public String toString() {
		return this.master + " -> " + this.slave;
	}
}
