package com.yangzb.framework.common.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

@Slf4j
public class HadoopUtil extends org.apache.hadoop.security.SecurityUtil{
    public static final String HADOOP_SECURITY_AUTHENTICATION_KEY = "hadoop.security.authentication";
    public static final String HADOOP_USER_NAME = "HADOOP_USER_NAME";
    public static final String HADOOP_HOME_DIR = "hadoop.home.dir";

    /**
     * kerberos 认证 
     * @Title: kerberosAuthentication
     * @Description: 
     * @param kerberosKeytabFilePath Keytab文件路径
     * @param kerberosPrincipal principal （klist -key XX.keytab可以查看）
     * @param krb5Realm	/etc/krb5.autoconfigure 中的 realms
     * @param kdc etc/krb5.autoconfigure 中 realms.kdc
     * <br>return type: void 
     * @author yzb yangzb@tydic.com,yzhengbin@gmail.com
     * @date 2017年7月13日 下午5:04:57
     */
	public static boolean kerberosAuthentication(String kerberosKeytabFilePath,String kerberosPrincipal,String krb5Realm,String kdc) {
		Configuration hadoopConf = new org.apache.hadoop.conf.Configuration();
       return kerberosAuthentication(hadoopConf,kerberosKeytabFilePath,kerberosPrincipal,krb5Realm,kdc);
	}

    /**
     * kerberos 认证
     * @Title: kerberosAuthentication
     * @Description:
     * @param kerberosKeytabFilePath Keytab文件路径
     * @param kerberosPrincipal principal （klist -key XX.keytab可以查看）
     * @param krb5Realm	/etc/krb5.autoconfigure 中的 realms
     * @param kdc etc/krb5.autoconfigure 中 realms.kdc
     * <br>return type: void
     * @author yzb yangzb@tydic.com,yzhengbin@gmail.com
     * @date 2017年7月13日 下午5:04:57
     */
    public static boolean kerberosAuthentication(Configuration hadoopConf,String kerberosKeytabFilePath,String kerberosPrincipal,String krb5Realm,String kdc) {
        hadoopConf.set(HADOOP_SECURITY_AUTHENTICATION_KEY, "kerberos");
        SecurityUtil.setJavaSecurityConf(krb5Realm, kdc);
        UserGroupInformation.setConfiguration(hadoopConf);
        try {
            UserGroupInformation.loginUserFromKeytab(kerberosPrincipal, kerberosKeytabFilePath);
            return UserGroupInformation.isLoginKeytabBased();
        } catch (Exception e) {
            String message = String.format("kerberos认证失败,请确定kerberosKeytabFilePath[%s]和kerberosPrincipal[%s]填写正确",
                    kerberosKeytabFilePath, kerberosPrincipal);
            log.error(message,e);
        }
        return false;
    }
	
	/**
	 * 
	 * @Title: setHadoopUser
	 * @Description: 
	 * @param userName 
	 * <br>return type: void 
	 * @author yzb yangzb@tydic.com,yzhengbin@gmail.com
	 * @date 2017年7月14日 上午10:47:53
	 */
	public static void setHadoopUser(String userName){
		System.setProperty(HADOOP_USER_NAME, userName);
	}
	/**
	 * 
	 * @Title: setHadoopHomeDir
	 * @Description: 
	 * @param hadoopHomeDir 
	 * <br>return type: void 
	 * @author yzb yangzb@tydic.com,yzhengbin@gmail.com
	 * @date 2017年7月14日 上午10:48:52
	 */
	public static void setHadoopHomeDir(String hadoopHomeDir){
		System.setProperty(HADOOP_HOME_DIR, hadoopHomeDir);
	}
	
	
	
	/**
	 * 
	 * @Title: kerberosAuthentication
	 * @Description: 
	 * @param kerberosKeytabFilePath Keytab文件路径
	 * @param kerberosPrincipal  principal （klist -key XX.keytab可以查看）
	 * <br>return type: void 
	 * @author yzb yangzb@tydic.com,yzhengbin@gmail.com
	 * @date 2017年7月13日 下午5:08:04
	 */
	public static boolean kerberosAuthentication(String kerberosKeytabFilePath,String kerberosPrincipal) {
		return kerberosAuthentication(kerberosKeytabFilePath,kerberosPrincipal,null,null);
	}
}