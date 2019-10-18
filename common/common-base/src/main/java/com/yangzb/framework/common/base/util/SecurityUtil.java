package com.yangzb.framework.common.base.util;

import cn.hutool.core.util.StrUtil;

/**
 * 
 * @ClassName: SecurityUtil
 * @Description: 
 * @author yzb yangzb@tydic.com,yzhengbin@gmail.com
 * @date 2017年7月14日 上午10:33:28
 */
public class SecurityUtil {
	public static final String JAVA_SECURITY_KRB5_REALM = "java.security.krb5.realm";
    public static final String JAVA_SECURITY_KRB5_KDC = "java.security.krb5.kdc";
    public static final String JAVA_SECURITY_KRB5_DEBUG = "java.security.krb5.debug";
    public static final String JAVA_SECURITY_KRB5_CONF = "java.security.krb5.autoconfigure";
    /**
     * 
     * @Title: setJavaSecurityConf
     * @Description: 当java虚拟机设置有 <br/>
     *  -Djava.security.krb5.autoconfigure=/etc/kafka/krb5.autoconfigure <br/>
     *	-Djava.security.auth.login.config=/etc/kafka/kafka_client_jaas.autoconfigure
     * 会被覆盖
     * @param krb5Realm
     * @param kdc 
     * <br>return type: void 
     * @author yzb yangzb@tydic.com,yzhengbin@gmail.com
     * @date 2017年7月14日 上午10:36:35
     */
	public static void setJavaSecurityConf(String krb5Realm,String krb5kdc){
		setJavaSecurityKrb5Realm(krb5Realm);
		setJavaSecurityKrb5Kdc(krb5kdc);
	}
	public static void setJavaSecurityConf(String krb5Realm,String krb5kdc,Boolean debug){
		System.setProperty(JAVA_SECURITY_KRB5_DEBUG, debug.toString());
		setJavaSecurityKrb5Realm(krb5Realm);
		setJavaSecurityKrb5Kdc(krb5kdc);
	}
	public static void setJavaSecurityKrb5Realm(String krb5Realm){
		if(!StrUtil.isEmpty(krb5Realm)){
			 System.setProperty(JAVA_SECURITY_KRB5_REALM,krb5Realm);
		}
	}
	public static void setJavaSecurityKrb5Kdc(String krb5kdc){
		if(!StrUtil.isEmpty(krb5kdc)){
			System.setProperty(JAVA_SECURITY_KRB5_KDC,krb5kdc);
		}
	}
	public static void setJavaSecurityKrb5Conf(String krb5Conf){
		if(!StrUtil.isEmpty(krb5Conf)){
			System.setProperty(JAVA_SECURITY_KRB5_CONF,krb5Conf);
		}
	}
}
