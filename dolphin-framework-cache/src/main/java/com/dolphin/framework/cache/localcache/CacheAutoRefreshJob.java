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
package com.dolphin.framework.cache.localcache;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.dolphin.framework.cache.localcache.interfaces.IReadOnlyCache;
import com.dolphin.framework.cache.localcache.interfaces.IReadWriteCache;

/**
 * @author steven.zhou
 */
public class CacheAutoRefreshJob implements Job {

	private static final transient Logger log = Logger.getLogger(CacheAutoRefreshJob.class);

	public static final String CACHE_NAME = "CACHE_NAME";
	public static final String CACHE_TYPE = "CACHE_TYPE";
	public static final String READONLY_CACHE = "READONLY_CACHE";
	public static final String READWRITE_CACHE = "READWRITE_CACHE";

	public void execute(JobExecutionContext ctx) throws JobExecutionException {

		String isPrepared = System.getProperty("isPrepared", "");
		if (!isPrepared.startsWith("StartTime")) {
			log.info("系统未预热，本地缓存不进行自动刷新!");
			return;
		}
		
		JobDataMap map = ctx.getJobDetail().getJobDataMap();
		String cacheType = map.getString(CacheAutoRefreshJob.CACHE_TYPE);
		
		if (READONLY_CACHE.equals(cacheType)) {

			Class clazz = (Class) map.get(CACHE_NAME);
			
			/** 未使用过的本地缓存，不需要自动刷新! */
			if (CacheFactory.isNotUsed(clazz)) {
				return;
			}
			
			try {
				IReadOnlyCache cache = CacheFactory.getReadOnlyCache(clazz);
				int oldSize = cache.size();
				if (null != cache) {
					cache.refresh();
					int newSize = cache.size();
					log.info("本地只读缓存自动刷新成功! " + clazz.getName() + ",刷新前:" + oldSize + "条数据，刷新后:" + newSize + "条数据。");
				}
			} catch (Exception e) {
				log.error("本地只读缓存自动刷新失败! " + clazz.getName() + e);
			}

		} else if (READWRITE_CACHE.equals(cacheType)) {
			
			String cachename = map.getString(CACHE_NAME);
			IReadWriteCache cache = CacheFactory.getReadWriteCache(cachename);
			cache.refresh();

			log.info("本地读写缓存自动刷新成功! " + cachename);
		}
	}

}
