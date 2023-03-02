package MyHashMap;

import java.util.*;


public class MyHashtableK<K, V> implements Map<K, V> {

	// MyBetterMap uses a collection of MyLinearMap
	protected List<MyLinearMapN<K, V>> maps;

	/**
	 * Initialize the map with 2 sub-maps.
	 */
	public MyHashtableK() {
		makeMaps(2);
	}

	/**
	 * Makes a collection of `k` MyLinearMap
	 */
	protected void makeMaps(int k) {
		maps = new ArrayList<MyLinearMapN<K, V>>(k);
		for (int i=0; i<k; i++) {
			maps.add(new MyLinearMapN<K, V>());
		}
	}

	@Override
	public void clear() {
		// clear the sub-maps
		for (int i=0; i<maps.size(); i++) {
			maps.get(i).clear();
		}
	}

	/**
	 * Uses the hashCode to find the map that would/should contain the given key.
	 */
	protected MyLinearMapN<K, V> chooseMap(Object key) {
		int index = key==null ? 0 : Math.abs(key.hashCode()) % maps.size();
		return maps.get(index);
	}

	@Override
	public boolean containsKey(Object target) {
		// to find a key, we only have to search one map
		// TODO: FILL THIS IN!
		return chooseMap(target).containsKey(target);
	}

	@Override
	public boolean containsValue(Object target) {
		// to find a value, we have to search all map
		// TODO: FILL THIS IN!
		for (MyLinearMapN<K, V> map : maps) {
			if (map.containsValue(target)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public V get(Object key) {
		MyLinearMapN<K, V> map = chooseMap(key);
		return map.get(key);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Set<K> keySet() {
		// add up the keySets from the sub-maps
		Set<K> set = new HashSet<K>();
		for (MyLinearMapN<K, V> map: maps) {
			set.addAll(map.keySet());
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
		MyLinearMapN<K, V> map = chooseMap(key);
		return map.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for (Entry<? extends K, ? extends V> entry: map.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public V remove(Object key) {
		MyLinearMapN<K, V> map = chooseMap(key);
		return map.remove(key);
	}

	@Override
	public int size() {
		// add up the sizes of the sub-maps
		int total = 0;
		for (MyLinearMapN<K, V> map: maps) {
			total += map.size();
		}
		return total;
	}

	@Override
	public Collection<V> values() {
		// add up the valueSets from the sub-maps
		Set<V> set = new HashSet<V>();
		for (MyLinearMapN<K, V> map: maps) {
			set.addAll(map.values());
		}
		return set;
	}

	public static void main(String[] args) {
		Map<String, Integer> map = new MyHashtableK<String, Integer>();
		map.put("Word1", 1);
		map.put("Word2", 2);
		Integer value = map.get("Word1");
		System.out.println(value);

		for (String key: map.keySet()) {
			System.out.println(key + ", " + map.get(key));
		}
	}
}
