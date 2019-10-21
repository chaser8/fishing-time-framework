package com.fishingtime.framework.common.base.map;

import java.sql.Clob;
import java.sql.SQLException;

@SuppressWarnings("all")
public class HashMapLowerCaseClob extends HashMapLowerCase {

	@Override
	public Object put(Object key, Object value) {
		Object v = value;
		if(value instanceof Clob){
			Clob c = (Clob) value;
			 try {
				v = c.getSubString(1, (int)c.length());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return super.put(key, v);
	}
}
