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
package com.dolphin.framework.cache.redis.performance.impl;

import com.dolphin.framework.cache.redis.performance.IRedisPerformance;

/**
 * @author steven.zhou
 */
public class LazyWorkPerformance implements IRedisPerformance {

	@Override
	public void report(String cmd, String key, long cCost, long eCost) {
		// 空函数
	}

}
