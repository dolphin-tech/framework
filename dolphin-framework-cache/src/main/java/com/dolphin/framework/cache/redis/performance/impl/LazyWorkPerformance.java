package com.dolphin.framework.cache.redis.performance.impl;

import com.dolphin.framework.cache.redis.performance.IRedisPerformance;

public class LazyWorkPerformance implements IRedisPerformance {

	@Override
	public void report(String cmd, String key, long cCost, long eCost) {
		// 空函数
	}

}
