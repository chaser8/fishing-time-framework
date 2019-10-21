package com.fishingtime.framework.common.base.map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@SuppressWarnings("all")
public class MapEntry<K, V> extends HashMap<K, V> implements Cloneable, IMapEntry<K, V> {
    @Override
    public Class<?> getClazz(K key) {
        Object obj = this.get(key);
        if(obj instanceof Class){
            return (Class)obj;
        }
        return null;
    }

    public MapEntry() {
		super();
	}

	public MapEntry(int size) {
		super(size);
	}

	public MapEntry(Map map) {
		super(map);
	}

	@Override
    public File getFile(K key) {
		return getFile(key, null);
	}

	@Override
    public File getFile(K key, File defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof File) {
			return (File) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public InputStream getInputStream(K key) {
		return getInputStream(key, null);
	}

	@Override
    public InputStream getInputStream(K key, InputStream defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof InputStream) {
			return (InputStream) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public OutputStream getOutputStream(K key) {
		return getOutputStream(key, null);
	}

	@Override
    public OutputStream getOutputStream(K key, OutputStream defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof OutputStream) {
			return (OutputStream) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public Collection getCollection(K key) {
		return getCollection(key, null);
	}

	@Override
    public Collection getCollection(K key, Collection defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof Collection) {
			return (Collection) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public Map getMap(K key) {
		return getMap(key, null);
	}

	@Override
    public Map getMap(K key, Map defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof Map) {
			return (Map) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public HashMap getHashMap(K key) {
		return getHashMap(key, null);
	}

	@Override
    public HashMap getHashMap(K key, HashMap defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof HashMap) {
			return (HashMap) obj;
		} else if (obj instanceof Map) {
			MapEntry map = Maps.newMapEntry();
			map.putAll((Map) obj);
			return map;
		} else {
			return defauleValue;
		}
	}

	@Override
    public TreeMap getTreeMap(K key) {
		return getTreeMap(key, null);
	}

	@Override
    public TreeMap getTreeMap(K key, TreeMap defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof TreeMap) {
			return (TreeMap) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public LinkedHashMap getLinkedHashMap(K key) {
		return getLinkedHashMap(key, null);
	}

	@Override
    public LinkedHashMap getLinkedHashMap(K key, LinkedHashMap defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof LinkedHashMap) {
			return (LinkedHashMap) obj;
		} else if (obj instanceof Map) {
			LinkedHashMap map = Maps.newLinkedMapEntry();
			map.putAll((Map) obj);
			return map;
		} else {
			return defauleValue;
		}
	}

	@Override
    public MapEntry getMapEntry(K key) {
		return getMapEntry(key, null);
	}

	@Override
    public MapEntry getMapEntry(K key, MapEntry defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof MapEntry) {
			return (MapEntry) obj;
		} else if (obj instanceof Map) {
			MapEntry map = Maps.newMapEntry();
			map.putAll((Map) obj);
			return map;
		} else {
			return defauleValue;
		}
	}

	@Override
    public LinkedMapEntry getLinkedMapEntry(K key) {
		return getLinkedMapEntry(key, null);
	}

	@Override
    public LinkedMapEntry getLinkedMapEntry(K key, LinkedMapEntry defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof LinkedMapEntry) {
			return (LinkedMapEntry) obj;
		} else if (obj instanceof Map) {
			LinkedMapEntry map = Maps.newLinkedMapEntry();
			map.putAll((Map) obj);
			return map;
		} else {
			return defauleValue;
		}
	}

	@Override
    public List getList(K key) {
		return getList(key, null);
	}

	@Override
    public List getList(K key, List defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof List) {
			return (List) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public ArrayList getArrayList(K key) {
		return getArrayList(key, null);
	}

	@Override
    public ArrayList getArrayList(K key, ArrayList defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof ArrayList) {
			return (ArrayList) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public LinkedList getLinkedList(K key) {
		return getLinkedList(key, null);
	}

	@Override
    public LinkedList getLinkedList(K key, LinkedList defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof LinkedList) {
			return (LinkedList) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public Set getSet(K key) {
		return getSet(key, null);
	}

	@Override
    public Set getSet(K key, Set defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof Set) {
			return (Set) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public HashSet getHashSet(K key) {
		return getHashSet(key, null);
	}

	@Override
    public HashSet getHashSet(K key, HashSet defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof HashSet) {
			return (HashSet) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public TreeSet getTreeSet(K key) {
		return getTreeSet(key, null);
	}

	@Override
    public TreeSet getTreeSet(K key, TreeSet defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof TreeSet) {
			return (TreeSet) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public LinkedHashSet getLinkedHashSet(K key) {
		return getLinkedHashSet(key, null);
	}

	@Override
    public LinkedHashSet getLinkedHashSet(K key, LinkedHashSet defauleValue) {
		Object obj = get(key);
		if (obj == null) {
			return defauleValue;
		} else if (obj instanceof LinkedHashSet) {
			return (LinkedHashSet) obj;
		} else {
			return defauleValue;
		}
	}

	@Override
    public String getString(K key, String defauleValue) {
		Object object = get(key);
		if (object == null) {
			return defauleValue;
		} else {
			return object.toString();
		}
	}

	@Override
    public Object getObject(Object key, Object defauleValue) {
		Object object = get(key);
		if (object == null) {
			return defauleValue;
		} else {
			return object;
		}
	}

	@Override
    public Object getObject(Object key) {
		return getObject(key, null);
	}

	@Override
    public String getString(K key) {
		Object object = get(key);
		if (object == null) {
			return null;
		} else {
			return object.toString();
		}
	}

	protected Number getNumber(K key) {
		return getNumber(key, 0);
	}

	protected Number getNumber(K key, Number num) {
		Object object = get(key);
		if (object == null) {
			return num;
		} else if (NumberUtils.isNumber(object.toString())) {
			return NumberUtils.createNumber(object.toString());
		} else {
			return num;
		}
	}

	@Override
    public BigDecimal getBigDecimal(K key) {
		return getBigDecimal(key, 0);
	}

	@Override
    public BigDecimal getBigDecimal(K key, Number num) {
		return new BigDecimal(getNumber(key, num).toString());
	}

	@Override
    public BigInteger getBigInteger(K key) {
		return getBigInteger(key, 0);
	}

	@Override
    public BigInteger getBigInteger(K key, Number num) {
		return new BigInteger(getNumber(key, num).toString());
	}

	@Override
    public byte[] getByte(K key) {
		return getByte(key, null);
	}

	@Override
    public byte[] getByte(K key, byte[] num) {
		Object object = get(key);
		if (object == null) {
			return num;
		} else if (object instanceof byte[]) {
			return (byte[]) object;
		} else {
			return num;
		}
	}

	@Override
    public Short getShort(K key) {
		return getShort(key, 0);
	}

	@Override
    public Short getShort(K key, Number num) {
		return getNumber(key, num).shortValue();
	}

	@Override
    public Integer getInteger(K key) {
		return getInteger(key, 0);
	}

	@Override
    public Integer getInteger(K key, Number num) {
		return getNumber(key, num).intValue();
	}

	@Override
	public Long getLong(K key) {
		// TODO Auto-generated method stub
		return getLong(key,0);
	}

	@Override
	public Long getLong(K key, Number num) {
		// TODO Auto-generated method stub
		return getNumber(key,num).longValue();
	}

	@Override
    public Double getDouble(K key) {
		return getDouble(key, 0);
	}

	@Override
    public Double getDouble(K key, Number num) {
		return getNumber(key, num).doubleValue();
	}

	@Override
    public Float getFloat(K key) {
		return getFloat(key, 0);
	}

	@Override
    public Float getFloat(K key, Number num) {
		return getNumber(key, num).floatValue();
	}

	@Override
    public Boolean getBoolean(K key) {
		return getBoolean(key, Boolean.FALSE);
	}

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
	@Override
    public Boolean getBoolean(K key, Boolean b) {
		Object obj = get(key);
		if (obj == null) {
			return b;
		}
		if (obj instanceof Boolean) {
			return (Boolean) obj;
		}
		if (obj instanceof String) {
			if (NumberUtils.isNumber(obj.toString())) {
				if (NumberUtils.createInteger(obj.toString()) >= 1) {
					return true;
				} else {
					return false;
				}
			} else if ("Y".equalsIgnoreCase((String) obj) || "TRUE".equalsIgnoreCase((String) obj) || "YES".equalsIgnoreCase((String) obj) || "是".equals((String) obj)) {
				return true;
			} else if ("N".equalsIgnoreCase((String) obj) || "FALSE".equalsIgnoreCase((String) obj) || "NO".equalsIgnoreCase((String) obj) || "否".equals((String) obj)) {
				return false;
			} else {
				return BooleanUtils.toBooleanObject(obj.toString());
			}
		} else if (obj instanceof Number) {
			if (obj.equals(1) || ((Number) obj).intValue() >= 1) {
				return true;
			} else {
				return false;
			}
		}
		return b;
	}

	/**
	 * Title: getDate<br/>
	 *
	 * @param key
	 * @return
	 * @see com.fishingtime.base.util.IMapEntry#getDate(Object)
	 */
	@Override
    public Date getDate(K key) {
		return getDate(key,null);
	}

	/**
	 * Title: getDate<br/>
	 *
	 * @param key
	 * @param date
	 * @return
	 * @see com.fishingtime.base.util.IMapEntry#getDate(Object, Date)
	 */
	@Override
    public Date getDate(K key, Date date) {
		Object object = get(key);
		if (object == null) {
			return date;
		} else if (object instanceof Date) {
			return (Date) object;
		} else {
			return date;
		}
	}
	/**
	 * Title: getArray<br/>
	 * @param key
	 * @return
	 * @see com.fishingtime.base.util.IMapEntry#getArray(Object)
	 */
	@Override
    public String[] getArray(K key) {
		return getArray(key,null);
	}

	/**
	 * Title: getArray<br/>
	 * @param key
	 * @param defauleValue
	 * @return
	 * @see com.fishingtime.base.util.IMapEntry#getArray(Object, String[])
	 */
	@Override
    public String[] getArray(K key, String[] defauleValue) {
		Object object = get(key);
		if (object == null) {
			return defauleValue;
		} else if(object instanceof String[]){
			return (String[])object;
		}else{
			return defauleValue;
		}
	}

	

}
