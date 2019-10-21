/**   
 * @Title: MapResult.java
 * @Description: TODO
 * @author
 * @date 2015年7月31日 上午10:18:39
 * @version 
 */
package com.fishingtime.framework.common.base.map;

@SuppressWarnings("all")
public class HashMapLowerCase<K, V> extends LinkedMapEntry<K, V> {

	private static final long serialVersionUID = 1L;

	@Override
	public V put(K key, V value) {
		if(key instanceof String){
			key = (K) ((String) key).toLowerCase();
		}
		return super.put(key, value);
	}

	@Override
	public V remove(Object key) {
		if(key instanceof String){
			key = (K) ((String) key).toLowerCase();
		}
		return super.remove(key);
	}

}
