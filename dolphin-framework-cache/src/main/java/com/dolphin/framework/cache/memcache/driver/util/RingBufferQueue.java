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
package com.dolphin.framework.cache.memcache.driver.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author steven.zhou
 */
public class RingBufferQueue<E> {
	
	private final int mask;
	private final E[] buffer;

	private final AtomicLong tail = new AtomicLong(0);
	private final AtomicLong head = new AtomicLong(0);

	@SuppressWarnings("unchecked")
	public RingBufferQueue(int capacity) {
		capacity = findNextPositivePowerOfTwo(capacity);
		mask = capacity - 1;
		buffer = (E[]) new Object[capacity];
	}

	public static int findNextPositivePowerOfTwo(final int value) {
		return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
	}

	public boolean add(final E e) {
		if (offer(e)) {
			return true;
		}

		throw new IllegalStateException("Queue is full");
	}

	public boolean offer(final E e) {
		if (null == e) {
			throw new NullPointerException("Null is not a valid element");
		}

		final long currentTail = tail.get();
		final long wrapPoint = currentTail - buffer.length;
		if (head.get() <= wrapPoint) {
			return false;
		}

		buffer[(int) currentTail & mask] = e;
		tail.lazySet(currentTail + 1);

		return true;
	}

	public E poll() {
		final long currentHead = head.get();
		if (currentHead >= tail.get()) {
			return null;
		}

		final int index = (int) currentHead & mask;
		final E e = buffer[index];
		buffer[index] = null;
		head.lazySet(currentHead + 1);

		return e;
	}
}
