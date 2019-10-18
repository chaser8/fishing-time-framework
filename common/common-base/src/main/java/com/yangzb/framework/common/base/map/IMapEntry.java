package com.yangzb.framework.common.base.map;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@SuppressWarnings("all")
public interface IMapEntry<K, V> extends Map<K,V> {
	public File getFile(K key);

	public File getFile(K key, File defauleValue);

	public InputStream getInputStream(K key);

	public InputStream getInputStream(K key, InputStream defauleValue);

	public OutputStream getOutputStream(K key);

	public OutputStream getOutputStream(K key, OutputStream defauleValue);

	public Collection getCollection(K key);

	public Collection getCollection(K key, Collection defauleValue);

	public Map getMap(K key);

	public Map getMap(K key, Map defauleValue);
	
	public String [] getArray(K key);
	
	public String [] getArray(K key, String[] defauleValue);

	public HashMap getHashMap(K key);

	public HashMap getHashMap(K key, HashMap defauleValue);

	public TreeMap getTreeMap(K key);

	public TreeMap getTreeMap(K key, TreeMap defauleValue);

	public LinkedHashMap getLinkedHashMap(K key);

	public LinkedHashMap getLinkedHashMap(K key, LinkedHashMap defauleValue);

	public MapEntry getMapEntry(K key);

	public MapEntry getMapEntry(K key, MapEntry defauleValue);

	public LinkedMapEntry getLinkedMapEntry(K key);

	public LinkedMapEntry getLinkedMapEntry(K key, LinkedMapEntry defauleValue);

	public List getList(K key);

	public List getList(K key, List defauleValue);

	public ArrayList getArrayList(K key);

	public ArrayList getArrayList(K key, ArrayList defauleValue);

	public LinkedList getLinkedList(K key);

	public LinkedList getLinkedList(K key, LinkedList defauleValue);

	public Set getSet(K key);

	public Set getSet(K key, Set defauleValue);

	public HashSet getHashSet(K key);

	public HashSet getHashSet(K key, HashSet defauleValue);

	public TreeSet getTreeSet(K key);

	public TreeSet getTreeSet(K key, TreeSet defauleValue);

	public LinkedHashSet getLinkedHashSet(K key);

	public LinkedHashSet getLinkedHashSet(K key, LinkedHashSet defauleValue);

	public String getString(K key, String defauleValue);

	public Object getObject(Object key, Object defauleValue);

	public Object getObject(Object key);

	public String getString(K key);

	// protected Number getNumber(K key);
	// protected Number getNumber(K key, Number num);
	public BigDecimal getBigDecimal(K key);

	public BigDecimal getBigDecimal(K key, Number num);

	public BigInteger getBigInteger(K key);

	public BigInteger getBigInteger(K key, Number num);

	public byte[] getByte(K key);

	public byte[] getByte(K key, byte[] num);

	public Short getShort(K key);

	public Short getShort(K key, Number num);

	public Integer getInteger(K key);

	public Integer getInteger(K key, Number num);
	
	public Long getLong(K key);
	
	public Long getLong(K key, Number num);

	public Double getDouble(K key);

	public Double getDouble(K key, Number num);

	public Float getFloat(K key);

	public Float getFloat(K key, Number num);
	
	public Date getDate(K key);
	public Date getDate(K key, Date date);

	public Boolean getBoolean(K key);

	/**
	 * 
	 * Y = true,N = false<br/>
	 * YES = true,NO = false<br/>
	 * 1 >= true , 0 <= false<br/>
	 * 
	 * @param key
	 * @param b
	 * @return
	 */
	public Boolean getBoolean(K key, Boolean b);

    public Class<?> getClazz(K key);
}