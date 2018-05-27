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
package com.dolphin.framework.cache.redis.performance;

/**
 * Copyright: Copyright (c) 2016 Asiainfo
 * 
 * @className: IRedisPerformance
 * @description: Redis接口性能日志输出接口
 * 
 * @version: v1.0.0
 * @author: zhoulin2
 * @date: 2016-7-25
 */
public interface IRedisPerformance {
	public void report(String cmd, String key, long cCost, long eCost);
}
