/**   
 * @Title: PropertiesUtil.java
 * @Package com.fishingtime.util
 * @author
 * @date 2017年6月12日 上午10:15:40
 * @version 
 */
package com.fishingtime.framework.common.base.util;

import cn.hutool.core.util.StrUtil;
import org.mortbay.util.StringUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

/**
 * @ClassName: PropertiesUtil
 * @Description:
 * @author
 * @date 2017年6月12日 上午10:15:40
 */
public class PropertiesUtil {      
      
    private Properties props;
    
    public PropertiesUtil(String fileName){      
        readProperties(fileName);      
    }      
    private void readProperties(String fileName) {   
        try {      
            props = new Properties();
            InputStream fis =getClass().getResourceAsStream(fileName);
            if(fis==null){
            	fis = new FileInputStream(fileName);
            }
            props.load(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }      
    }
    
    /**   
     * 获取某个属性   
     */      
    public String getProperty(String key){      
        return props.getProperty(key);      
    }      
    public Map getAllPropertyByPrefix(String prefix){      
        Map map=new HashMap();      
        Enumeration enu = props.propertyNames();      
        while (enu.hasMoreElements()) {      
            String key = (String) enu.nextElement();
            if(key.indexOf(prefix)==0){
            	String value = props.getProperty(key);      
            	key=key.replaceFirst(prefix, "");
                map.put(key, value);
            }
        }
        return map;      
    }
    /**
     * 
     * @Title: getAllPropertyByPrefix
     * @Description: 
     * @param props
     * @param prefix
     * @return 
     * <br>return type: Properties 
     * @author
     * @date 2017年6月13日 上午10:03:42
     */
    public static Properties getAllPropertyByPrefix(Map props,String prefix){      
    	Properties properties = new Properties();
		Set<Entry<Object, Object>> entrys = props.entrySet();
		for (Entry<Object, Object> entry : entrys) {
			Object key = entry.getKey();
			if(StrUtil.toString(key).indexOf(prefix)==0){
				Object value = entry.getValue();   
            	key=StrUtil.toString(key).replaceFirst(prefix, "");
            	properties.put(key, value);
			}
		}
        return properties;      
    }
    /**   
     * 获取所有属性，返回一个map,不常用   
     * 可以试试props.putAll(t)   
     */      
    public Map getAllProperty(){      
        Map map=new HashMap();      
        Enumeration enu = props.propertyNames();      
        while (enu.hasMoreElements()) {      
            String key = (String) enu.nextElement();      
            String value = props.getProperty(key);      
            map.put(key, value);  
        }      
        return map;      
    }
    public Properties getProperties(){
    	return props;
    }
    /**   
     * 在控制台上打印出所有属性，调试时用。   
     */      
    public void printProperties(){      
        props.list(System.out);      
    }      
}  