package com.dsaproblems.DSAProblems.linkedlist;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class LeastRecentlyUsedCache {

	private Deque<CacheEntry> queue;

	HashMap<Integer, CacheEntry> map;

	private static final int CACHE_SIZE = 3;

	public LeastRecentlyUsedCache() {
		this.queue = new LinkedList<CacheEntry>();
		this.map = new HashMap<Integer, CacheEntry>();
	}

	public CacheEntry getEntryFromCache(int key) {
		CacheEntry entry = null;
		if (map.containsKey(key)) {
			entry = map.get(key);
			queue.remove(entry);
			queue.addLast(entry);
		}
		return entry;
	}

	public void putEntryIntoCache(int key, String value) {
		if (map.containsKey(key)) {
			CacheEntry entry = map.get(key);
			queue.remove(entry);
			queue.addLast(entry);
		} else {
			if (queue.size() == CACHE_SIZE) {
				CacheEntry entryToBeRemoved = queue.removeFirst();
				map.remove(entryToBeRemoved.getKey());
			}
		}
		CacheEntry newEntry = new CacheEntry(key, value);
		queue.addLast(newEntry);
		map.put(key, newEntry);
	}

}
